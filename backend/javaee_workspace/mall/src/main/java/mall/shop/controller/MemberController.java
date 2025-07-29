package mall.shop.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Member;
import mall.model.member.MemberService;
import mall.model.member.SnsProviderService;

@Slf4j
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private SnsProviderService snsProviderService;

	@Autowired
	@Qualifier("googleAuthService")
	private OAuth20Service googleAuthService;

	@Autowired
	@Qualifier("naverAuthService")
	private OAuth20Service naverAuthService;

	@Autowired
	@Qualifier("kakaoAuthService")
	private OAuth20Service kakaoAuthService;

	// 로그인 폼 요청 처리
	@GetMapping("/member/loginform")
	public String getForm() {

		return "shop/member/login";
	}

	// 로그아웃 요청 처리
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		// 세션 제거할 수는 없으며 , 단 세션을 무효화 시켜야 한다.
		session.invalidate(); // 현재 사용중인 세션 무효화... 따라서 이 시점부터 기존 세션을 참조할 수 없음.
		return "redirect:/shop/main";
	}
	
	@GetMapping("/member/registform")
	public String getRegistForm() {
		return "shop/member/join";
	}
	
	@PostMapping("/member/regist")
	public String regist(Member member) {
		log.debug("ID = " + member.getId());
		log.debug("Password = " + member.getPassword());
		log.debug("name = " + member.getName());
		log.debug("email = " + member.getEmail());
		return "redirect:/shop/member/loginform";
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------
		구글 로그인 처리
	-------------------------------------------------------------------------------------------------------------------------------------------*/
	// 인증 동의화면 요청 처리
	@GetMapping("/member/google/authurl")
	@ResponseBody
	public String getGoogleAuthUrl() {
		return googleAuthService.getAuthorizationUrl();
	}

	// 구글에 등록해놓은 콜백 주소로 전송되는 콜백 요청 처리
	@GetMapping("/callback/sns/google")
	public String googleCallback(@RequestParam("code") String code, HttpSession session)
			throws ExecutionException, IOException, InterruptedException {

		// OAuth20Service 빈 등록 시 이미 Client ID , Client Secret 등록 해 놓았으므로
		// 토큰 요청 시 , Authorization Code만 추가하면 된다...
		OAuth2AccessToken accessToken = googleAuthService.getAccessToken(code);

		log.debug("구글이 나에게 보내준 권한 코드는 " + code);
		log.debug("인증 결과인 token은 " + accessToken);

		// 토큰을 받았다면 언제든 Resource Owner의 개인정보를 접근할 수 있다.
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v2/userinfo");
		googleAuthService.signRequest(accessToken, request); // 요청 시 사용할 토큰 지정
		Response response = googleAuthService.execute(request); // 사용자 정보 요청 시도

		// json 파싱
		JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();

		// 필요한 개인 정보를 key 값으로 조회하여 가져오기
		String email = json.get("email").getAsString();
		log.debug("사용자의 이메일은 " + email);

		String name = json.get("name").getAsString();
		log.debug("사용자명은 " + name);

		String openid = json.get("id").getAsString();
		log.debug("sns id 는" + openid);

		// 회원가입 확인 및 등록 : 토큰을 통해 얻은 회원정보가 우리 쇼핑몰에 등록되어 있는지 체크
		// 1. 없으면 가입 후 로그인
		Member member = null;
		member = memberService.selectById(openid);
		if (member == null) {
			member = new Member();
			member.setSnsProvider(snsProviderService.selectByName("google"));
			member.setId(openid);
			member.setEmail(email);
			member.setName(name);

			memberService.regist(member);
		}

		// 2. 있으면 로그인
		session.setAttribute("member", member); // 세션이 살아있는 한 , Member를 사용할 수 있다.

		return "redirect:/shop/product/list";
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------
		네이버 로그인 처리
	-------------------------------------------------------------------------------------------------------------------------------------------*/

	// 네이버 인증 동의화면 요청 처리
	@GetMapping("/member/naver/authurl")
	@ResponseBody
	public String getNaverAuthUrl() {
		return naverAuthService.getAuthorizationUrl();
	}

	// 네이버에 등록해 놓은 콜백 주소로 전송되는 콜백 요청 처리
	@GetMapping("/callback/sns/naver")
	public String naverCallback(@RequestParam("code") String code, @RequestParam("state") String state,
			HttpSession session) throws IOException, InterruptedException, ExecutionException {

		// IDP가 전송한 code와 client Id, client Secret을 조합하여 토큰을 요청하자 !!
		// 클라이언트Id 와 클라이언트Secret은 빈 등록 시 이미 등록해 놓은 걸 사용한다.
		OAuth2AccessToken accessToken = naverAuthService.getAccessToken(code);
		log.debug("네이버에서 발급받은 token은 " + accessToken);

		// 발급받은 토큰을 이용하여 회원 정보 조회
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://openapi.naver.com/v1/nid/me");
		naverAuthService.signRequest(accessToken, request);
		Response response = naverAuthService.execute(request); // 요청 후 그 결과를 받자

		// Gson으로 파싱하기 !!
		JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
		JsonObject resObj = json.getAsJsonObject("response");
		// 필요한 개인 정보를 key 값으로 조회하여 가져오기
		String email = resObj.get("email").getAsString();
		String name = resObj.get("name").getAsString();
		String openid = resObj.get("id").getAsString();

		log.debug("Response 로 나온 json은 " + json);

		// 회원가입 확인 및 등록 : 토큰을 통해 얻은 회원정보가 우리 쇼핑몰에 등록되어 있는지 체크
		// 1. 없으면 가입 후 로그인
		Member member = null;
		member = memberService.selectById(openid);
		if (member == null) {
			member = new Member();
			member.setSnsProvider(snsProviderService.selectByName("naver"));
			member.setId(openid);
			member.setEmail(email);
			member.setName(name);

			memberService.regist(member);
		}

		// 2. 있으면 로그인
		session.setAttribute("member", member); // 세션이 살아있는 한 , Member를 사용할 수 있다.

		return "redirect:/shop/main";
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------
	카카오 로그인 처리
	-------------------------------------------------------------------------------------------------------------------------------------------*/
	//인증 동의화면 요청 처리
	@GetMapping("/member/kakao/authurl")
	@ResponseBody
	public String getKakaoAuthUrl() {
		return kakaoAuthService.getAuthorizationUrl();
	}

	//콜백 요청 처리
	@GetMapping("/callback/sns/kakao")
	public String kakaoCallback(@RequestParam("code") String code, HttpSession session) throws IOException, InterruptedException, ExecutionException {
		OAuth2AccessToken accessToken = kakaoAuthService.getAccessToken(code);

		// 토큰을 받았다면 언제든 Resource Owner의 개인정보를 접근할 수 있다.
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://kapi.kakao.com/v2/user/me");
		kakaoAuthService.signRequest(accessToken, request); // 요청 시 사용할 토큰 지정
		Response response = kakaoAuthService.execute(request); // 사용자 정보 요청 시도

		// json 파싱
		JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
		JsonObject userJson = json.getAsJsonObject("response");
		log.debug("보낸 정보는 "+userJson);
		// 필요한 개인 정보를 key 값으로 조회하여 가져오기
		String openid = json.get("id").getAsString();
		//String email = json.get("email").getAsString();

		//String name = json.get("profile_nickname").getAsString();


		return "redirect:/shop/product/list";
	}
}

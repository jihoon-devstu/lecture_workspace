package mall.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.oauth.OAuth20Service;

@Controller
public class MemberController {
	
	@Autowired
	private OAuth20Service googleAuthService;
	
	//로그인 폼 요청 처리
	@GetMapping("/member/loginform")
	public String getForm() {

		return "shop/member/login";
	}
	
	//인증 동의화면 요청 처리
	@GetMapping("/member/google/authurl")
	@ResponseBody
	public String getGoogleAuthUrl() {
		return googleAuthService.getAuthorizationUrl();
	}
	
	
}

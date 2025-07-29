package mall.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

//쇼핑몰의 기능 중 , 로그인이 요구되는 요청 마다 세션 존재 여부를 체크하면
//코드가 여기저기 중복되게 되므로, 따라서 해당 요청에 필터 수준에서 세션 체크를 진행.
@Slf4j
public class LoginCheckFilter implements Filter{

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;

		log.debug("필터 동작함");
		
		//클라이언트의 요청이 /shop/cart/list , /shop/member/mypage 등등의 로그인이 필요한 서비스인 경우엔
		//세션 정보가 없다면 , 아래의 chain.doFilter() 를 만나지 않도록 처리.
		
		String uri=request.getRequestURI();
		
		if(
			//메인 화면
			uri.equals("/shop/main") ||
			
			//로그인 폼 / 로그 아웃
			uri.equals("/shop/member/loginform") ||
			uri.equals("/shop/member/logout") ||
			
			//회원가입 관련
			uri.equals("/shop/member/registform") ||
			uri.equals("/shop/member/regist") ||
			
			//동의화면 관련
			uri.equals("/shop/member/kakao/authurl") ||
			uri.equals("/shop/member/naver/authurl") ||
			uri.equals("/shop/member/google/authurl") ||
			
			//콜백 관련
			uri.equals("/shop/callback/sns/kakao") ||
			uri.equals("/shop/callback/sns/naver") ||
			uri.equals("/shop/callback/sns/google")||
			
			//상품 관련
			uri.equals("/shop/product/list") ||
			uri.equals("/shop/product/detail") ||
			uri.equals("/shop/callback/sns/naver") ||
			uri.equals("/shop/callback/sns/google")
		){
			//그냥 가도록 허용하고 싶은 uri
			chain.doFilter(req, res); //가던길 가도록...
			//어차피 doFilter() 를 만나면 실행부는 원래 요청하려고 했던 서블릿 또는 다른 필터를 수행하러 가기 때문에
			// 아래의 코드로 진행할 가능성은 없지만 , 코드 관습 상 다른 개발자들에게 명확히 알려주기 위해서 return을 적는다.
			return; 
		}
		
		HttpSession session = request.getSession(false);
		boolean loggined=(session != null && session.getAttribute("member") !=null);
	
		if(loggined ==false) { //로그인 하지 않은 경우엔 ... 강제적으로 loginform을 만나게 하자.
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect("/shop/member/loginform");
		
		}else {
			chain.doFilter(req, res);
		}	
	
		
		
		
	}


	
	@Override
	public void destroy() {
		
	}

}

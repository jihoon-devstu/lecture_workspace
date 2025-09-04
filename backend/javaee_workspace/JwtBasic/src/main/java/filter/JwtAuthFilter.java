package filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import util.MyJwtUtil;

//회원 로그인이 필요한 모든 서블릿이나 , jsp 마다 각각 jwt 보유 여부를 검증하는 코드를 작성하게 되면
//코드가 중복되므로 , 필터 수준에서 jwt 보유 여부를 검증하고 , 즉 로그인 검증을 처리하고
//개발자가 하고 싶은 작업을 진행하면 됨
@WebFilter("/api/*")
public class JwtAuthFilter implements Filter{
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//필터 수준에서 들어오는 요청에 Jwt에 대한 검증을 진행
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//Jwt가 헤더를 통해 전송되므로 , 헤더를 접근해보자
		String authHeader = req.getHeader("Authorization"); //여러 헤더 값 중 , JWT 토큰이 전달되는 헤더는  Authorization 헤더임.
		
		//클라이언트가 요청 시 헤더에 지참했던 토큰을 꺼내보자 !!
		//주의 , 꺼내기 전에 토큰이 존재할 경우만 꺼내야 함.
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			//클라이언트에게 JWT가 없다면 에러 메세지 전송.
			res.setContentType("application/json;charset=utf-8");
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			Map<String,String> map = new HashMap<>();
			map.put("error","로그인 정보가 올바르지 않습니다.");
			res.getWriter().print(new Gson().toJson(map));
			
			return; //현재 이 필터 실행을 빠져나감.
		}
		
		
		//토큰을 가져온 경우. 순수 토큰 값을 꺼내서 검증
		String token = authHeader.substring(7);
		
		if(!MyJwtUtil.validateToken(token)) {
			res.setContentType("application/json;charset=utf-8");
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			Map<String,String> map = new HashMap<>();
			map.put("error","로그인 정보가 올바르지 않습니다.");
			res.getWriter().print(new Gson().toJson(map));
			
			return;
		}
		
		String username = MyJwtUtil.getUername(token);
		req.setAttribute("username",username);
		
		chain.doFilter(request, response); //다음 서블릿 또는 다른 필터로 요청 전달
	}


}

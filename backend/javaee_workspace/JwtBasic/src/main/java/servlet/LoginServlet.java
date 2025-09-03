package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import util.MyJwtUtil;

@WebServlet("/member/login") //web.xml을 사용하지 않고 어노테이션 기반으로 서블릿을 매핑하는 방법
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//파라미터 받기
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username is "+username);
		System.out.println("password is "+password);
		
		if("batman".equals(username) && "1234".equals(password)) {
			System.out.println("로그인 성공");
			
			//인증 증표를 생성하여 클라이언트 응답 정보로 보내주자
			String accessToken = MyJwtUtil.generateAccessToken(username, 1000*60*15); //15분 동안 유효
			
			//클라이언트에게 발급.
			response.setContentType("application/json;charset=utf-8");
			
			//java 클래스를 정의해도 되지만 , 귀찮을 경우 Map을 json 문자열로 변환시키자
			Map<String,String> tokens = new HashMap<>();
			tokens.put("accessToken", accessToken);
			
			Gson gson = new Gson();
			String json = gson.toJson(tokens);
			response.getWriter().print(json);
			
		}
		
	}
}

package com.sinse.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinse.mvcapp.color.model.ColorManager;

/*
 * MVC 패턴에 의해 디자인과 로직을 분리시키려면, 중간에 중재자가 나서야 하므로 , 코드에서 분리.
 * */

public class ColorController extends HttpServlet{
	
	ColorManager manager = new ColorManager();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*컨트롤러의 5대 업무
		 * 1. 요청을 받는다.
		 * 2. 요청을 분석한다.
		 * 3. 직접 일하지 않고 알맞는 로직객체(모델) 에게 일을 시킨다
		 * 4. 결과에 보여질 데이터를 보관한다.
		 * 5. 알맞는 결과를 보여준다.
		 * */
		String color = request.getParameter("color");
		String result = manager.getAdvice(color);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg",result);
		
		//result.jsp를 클라이언트가 보도록 처리
		response.sendRedirect("/color/result.jsp"); //클라이언트로 하여금 지정된 url로 다시 요청을 해야함.
		//위 코드는 <script>location.href='/color/result.jsp'</script> 와 동일한 효과를 냄.
	}
}

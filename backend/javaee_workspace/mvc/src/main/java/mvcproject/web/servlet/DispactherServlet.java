package mvcproject.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//아주 큰 규모의 웹애플리케이션에서 모든 요청을 이 서블릿이 받는다.
public class DispactherServlet extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request,response);
	}
	
	//Get 이던 Post이던 이 메서드에서 업무 처리
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("메서드 호출" );
	}
}

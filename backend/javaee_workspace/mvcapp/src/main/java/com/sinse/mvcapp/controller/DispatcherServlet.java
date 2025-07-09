package com.sinse.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.mvcapp.color.model.BloodManager;
import com.sinse.mvcapp.color.model.ColorManager;

//모든 종류의 요청을 다 받을 수 있는 서블릿
public class DispatcherServlet extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doRequest(request,response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청을 받는다
		//요청 분석 (생략)
		
		//매 요청마다 1:1 대응되는 매핑을 피하기 위해 하나의 진입점으로 몰았으나
		//
		
		if(request.getRequestURI().equals("/blood.do")) {
		String blood = request.getParameter("blood");
		BloodManager manager = new BloodManager();
		
		String result = manager.getAdvice(blood);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", result);
		
		//알맞는 뷰 선택
		response.sendRedirect("/blood/result.jsp"); //클라이언트의 재접속 강제
		}else if(request.getRequestURI().equals("/color.do")) {
			String blood = request.getParameter("color");
			ColorManager manager = new ColorManager();
			
			String result = manager.getAdvice(blood);
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", result);
			
			//알맞는 뷰 선택
			response.sendRedirect("/color/result.jsp"); //클라이언트의 재접속 강제
		}
	}
	
}

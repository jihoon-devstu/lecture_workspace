package com.sinse.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.mvcapp.color.model.BloodManager;

//모든 종류의 요청을 다 받을 수 있는 서블릿
public class DispatcherServlet extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청을 받는다
		//요청 분석 (생략)
		String blood = request.getParameter("blood");
		BloodManager bloodManager = new BloodManager();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
}

package com.sinse.memberapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.memberapp.exception.MemberNotFoundException;
import com.sinse.memberapp.model.Member;
import com.sinse.memberapp.repository.MemberDAO;

public class LoginServlet extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(getClass());

	MemberDAO memberDAO = new MemberDAO();
	//post로 전송되어 오는 로그인 정보를 받아 , 회원 테이블과 일치 여부에 따라
	//로그인 성공 및 실패...
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		String id = request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		for(int i =1; i<=10; i++) {
			logger.trace("i는" +i);
		}
		
		logger.debug("넘겨받은 아아디 " +id);
		logger.debug("넘겨받은 비밀번호 "+pwd);
		
		Member member = new Member();
		
		member.setId(id);
		member.setPwd(pwd);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Member obj=null;
		out.print("<script>");
		try {
			obj = memberDAO.login(member);
			out.print("alert('"+obj.getName()+"님 반갑습니다');");
			out.print("location.href='/index.jsp';");
		} catch (MemberNotFoundException e) {
			e.printStackTrace();
			out.print("alert('"+e.getMessage()+"');");
			out.print("history.back();");
		}
		out.print("</script>");
//		logger.trace("trace 레벨 출력");
//		logger.debug("debug 레벨 출력");
//		logger.info("info 레벨 출력");
//		logger.warn("warn 레벨 출력");
//		logger.error("error 레벨 출력");
		
	}
}

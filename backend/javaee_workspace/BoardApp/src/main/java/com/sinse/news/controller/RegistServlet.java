package com.sinse.news.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.boardapp.exception.NewsException;
import com.sinse.boardapp.model.News;
import com.sinse.boardapp.repository.NewsDAO;

//뉴스 기사 등록 요청을 받는 서블릿
public class RegistServlet extends HttpServlet{
	NewsDAO newsDAO = new NewsDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//다국어 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8"); //jsp @page 지시영역
		
		//파라미터 받기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		System.out.println(title);
		System.out.println(writer);
		System.out.println(content);
		
		//글쓰기 수행
		News news = new News();
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		PrintWriter out = response.getWriter();
		out.print("<script>");
		try {
			newsDAO.insert(news);
			out.print("location.href='/news/list.jsp';");
		} catch (NewsException e) {
			e.printStackTrace();
			out.print("alert('"+e.getMessage()+"');");
			out.print("history.back();");
		}
		out.print("</script>");
			
	}
}

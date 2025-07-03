package com.sinse.comment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.boardapp.exception.CommentException;
import com.sinse.boardapp.model.Comment;
import com.sinse.boardapp.model.News;
import com.sinse.boardapp.repository.CommentDAO;

public class WriteServlet extends HttpServlet{
	
	CommentDAO commentDAO = new CommentDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//파라미터를 받자
	request.setCharacterEncoding("utf-8");
	String msg = request.getParameter("msg");
	String user = request.getParameter("user");
	String news_id = request.getParameter("news_id"); //내가 지금 보고 있는 뉴스 기사
	
	Comment comment = new Comment();
	News news = new News();
	news.setNews_id(Integer.parseInt(news_id));
	comment.setMsg(msg);
	comment.setUser(user);
	comment.setNews(news);
	
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = response.getWriter();
	out.print("<script>");
	try {
		commentDAO.insert(comment);
		out.print("location.href='/news/content.jsp?news_id="+news_id+"';");
	} catch (CommentException e) {
		e.printStackTrace();
		out.print("alert('등록 실패');");
		out.print("history.back();");
	}
	out.print("</script>");
	
	
	}
}

package com.sinse.boardapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.boardapp.exception.NoticeException;
import com.sinse.boardapp.model.Notice;
import com.sinse.boardapp.repository.NoticeDAO;

//삭제 요청을 처리하는 서블릿 , 삭제 요청은 별도의 디자인을 요구하는 요청이 아니므로 , jsp가 아닌 서블릿 으로처리.
public class DeleteServlet extends HttpServlet{
	NoticeDAO noticeDAO;
	
	public DeleteServlet() {
		noticeDAO = new NoticeDAO();
	}

	//삭제를 요청하는 클라이언트가 get방식으로 요청을 하고 있으므로....
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8"); //파라미터에 대한 인코딩 지정
		
		//jsp <% @ page contentType = "text/html;charset = utf-8">
	response.setContentType("text/html;charset = utf-8");
	PrintWriter out = response.getWriter();
		
	String notice_id = request.getParameter("notice_id"); //파라미터 받기
	
	Notice notice = new Notice();
	notice.setNotice_id(Integer.parseInt(notice_id));
	try {
		noticeDAO.delete(notice);
		out.print("<script>");
		out.print("alert('글 삭제 성공');");
		out.print("location.href = '/notice/list.jsp';");			
		out.print("</script>");
	} catch (NoticeException e) {
		e.printStackTrace();
		out.print("<script>");
		out.print("alert('"+e.getMessage()+"');");
		out.print("history.back()"); //브라우저 아이콘 중 뒤로가기 누르는 효과.			
		out.print("</script>");
	}
	}
	
}

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

public class UpdateServlet extends HttpServlet{

	//클라이언트 인 브라우저가 폼의 파라미터들을 Post로 요청하고 있음.
	//따라서 doXX형 중 doPost로 처리하자.
	NoticeDAO noticeDAO = new NoticeDAO();	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = utf-8"); //jsp의 page 지시영역과 동일
		
		String notice_id = request.getParameter("notice_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		PrintWriter out = response.getWriter();
		Notice notice = new Notice();
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		out.print("<script>");
		try {
			noticeDAO.update(notice); //수정 수행
			out.print("alert('글 수정 성공');");
			out.print("location.href = '/notice/content.jsp?notice_id="+notice_id+"';"); //응답을 받은 브라우저로 하여금 , 지정한 url로 재 접속하라 !!!
		} catch (NoticeException e) {
			out.print("alert('"+e.getMessage()+"');");
			out.print("history.back()"); //브라우저 아이콘 중 뒤로가기 누르는 효과.	
		}
		out.print("</script>");
		
	}
	
	
}

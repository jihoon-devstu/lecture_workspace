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


//클라이언트가 전송한 글쓰기 폼의 파라미터들을 받아 , db에 insert 시키는 서블릿
public class RegistServlet extends HttpServlet {
	NoticeDAO noticeDAO;
	// 클라이언트인 브라우저가 전송한 변수 = 파라미터 값을 받아서 , db에 넣기
	// 이 요청은 유저가 보게될 디자인과 관련없으므로, 이 요청을 처리할 기술을 Servlet으로 선택.
	
	public RegistServlet() {
		noticeDAO = new NoticeDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //파라미터에 대한 인코딩 지정
		
		//jsp <% @ page contentType = "text/html;charset = utf-8">
		response.setContentType("text/html;charset = utf-8");
		//response.setCharacterEncoding("utf-8"); //응답시 컨텐츠의 문자 인코딩을 지정.
		PrintWriter out = response.getWriter();
		//요청 객체에 들어있는 파라미터 꺼내기 
		String title = request.getParameter("title"); //html에 지정한 파라미터명
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		
		
		try {
			noticeDAO.insert(notice); //등록 완료.
			out.print("<script>");
			out.print("alert('글 등록 성공');");
			out.print("location.href = '/notice/list.jsp';");			
			out.print("</script>");
		} catch (NoticeException e) {
			//클라이언트가 받게 될 응답 정보에 스크립트 문자열을 채워 넣기.
			//Tomcat과 같은 웹컨테이너가 , 이 메서드의 닫는 괄호를 만나면 , 그 시점부터 나선다...
			//응답 객체인 response에 들어있는 출력 스트림에 누적되어 있는 문자열을 이용하여
			//HTML 컨텐츠를 만든다...
			
			out.print("<script>");
			out.print("alert('"+e.getMessage()+"');");
			out.print("</script>");
			e.printStackTrace();
		}
		
	}

}

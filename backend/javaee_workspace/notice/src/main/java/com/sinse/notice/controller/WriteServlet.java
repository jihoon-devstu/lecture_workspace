package com.sinse.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 글쓰기 요청을 처리하는 서버측의 서블릿 정의
public class WriteServlet extends HttpServlet{
	
	//크라이언트의 요청이 post 방식인 경우 , doXXXX()형 메서드 중 , doPost로 받아야 한다. must
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청을 받고 , 
		//전송된 데이터에 대한 언어 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); //고양이가 문서 만들 때 다국어 인식
		// text/html 과 같은 파일의 종류 유형을 가리켜 마임 타입(MIMEType)
		//application/json; text/xml , image/jpeg
		
		String title = request.getParameter("title"); //제목
		String writer = request.getParameter("writer"); //글쓴이
		String content = request.getParameter("content"); //내용
		
		System.out.println("title is" + title );
		System.out.println("writer is" + writer );
		System.out.println("content is" + content );
		
		PrintWriter out = response.getWriter();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/notice","shop","1234");
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into notice(title,writer,content) values(?,?,?)");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int result = pstmt.executeUpdate(); //DML 수행
			
			if(result>0) {
			
				//문자열을 적재해 놓으면 , 응답 정보를 만들 때 고양이가 참고한다.
				//브라우저에서 직접 작성하는 script의 경우엔 세미콜론을 생략해도 관대하다...
				//하지만 서버에서 문자열로 작성한 JS코드는 문장의 끝에 반드시 세미콜론을 줘야함.
			
				out.print("<script>");
				out.print("alert('글 등록 완료');");
				out.print("location.href='/notice/list';");
				out.print("</script>");
			}else {
				out.print("<script>");
				out.print("alert('글 등록 실패');");
				out.print("history.back();"); //브라우저의 뒤로가기 버튼과 동일.
				out.print("</script>");
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
		
		
		
		//응답 정보만 만들 수 있다...(응답은 톰켓 서버가 담당)
	}

}

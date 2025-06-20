package com.sinse.web0618;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{

	public void init() throws ServletException{
		System.out.println("저 방금 태어나서 , 제가 누군지 알게되었어요");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service()호출 요청을 처리합니다");
	}
	
	
	@Override
	public void destroy() {
		System.out.println("destroy()호출 : 저 갑니다 여기서 무언가 다 반납할게요");
	}
}

package com.sinse.mvcapp.controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinse.mvcapp.controller.Controller;
import com.sinse.mvcapp.repository.NoticeDAO;

public class ListController implements Controller{
	
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = noticeDAO.selectAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("noticeList", list);
	}
	public String getViewPage() {

		return "/notice/list/view";
	}
}

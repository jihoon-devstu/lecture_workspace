package com.sinse.hiberasync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sinse.hiberasync.model.Store;
import com.sinse.hiberasync.repository.StoreDAO;
import com.sinse.hiberasync.util.Message;

public class StoreDel extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	StoreDAO storeDAO = new StoreDAO();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String store_id=request.getParameter("store_id");
		
		Store store = new Store();
		store.setStore_id(Integer.parseInt(store_id));
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Message message = new Message();
		Gson gson = new Gson();
		
		try {
			storeDAO.delete(store);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			message.setResult("fail");
			message.setMsg(e.getMessage()); // 에러 메시지
			out.print(gson.toJson(message)); // 메시지가 json 문자열로 변환된어 전송
		}
		
	}
}

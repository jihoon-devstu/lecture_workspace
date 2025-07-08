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
import com.sinse.hiberasync.exception.StoreException;
import com.sinse.hiberasync.model.FoodType;
import com.sinse.hiberasync.model.Store;
import com.sinse.hiberasync.repository.StoreDAO;
import com.sinse.hiberasync.util.Message;

//맛집 등록 요청을 처리하는 서블릿
public class StoreRegist extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	StoreDAO storeDAO = new StoreDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//필터가 제대로 동작 했다면 , 한글 처리를 따로 하지 않아도 인코딩 처리가 되어 있어야 함...
		String food_type_id = request.getParameter("food_type_id");
		String store_name = request.getParameter("store_name");
		String tel = request.getParameter("tel");

		FoodType foodType = new FoodType();
		foodType.setFood_type_id(Integer.parseInt(food_type_id));
		Store store = new Store();
		store.setStore_name(store_name);
		store.setTel(tel);
		store.setFoodType(foodType); //부모 객체 주입 !! 
		
		
		//응답 정보를 HTML이 아닌 , json으로 생성하여 보내자.
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Message message = new Message();
		Gson gson = new Gson();
		
		//등록
		try {
			storeDAO.insert(store);
			
			//200,500 -> Http Status 코드 : 서버가 클라이언트에게 응답 시 보내는 코드 , (성공,실패...)
			//IETF (Internet Engineering Task Force) - 인터넷 표준 프로토콜을 정의하는 국제 조직이 정함.
			response.setStatus(HttpServletResponse.SC_CREATED); //201
			message.setResult("success");
			message.setMsg("등록 성공");
			
		} catch (StoreException e) {
			e.printStackTrace();
			message.setResult("fail");
			message.setMsg(e.getMessage()); // 에러 메시지
		}
		out.print(gson.toJson(message)); // 메시지가 json 문자열로 변환된어 전송
	}
}

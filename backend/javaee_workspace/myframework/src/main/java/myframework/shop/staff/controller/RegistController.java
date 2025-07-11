package myframework.shop.staff.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myframework.staff.model.domain.Bio;
import myframework.staff.model.domain.Staff;
import myframework.staff.model.service.StaffService;
import myframework.web.servlet.Controller;

public class RegistController implements Controller{
	StaffService staffService = new StaffService();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		
		String name = request.getParameter("name");
		String sal = request.getParameter("sal");
		String email = request.getParameter("email");
		String blood = request.getParameter("blood");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		
		Staff staff = new Staff();

		staff.setName(name);
		staff.setSal(Integer.parseInt(sal));
		staff.setEmail(email);
		
		Bio bio = new Bio();
		bio.setBlood(blood);
		bio.setHeight(Integer.parseInt(height));
		bio.setWeight(Integer.parseInt(weight));
		bio.setStaff(staff);
		//서비스에게 일시키기
		staffService.regist(bio);
	
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/shop/staff/regist/view";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}

}

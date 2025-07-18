package myframework.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException;
	
	public String getViewName();
	public boolean isForward();
}

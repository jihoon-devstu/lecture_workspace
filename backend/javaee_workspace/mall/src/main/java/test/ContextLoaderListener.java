package test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener{

	//Tomcat과 같은 웹 컨테이너가 중단될 때 동작하는 메서드
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("서버 가동 되었어요");
		//애플리케이션 수준의 객체인 ServletContext에 나의 이름을 저장
		ServletContext context = sce.getServletContext();
		context.setAttribute("tel", "010-1234-5678");
		//스프링에 사용될 빈즈들은 인스턴스화 시켜서 , 보관...
	}
	
	//Tomcat과 같은 웹컨테이너가 가동될 때 동작하는 메서드
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("서버 중지 되었어요");
		
	}



	
}

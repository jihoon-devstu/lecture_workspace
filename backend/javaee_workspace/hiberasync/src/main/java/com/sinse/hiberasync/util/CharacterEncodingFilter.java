package com.sinse.hiberasync.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * 요청이 들어오면 , 사실 서블릿 보다도 앞선 단계에서 업무를 처리할 수 있도록 지원되는 인터페이스
 * 주 용도) 웹 애플리케이션 전반적으로 공통된 설정 , 사용자의 로그 기록 , 로그인 처리 ...
 * */
public class CharacterEncodingFilter implements Filter{

	String encoding;
	
	//아래의 init은 서블릿의 생명주기와 완전 흡사하므로 , 필터 인스턴스가 생성되자 마자 호출되는
	//필터 초기화 메서드 이다. 개발자는 web.xml에 초기값을 전달하려면 이 메서드에서 진행할 수 있다.
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		//이 요청의 흐름을 그대로 흐르게 처리... (안해주면 요청은 여기서 막힘)
		
		//chain.doFilter()를 기준으로 이전에 코드를 작성하면, 요청이 들어올 때 하고싶은 작업을 처리....
		chain.doFilter(request, response); //다음 필터 또는 다음 서블릿으로 요청 전달
		//chain.doFilter()를 기준으로 다음에 코드를 작성하면, 응답시 하고싶은 작업을 처리....		
		
	}

	@Override
	public void destroy() {
	}
	
	
}

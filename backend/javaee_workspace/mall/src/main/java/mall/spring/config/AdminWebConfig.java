package mall.spring.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import mall.util.Paging;

/*
 * 스프링의 고전적 설정을 담당하는 xml을 대신하는 java class
 * 
 * */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"mall.admin.controller , mall.util"})
public class AdminWebConfig extends WebMvcConfigurerAdapter{
	
	/*하위 컨트롤러가 3,4단계를 수행한 후 DispatcherServlet에게 정확한 파일명을 알려주는것이 아니라
	 * 파일의 일부 단서만 반환한다.(ModelAndView에 심어서...) 따라서 이 객체를 넘겨받은 DispatcherServlet은
	 * 일부 단서를 직접 해석하지 않고 , view에 대한 해석을 담당하는 전담객체인 resolver 에게 맡긴다.
	 * 이 View 영역을 전담하는 객체들을 가리켜 스프링에서는 ViewResolver라 한다.
	 * JSP 사용 시 주로 사용되는 ViewResolver 는 InternalResourceViewResolver
	 * 
	 * 예시) 하위 컨트롤러가 notice/list 를 심어서 보내면 --> /WEB-INF/views/notice/list.jsp
	 * */
	@Bean
	public InternalResourceViewResolver InternalResourceViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	//어노테이션으로 하지 않을 경우, xml로 설정해야 함.
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//pom.xml에 추가한 jackson-bind 라이브러리의 객체 추가
		converters.add(new MappingJackson2HttpMessageConverter());
	}
	
	//파일 업로드를 위한 설정
	//아파치 파일 업로드를 스프링에서 , 내부적으로 처리한 업로드 빈
	//클라이언트가 파일을 전송할 때, 사용
	@Bean 
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(10*1024*1024); //10M 
		return resolver;
	}
	
}

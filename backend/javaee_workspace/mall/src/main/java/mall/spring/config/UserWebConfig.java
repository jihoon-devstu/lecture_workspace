package mall.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"mall.shop.controller"})
public class UserWebConfig {
	
	@Bean
	public InternalResourceViewResolver InternalResourceViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	//구글 로그인 관련 서비스 객체 등록
	@Bean
	public OAuth20Service googleAuthService() {
		//클라이언트 ID , Secret , 콜백주소 , 리소스 Owner , 접근 범위
		ServiceBuilder builder = new ServiceBuilder("99629635046-3mao7qk13pph1m7hk3tmfqv2dgu43iee.apps.googleusercontent.com");
		builder.apiSecret("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");
		builder.defaultScope("email profile openid");
		builder.callback("http://localhost:8888/shop/callback/sns/google");
		return builder.build(GoogleApi20.instance());
	}
	
}

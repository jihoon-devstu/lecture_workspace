package springapp.cook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 전통적인 스프링의 개발 방식에서는 주로 XML이 사용되었으나 , 
 * */
@Configuration
public class AppConfig {

	//클래스 중 스프링의 관리 대상이 되는 클래스를 전통적으로 빈이라 한다.
	@Bean
	public Fripan firpan() {
		return new Fripan();
	}
	
	@Bean
	public Induction induction() {
		return new Induction();
	}
	
	//요리사 빈을 생성자 주입으로 위빙(weaving)
	@Bean
	public Cook cook() {
		return new Cook(induction());
	}
}

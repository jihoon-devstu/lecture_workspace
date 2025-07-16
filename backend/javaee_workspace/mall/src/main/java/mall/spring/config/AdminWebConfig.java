package mall.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * 스프링의 고전적 설정을 담당하는 xml을 대신하는 java class
 * 
 * */

@Configuration
@ComponentScan(basePackages = {"mall.admin.controller"})
public class AdminWebConfig {

	
	
}

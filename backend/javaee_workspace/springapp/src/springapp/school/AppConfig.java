package springapp.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //xml을 대신하여 , 스프링 컨테이너에게 빈을 관리하도록 부탁하는 설정파일
@EnableAspectJAutoProxy
@ComponentScan("springapp.school") //지정된 컴포넌트의 위치를 알려준다.
public class AppConfig {

	@Bean
	public Bell bell() {		//<bean class"~~.Bell" id = "bell"></bean> 과 동일.
		return new Bell();
	}
	
	@Bean
	public Student student() {		//<bean class"~~.Student" id = "student"></bean> 과 동일.
		return new Student();
	}
}

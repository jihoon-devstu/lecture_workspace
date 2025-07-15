package springapp.school;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Appmain {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Student student = context.getBean(Student.class);
		student.getUp();
		student.goToSchool();
		student.study();
		student.rest();
		student.haveLunch();
		student.goHome();
	}
}

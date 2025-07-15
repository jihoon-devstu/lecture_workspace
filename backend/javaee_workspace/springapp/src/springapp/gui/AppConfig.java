package springapp.gui;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//XML을 대신할 설정

@Configuration
public class AppConfig {

	@Bean
	public JTextField name() {
		return new JTextField();
	}
	
	@Bean
	public JTextField email() {
		return new JTextField();
	}
	
	@Bean
	public JButton bt() {
		return new JButton("버튼");
	}

	@Bean
	public MyWin myWin() {
		MyWin win = new MyWin(name(),email(),bt());
		win.setName(name());
		win.setEmail(email());
		win.setBt(bt());
		return win;
	}
}

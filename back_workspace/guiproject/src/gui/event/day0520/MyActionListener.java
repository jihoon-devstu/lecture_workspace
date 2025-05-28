package gui.event.day0520;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class MyActionListener implements ActionListener{
	
	Button bt1;
	Button bt2;
	
	//메서드를 통해 다른 클래스에 존재하던 버튼들을 전달받음.(생성자 주입 == 인젝션 injection)
	public MyActionListener(Button bt1, Button bt2){
		this.bt1=bt1;
		this.bt2=bt2;
	}

	public void actionPerformed(ActionEvent e){
		Object obj=e.getSource();
		
		System.out.println("액션을 일으킨 주체는"+obj);
		
		if(obj==bt1){
			System.out.println("A를 눌렀어?");
		}
		else if(obj==bt2){
		System.out.println("B를 눌렀어?");
	}

}

}

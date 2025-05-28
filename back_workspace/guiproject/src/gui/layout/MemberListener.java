package gui.layout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class MemberListener implements ActionListener {
	
	Button bt1;
	Button bt2;
	JoinForm join = new Joinform;
	
	public MemberListener(JoinForm join, Button bt1, Button bt2){
		this.bt1 = bt1;
		this.bt2 = bt2;
		this.join=join;
	}
	

		//오버라이딩
		public void actionPerformed(ActionEvent e){
			
			Object obj = e.getSource();
			
			if(obj==bt1){
			System.out.println("로그인 버튼 누름");
			join.checkForm();
			}else if(obj==bt2){
			System.out.println("가입 버튼 누름");
			}
			join.checkForm();
		}
	
}

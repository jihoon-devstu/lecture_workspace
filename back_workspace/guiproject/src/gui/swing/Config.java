package gui.swing;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class  Config extends JFrame implements ActionListener{
	
	JTextField t_size;
	JButton bt;
	
	//has a 관계는 멤버변수로 보유한 관계를 의미한다
	MyWin myWin;//null
	
	public Config(MyWin myWin){
		this.myWin=myWin;
		
		t_size = new JTextField(20);
		bt = new JButton("설정적용");
		
		setLayout(new java.awt.FlowLayout());
		
		add(t_size);
		add(bt);
		//버튼과 리스너 연결
		
		bt.addActionListener(this);
		
		setBounds(700,200,300,400);
		setVisible(true);
		
		
		//this란? 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
		
	}
	public void actionPerformed(ActionEvent e){
		//MyWin이 보유한 area의 폰트의 크기를 설정해주자
		//단 폰트의 크기 값은 나의 TextField로부터 얻은 값이다.
		int size = Integer.parseInt(t_size.getText());
		myWin.area.setFont(new Font(null,0,size));
	}
}



package gui.swing;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyWin extends JFrame implements ActionListener{
	JTextArea area;
	JPanel p_south;
	JButton bt;
	Config config;
	
		public MyWin(){
			area = new JTextArea(4,15);
			p_south = new JPanel();
			bt= new JButton("환경설정");
			
			area.setBackground(Color.YELLOW);
			
			add(area);
			p_south.add(bt);
			add(p_south, BorderLayout.SOUTH);
			
			bt.addActionListener(this);
			
			
			setBounds(400,200,300,400);
			setVisible(true);
			
		}
		
		//부모의 메서드 오버라이딩
		public void actionPerformed(ActionEvent e){
		//this란? 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
		config  = new Config(this);
		}
	
	
		public static void main(String[] args) 
	{
		new MyWin();
	}
	
}  




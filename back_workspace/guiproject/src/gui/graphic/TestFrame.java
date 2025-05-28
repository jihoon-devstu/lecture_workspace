package gui.graphic;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;




public class TestFrame extends JFrame{
	MyButton bt; //같은 자료형이므로 당연히 가능 !
	ImgPanel ip;
	
	public TestFrame(){
	
		
		bt = new MyButton("커스텀 버튼");
		ip = new ImgPanel();
		
		
		setLayout(new FlowLayout());
		add(bt);
		add(ip);
		setSize(600,600);
		setVisible(true);
		
		//윈도우창을 닫을 때, 프로세스 종료
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	
	public static void main(String[] args) 
	{
		new TestFrame();
	}
}

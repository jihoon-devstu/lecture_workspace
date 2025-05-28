package gui.event;
import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gui.event.day0520.MyActionListener;

public class  DoubleButton{
	public static void main(String[] args) 
	{
		Frame frame = null;
		Button bt1 = null;
		Button bt2 = null;
		
		frame = new Frame();
		bt1 = new Button("A");
		bt2 = new Button("B");
		
		frame.setLayout(new FlowLayout());		
		frame.add(bt1);
		frame.add(bt2);
		
		MyActionListener my = new MyActionListener(bt1,bt2);
		//my.setBtn(bt1, bt2);
		

		
		bt1.addActionListener(my);
		bt2.addActionListener(my);
		
		frame.setSize(300,400);
		frame.setVisible(true);
	}
}

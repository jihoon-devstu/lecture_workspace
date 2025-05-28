package gui.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoveTest  extends JFrame implements ActionListener{
	
	JPanel p_north;
	JButton bt;
	MovePanel p_center;
	
	public MoveTest(){
		p_north = new JPanel();
		bt = new JButton("이동");
		p_center = new MovePanel();
		
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(p_center, BorderLayout.CENTER);
		
		bt.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p_north.setPreferredSize(new Dimension(600, 50));
		p_center.setPreferredSize(new Dimension(600, 600));
		
		
		
		setSize(600,650);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		//MovePanel의 빨간색 원을 이동시키자
		//x,y를 증가시키자
		p_center.move();
		p_center.repaint();
		/*
		int x = p_center.getX();
		x++;
		p_center.setX(x);
		
		int y = p_center.getY();
		y++;
		p_center.setY(y);
		*/
	}
	
		public static void main(String[] args) {
		new MoveTest();
	}

}

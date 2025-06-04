package com.sinse.threadapp.ani;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//사각형을 이동시켜보자. (지우고 그리는 작업을 연속적으로 시도해보자)
public class RectMove extends JFrame{
	
	JButton bt;
	JPanel p_north;
	JPanel p_center;
	
	
	
	public RectMove() {
		bt = new JButton("동작");
		p_north = new JPanel();
		p_center = new JPanel();
		
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args) {
	
		new RectMove();
	}
}

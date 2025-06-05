package com.sinse.threadapp.ani;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ThreadPractice extends JFrame{
	
	JPanel p_north;
	JPanel p_center;
	JButton bt;
	JTextArea area_left;
	JTextArea area_right;
	
	ThreadP t1;
	ThreadP t2;
	
	public ThreadPractice() {
		p_north = new JPanel();
		p_center = new JPanel();
		bt = new JButton("시작");
		area_left = new JTextArea();
		area_right = new JTextArea();
		t1 = new ThreadP(area_left,1);
		t2 = new ThreadP(area_right,2);
		
		//스타일
		
		area_left.setFont(new Font(null, Font.PLAIN, 100));
		area_right.setFont(new Font(null, Font.PLAIN, 100));
		
		//조립
		p_north.setPreferredSize(new Dimension(800,200));
		
		p_center.setPreferredSize(new Dimension(800,600));
		
		Dimension d = new Dimension(200,200);
		area_left.setPreferredSize(d);
		area_right.setPreferredSize(d);
		
		p_north.add(bt);
		p_center.add(area_left);
		p_center.add(area_right);
		
		p_center.setLayout(new FlowLayout());
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t1.start();
				t2.start();
				
			}
		});
		
		setSize(800,800);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ThreadPractice();
	}
	
}

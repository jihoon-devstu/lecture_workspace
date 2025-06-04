package com.sinse.threadapp.ani;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressTest extends JFrame {

	JProgressBar bar1;
	JProgressBar bar2;
	JProgressBar bar3;
	JButton bt;
	int n;
	ThreadA thread1;
	ThreadA thread2;
	ThreadA thread3;
	
	boolean flag = false;

	public ProgressTest() {

		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		bt = new JButton("진행");

		thread1 = new ThreadA();
		thread2 = new ThreadA();
		thread3 = new ThreadA();

		Dimension d = new Dimension(750, 45);
		bar1.setPreferredSize(d);
		bar2.setPreferredSize(d);
		bar3.setPreferredSize(d);

		setLayout(new FlowLayout());

		add(bar1);
		add(bar2);
		add(bar3);
		add(bt);

		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread.start();
			}
		});

		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void move(JProgressBar bar) {
		// bar1 증가
		n += 2;
		bar.setValue(n);
	}

	public static void main(String[] args) {
		new ProgressTest();
	}
}

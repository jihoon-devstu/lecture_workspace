package com.sinse.threadapp.ani;

import javax.swing.JTextArea;

public class ThreadP extends Thread{
	JTextArea area;
	 int count;
	 int n;
	 
	public ThreadP(JTextArea area , int count) {
		this.area = area;
		this.count = count;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
				 area.setText(""+n);
				n = count+n;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

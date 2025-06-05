package com.sinse.threadapp.ani;

import javax.swing.JProgressBar;

public class ThreadA extends Thread{
	
	int n ;
	JProgressBar bar;
	int velX;
	
	public ThreadA(JProgressBar bar, int velX) {
		this.bar=bar;
		this.velX = velX;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				n = n+velX;
				bar.setValue(n);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

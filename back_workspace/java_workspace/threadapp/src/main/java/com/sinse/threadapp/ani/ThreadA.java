package com.sinse.threadapp.ani;

import javax.swing.JProgressBar;

public class ThreadA extends Thread{
	int n ;
	JProgressBar bar;
	public ThreadA(JProgressBar bar) {
		this.bar=bar;
	}
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				n += 2;
				bar.setValue(n);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

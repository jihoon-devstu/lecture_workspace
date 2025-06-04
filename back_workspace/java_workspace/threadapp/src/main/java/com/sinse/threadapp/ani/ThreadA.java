package com.sinse.threadapp.ani;

public class ThreadA extends Thread{

	ProgressTest p = new ProgressTest();
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				p.move();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

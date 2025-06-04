package com.sinse.threadapp;

public class ThreadTestB extends Thread{
	public void run() {
		for(int i = 1; i<=50;i++) {
	    	System.out.println( "B" );
	    }
    }
}
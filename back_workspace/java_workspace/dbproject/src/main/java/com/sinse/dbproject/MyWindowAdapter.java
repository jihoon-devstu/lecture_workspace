package com.sinse.dbproject;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowAdapter extends WindowAdapter{
	
	public void windowClosing(WindowEvent e) {
		System.out.println("창 닫았어?");
	}

}

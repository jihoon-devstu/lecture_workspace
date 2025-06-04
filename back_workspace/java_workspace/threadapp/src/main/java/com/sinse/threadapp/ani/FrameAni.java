package com.sinse.threadapp.ani;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class FrameAni extends JFrame{
	JPanel p_center;
	ImgUtil imgUtil = new ImgUtil();
	Image image;
	Image[] imgArray = new Image[18];
	int n;
	Thread thread;
	
	public FrameAni() {
		createImage();
		
		p_center = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(imgArray[n], 100, 100, 300,300, p_center);
			}
		};
		
		thread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(30);
						moveImage();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		
		add(p_center);
		
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		thread.start();
	}

	
	public void createImage() {
		for(int i=0;i<18;i++) {
			image = imgUtil.getImage("hero/image"+(i+1)+".png",300,300);
			imgArray[i] = image;
		}
	}
	
	public void moveImage() {
		n++;
		if(n>=imgArray.length) {
			n=1;
			
		}
		p_center.repaint();
	}
	
	public static void main(String[] args) {
		new FrameAni();
	}
}

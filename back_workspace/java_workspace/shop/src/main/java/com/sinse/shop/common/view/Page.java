package com.sinse.shop.common.view;

import javax.swing.JPanel;

import com.sinse.shop.AppMain;

//쇼핑몰의 모든 페이지의 최상위 객체 --> JPanel 상속
public class Page extends JPanel{

	AppMain appMain;
	public Page(AppMain appMain) {
		this.appMain=appMain;
		
		setVisible(false);
		
	}
	
}

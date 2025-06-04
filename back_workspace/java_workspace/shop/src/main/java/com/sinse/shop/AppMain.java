package com.sinse.shop;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppMain extends JFrame{
	
	//최상단 유틸리티 네비 영역 (서브메뉴)
	JPanel p_util;
	
	//메인 네비게이션 영역
	JPanel p_navi;
	
	//컨테이너 영역
	JPanel p_container;
	
	public AppMain() {
		//생성_유틸리티 영역
		p_util = new JPanel();
		
		
		//생성_네비게이션 영역
		p_navi = new JPanel();
		
		
		//생성_컨테이너 영역
		p_container = new JPanel();
		
		
		
		//스타일
		p_util.setBackground(Color.YELLOW);
		p_navi.setBackground(Color.PINK);
		p_container.setBackground(Color.GREEN);
		
		p_util.setPreferredSize(new Dimension(1400,40));
		p_navi.setPreferredSize(new Dimension(1400,50));
		p_container.setPreferredSize(new Dimension(1400,610));
		
		//조립
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);//DB연동 후엔 지우기 ! 
		setBounds(300,50,1400,900);
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new AppMain();
	}

}

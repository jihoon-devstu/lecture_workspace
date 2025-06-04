package com.sinse.shop.home;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.view.Page;

public class MainPage extends Page {
	JPanel p_visual; // 메인 비쥬얼 영역 (메인 배너 영역 - carousel)
	JPanel p_content; // 상품이 출력될 영역

	public MainPage() {
		//생성
		p_visual = new JPanel();
		p_content = new JPanel();
		
		//스타일
		p_visual.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH,Config.MAIN_VISUAL_HEIGHT));
		p_content.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH,410));
		
		p_visual.setBackground(Color.CYAN);
		p_content.setBackground(Color.PINK);
		
		//조립
		add(p_visual);
		add(p_content);
		
		setVisible(true);
	}
}

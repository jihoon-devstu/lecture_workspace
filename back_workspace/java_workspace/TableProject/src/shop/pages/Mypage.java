package shop.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mypage extends JPanel{
	
	JLabel la_title;
	
	public Mypage() {
		la_title = new JLabel("나의페이지 에요.");
		la_title.setFont(new Font("굴림", Font.BOLD, 30));
		
		add(la_title);
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(800,650));
		setVisible(false);
	}

}

package shop.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

//쇼핑몰의 메인 페이지
public class Home extends JPanel{
	JLabel la_title;
	
	public Home() {
		la_title = new JLabel("쇼핑몰의 메인 페이지에요");
		la_title.setFont(new Font("굴림", Font.BOLD, 30));
		
		add(la_title);
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(800,650));
		setVisible(false);
	}
}

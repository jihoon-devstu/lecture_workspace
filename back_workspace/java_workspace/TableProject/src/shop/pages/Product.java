package shop.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Product extends JPanel{

	JLabel la_title;
	
	public Product() {
		la_title = new JLabel("상품들이 나오는 페이지에요");
		la_title.setFont(new Font("굴림", Font.BOLD, 30));
		
		add(la_title);
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(800,650));
		setVisible(false);
	}
	
}

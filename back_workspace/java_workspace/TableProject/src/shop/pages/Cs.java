package shop.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cs extends JPanel{

	JLabel la_title;
	
	public Cs() {
		la_title = new JLabel("Cs 페이지에요");
		la_title.setFont(new Font("굴림", Font.BOLD, 30));
		
		add(la_title);
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(800,650));
		setVisible(false);
	}
	
}

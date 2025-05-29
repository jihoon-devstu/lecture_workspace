package shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShopMain extends JFrame{
	
	JPanel p_north ; //북쪽 패널
	JButton bt_home;
	JButton bt_product;
	JButton bt_mypage;
	JButton bt_cs;
	
	JPanel p_center;
	
	public ShopMain() {
		//아이콘 얻기
		Class myClass = getClass();
		
		//패키지 안에 들어있는 자원의 이름을 명시하면 , URL을 반환해줌
		URL url = myClass.getClassLoader().getResource("cart.png");
		ImageIcon icon = new ImageIcon(url);
		
		BufferedImage buffrImg;
		
		//생성
		p_north = new JPanel();
		
		bt_home = new JButton();
		bt_product = new JButton();
		bt_mypage = new JButton();
		bt_cs = new JButton();
		
		p_center = new JPanel();
			
		//스타일
		p_north.setPreferredSize(new Dimension(800,50));
		p_north.setBackground(Color.YELLOW);
		
		
		//조립
		p_north.add(bt_home);
		p_north.add(bt_product);
		p_north.add(bt_mypage);
		p_north.add(bt_cs);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		
		
		setSize(800,650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);;
	}

	public static void main(String[] args) {
		new ShopMain();

	}

}

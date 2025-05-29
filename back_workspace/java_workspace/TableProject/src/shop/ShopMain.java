package shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shop.pages.Cs;
import shop.pages.Home;
import shop.pages.Mypage;
import shop.pages.Product;

public class ShopMain extends JFrame implements ActionListener{
	
	JPanel p_north ; //북쪽 패널
	JButton bt_home;
	JButton bt_product;
	JButton bt_mypage;
	JButton bt_cs;
	
	JPanel p_center;
	
	ImageUtil imageUtil;
	
	/*쇼핑몰을 구성하느 모든 페이지를 보유한다*/
	
	Home home;
	Product product;
	Mypage mypage;
	Cs cs;
	
	
	public ShopMain() {

		imageUtil = new ImageUtil();
		//생성
		p_north = new JPanel();
		
		bt_home = new JButton(imageUtil.getIcon("home.png", 35, 30));
		bt_product = new JButton(imageUtil.getIcon("cart.png", 35, 30));
		bt_mypage = new JButton(imageUtil.getIcon("update.png", 35, 30));
		bt_cs = new JButton(imageUtil.getIcon("menu.png", 35, 30));
		
		p_center = new JPanel();
		
		home = new Home();
		product = new Product();
		mypage = new Mypage();
		cs = new Cs();
			
		//스타일
		p_north.setPreferredSize(new Dimension(800,50));
		p_north.setBackground(Color.YELLOW);
		
		Dimension d = new Dimension(40,35);
		bt_home.setPreferredSize(d);
		bt_product.setPreferredSize(d);
		bt_mypage.setPreferredSize(d);
		bt_cs.setPreferredSize(d);
		
		//조립
		p_north.add(bt_home);
		p_north.add(bt_product);
		p_north.add(bt_mypage);
		p_north.add(bt_cs);
		
		p_center.add(home); //메인 페이지 
		p_center.add(product);
		p_center.add(mypage);
		p_center.add(cs);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt_home.addActionListener(this);
		
		setSize(800,650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);;
	}
	
	//원하는 페이지만 보여지게 처리하는 메서드
	public void showHide() {
		home.setVisible(true);
		product.setVisible(false);
		mypage.setVisible(false);
		cs.setVisible(false);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		showHide();
		
	}
	

	public static void main(String[] args) {
		new ShopMain();

	}



}

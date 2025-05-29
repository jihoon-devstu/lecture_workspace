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
import shop.pages.Page;
import shop.pages.Product;

public class ShopMain extends JFrame implements ActionListener{
	
	JPanel p_north ; //북쪽 패널
	JButton bt_home;
	JButton bt_product;
	JButton bt_mypage;
	JButton bt_cs;
	
	JPanel p_center;
	
	ImageUtil imageUtil;
	
	Page[] pageArray = new Page[4];
	
	public static final int HOME = 0;
	public static final int PRODUCT = 1;
	public static final int MYPAGE = 2;
	public static final int CS = 3;
	
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
		
		//개발자가 버튼에 추가적인 값을 심을 수 있다..
		bt_home.putClientProperty("id", 0);
		bt_product.putClientProperty("id", 1);
		bt_mypage.putClientProperty("id", 2);
		bt_cs.putClientProperty("id", 3);
		
		p_center = new JPanel();
		
		pageArray[0] = new Home();
		pageArray[1] = new Product();
		pageArray[2] = new Mypage();
		pageArray[3] = new Cs();
			
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
		
		for(int i = 0;i<pageArray.length;i++) {
			p_center.add(pageArray[i]); //페이지를 센터 패널에 부착.
		}
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt_home.addActionListener(this);
		bt_product.addActionListener(this);
		bt_mypage.addActionListener(this);
		bt_cs.addActionListener(this);
		
		setSize(800,650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);;
	}
	
	//원하는 페이지만 보여지게 처리하는 메서드
	//눈 뜨기를 원하는 페이지의 인덱스를 호출시 결정.
	//showPage(2)
	public void showPage(int target) {
		
		for(int i=0;i<pageArray.length;i++) {
			pageArray[i].setVisible((i==target)? true : false);
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton obj = (JButton)e.getSource();
		
		int id = (int)obj.getClientProperty("id");
		
		showPage(id);
		
	}
	

	public static void main(String[] args) {
		new ShopMain();

	}


}

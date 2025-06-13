package EoneIfour.wms.shop;

import javax.swing.*;
import java.awt.*;

public class AppMainTest extends JFrame{
	
	JPanel p_navi;
	JPanel p_account;
	JPanel p_util;
	JPanel p_content;
	
	JTextArea area;
	JButton bt_login;
	JButton bt_logout;
	
	JButton bt_menu1;
	JButton bt_menu2;
	JButton bt_menu3;
	JButton bt_menu4;
	JButton bt_menu5;
	JButton bt_menu6;
	
	public AppMainTest() {
		setLayout(new BorderLayout());

		//생성
		p_navi = new JPanel();
		p_account = new JPanel();
		p_util = new JPanel();
		p_content = new JPanel();
		
		area = new JTextArea("OOO님 안녕하세요");
		bt_login = new JButton("로그인");
		bt_logout = new JButton("로그아웃");
		
		bt_menu1 = new JButton("메뉴1");
		bt_menu2 = new JButton("메뉴2");
		bt_menu3 = new JButton("메뉴3");
		bt_menu4 = new JButton("메뉴4");
		bt_menu5 = new JButton("메뉴5");
		bt_menu6 = new JButton("메뉴6");
		
		//스타일
		Color orange = new Color(255 , 204 , 64);
		
		p_navi.setBackground(orange);
		p_navi.setPreferredSize(new Dimension(1280,60));
		p_navi.setLayout(new BorderLayout());
		
		area.setEditable(false);
		area.setFont(new Font("맑은 고딕", Font.BOLD , 15));
		area.setForeground(Color.DARK_GRAY);
		area.setBackground(orange);
		
		p_account.setBackground(orange);
		
		bt_login.setBackground(orange);
		bt_login.setForeground(Color.DARK_GRAY);
		bt_logout.setBackground(orange);
		bt_logout.setForeground(Color.DARK_GRAY);
		
		p_util.setBackground(new Color(255, 228, 196));
		p_util.setLayout(new FlowLayout(FlowLayout.CENTER));
		p_util.setPreferredSize(new Dimension(1280,80));
		
		p_content.setPreferredSize(new Dimension(1280,660));
		//조립
		p_navi.add(area, BorderLayout.WEST);
		p_account.add(bt_login);
		p_account.add(bt_logout);
		p_navi.add(p_account, BorderLayout.EAST);
		
		p_util.add(bt_menu1);
		p_util.add(bt_menu2);
		p_util.add(bt_menu3);
		p_util.add(bt_menu4);
		p_util.add(bt_menu5);
		p_util.add(bt_menu6);
		
		add(p_navi, BorderLayout.NORTH);
		add(p_util, BorderLayout.CENTER);
		add(p_content, BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(1280,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	
	public static void main(String[] args) {
		new AppMainTest();
	}

}

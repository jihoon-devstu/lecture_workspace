package com.sinse.shopadmin.security;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame{

	JLabel la_id;
	JLabel la_pwd;
	
	JTextField t_id;
	JPasswordField t_pwd;
	
	JButton bt_login;
	JButton bt_join;
	
	public LoginForm() {
		la_id = new JLabel("ID");
		la_pwd = new JLabel("PassWord");
		
		t_id = new JTextField();
		t_pwd = new JPasswordField();
		
		bt_login = new JButton("Login");
		bt_join = new JButton("Join");
		
		
		//스타일
		Dimension d = new Dimension(110,30);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pwd.setPreferredSize(d);
		t_pwd.setPreferredSize(d);
		
		//조립
		this.setLayout(new FlowLayout());
		add(la_id);
		add(t_id);
		add(la_pwd);
		add(t_pwd);
		add(bt_login);
		add(bt_join);

		setBounds(300,100,270,145);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new LoginForm();

	}

}

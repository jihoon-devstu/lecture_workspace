package com.sinse.shopadmin.member.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.view.Page;

public class MemberJoin extends Page {
	JLabel la_id;
	JLabel la_pwd;
	JLabel la_name;
	JLabel la_email;

	JTextField t_id;
	JPasswordField t_pwd;
	JTextField t_name;
	JTextField t_email;

	JButton bt_login;
	JButton bt_join;

	public MemberJoin(AppMain appMain) {
		super(appMain);

		la_id = new JLabel("ID");
		la_pwd = new JLabel("pwd");
		la_name = new JLabel("name");
		la_email = new JLabel("email");

		t_id = new JTextField();
		t_pwd = new JPasswordField();
		t_name = new JTextField();
		t_email = new JTextField();

		bt_login = new JButton("로그인");
		bt_join = new JButton("회원가입");

		Dimension d = new Dimension(200, 35);

		la_id.setPreferredSize(d);
		la_pwd.setPreferredSize(d);
		la_name.setPreferredSize(d);
		la_email.setPreferredSize(d);

		t_id.setPreferredSize(d);
		t_pwd.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_email.setPreferredSize(d);

		this.setPreferredSize(new Dimension(450, 300));

		add(la_id);
		add(t_id);
		add(la_pwd);
		add(t_pwd);
		add(la_name);
		add(t_name);
		add(la_email);
		add(t_email);
		add(bt_login);
		add(bt_join);

		// 가입 버튼에 리스너 연결
		bt_join.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				regist();

			}
		});

	}

	public void regist() {
		// 데이터 베이스에 넣기 전에 , 폼 양식을 제대로 작성했는지 여부를 체크해보자.
		// 유효성 체크

		if (t_id.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "ID를 입력하세요");
		} else if (t_pwd.getPassword().length < 1) {
			JOptionPane.showMessageDialog(this, "password를 입력하세요");

		} else if (t_name.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "Name을 입력하세요");

		} else if (t_email.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "Email을 입력하세요");

		}else {
			//mysql에 insert
			String sql = "insert into admin(id, pwd, name, email) values(?,?,?,?)";
			PreparedStatement pstmt  = null;
			try {
				pstmt = appMain.con.prepareStatement(sql);
				pstmt.setString(1,t_id.getText());
				//pstmt.setString(2,t_pwd.getPassword());
				pstmt.setString(3,t_name.getText());
				pstmt.setString(4,t_email.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

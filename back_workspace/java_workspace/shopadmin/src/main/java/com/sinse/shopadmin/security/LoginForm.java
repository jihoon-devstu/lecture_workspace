package com.sinse.shopadmin.security;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.security.model.Admin;

public class LoginForm extends Page {

	JLabel la_id;
	JLabel la_pwd;

	JTextField t_id;
	JPasswordField t_pwd;

	JButton bt_login;
	JButton bt_join;


	public LoginForm(AppMain appMain) {
		super(appMain);
		la_id = new JLabel("ID");
		la_pwd = new JLabel("PassWord");

		t_id = new JTextField();
		t_pwd = new JPasswordField();

		bt_login = new JButton("Login");
		bt_join = new JButton("Join");

		// 스타일
		Dimension d = new Dimension(110, 30);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pwd.setPreferredSize(d);
		t_pwd.setPreferredSize(d);

		// 조립
		this.setLayout(new FlowLayout());
		add(la_id);
		add(t_id);
		add(la_pwd);
		add(t_pwd);
		add(bt_login);
		add(bt_join);

		bt_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginCheck();

			}
		});
		
		bt_join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appMain.showPage(Config.JOIN_PAGE);
			}
		});

		setPreferredSize(new Dimension(270,145));

	}


	public void loginCheck() {
		String id = t_id.getText(); // 일반 텍스트 컴포넌트의 스트링 값 얻기.
		String pwd = new String(t_pwd.getPassword());

		String sql = "select *from admin where id=? and pwd =? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = appMain.con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			rs = pstmt.executeQuery(); // select문은 표를 반환한다...

			if (rs.next()) { // 한칸 전진 후 true가 반환 된다면... 일치하는 데이터가 있다면 !
								// 로그인 성공.
				JOptionPane.showMessageDialog(this, "로그인 성공");
				
				//현재 유저가 보고 있는 페이지가 MainPage로 교체.
				
			

				Admin admin = new Admin();
				admin.setAdmin_id(rs.getInt("admin_id"));
				admin.setId(rs.getString("id"));
				admin.setPwd(rs.getString("pwd"));
				admin.setName(rs.getString("name"));
				//AppMain이 보유하고 있는 Admin 모델 객체의 현재 null인 값을 
				//위에서 생성한 admin으로 대체
				appMain.admin=admin;
				
				appMain.showPage(Config.MAIN_PAGE);

			} else {
				JOptionPane.showMessageDialog(this, "로그인 실패");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}

package com.sinse.shopadmin.security;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.security.model.Admin;

public class LoginForm extends JFrame{

	JLabel la_id;
	JLabel la_pwd;
	
	JTextField t_id;
	JPasswordField t_pwd;
	
	JButton bt_login;
	JButton bt_join;
	
	Connection con;
	Admin admin;
	
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

		connect();
		
		bt_login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				loginCheck();
				
			}
		});
		
		setBounds(300,100,270,145);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void connect() {
		String url = "jdbc:mysql://localhost:3306/shop";
		String user = "shop";
		String pass = "1234";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			
			if(con !=null) {
				this.setTitle("접속 중");
			}else {
				this.setTitle("접속 에러");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loginCheck() {
		String id = t_id.getText(); //일반 텍스트 컴포넌트의 스트링 값 얻기.
		String pwd = new String( t_pwd.getPassword());
		
		String sql = "select *from admin where id=? and pwd =? ";
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery(); //select문은 표를 반환한다...
			
			if(rs.next()) { //한칸 전진 후 true가 반환 된다면... 일치하는 데이터가 있다면 ! 
									//로그인 성공.
				JOptionPane.showMessageDialog(this, "로그인 성공");
				
				Admin admin = new Admin(); //empty 상태의 객체 생성		
				admin.setAdmin_id(rs.getInt("admin_id"));
				admin.setId(rs.getString("id"));
				admin.setPwd(rs.getString("pwd"));
				admin.setName(rs.getString("name"));
				
				AppMain appMain = new AppMain(con ,admin);
				this.setVisible(false); //자기 자신은 안보여야함.
				
			}else{
				JOptionPane.showMessageDialog(this, "로그인 실패");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new LoginForm();

	}

}

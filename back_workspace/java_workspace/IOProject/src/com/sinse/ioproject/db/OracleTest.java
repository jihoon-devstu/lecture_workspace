package com.sinse.ioproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleTest {
	
	
	public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			//원격지의 오라클에 접속 !! 		
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","java","1234");
			if(con !=null) {
				System.out.println("접속성공");
			}else {
				System.out.println("접속 실패");
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

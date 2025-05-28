package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/dev";
		String user="java";
		String pass="1234";
		Connection con = null; // finally에서 닫기 위해서는 try의 지역변수이면 안됨.
		PreparedStatement pstmt = null; //query 수행 객체 , 쿼리문 마다 1:1 대응 됨.
											//3개의 쿼리를 수행할 경우 , 3개 만듦
		ResultSet rs = null;
		
		//select version();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Load Success !!");
			
			//접속하기
			//다른 언어와 달리 Connection 객체는 접속이 성공되면 , 메모리에 올라오는 결과물.
			//즉 접속 정보를 가진 객체임.
			con = DriverManager.getConnection(url,user,pass);
			if(con != null) {
				System.out.println("접속성공");
				
				String sql = "select* from emp";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
						
					String ename = rs.getString("ename");
					int sal = rs.getInt("sal");
					String job = rs.getString("job");
					String hiredate = rs.getString("hiredate");
					System.out.println("ename = "+ename+", sal= "+sal+", job= "+job+", hiredate= "+hiredate);
				}
						
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(rs !=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
				
			if(con !=null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

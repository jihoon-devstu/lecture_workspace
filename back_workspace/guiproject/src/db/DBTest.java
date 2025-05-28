package db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

class  DBTest
{
	public static void main(String[] args) 
	{
		PreparedStatement pstmt=null;
		Connection con=null;
		String mysqlDriver="com.mysql.jdbc.Driver";
		String oracleDriver="oracle.jdbc.driver.OracleDriver";
		String mysqlUrl =  "jdbc:mysql://localhost:3306/dev";
		String mysqlId = "root";
		String mysqlPass = "1234";
		String oracleUrl =  "jdbc:oracle:thin:@localhost:1521:XE";
		String oracleId = "java";
		String oraclePass = "1234";

		
		try{
		//1단계) 드라이버 로드
		Class.forName(oracleDriver);
		System.out.println("드라이브 로드 성공");
		//2단계) 접속
		String url=oracleUrl;
		String id=oracleId;
		String pass=oraclePass;

		
		//Connection 이란? 접속 성공 후, 그 접속 정보를 가진 인터페이스
		//이 객체가 null이면 접속 실패임
		con=DriverManager.getConnection(url, id, pass);
		if(con == null){
			System.out.println("접속 실패 ㅜㅜ");
		}else{
			System.out.println("접속 성공 ^^");
			
			//접속이 성공된 이후, 원격지의 db서버에 sql문을 네트워크 통해 전송
			
			String sql="insert into member3(member3_id,id,pwd,email)";
			sql=sql+"values(seq_member3.nextval,'james', '3333', 'jjj@gmail.com')";
			//JDBC = Java DataBase Connectivity = 자바의 데이터베이스 연동 기술 및 지원 패키지 (java.sql 패키지에서...)
			
			pstmt = con.prepareStatement(sql);
			//준비된 문장으로 쿼리 실행
			int result = pstmt.executeUpdate(); //DML 수행시 이 메서드 사용해야함
			
			if(result>0){
				System.out.println("등록 성공");
			}else{
				System.out.println("등록 실패");
			}
		}
		
		}catch(ClassNotFoundException e){
			System.out.println("드라이브명을 확인해주세요");	
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
					try{
						pstmt.close();
				}catch(SQLException e){
				e.printStackTrace();
				}
					}
				
					if(con!=null){
					try{
						con.close();
				}catch(SQLException e){
				e.printStackTrace();
			}
			}
		}
	}
}


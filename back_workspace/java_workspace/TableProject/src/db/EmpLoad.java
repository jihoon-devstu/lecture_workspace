package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//EMP 테이블에 있는 레코드를 모두 가져와서 JTalbe에 출력

public class EmpLoad extends JFrame{
	
	JTable table;
	JScrollPane scroll;
	
	String[][] data; //몇 건인지 알 수 없으므로 , 배열을 아직 생성 못함. 따라서 null
	String[] columns = {
			"empno","ename","job","mgr","hiredate","sal","comm","deptno"
	};
	
	public void loadData() {
		String url="jdbc:mysql://localhost:3306/dev";
		String user="java";
		String pass="1234";
		
		Connection con = null; //접속 시도가 아니라 , 접속 후 그 정보를 가지고 있는 객체. 따라서 이 객체를 이용하여 접속 종료 가능.
		PreparedStatement pstmt = null; // 쿼리문 수행 객체
		ResultSet rs = null; //표 데이터를 표현한 객체
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Load Success !!");
			
			con = DriverManager.getConnection(url,user,pass);
			
			if(con !=null) {
				System.out.println("접속 성공!");
				
				String sql = "select *from emp order by empno asc";
				pstmt = con.prepareStatement(sql
						, ResultSet.TYPE_SCROLL_INSENSITIVE
						, ResultSet.CONCUR_READ_ONLY); //쿼리 수행 객체 생성
				
				rs = pstmt.executeQuery(sql); //select 문은 ResultSet으로 결과를 반환
				
				//rs가 데이터 베이스 연동 결과이므로 , 바로 이 시점이후부터 배열의 크기를 결정지을 수 있음.
				
				rs.last();
				
				int total = rs.getRow();
				
				
				data = new String[total][8];
				
				//아래의 코드를 반복문으로 처리하면서 층수 변경..
				//rs.next() 커서도 한칸씩 이동...
				// 주의 현재 총 레코드 수를 얻어오는 바람에 커서는 제일 아래 가있음.
				//따라서 커서를 first , beforefirst 도 있음. rs.beforeFirst() 메서드로 커서의 위치를 다시 원상복구 시킨 후 반복문.
				
				rs.beforeFirst();
				
				for(int i = 0 ; i<total;i++) {
					rs.next();
					data[i][0]=rs.getString("empno"); //empno
					data[i][1]=rs.getString("ename"); //ename
					data[i][2]=rs.getString("job"); //job
					data[i][3]=rs.getString("mgr"); //mgr
					data[i][4]=rs.getString("hiredate"); //hiredate
					data[i][5]=rs.getString("sal"); //sal
					data[i][6]=rs.getString("comm"); //comm
					data[i][7]=rs.getString("deptno"); //deptno	
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
	
	public EmpLoad() {
		//mysql에서 이미 사원정보 가져왔어야 함.
		loadData();
		
		table = new JTable(data,columns); //row , column
		scroll = new JScrollPane(table);
		
		add(scroll);
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new EmpLoad();
	}

}

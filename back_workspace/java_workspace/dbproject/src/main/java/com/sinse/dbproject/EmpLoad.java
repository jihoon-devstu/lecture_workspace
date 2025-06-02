package com.sinse.dbproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*JTable은 껍데기에 불과하므로, 연동할 테이블이 수백개라 할지라도
 * TableModel은 1개만 충분하다..결국 바뀌는 건 쿼리문만...

 WindowListener를 포함하여, 이벤트 리스너 중 재정의할 메서드의 수가 너무 많은경우
 사용하지도 않는 부모의 메서드를 클래스 코드 안에 남겨놔야 하는 상황이 발생하게 됨...
 
 * */

public class EmpLoad extends JFrame {
	JPanel p_north;
	JButton bt_emp;
	JButton bt_dept;
	JButton bt_excel;

	DataModel model; // JTable에 바라보는 Model 객체
	JTable table;
	JScrollPane scroll;

	// 윈도우 프레임이 뜰때 한번 접속하고, 윈도우 닫을때 데이터베이스 닫자
	String url = "jdbc:mysql://localhost:3306/dev";
	String user = "root";
	String pwd = "1234";
	Connection con; // 윈도우 창 닫으면 접속을 끊어야 하므로, 멤버변수로 빼두어야 함
					// Connection은 접속 정보를 가진 객체이므로, 접속을 끊을 수도 있다
	
	JFileChooser chooser = new JFileChooser();

	public EmpLoad() {
		p_north = new JPanel();
		bt_emp = new JButton("사원테이블 로드");
		bt_dept = new JButton("부서테이블 로드");
		bt_excel = new JButton("엑셀 파일 로드");

		table = new JTable(); // 테이블과 모델 연결은 반드시 생성자에서만 할 수 있는 건 아니다...
		scroll = new JScrollPane(table);

		// style
		p_north.setPreferredSize(new Dimension(800, 30));

		// assemble
		p_north.add(bt_emp);
		p_north.add(bt_dept);
		p_north.add(bt_excel);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		// 이벤트 구현 시 정의되는 리스너 클래스는 재사용성이 없으므로, 굳이
		// .java 파일까지 정의해가면서 개발할 필요가 있는가?
		// 내부클래스 중, 이름 없는 클래스를 가리켜 익명내부 클래스라 한다.
		// 주로, 일회성 객체 사용시(이벤트)
		// 익명 내부클래스는, 자신을 감싸고 있는 바깥쪽 외부 클래스의 멤버들을
		// 같이 사용할 수 있다..즉 접근할 수 있다는 점이 장점
		bt_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable("select *from emp");
			}
		});

		bt_dept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable("select *from dept");
			}
		});
		
		bt_excel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = chooser.showOpenDialog(EmpLoad.this);
				if(result == JFileChooser.APPROVE_OPTION) { //파일 열기를 수락했다면
					loadExcel();
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("창 닫았어?");

				if (con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		connect();// db접속

		setSize(800, 630);
		setVisible(true);
	}

	// 데이터베이스 접속
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			if (con != null) {
				this.setTitle("접속 성공");
			} else {
				this.setTitle("접속 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 사원 테이블 가져오기
	public void loadTable(String sql) {

		// 쿼리 수행 객체 생성
		PreparedStatement pstmt = null;
		// 결과 표를 표현한 객체
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // pstmt
																												// 객체 생성

			// 표 반환... 커서의 초기 위치는 첫번째 레코드보다 더 위쪽
			// 현재의 rs 자체는 JTable이 이해할 수 없는 상태 이므로 , TableModel에 rs를 가공하여 넣어주면 된다.
			rs = pstmt.executeQuery();

			// 현재 select 문의 대상이 되는 table의 컬럼 정보를
			// 프로그래밍에서 얻어오려면 ResultSetMetaData라는 객체를 이용하면 된다.
			ResultSetMetaData metaData = pstmt.getMetaData();
			// rs는 몇층까지일까?
			rs.last();

			int total = rs.getRow(); // 총 레코드 수
			int colCount = metaData.getColumnCount(); // 총 컬럼 수

			// 레코드 수와 컬럼수를 알아냈으니 , 모델 객체가 보유한 현재 null인 상태인
			// 2차원 배열을 메모리에 올리자.
			model = new DataModel();
			model.data = new String[total][colCount];
			model.title = new String[colCount]; // 컬럼 배열 생성

			for (int i = 0; i < colCount; i++) {
				model.title[i] = metaData.getColumnName(i+1);
			}

			// rs의 데이터를 이차원 배열로 옮겨심기
			rs.beforeFirst(); // rs의 커서 원위치

			int index = 0; // 층수를 접근하기 위한 index

			while (rs.next()) {
				// 어떤 테이블인지는 모르나 , 그 테이블의 컬럼수만큼 반복
				for (int i = 0; i < colCount; i++) {
					model.data[index][i] = rs.getString(i+1);
				}
				index++;
			}

			// 모든 데이터가 완성 되었으므로 , JTable에 모델을 동적으로 적용하자.

			table.setModel(model);
			table.updateUI();

		} catch (SQLException e) {
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
	
	public void loadExcel() {
		
	}

	public static void main(String[] args) {
		new EmpLoad();
	}

}
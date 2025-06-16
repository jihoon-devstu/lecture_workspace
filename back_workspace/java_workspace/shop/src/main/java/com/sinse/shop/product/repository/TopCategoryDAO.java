package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.TopCategory;

/*
 * javaEE(엔터프라이즈) 개발에서는 , 개발자들 간 협업을 위해 , 정해진 규칙에 따라 코드를 작성해야 한다.
 * 특히 DB에서 테이블이 하나 정의되면 , java 개발자는 이 테이블에 대해 1:1 대응하는 모델 객체 + 
 * 이 테이블에 대한 CRUD (C = insert , R = read , U = update , D = Delete) 를 무조건 만든다...
 * 예를 들어 , 협업관계에 있는 같은 팀 프로젝트 구성원인 A,B가 있다고 가정했을 때 , 
 * Product 테이블에 대해서 A가 모델, DAO를 만들면 B는 그 객체를 가져가 사용하면 됨... (중복정의하지 않음)
 * */

//이 코드는 오직 데이터베이스에 관련된 코드만 들어있어야 함. 따라서 Swing을 제어 등의 디자인 코드가
//절대로 들어있으면 안됨. 왜?? 이 객체는 MVC 영역 중 , Model 영역의 즉 로직 객체 영역을 담당하므로.
// View가 섞여 있으면 안됨 !! 
public class TopCategoryDAO {
	//아래의 코드의 동작 시점 ? dbManager는 인스턴스 변수 이므로 , 누군가가 new TopCategoryDAO() 호출 할 때
	//초기화되면서 값이 채워진다 !! 
	DBManager dbManager = DBManager.getInstance();
	
	public TopCategoryDAO() {
		//DBManager 는 싱글턴 패턴으로 정의되어 있으므로 , new할 수 없다 !!
		//dbManager = DBManager.getInstance();
	}
	//TopCategory의 모든 레코드 가져오기
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		con = dbManager.getConnection();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select *from topcategory");
			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			list = new ArrayList();
			//만일 배열로 RS의 데이터를 담게되면 , 배열의 크기를 명시해야 하는 이유로 , rs.last() , getRow()
			//rs.before() 
			//TopCategory[] list = new TopCategory[];
			//Collection framework ? 객체를 모아서 처리하는데 , 유용한 Api 지원하는 패키지
			//모여진 모습은 총 2가지. 
			//순서 있는 모습 (List, Queue) or 순서 없는 모습 (Set, Map)
			
			while(rs.next()) {
			TopCategory topcategory = new TopCategory();
			topcategory.setTopcategory_id(rs.getInt("topcategory_id"));
			topcategory.setTop_name(rs.getString("top_name"));
			list.add(topcategory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
			
		}
		
		return list;
		
	}
	
	//TopCategory의 레코드 한건 가져오기
	public void select() {
		
	}
	
	//한 건 입력
	public void insert() {
		
	}
	
	//한 건 수정
	public void update() {
		
	}
	
	//한 건 삭제
	public void delete() {
		
	}
	
	
	
	
}

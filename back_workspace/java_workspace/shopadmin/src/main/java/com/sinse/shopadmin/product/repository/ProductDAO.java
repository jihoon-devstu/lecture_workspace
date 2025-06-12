package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Product;

//Product 테이블에 대한 CRUD 만을 수행함 - 즉 데이터베이스 작업코드만 작성해야 함...
public class ProductDAO {
	DBManager dbManager = DBManager.getInstance();

	// 1건 등록
	public int insert(Product product) {
		// 상품 입력 폼의 값을 담고있는 product모델 출력
		System.out.println(product.getProduct_name());
		System.out.println(product.getBrand());
		System.out.println(product.getPrice());
		System.out.println(product.getDiscount());
		System.out.println(product.getIntroduce());
		System.out.println(product.getIntroduce());
		System.out.println(product.getDetail());
		System.out.println("SubCategory의 pk는 " + product.getSubCategory().getSubcategory_id());

		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; // 쿼리 실행 성공 여부 결정짓는 변수

		con = dbManager.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("insert into product(product_name, brand,price,discount,introduce,detail,subcategory_id) ");
		sql.append("values(?,?,?,?,?,?,?)");

		try {
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setString(1, product.getProduct_name());
			pstmt.setString(2, product.getBrand());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getDiscount());
			pstmt.setString(5, product.getIntroduce());
			pstmt.setString(6, product.getDetail());
			pstmt.setInt(7, product.getSubCategory().getSubcategory_id());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		return result;
	}

	//방금 수행한 insert에 의해 증가된 pk의 최신값 얻기 !! 
	//나의 세션 내에서 증가된 것만 가져오기 !!!! 
	//mysql = select last_insert_id()
	// 절대 max() 쓰지 말기. -> 다른 유저 계정에 의한 증가값도 반환해버리기 때문에 ! 
	public int selectRecentPk() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pk = 0;
		
		con = dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select last_insert_id() as product_id");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			//쿼리 실행 및 결과 표 반환
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pk = rs.getInt("product_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt , rs);
		}
		
		return pk;
	}
	
	
}












package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Product;

//Product 테이블에 대한 CRUD 만을 수행함 - 즉 데이터베이스 작업코드만 작성해야 함...
public class ProductADO {
	DBManager dbManager = DBManager.getInstance();
	
	//1건 등록
	public int insert(Product product) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; //쿼리 실행 성공 여부 결정짓는 변수
		
		con = dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product(product_name, brand,price,discount,introduce,detail,subcategory_id) ");
		sql.append("values(?,?,?,?,?,?,?)");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, product.getProduct_name());
			pstmt.setString(2, product.getBrand());
			pstmt.setString(3, product.getPrice());
			pstmt.setString(4, product.getDiscount());
			pstmt.setString(5, product.getIntroduce());
			pstmt.setString(6, product.getDetail());
			pstmt.setString(7, product.);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		
		return result;
		
	}

}

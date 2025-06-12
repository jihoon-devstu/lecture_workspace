package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.ProductSize;

public class ProductSizeDAO {

	DBManager dbManager = DBManager.getInstance();
	ProductSize productSize = new ProductSize();
	
	public int insert() {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product_size(product_id,size_id) values(?,?)");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, productSize.getProduct().getProduct_id());
			pstmt.setInt(2, productSize.getSize().getSize_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		
		return result;
	}
	
	
}

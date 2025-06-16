package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.Color;

//다른 로직은 포함하면 안되며 , 오직 Color 테이블에 대한 CRUD만을 수행하는
//쿼리 수행 객체. Data Access Object (쿼리 전담 객체)
public class ColorDAO {
	DBManager dbManager = DBManager.getInstance();

	// Create = Insert

	public int insert(Color color) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into color(color_name) values (?)");

		con = dbManager.getConnection();

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, color.getColor_name());
			result = pstmt.executeUpdate();// DML이 수행되면 , 이 쿼리에 의해 영향을 받은 레코드 등록

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}

		return result;
	}

	// 등록된 모든 색상 가져오기
	public List selectAllColor() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();

		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from color");

		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(); // 표 반환
			// rs 죽이기 전에 rs가 보유한 데이터를 모델 객체로 옮기자 !!!
			// 모델 인스턴스 1건은 레코드 1건

			while (rs.next()) {
				Color color = new Color(); // 리코드 1건을 담는 모델 인스턴스
				color.setColor_id(rs.getInt("color_id"));
				color.setColor_name(rs.getString("color_name"));
				list.add(color);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt, rs);
		}

		return list;
	}

}

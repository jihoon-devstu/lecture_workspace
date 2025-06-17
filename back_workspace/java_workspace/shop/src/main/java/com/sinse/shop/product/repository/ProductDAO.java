package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shop.common.exception.ProductException;
import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.Color;
import com.sinse.shop.product.model.Product;
import com.sinse.shop.product.model.Size;
import com.sinse.shop.product.model.SubCategory;
import com.sinse.shop.product.model.TopCategory;



//Product 테이블에 대한 CRUD 만을 수행함 - 즉 데이터베이스 작업코드만 작성해야 함..
public class ProductDAO {
	DBManager dbManager=DBManager.getInstance();
	
	public void insert(Product product) throws ProductException{
		//상품입력 폼의 값을 담고있는 Product 모델을 출력해보기 
		System.out.println(product.getProduct_name());
		System.out.println(product.getBrand());
		System.out.println(product.getPrice());
		System.out.println(product.getDiscount());
		System.out.println(product.getIntroduce());
		System.out.println(product.getDetail());
		System.out.println("SubCategory의 pk는 "+product.getSubCategory().getSubcategory_id());
		
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0; //쿼리 실행 성공 여부 결정짓는 변수 
		
		con=dbManager.getConnection();
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product(product_name, brand,price,discount,introduce,detail,subcategory_id)");
		sql.append(" values(?,?,?,?,?,?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			
			//모델 객체에 채워진 데이터를 꺼내서, 바인드 변수에 대입하기!! 
			pstmt.setString(1, product.getProduct_name());
			pstmt.setString(2, product.getBrand());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getDiscount());
			pstmt.setString(5, product.getIntroduce());
			pstmt.setString(6, product.getDetail());
			pstmt.setInt(7, product.getSubCategory().getSubcategory_id());
			
			//쿼리수행 
			result = pstmt.executeUpdate(); //DML 실행 0
			if(result==0) {
				throw new ProductException("등록이 되지 않았어요");
			}
		} catch (SQLException e) {
			//e.printStackTrace() 처리만해버리면, 바깥쪽 즉 유저가 사용하는 프로그램에서는 
			//에러의 원인을 알수 없으므로, 신뢰성 떨어짐..따라서 에러가 발생하면, 이 영역에서만 처리를
			//국한시키지 말고, 외부 영역까지ㅇ 에러원인을 전달해야 한다..
			e.printStackTrace();
			throw new ProductException("등록에 실패하였습니다.\n이용에 불편을 드려 죄송합니다", e);
		}finally {
			dbManager.release(pstmt);
		}
	}
	
	//방금 수행한 insert 에 의해 증가된 pk의 최신값 얻기!!
	//나의 세션 내에서 증가된 것만 가져오기!!!(select last_insert_id() 함수 )
	//절대 max()는 사용하면 안됨...다른 유저계정에 의한 증가값도 반환해 버리기 때문에...
	public int selectRecentPk() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int pk=0;
		
		con=dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select last_insert_id() as product_id");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery(); //쿼리실행 및 결과표 반환.
			
			if(rs.next()) { //조회된 결과가 있다면..
				pk=rs.getInt("product_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return pk;
	}
	
	
	//모든 상품 목록 가져오기 (상품+상위+하위)
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Product> list=new ArrayList(); //만일 배열을 쓸 경우 rs는 전방향, 후방향 스크롤까지가능해야 함..
												//배열은 생성 시 크기를 먼저 지정해야 하므로..		
		
		con=dbManager.getConnection();
		StringBuffer sql=new StringBuffer();
		/*
		sql.append("select * from topcategory t, subcategory s, product p");
		sql.append(" where t.topcategory_id=s.topcategory_id and");
		sql.append(" s.subcategory_id=p.subcategory_id");
		*/
		sql.append("select t.topcategory_id,top_name, s.subcategory_id, sub_name ");
		sql.append(",product_id, product_name, brand, price,discount,introduce,detail");
		sql.append(" from topcategory t inner join subcategory s inner join product p");
		sql.append(" on t.topcategory_id=s.topcategory_id and");
		sql.append(" s.subcategory_id=p.subcategory_id");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery(); //쿼리수행 및 표반환 
			
			while(rs.next()) {//레코드 수만큼 커서 이동하면서..
				Product product = new Product(); 
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setDiscount(rs.getInt("discount"));
				product.setIntroduce(rs.getString("introduce"));
				product.setDetail(rs.getString("detail"));
				
				//하위 카테고리와
				SubCategory subCategory=new SubCategory();
				subCategory.setSubcategory_id(rs.getInt("s.subcategory_id"));
				subCategory.setSub_name(rs.getString("sub_name"));
				product.setSubCategory(subCategory);
				
				//상위 카테고리..
				TopCategory topCategory = new TopCategory();
				topCategory.setTopcategory_id(rs.getInt("t.topcategory_id"));
				topCategory.setTop_name(rs.getString("top_name"));
				subCategory.setTopcategory(topCategory);//서브가 탑을 참조해야 하므로, 보유
				
				list.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
	
	//원하는 수 만큼의 최신 상품 가져오기 
	public List selectRecentList(int limit) {
		Connection con=null;

		PreparedStatement pstmt=null;
		ResultSet rs=null;

		PreparedStatement pstmt2=null; //size
		ResultSet rs2=null;
		
		PreparedStatement pstmt3=null; //color
		ResultSet rs3=null;
		
		PreparedStatement pstmt4=null;//filename
		ResultSet rs4=null;
		
		List<Product> list =new ArrayList();
		
		con=dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		//상품과 서브분류 조인 후, 1:多 관계에 있는 product_img, product_size, product_color
		//서브쿼리로 가져오자 최종결과는 모두 Product 모델에 심어져야 함...
		sql.append("select product_id,product_name, brand, price, discount, introduce, detail");
		sql.append(", s.subcategory_id, sub_name");
		sql.append(" from product p inner join subcategory s ");
		sql.append(" on p.subcategory_id=s.subcategory_id ");
		sql.append(" order by product_id desc limit ?");
		
		System.out.println(sql.toString());
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, limit);
			rs=pstmt.executeQuery();//select 실행 및 rs 반환
			
			while(rs.next()) {//상품의 수만큼...
				Product product = new Product(); //empty 상태 
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setDiscount(rs.getInt("discount"));
				product.setIntroduce(rs.getString("introduce"));
				product.setDetail(rs.getString("detail"));
				
				//이 상품이 보유한 사이즈들...(쿼리 수행 pstmt, rs)
				sql.delete(0, sql.length());//스트링 버퍼의 문자열 비우기
				
				sql.append(" select size_name");
				sql.append(" from product_size p inner join size s");
				sql.append(" on p.size_id  = s.size_id");
				sql.append(" and p.product_id=?");
				
				pstmt2=con.prepareStatement(sql.toString());
				pstmt2.setInt(1, product.getProduct_id());
				rs2=pstmt2.executeQuery();
				
				//사이즈들을 Product 모델의 List에 담기!!!
				List sizeList=new ArrayList();
				
				while(rs2.next()) {
					Size size = new Size();
					size.setSize_name(rs2.getString("size_name"));
					sizeList.add(size);
				}
				product.setSizeList(sizeList);
				
				//이 상품이 보유한 색상들....(쿼리 수행  pstmt, rs)
				sql.delete(0, sql.length());//스트링 버퍼의 문자열 비우기
				
				sql.append("select color_name");  
				sql.append(" from product_color p inner join color c");  
				sql.append(" on p.color_id =c.color_id");  
				sql.append(" and p.product_id =?");  
				
				pstmt3=con.prepareStatement(sql.toString());
				pstmt3.setInt(1, product.getProduct_id());
				rs3=pstmt3.executeQuery();
				
				List colorList = new ArrayList();
				while(rs3.next()) {
					Color color =new Color();
					color.setColor_name(rs3.getString("color_name"));
					colorList.add(color);
				}
				product.setColorList(colorList);
				
				//이 상품이 보유한 이미지들...(쿼리 수행  pstmt, rs)
				sql.delete(0, sql.length());
				sql.append("select filename from product_img where product_id=?");
				pstmt4=con.prepareStatement(sql.toString());
				pstmt4.setInt(1, product.getProduct_id());
				rs4=pstmt4.executeQuery();
				
				List filenameList = new ArrayList();
				while(rs4.next()) {
					filenameList.add(rs4.getString("filename"));
				}
				product.setFilenameList(filenameList);
				
				SubCategory subCategory=new SubCategory();
				subCategory.setSubcategory_id(rs.getInt("s.subcategory_id"));
				subCategory.setSub_name(rs.getString("sub_name"));
				
				product.setSubCategory(subCategory); //서브 카테고리 모델을 Product모델에 대입 
				
				list.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbManager.release(pstmt, rs);
			dbManager.release(pstmt2, rs2);
			dbManager.release(pstmt3, rs3);
			dbManager.release(pstmt4, rs4);
		}
		return list;
	}
	
	
}









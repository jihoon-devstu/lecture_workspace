package mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.ProductColor;
import mall.exception.ProductColorException;

@Repository
public class MybatisProductColorDAO implements ProductColorDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(ProductColor productColor) throws ProductColorException{
		int result = sqlSessionTemplate.insert("ProductColor.insert",productColor);
		if(result<1) {
			throw new ProductColorException("상품 컬러 등록 실패"); //실패되었음을 다른 계층에서도 알아야하므로 , 잡으면 안됨.
		}
		
	}

}

package mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.ProductSize;
import mall.exception.ProductSizeException;

@Repository
public class MybatisProductSizeDAO implements ProductSizeDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(ProductSize productSize) throws ProductSizeException{
		int result = sqlSessionTemplate.insert("ProductSize.insert",productSize);
		if(result<1) {
			throw new ProductSizeException("상품 사이즈 등록 실패"); //실패되었음을 다른 계층에서도 알아야하므로 , 잡으면 안됨.
		}
		
	}

}

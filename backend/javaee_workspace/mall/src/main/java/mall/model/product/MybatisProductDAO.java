package mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.Product;
import mall.exception.ProductException;

@Repository
public class MybatisProductDAO implements ProductDAO{
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(Product product) throws ProductException{
		int result = sqlSessionTemplate.insert("Product.insert",product);
		if(result<1) {
			throw new ProductException("상품 등록 실패"); //실패되었음을 다른 계층에서도 알아야하므로 , 잡으면 안됨.
		}
	}

	
}

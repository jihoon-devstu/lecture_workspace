package mall.model.category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mall.domain.TopCategory;

public class MybatisTopCategoryDAO implements TopCategoryDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplage;
	
	@Override
	public List selectAll() {
		
		return sqlSessionTemplage.selectList("TopCategory.selectAll");
	}

	@Override
	public TopCategory select(int topcategory_id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package mall.model.category;

import java.util.List;

import mall.domain.SubCategory;

//컨트롤러가 느슨하게 서비스를 보유하게 하려면(DI) 인터페이스로 정의.
public interface SubCategoryService {
	
	public List selectAll();
	public SubCategory select(int subcategory_id);
	
}

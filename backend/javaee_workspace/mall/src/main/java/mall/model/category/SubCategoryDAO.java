package mall.model.category;

import java.util.List;

import mall.domain.SubCategory;

public interface SubCategoryDAO {
	public List selectAll();
	public SubCategory select(int subCategory_id);
	
}

package mall.model.category;

import java.util.List;

import mall.domain.SubCategory;

public interface SubCategoryDAO {
	public List selectAll();
	public List selectByTopCategoryId(int topcategory_id);
	public SubCategory select(int subCategory_id);
	
}

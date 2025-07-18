package mall.model.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.SubCategory;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

	//DAO를 느슨하게 보유
	@Autowired
	private SubCategoryDAO subCategoryDAO;
	
	@Override
	public List selectAll() {
		return subCategoryDAO.selectAll();
	}

	@Override
	public SubCategory select(int subcategory_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectByTopCategoryId(int topcategory_id) {
		return subCategoryDAO.selectByTopCategoryId(topcategory_id);
	}

}

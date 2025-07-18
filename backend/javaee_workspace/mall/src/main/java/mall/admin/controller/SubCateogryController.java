package mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mall.model.category.SubCategoryService;

@Controller
public class SubCateogryController {

	@Autowired
	private SubCategoryService subCategoryService;
	//선택된, 상위 카테고리에 소속된 하위 카테고리 목록 가져오기
	

	
	@GetMapping("/admin/subcategory/list")
	@ResponseBody //response.setContentType("application/json") -> 문서가 아닌 여러 데이터형 올 수 있음.
	public List getList(int topcategory_id) {
		//3단계 일 시키기
		List subList=subCategoryService.selectByTopCategoryId(topcategory_id);
		
		return subList;
	}
}

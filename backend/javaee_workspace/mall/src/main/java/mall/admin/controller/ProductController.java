package mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.model.category.SubCategoryService;
import mall.model.category.TopCategoryService;

@Controller
public class ProductController {

	//서비스에게 일 시킴.(느슨하게 보유 , 즉 결합도를 낮추어서 보유, 따라서 인터페이스로 보유)
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	//localhost8888:/admin/admin/product/registform
	@RequestMapping(value="/admin/product/registform")
	public String registform(Model model) {
		//상품 등록 페이지를 보게되는 초기에 , 상위 카테고리가 채워져 있어야 함.
		
		
		//3단계 : 일 시키기
		List topList=topCategoryService.selectAll();
		List subList=subCategoryService.selectAll();
		//4단계 : 결과 저장
		model.addAttribute("topList",topList);
		model.addAttribute("subList",subList);
		
		
		return "secure/product/regist";
	}
}

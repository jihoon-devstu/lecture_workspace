package mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Product;
import mall.model.category.SubCategoryService;
import mall.model.category.TopCategoryService;

@Slf4j
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
	
	//상품 등록 요청을 처리
	@PostMapping("/admin/product/regist")
	public String regist(Product product) {
		//모델 객체는 table을 반영한 객체이므로 , 컨트롤러 영역에서 바로 파라미터를 받는 용도로 사용해서는 안됨.
		//왜? 데이터 베이스 컬럼명이 노출되기 때문에....
		//따라서 이 문제를 해결하려면 클라이언트의 파라미터를 받는 용도의 객체를 별도로 둔다.(DTO=Data Transfer Object)
		//DTO에서 Model 객체로 옮겨야 함.
		//3단계 일 시키기
		log.debug("product = ",product);
		//4단계 DML은 저장할게 없으므로 생략
		return "redirect:/admin/admin/product/list";
	}
}

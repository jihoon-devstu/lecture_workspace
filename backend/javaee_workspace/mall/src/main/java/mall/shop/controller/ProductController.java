package mall.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import mall.domain.Product;
import mall.model.category.TopCategoryService;
import mall.model.product.ProductService;

/*
 * 일반 유저가 사용하는 쇼핑몰의 상품 쪽 요청을 처리하는 컨트롤러
 * 
 * */

@Controller
public class ProductController {

	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/list")
	public ModelAndView getProductList() {
		
		ModelAndView mav = new ModelAndView("shop/list");
		
		//3단계 : 최상위 카테고리 가져오기
		List productList = productService.selectAll();
		//4단계 : 저장
		mav.addObject("productList",productList);
		
		return mav;
	}
	
	//상세 요청 처리
	@GetMapping("/product/detail")
	public ModelAndView getDetail(int product_id) {
		ModelAndView mav = new ModelAndView("shop/detail");
		
		//3단계 최상위 카테고리 가져오기 , 모든 사ㅣㅇ품 가져오기
		Product product = productService.select(product_id);
		
		mav.addObject("product",product);
		return mav;
	}
	
}

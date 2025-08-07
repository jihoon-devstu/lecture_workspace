package mall.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Cart;
import mall.domain.Member;

@Slf4j
@Controller
public class CartController {
	
	//장바구니 담기
	@PostMapping("/cart/regist")
	public String regist(Cart cart , HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		log.debug("product_id는 " +cart.getProduct().getProduct_id());
		log.debug("ea는 "+cart.getEa());
		log.debug("member_id는 "+member.getMember_id());
		log.debug("selectedColor는 "+cart.getColor().getColor_id());
		log.debug("selectedSize는 "+cart.getSize().getSize_id());
		
		return null;
	}
	
}

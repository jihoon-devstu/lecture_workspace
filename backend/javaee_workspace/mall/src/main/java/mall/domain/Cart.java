package mall.domain;

import lombok.Data;

@Data
public class Cart {

	private int cart_id;
	private int ea;
	private Product product;
	private int member_id; //내일 할 예정
	private String selectedColor;
	private String selectedSize;
}

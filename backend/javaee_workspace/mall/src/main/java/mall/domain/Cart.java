package mall.domain;

import lombok.Data;

@Data
public class Cart {

	private int cart_id;
	private int ea;
	private Product product;
	private Member member; //내일 할 예정
	private Color color; //선택한 색상
	private Size size; //선택한 사이즈
}

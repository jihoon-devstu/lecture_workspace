package mall.domain;

import java.util.List;

import lombok.Data;

@Data
public class Product {
	private int product_id;
	private String product_name;
	private String brand;
	private int price;
	private int discount;
	private String introduce;
	private String detail;
	//private MultipartFile[] photo;
	
	//하나의 상품은 여러 색상과 사이즈와 이미지를 보유할 수 있다. 1:多 관계 (mybatis에서 collection 수집)
	private List<ProductColor> colorList;
	private List<ProductSize> sizeList;
	private List<ProductImg> imgList;
	
	private SubCategory subcategory;
}

package shop.pages;

import java.awt.Color;
import java.awt.Font;

public class Product extends Page{

	
	public Product() {
		super("상품들이 나오는 페이지에요");
		la_title.setFont(new Font("굴림", Font.BOLD, 30));
		setBackground(Color.PINK);
	}
	
}

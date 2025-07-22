package mall.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mall.domain.Product;
import mall.domain.ProductColor;
import mall.domain.ProductImg;
import mall.domain.ProductSize;
import mall.exception.ProductException;
import mall.util.FileManager;

@Service //서비스는 모델 영역의 객체이기는 하나 , 직접 일하지 않고 주로 전담 객체들에게 일을 할당함.
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductSizeDAO productSizeDAO;
	
	@Autowired
	private ProductColorDAO productColorDAO;
	
	@Autowired
	private ProductImgDAO productImgDAO;
	
	@Autowired
	private FileManager fileManager;
	
	//상품등록+색상등록+사이즈등록+이미지등록+파일저장
	@Transactional //아래의 DAO가 가진 DML 메서드 중 단 하나라도 Exception이 발생되면 
	//Spring이 알아서 rollback
	public void regist(Product product, String savePath) throws ProductException{
		//1) 상품 등록 후 , product_id 취득 , mybatis selectkey에 의해 자동으로 채워져 있음
		productDAO.insert(product);
		
		for(ProductColor productColor : product.getColorList()) {
			//누락되어 있었던 product을 대입해주자
			productColor.setProduct(product);
			productColorDAO.insert(productColor);
		}
		for(ProductSize productSize : product.getSizeList()) {
			productSize.setProduct(product);
			productSizeDAO.insert(productSize);
		}
		
		//4) 이미지 저장
		fileManager.save(product, savePath);
		
		//5) 이미지 파일명도 채워진 상태이므로 db 저장
		for(ProductImg productImg : product.getImgList()) {
			productImg.setProduct(product);
			productImgDAO.insert(productImg);
		}
		
	}

}

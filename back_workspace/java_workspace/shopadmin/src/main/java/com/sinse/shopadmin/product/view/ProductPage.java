package com.sinse.shopadmin.product.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.product.model.Color;
import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.model.ProductColor;
import com.sinse.shopadmin.product.model.SubCategory;
import com.sinse.shopadmin.product.model.TopCategory;
import com.sinse.shopadmin.product.repository.ColorDAO;
import com.sinse.shopadmin.product.repository.ProductColorDAO;
import com.sinse.shopadmin.product.repository.ProductDAO;
import com.sinse.shopadmin.product.repository.SizeDAO;
import com.sinse.shopadmin.product.repository.SubCategoryDAO;
import com.sinse.shopadmin.product.repository.TopCategoryDAO;

public class ProductPage extends Page {

	JLabel la_topcategory;
	JLabel la_subcategory;
	JLabel la_product_name;
	JLabel la_brand;
	JLabel la_price;
	JLabel la_discount;
	JLabel la_color;
	JLabel la_size;
	JButton bt_open; // 파일탐색기 띄우기 버튼
	JLabel la_introduce;
	JLabel la_detail;

	JComboBox<TopCategory> cb_topcategory;
	JComboBox cb_subcategory;
	JTextField t_product_name;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_discount;
	JList t_color;
	JList t_size;
	JScrollPane scroll_color;
	JScrollPane scroll_size;
	JPanel p_preview; // 관리자가 선택한 상품 이미지를 미리보기 한다
	JTextArea t_introduce; // 상품 소개
	JTextArea t_detail;
	JButton bt_regist; // 상품 등록
	JButton bt_list; // 상품 목록
	TopCategoryDAO topCategoryDAO;
	SubCategoryDAO subCategoryDAO;
	ColorDAO colorDAO;
	SizeDAO sizeDAO;
	ProductDAO productDAO;
	ProductColorDAO productColorDAO;

	JFileChooser chooser;
	Image[] imgArray; // 유저가 선택한 파일로부터 생성된 이미지 배열
	File[] files; // 파일 복사 즉 업로드를 진행하려면 , 이미지가 아닌 파일을 대상으로 할 수 있다.

	public ProductPage(AppMain appMain) {
		super(appMain);
		setBackground(java.awt.Color.ORANGE);

		// 생성
		la_topcategory = new JLabel("최상위 카테고리");
		la_subcategory = new JLabel("하위 카테고리");
		la_product_name = new JLabel("상품명");
		la_brand = new JLabel("브랜드");
		la_price = new JLabel("가격");
		la_discount = new JLabel("할인가");
		la_color = new JLabel("색상");
		la_size = new JLabel("사이즈");
		bt_open = new JButton("상품 사진 등록");
		la_introduce = new JLabel("상품 소개");
		la_detail = new JLabel("상세 설명");

		cb_topcategory = new JComboBox<>();
		cb_subcategory = new JComboBox<>();
		t_product_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		t_discount = new JTextField();
		t_color = new JList();
		t_size = new JList();
		scroll_color = new JScrollPane(t_color);
		scroll_size = new JScrollPane(t_size);

		p_preview = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (imgArray != null) {
					// 유저가 선택한 파일 수 만큼 반복하면서 이미지를 그려주자.
					for (int i = 0; i < imgArray.length; i++) {
						g.drawImage(imgArray[i], 5 + (i * 50), 15, 45, 45, appMain);
					}
				}
			}
		};

		t_introduce = new JTextArea();
		t_detail = new JTextArea();
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록");
		topCategoryDAO = new TopCategoryDAO();
		subCategoryDAO = new SubCategoryDAO();
		colorDAO = new ColorDAO();
		sizeDAO = new SizeDAO();
		productDAO = new ProductDAO();
		productColorDAO = new ProductColorDAO();

		chooser = new JFileChooser("C:/lecture_workspace/frontend_workspace/images");
		chooser.setMultiSelectionEnabled(true); // 다중 선택 가능하도록 설정..

		// 스타일
		Dimension d = new Dimension(400, 30);

		la_topcategory.setPreferredSize(d);
		la_subcategory.setPreferredSize(d);
		la_product_name.setPreferredSize(d);
		la_brand.setPreferredSize(d);
		la_price.setPreferredSize(d);
		la_discount.setPreferredSize(d);
		la_color.setPreferredSize(d);
		la_size.setPreferredSize(d);
		bt_open.setPreferredSize(d);
		la_introduce.setPreferredSize(d);
		la_detail.setPreferredSize(d);

		cb_topcategory.setPreferredSize(d);
		cb_subcategory.setPreferredSize(d);

		t_product_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_discount.setPreferredSize(d);
		scroll_color.setPreferredSize(new Dimension(400, 80));
		scroll_size.setPreferredSize(new Dimension(400, 80));
		p_preview.setPreferredSize(new Dimension(400, 80)); // 이미지 미리보기 도화지
		t_introduce.setPreferredSize(new Dimension(400, 50)); // GPT를 연동한 소개글
		t_detail.setPreferredSize(new Dimension(400, 60));

		bt_regist.setPreferredSize(new Dimension(130, 30));
		bt_list.setPreferredSize(new Dimension(130, 30));

		// 조립

		add(la_topcategory);
		add(cb_topcategory);
		add(la_subcategory);
		add(cb_subcategory);
		add(la_product_name);
		add(t_product_name);
		add(la_brand);
		add(t_brand);
		add(la_price);
		add(t_price);
		add(la_discount);
		add(t_discount);
		add(la_color);
		add(scroll_color);
		add(la_size);
		add(scroll_size);
		add(bt_open);
		add(p_preview);
		add(la_introduce);
		add(t_introduce);
		add(la_detail);
		add(t_detail);
		add(bt_regist);
		add(bt_list);

		setPreferredSize(new Dimension(880, 750));

		// 최상위 카테고리에 이벤트 연결
		cb_topcategory.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 내가 선택한 아이템의 pk를 출력해 보기. 신발 = 3
					TopCategory topCategory = (TopCategory) cb_topcategory.getSelectedItem();
					getSubCategory(topCategory);
				}
			}
		});

		// 최상위 카테고리 불러오기

		getTopCategory();

		getColorList();
		getSizeList();

		// 파일 탐색기 띄우기

		bt_open.addActionListener(e -> {

			int result = chooser.showOpenDialog(ProductPage.this);
			// 유저가 선택한 파일에 대한 정보 얻어오기.
			if (result == JFileChooser.APPROVE_OPTION) {
				preview();
			}

		});

		// 등록 버튼과 리스너 연결

		bt_regist.addActionListener(e -> {
			regist();
		});

	}

	public void preview() {
		files = chooser.getSelectedFiles();

		if (files.length > 6) {
			JOptionPane.showMessageDialog(this, "이미지는 최대 6개까지 가능합니다");
		} else {
			imgArray = new Image[files.length];

			// 파일은 파일일 뿐 , 이미지가 아니므로 , 파일을 이용하여 이미지를 만들자 !!
			try {
				for (int i = 0; i < files.length; i++) {
					BufferedImage buffrImg = ImageIO.read(files[i]);
					imgArray[i] = buffrImg.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// 그림 다시 그리기

			p_preview.repaint();
		}
	}

	// DAO를 통해 얻어온 List를 이용하여 콤보박스 채우기
	public void getTopCategory() {

		List<TopCategory> topList = topCategoryDAO.selectAll();

		// 안내 문구 역할을 수행할 dummy 모델을 콤보박스에 넣어주자
		TopCategory dummy = new TopCategory();
		dummy.setTop_name("상위 카테고리를 선택하세요");
		dummy.setTopcategory_id(0);
		cb_topcategory.addItem(dummy);

		for (int i = 0; i < topList.size(); i++) {
			TopCategory topcategory = topList.get(i);
			cb_topcategory.addItem(topcategory);
		}
	}

	public void getSubCategory(TopCategory topCategory) {

		// 하위 카테고리 목록 가져오기
		List<SubCategory> subList = subCategoryDAO.selectByTop(topCategory);

		// 모든 하위 카테고리 콤보아이템 지우기
		cb_subcategory.removeAllItems();

		SubCategory dummy = new SubCategory();
		dummy.setSub_name("하위 카테고리를 선택하세요");
		dummy.setSubcategory_id(0);
		cb_subcategory.addItem(dummy);

		// 서브 카테고리 수만큼 반복하면서 , 두번째 콤보박스에 SubCategory 모델을 채워넣기.
		for (int i = 0; i < subList.size(); i++) {
			SubCategory subCategory = subList.get(i); // i번째 요소 꺼내기
			cb_subcategory.addItem(subCategory);
		}
	}

	public void getColorList() {
		t_color.setListData(new Vector(colorDAO.selectAllColor()));
	}

	public void getSizeList() {
		t_size.setListData(new Vector(sizeDAO.selectAllSize()));
	}

	public void upload() {
		// 시각적 효과를 위해 각, 이미지의 업로드 진행율을 보여주자 , 새창으로...
		UploadDialog dialog = new UploadDialog(this);
	}
	
	//Mysql에 상품 등록 관련 쿼리 수행
	public void insert() {
		//productDAO 에게 일 시키기 !!
		
		//product 모델 인스턴스 1개를 만들어 , 안에다가 상품 등록폼의 데이터를 채워넣자 !!
		Product product = new Product();
		
		product.setSubCategory((SubCategory)cb_subcategory.getSelectedItem());
		product.setProduct_name(t_product_name.getText());
		product.setBrand(t_brand.getText());
		product.setPrice(Integer.parseInt(t_price.getText()));
		product.setDiscount(Integer.parseInt(t_discount.getText()));
		product.setIntroduce(t_introduce.getText());
		product.setDetail(t_detail.getText());
		//productDAO.insert
		
		int result = productDAO.insert(product);
		
		
		int product_id = productDAO.selectRecentPk();
		product.setProduct_id(product_id);
		System.out.println("porduct_id" + product_id);
		
		//상품에 딸려있는 색상들 등록하기
		List<Color> colorList = t_color.getSelectedValuesList();
		
		for (Color color:colorList){
			
			//ProductColor 에 어떤 상품이 어떤 색상을....
			ProductColor productColor = new ProductColor();
			//어떤 상품이?
			//어떤 색상을?
			productColor.setProduct(product);
			productColor.setColor(color);
			productColorDAO.insert(productColor);
		}
		
	}

	// 이미지 업로드 및 DB insert
	public void regist() {
		// 양식을 제대로 입력했을 때 풀어주자
		// 상위 카테고리 유효성 체크
		if (cb_topcategory.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "상위 카테고리를 선택하세요");
		} else if (cb_subcategory.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "하위 카테고리를 선택하세요");
		} else if (t_product_name.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "상품명을 입력하세요");
		} else if (t_brand.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "브랜드을 입력하세요");
		} else if (t_price.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "가격을 입력하세요");
		} else if (t_discount.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "할인가을 입력하세요");
		} else if (t_color.getMinSelectionIndex() == -1) {
			JOptionPane.showMessageDialog(this, "적어도 1개 이상의 색상을 선택하세요");
		} else if (t_size.getMinSelectionIndex() == -1) {
			JOptionPane.showMessageDialog(this, "적어도 1개 이상의 사이즈를 선택하세요");
		} else if (files.length < 1) {
			JOptionPane.showMessageDialog(this, "상품 이미지를 입력하세요");
		} else if (t_introduce.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "상품 소개를 입력하세요");
		} else if (t_detail.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "상세내용 입력하세요");
		} else {
			upload();
			
			insert();
		}

	}

}

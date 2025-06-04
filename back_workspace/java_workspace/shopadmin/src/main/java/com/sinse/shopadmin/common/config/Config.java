package com.sinse.shopadmin.common.config;

//유저용 쇼핑몰에서 사용되는 모든 상수를 관리하는 클래스
public class Config {
	
	/*---------------------------------------------------------------------
	  페이지 정의
	 ---------------------------------------------------------------------*/

	public static final int MAIN_PAGE=0; //메인 페이지
	public static final int PRODUCT_PAGE=1; //상품 관리 페이지
	public static final int ORDER_PAGE=2; // 페이지
	public static final int MEMBER_PAGE=3; //상품 관리 페이지
	public static final int CUSTOMER_PAGE=4; //상품 관리 페이지
	public static final int CONFIG_PAGE=5; //상품 관리 페이지
	
	
	/*---------------------------------------------------------------------
	  관리자 앱 메인 설정
	 ---------------------------------------------------------------------*/
	
	public static final int ADMINMAIN_WIDTH=1300;
	public static final int ADMINMAIN_HEIGHT=800;
	
	public static final int UTIL_WIDTH=ADMINMAIN_WIDTH;
	public static final int UTIL_HEIGHT=50;
	
	public static final int SIDE_WIDTH=200;
	public static final int SIDE_HEIGHT=ADMINMAIN_HEIGHT-UTIL_HEIGHT;
	
	
}
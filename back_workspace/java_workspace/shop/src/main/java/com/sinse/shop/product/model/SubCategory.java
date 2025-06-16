package com.sinse.shop.product.model;

public class SubCategory {
	
private int subcategory_id;
	
	private String sub_name;
	
	private TopCategory topcategory; //데이터 베이스에서 부모를 표현한 모델을 자식이 보유..

	public int getSubcategory_id() {
		return subcategory_id;
	}

	public void setSubcategory_id(int subcategory_id) {
		this.subcategory_id = subcategory_id;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public TopCategory getTopcategory() {
		return topcategory;
	}

	public void setTopcategory(TopCategory topcategory) {
		this.topcategory = topcategory;
	}

	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return sub_name;
		}
	

}

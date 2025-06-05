package com.sinse.shopadmin.product.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.view.Page;

public class ProductPage extends Page{
	
	JLabel la_topcategory;
	JLabel la_subcategory;
	JLabel la_product_name;
	JLabel la_brand;
	JLabel la_price;
	JLabel la_discount;
	JLabel la_color;
	JLabel la_size;
	JLabel la_photo;
	JLabel la_introduce;
	JLabel la_detail;
	
	JComboBox ch_topcategory;
	JComboBox ch_subcategory;
	JTextField t_product_name;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_color;
	JTextField t_size;
	JButton bt_photo;
	JTextArea t_introduce;
	JTextArea t_detail;
	
	public ProductPage(AppMain appMain) {
		super(appMain);
		setBackground(Color.ORANGE);
	}

}
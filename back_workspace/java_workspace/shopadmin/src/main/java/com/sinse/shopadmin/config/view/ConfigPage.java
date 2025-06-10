package com.sinse.shopadmin.config.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;

public class ConfigPage extends Page{
	
	JPanel p_color;
	JLabel la_color;
	JTextField t_color;
	JButton bt_color;
	JList list_color;
	JScrollPane scroll_color;
	
	
	JPanel p_size;
	JLabel la_size;
	JTextField t_size;
	JButton bt_size;
	JList list_size;
	JScrollPane scroll_size;
	
	public ConfigPage(AppMain appMain) {
		super(appMain);
		setBackground(Color.RED);
		
		//생성
		p_color = new JPanel();
		la_color = new JLabel("색상 등록");
		t_color = new JTextField();
		bt_color = new JButton("등록");
		list_color = new JList();
		scroll_color = new JScrollPane(list_color);
		
		p_size = new JPanel();
		la_size = new JLabel("사이즈 등록");
		t_size = new JTextField();
		bt_size = new JButton("등록");
		list_size = new JList();
		scroll_size = new JScrollPane(list_size);
		
		//스타일
		
		Dimension d = new Dimension(150,30);
		
		la_color.setPreferredSize(d);
		t_color.setPreferredSize(d);
		la_size.setPreferredSize(d);
		t_size.setPreferredSize(d);

		scroll_color.setPreferredSize(new Dimension(200,300));
		scroll_size.setPreferredSize(new Dimension(200,300));
		
		p_color.setPreferredSize(new Dimension(Config.ADMINMAIN_WIDTH-200 , 350));
		p_size.setPreferredSize(new Dimension(Config.ADMINMAIN_WIDTH-200 , 350));
		
		p_color.setBorder(new TitledBorder("색상 정보 등록"));
		p_size.setBorder(new TitledBorder("사이즈 정보 등록"));
		
		//조립
		
		p_color.add(la_color);
		p_color.add(t_color);
		p_color.add(bt_color);
		p_color.add(scroll_color);
		
		p_size.add(la_size);
		p_size.add(t_size);
		p_size.add(bt_size);
		p_size.add(scroll_size);

		add(p_color);
		add(p_size);
	}

}
package com.sinse.dbproject;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class EmpLoad extends JFrame{
	
	JPanel p_north;
	JButton bt_emp;
	JButton bt_dept;
	
	JTable table;
	JScrollPane scroll;
	
	MyActionListener myActionListener;
	
	public EmpLoad() {
		
		p_north = new JPanel();
		bt_emp = new JButton("사원 테이블 로드");
		bt_dept = new JButton("부서 테이블 로드");
		
		table = new JTable();
		scroll = new JScrollPane(table);
		
		myActionListener = new MyActionListener();
		
		//style	
		p_north.setPreferredSize(new Dimension(800,30));
		
		//assemble
		p_north.add(bt_emp);
		p_north.add(bt_dept);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		setSize(800,630);
		setVisible(true);
		
		//ActionListener 연결
		bt_emp.addActionListener(myActionListener);
		bt_dept.addActionListener(myActionListener);
	}
	
	
	public static void main(String[] args) {
		new EmpLoad();
	}
	
}

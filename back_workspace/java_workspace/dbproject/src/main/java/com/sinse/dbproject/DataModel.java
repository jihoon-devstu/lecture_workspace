package com.sinse.dbproject;

import javax.swing.table.AbstractTableModel;

public class DataModel extends AbstractTableModel{
	//데이터를 표현하는 이차원 배열
	String[][] data;
	//컬럼을 표현하는 일차원 배열
	String[] title;
	

	//층수를 반환하여 JTable이 참조할 수 있도록 제공되는 메서드
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return title.length;
	}
	
	public String getColumnName(int column) {
		return title[column];
	}

	//JTable에 의해 row x col 수만큼 아래의 메서드 호출됨..
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}

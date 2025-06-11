package com.sinse.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

//API 자체적으로 지원해주는 진행바는 너무 단순하므로 , 우리가 원하는 스타일의 바로 커스텀 해보자
//Runnable 인터페이스란? run() 추상 메서드를 보유한인터페이스.
public class MyBar extends JProgressBar{
	
	FileInputStream fis; //파일을 대상으로 한 바이트 기반의 입력스트림
	FileOutputStream fos; //파일을 대상으로 한 바이트 기반의 출력스트림
	
	
	public MyBar(File origin , File dest) {
		
		try {
			fis = new FileInputStream(origin); //원본 파일을 대상으로 스트림 생성
			fos = new FileOutputStream(dest); //파일이 복사될 대상에 대한 출력 스트림 생성
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setPreferredSize(new Dimension(680, 50));
		setBackground(Color.YELLOW);
		setBorder(new TitledBorder("지난해 단합대회.jpg ---> 237489234.jpg"));
		setStringPainted(true); //진행바 중앙에 텍스트 띄우기
		setFont(new Font("Verdana", Font.BOLD, 17));
	}
	
	public void run() {
		
	}
	
}

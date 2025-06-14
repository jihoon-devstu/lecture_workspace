package com.sinse.ioproject;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GuiCopy extends JFrame implements ActionListener{
	JLabel la_ori;
	JLabel la_dest;
	JTextField t_ori;
	JTextField t_dest;
	JButton bt_ori;
	JButton bt_dest;
	JButton bt_copy;
	JFileChooser fileChooser;
	
	public GuiCopy() {
		la_ori = new JLabel("원본");
		la_dest = new JLabel("복사본");
		t_ori = new JTextField();
		t_dest = new JTextField();
		bt_ori = new JButton("열기");
		bt_dest = new JButton("저장");
		bt_copy = new JButton("복사");
		fileChooser = new JFileChooser("C:/lecture_workspace/back_workspace/guiproject/res");
		
		bt_ori.addActionListener(this);
		bt_dest.addActionListener(this);
		bt_copy.addActionListener(this);
		
		setLayout(new FlowLayout());
		
		Dimension d = new Dimension(100,24); //라벨,버튼에 적용할 크기
		Dimension d2 = new Dimension(330,24); //텍스트 필드에 적용할 크기
		
		la_ori.setPreferredSize(d);
		la_dest.setPreferredSize(d);
		bt_ori.setPreferredSize(d);
		bt_dest.setPreferredSize(d);
		
		t_ori.setPreferredSize(d2);
		t_dest.setPreferredSize(d2);
		
		
		
		
		add(la_ori);
		add(t_ori);
		add(bt_ori);
		add(la_dest);
		add(t_dest);
		add(bt_dest);
		add(bt_copy);
		
		setSize(580,130);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void copy() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(t_ori.getText());
			fos = new FileOutputStream(t_dest.getText());
			//한 바이트씩 읽어들여(입력) , 한바이트씩 내뱉기
			
			int data = -1;
			
			while (true) {
				data = fis.read();
				if(data==-1) break;
				fos.write(data);
			}
			JOptionPane.showMessageDialog(this, "복사완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(fos!=null) {
				try {
					fos.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==bt_ori) {
			int result = fileChooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION) {
				//유저가 선택한 파일 정보를 이용하여 경로 추출!!
				//그 결과를 텍스트 필드에 출력
				
				//File 클래스는 일반클래스이지만, 개발자가 new 할 필요 없다.
				//파일이 생성되는 시점은 유저가 선택한 시점이니까.
				File file = fileChooser.getSelectedFile();
				t_ori.setText(file.getAbsolutePath());
			}		
		}else if(e.getSource()==bt_dest) {
			if(fileChooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile(); //유저가 탐색기에서 선택한 파일정보
				t_dest.setText(file.getAbsolutePath());
			}		
			
		}else if(e.getSource()==bt_copy) {
			copy();
			
		}
		
		

		
	}
	
	public static void main(String[] args) {
		new GuiCopy();
	}
}

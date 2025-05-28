package com.sinse.ioproject;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileCopy extends JFrame implements ActionListener{
	JLabel la_ori;
	JLabel la_dest;
	JTextField t_ori;
	JTextField t_dest;
	JButton bt;
	
	public FileCopy() {
		la_ori = new JLabel("원본");
		la_dest = new JLabel("복사본");
		t_ori  = new JTextField();
		t_dest  = new JTextField();
		bt = new JButton("복사 실행");
		
		setLayout(new FlowLayout());
		
		//크기 지정
		la_ori.setPreferredSize(new Dimension(100, 25));
		t_ori.setPreferredSize(new Dimension(350, 25));
		la_dest.setPreferredSize(new Dimension(100, 25));
		t_dest.setPreferredSize(new Dimension(350, 25));
		
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(bt);
		
		bt.addActionListener(this);
		
		setSize(490,130);
		setVisible(true);
		
	}
	
	public void copy() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(t_ori.getText()); //원본 스트림
			fos = new FileOutputStream(t_dest.getText()); //복사본 스트림
			
			int data = -1;
			while(true) {
			data = fis.read();
			if(data==-1) break;
			fos.write(data);
			}
			
			//시각적으로 완료되었음을 알려주기
			JOptionPane.showMessageDialog(this, "복사완료");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}finally {			
			if(fis != null) {
				try {
					fis.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fis.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		copy();
	}
	
	public static void main(String[] args) {
		new FileCopy();
	}
}

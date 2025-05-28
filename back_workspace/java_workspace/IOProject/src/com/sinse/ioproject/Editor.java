package com.sinse.ioproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Editor extends JFrame implements ActionListener{
	
	JMenuBar bar;
	JMenu[] menu = new JMenu[5];
	String menuTitle[]= {
			"파일",
			"편집",
			"서식",
			"보기",
			"도움말"
	};
	String itemTitle[]= {
		"새로 만들기",
		"새 창",
		"열기",
		"저장",
		"다른 이름으로 저장",
		"페이지 설정",
		"인쇄",
		"끝내기"	
	};
	
	JMenuItem[] item = new JMenuItem[8];
	
	JTextArea area;
	
	//메뉴의 이름이 너무 불편하다 (메뉴어레이의0,1,2...) 직관성을 부여하기 위한 상수를 정의한다.
	public static final int FILE = 0;
	public static final int EDIT = 1;
	public static final int STYLE = 2;
	public static final int VIEW = 3;
	public static final int HELP = 4;
	
	JFileChooser chooser; //파일 탐색기를 컨트롤 하는 전담 객체
	
	public Editor() {
		bar = new JMenuBar();
		bar.setBackground(Color.YELLOW);
		
		//메뉴들 조립
		//메뉴 아이템들을 '파일' 메뉴에 부착
		
		for(int i=0;i<menu.length;i++) {
		menu[i] = new JMenu(menuTitle[i]);
		}
		
		for(int i=0;i<itemTitle.length;i++) {
			item[i] = new JMenuItem(itemTitle[i]);
		}
		
		for(int i=0;i<itemTitle.length;i++) {
			menu[FILE].add(item[i]);
			if(i==4 || i==6) {
				menu[FILE].addSeparator();
			}
		}
		
		
		
		for(int i=0; i<menu.length;i++) {
			bar.add(menu[i]);
		}
		
		area = new JTextArea();
		
		chooser = new JFileChooser("C:/lecture_workspace/back_workspace/java_workspace");

		//바 부착
		
		setJMenuBar(bar);
		add(area);
		
		//열기 버튼에 이벤트 연결
		
		item[2].addActionListener(this);
		
		//exit 버튼에 이벤트 연결
		item[item.length-1].addActionListener(this);
		
		
		
		setBounds(600,200,800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void openFile() {
		//어떤 파일을 대상으로 열지는 , 개발자가 아니라 사용자가 결정하므로 새창을 띄워주자
		int result = chooser.showOpenDialog(this);
		File file=null;
		if(result == JFileChooser.APPROVE_OPTION) {//열기 누르면
			//유저가 선택한 파일을 얻어와서 스트림을 생성하자.
			file = chooser.getSelectedFile();
		}
		FileInputStream fis = null; //파일을 대상으로 한 입력 스트림
		try {
			fis = new FileInputStream(file);
			//파일의 끝까지 1byte씩 읽어가면서 area에 추가
			int data = -1;
					
			while(true) {
				data = fis.read(); //1byte 읽기, Wrapper 클래스 고려..
				if(data==-1)break;
				area.append(Character.toString((char)data));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==item[item.length-1]) {
			if(JOptionPane.showConfirmDialog(this, "프로그램 종료할거에요?")==JOptionPane.OK_OPTION) {
			System.exit(0);
			}
		}else if(e.getSource()==item[2]) {
			openFile();
		}
		
		
	}
	
	public static void main(String[] args) {
		new Editor();
	}
}

package com.sinse.threadapp.ani;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Gallery extends JFrame {

	JPanel p_west; // 썸네일이 그려질 통 패널 (모든 썸네일을 그려서 표현)
	JPanel p_container; // 북쪽 , 큰 사진 패널들을 감싸안을 바깥쪽 컨테이너 (BorderLayout)
	JPanel p_north; // 북쪽 컨트롤러 영역
	JPanel p_center; // 큰 사진 보여질 영역

	ImageUtil imageUtil = new ImageUtil();

	Image[] imgArray = new Image[9];
	int y=10;
	Thread thread; //JS 시절의 게임루프 (setInterval)
	// 화면에 랜더링 하지는 않지만 , 원하는 좌표에 사각형을 메모리상에 존재시키면
	// 원하는 영역에 이벤트를 부여할 수 있다.
	float a = 0.1f;
	int targetY;

	int currentIndex;
	
	Rectangle[] rectArray = new Rectangle[imgArray.length];

	public Gallery() {
		createImage();
		thread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
						//what to do?
						move();
						p_west.repaint();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};
		p_west = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); //배경을 스스로 채워야함
				for (int i = 0; i < imgArray.length; i++) {
					g.drawImage(imgArray[i], 5, 10 + (95 * i), 90, 90, p_west);
				}
				
				//그려진 이미지 위로 옮겨다닐 사각 포인터...
				Graphics2D g2 = (Graphics2D)g;
				g2.setStroke(new BasicStroke(5));
				g.setColor(Color.RED);
				g.drawRect(5,y,90,90);
			}
		};
		p_container = new JPanel();
		p_north = new JPanel();
		p_center = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(imgArray[currentIndex],0,0,800,850,p_center);
			}
		};

		// 스타일
		p_west.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p_west.setPreferredSize(new Dimension(100, 800));

		add(p_west, BorderLayout.WEST);
		p_container.setLayout(new BorderLayout());
		p_container.add(p_north, BorderLayout.NORTH);
		p_container.add(p_center);

		add(p_container);

		// 좌측 패널에 마우스 이벤트 연결
		p_west.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < rectArray.length; i++) {
					if (rectArray[i].contains(e.getPoint())) {
						System.out.println("썸네일 클릭했어?");
						currentIndex=i;
						p_center.repaint();
						targetY = rectArray[i].y;
					}
				}
			}

		});
		
		thread.start();
		setSize(900, 900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void createImage() {
		for (int i = 0; i < imgArray.length; i++) {
			imgArray[i] = imageUtil.getImage("geographic/geo" + (i + 1) + ".jpg", 90, 90);
			rectArray[i] = new Rectangle(5, 10 + (95 * i), 90, 90);
		}
	}
	
	//pointer가 목적지에 한번에 도달하는게 아니라 , 감속도 공식 적용하여 부드럽게 도달하게 하기.
	public void move() {
		//y의 값은 = 기존 y 값 + a(목적y - 현재y)
		y = y+(int)(a*(targetY-y));
	}

	public static void main(String[] args) {
		new Gallery();
	}
}

package gui.graphic;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


class Gallery extends JFrame implements ActionListener{
	JPanel p_north;
	JButton bt_prev;
	JButton bt_next;
	JLabel la_title;
	Toolkit kit;
	MyCanvas myCanvas; //우리가 만든 패널로 교체
	Image[] imgArray = new Image[9];
	int index=0; //이미지 배열을 접근하기 위한 인덱스 멤버변수 (자바엔 전역변수가 없으므로)
	
	public Gallery(){
		p_north = new JPanel();
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		la_title = new JLabel("제목");
		kit = Toolkit.getDefaultToolkit();//툴킷의 인스턴스 얻기, 클래스 메서드
		myCanvas = new MyCanvas();
		
		myCanvas.setBackground(Color.YELLOW);
		
		
		//북쪽 패널에 버튼과 라벨 부착
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		//북쪽 패널을 프레임의 북쪽에 부착
		add(p_north,BorderLayout.NORTH);
		add(myCanvas, BorderLayout.CENTER);
		
		//중앙 패널을 프레임의 중앙에 부착
		add(myCanvas);
		
		//초기 이미지 보여주기
		createImage();
		
		//패널에 초기에 이미지 1개를 전달해 주자.
		myCanvas.setImage(imgArray[index]);
		
		//이전 버튼과 다음 버튼 리스너 연결
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		//윈도우 크기
		setSize(600,500);
		setVisible(true);
		
	}
	
	//멤버 변수로 선언된 이미지 배열에 , 이미지 인스턴스 9개를 준비해놓아야
	//프로그램 가동과 동시에 사용자가 사용할 수 있다.
	public void createImage(){
		String[] path = {
			"geo (1).jpg",
			"geo (2).jpg",
			"geo (3).jpg",
			"geo (4).jpg",
			"geo (5).jpg",
			"geo (6).jpg",
			"geo (7).jpg",
			"geo (8).jpg",
			"geo (9).jpg"
		};
		
		for(int i=0;i<path.length;i++){
			//툴킷으로부터 이미지 인스턴스 9개를 생성하여 image 배열에 넣어주기
			//주의) 디렉토리 경로 붙이기
			
			imgArray[i]=kit.getImage("C:/lecture_workspace/back_workspace/guiproject/res/geographic/"+path[i]);
		}
	}
	
	//p_center 영역에 이미지 출력하기
	
	// 그래픽 프로그래밍에서, 컴포넌트의 약간의 변화라도 생기면 그 그림은
	//지워지고 다시 그려져야 하는데 , 개발자가 처리하는것이 아니라, 시스템이 알아서 렌더링을
	//담당하게 됨...
	//컴포넌트에 변경이 생기면 다음의 과정을 거쳐서 그래픽이 구현된다.
	/*
	최초 컴포넌트 등장 시 paint()에 의해 눈에 보여짐
	*/
	public void showImage(){
		myCanvas.setImage(imgArray[index]);
		myCanvas.repaint(); //패널을 프로그래밍적으로 다시 그려줘 !! 	
	}
	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		
		//버튼 인스턴스의 주소값만 비교하면 되므로 , 굳이 형변환 할 필요가 없다.
		if(obj==bt_prev && index>0){
			index--;			
		}else if(obj==bt_next && index<8){
			index++;
		}
		showImage();
	}
	/*
	public void paint(Graphics g){
		System.out.println("나 그려짐");
	}
	*/
	
	public static void main(String[] args) {
		new Gallery();
	}
}
package gui.graphic;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Font;


class  ImgPanel extends JPanel{
	
	Toolkit kit = Toolkit.getDefaultToolkit(); //이미지를 우리 대신 얻어옴
	Image image; //추상 클래스 이므로 , 툴킷으로부터 얻어오자.
	
	public ImgPanel(){
		setBackground(Color.YELLOW);
		
		//그림을 그리기 전에 , 이미지를 먼저 얻어다 놓자
		image = kit.getImage("C:/lecture_workspace/back_workspace/guiproject/res/pikachu.png");
		
		setPreferredSize(new Dimension(270,350));
		
		//패널이 보유한 그리기 메서드를 재정의한다
		//붓에 해당됨
		
		
	}
	public void paint(Graphics g){
		
			g.setColor(java.awt.Color.RED);
			
			g.drawImage(image,0,0,this);  //이미지 그리기
			
			//선그리기
			g.drawLine(100,0,300,200);
			
			//타원 그리기
			g.drawOval(0,0,200,200);
			
			g.setColor(java.awt.Color.YELLOW);
			g.setFont(new Font("Verdana", Font.BOLD,40));
			g.drawString("graphic test", 50,100);
			g.drawRect(150,250,100,100);
		}
		
}

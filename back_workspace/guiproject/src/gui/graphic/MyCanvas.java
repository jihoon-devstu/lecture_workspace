package gui.graphic;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class  MyCanvas extends JPanel{
	Image image;
	
	//1) 이미지를 넘겨받을 메서드를 선언하자
	//2) 프레임을 보유하면 , 프레임이 가지고 있는 재료(배열)도 쓸수있다. 
	
	public void setImage(Image image){
		this.image=image;
	}
	
	public void paint(Graphics g){
		g.drawImage(image,0,0,600,450,this);
	}

}

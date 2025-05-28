package gui.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class  MovePanel extends JPanel{
	
	private int x=100;
	private int y=100;
	
	public void move(){
		x+=15;
		y+=15;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y=y;
	}

	//JPanel의 paint() 메서드를 오버라이딩
	public void paint(Graphics g){
		//채워진 원 그리기 
		g.setColor(Color.RED);
		g.fillOval(x,y,45,45);
	}
}

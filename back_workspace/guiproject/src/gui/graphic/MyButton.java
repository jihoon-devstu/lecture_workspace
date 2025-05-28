package gui.graphic;
import javax.swing.JButton;
import java.awt.Graphics;

class  MyButton extends JButton{
	
	public MyButton(String title){
		super(title);
	}
	
	public void paint(Graphics g){
		g.drawOval(0,0,25,25);
	}
}

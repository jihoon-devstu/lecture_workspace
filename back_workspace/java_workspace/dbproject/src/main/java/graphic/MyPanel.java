package graphic;

import java.awt.GradientPaint;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	AniTest aniTest;
	
	//나를 생성하는 자는 AniTest의 주소값을 넘겨달라
	public MyPanel(AniTest aniTest) {
		this.aniTest=aniTest;
	}
	

	
	
}

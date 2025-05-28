package test;
import java.awt.*;

class GridTest 
{
	public static void main(String[] args) 
	{
		Frame f= new Frame("그리드 배치");
		f.setLayout(new GridLayout(3,4));
		
		for(int i=0; i<3;i++){
			for(int a=0; a<4; a++){
				f.add(new Button(i+"층"+a+"호"));
				//Button bt = new Button(i+"층"+a+"호");
				//bt.setBackground(Color.YELLOW);
				//f.add(bt);
			}
		}
		
		f.setSize(100,300);
		f.setVisible(true);
	}
}

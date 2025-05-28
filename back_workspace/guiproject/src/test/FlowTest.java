package test;
import java.awt.*;
class FlowTest 
{
	public static void main(String[] args) {
		Frame f = new Frame("플로우 배치방식");
		f.setLayout(new FlowLayout());
		
		for(int i=0; i<20;i++){
			f.add(new Button("버튼"+i));
		}
		f.setSize(200,250);
		f.setVisible(true);

	}
}

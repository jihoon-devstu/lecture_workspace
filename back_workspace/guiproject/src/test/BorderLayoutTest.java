package test;
import java.awt.*;
public class BorderLayoutTest
{
	public static void main(String[] args) {
		Frame f = new Frame();
		BorderLayout border = new BorderLayout();
		
		f.setLayout(border);
		
		Button bt_east = new Button("동쪽");
		Button bt_west = new Button("서쪽");
		Button bt_south = new Button("남쪽");
		Button bt_north = new Button("북쪽");
		Button bt_center = new Button("센터");
		
		f.add(bt_north, BorderLayout.NORTH);
		f.add(bt_south, BorderLayout.SOUTH);
		f.add(bt_east, BorderLayout.EAST);
		f.add(bt_west, BorderLayout.WEST);
		
		f.add(bt_center);  //개발자가 방위를 설정하지 않으면 default가 중앙임
		
		f.setSize(500,400); //윈도우 창 크기 설정
		f.setVisible(true); //윈도우 보이게
	}
}

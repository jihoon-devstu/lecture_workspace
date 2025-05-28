package gui.event;
import java.awt.*;

public class LayoutTest {
	public static void main(String[] args) { 
		//윈도우 생성
		Frame frame = new Frame("배치 학습");
		
		Panel panel;
		Panel panelC;
		
		panel = new Panel();
		panelC = new Panel();
		//윈도우 안에 소속되는 컨테이너형 컴포넌트.
		//따라서 다른 컴포넌트를 포함할 수 있다. 배치관리자가 지정하지 않으면 디폴트가 FlowLayout.
		
		Button bt_center= new Button("CENTER");
		Button bt_north = new Button("NORTH");
		Button bt_west = new Button("WEST");
		Button bt_south = new Button("SOUTH");

		frame.add(bt_west,BorderLayout.WEST);
		frame.add(panel,BorderLayout.SOUTH);
		frame.add(panelC,BorderLayout.CENTER);
		
		panel.add(bt_south);
		
		panelC.add(bt_center);
		panelC.add(bt_north);
		//상수는 public static final로 선언되었고, 클래스 소속이므로 인스턴스 생성없이
		//사용 간으함. 따라서 BorderLayout이 보유한 상수명으로 접근가능.

		
		frame.setSize(500,400);
			//윈도우는 보이고 , 안보이게하는 기능이 있기 때문에 디폴트는 눈에 안보임
		frame.setVisible(true);
	}
}

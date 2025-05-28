

package gui.event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class  MyActionListener implements ActionListener{
	//부모인 ActionListener의 메서드 오버라이딩...
	public void actionPerformed(ActionEvent e){
		//개발자는 액션이벤트가 발생했을 때, 처리할 로직을 여기에 작성...
		//js에서의 익명함수 영역과 동일하다고 보면 됨..
		
		System.out.println("나 눌렀어?");
	}
}

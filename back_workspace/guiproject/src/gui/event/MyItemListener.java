package gui.event;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MyItemListener implements ItemListener{
	
	public void itemStateChanged(ItemEvent e){
		System.out.println("메일 주소 바꿧어?");
	};
}

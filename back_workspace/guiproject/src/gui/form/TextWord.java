
package gui.form;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class  TextWord extends Frame implements ActionListener{
	TextArea area;
	Panel p_south;
	Button bt_form;
	SetForm setForm;

	
	public TextWord(){
		area = new TextArea();
		p_south = new Panel();
		bt_form = new Button("서식");
		
		area.setBackground(Color.YELLOW);
		
		add(area);
		p_south.add(bt_form);
		
		this.setSize(300,400);
		this.setVisible(true);
		
		bt_form.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		//SetForm의 인스턴스 생성
		//this란? 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
		setForm = new SetForm(this);
		}
	
	
	public static void main(String[] args) 
	{
		new TextWord();
	}
}

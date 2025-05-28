package gui.form;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class  SetForm extends Frame implements ActionListener{
	
	TextField font_field;
	TextField color_field;
	Button bt_set;
	TextWord word ;
	
	public SetForm(TextWord word){
		font_field = new TextField(20);
		color_field = new TextField(20);
		bt_set = new Button();
		this.word = word;
		
		add(font_field);
		add(color_field);
		add(bt_set);
		bt_set.addActionListener(this);
		

	}
	
			public void actionPerformed(ActionEvent e){
			
		}
}

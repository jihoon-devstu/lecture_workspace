package gui.layout;
import java.awt.*;

public class LoginForm {
	
	public static void main(String[] args) 
	{	
		Frame frame = null;
		Panel p_center;
		Panel p_south;
		Label ID = null;
		Label PW = null;
		TextField IDT = null;
		TextField PWT = null;
		Button login = null;
		Button join = null;
		
		frame = new Frame("Login Form");
		p_center = new Panel();
		p_south = new Panel();
		ID = new Label("ID");
		PW = new Label("Password");
		IDT = new TextField();
		PWT = new TextField();
		login = new Button("login");
		join = new Button("join");
		
		Dimension d = new Dimension(105, 25);
		
		ID.setPreferredSize(d);
		PW.setPreferredSize(d);
		IDT.setPreferredSize(d);
		PWT.setPreferredSize(d);
		
		p_center.setLayout(new GridLayout(2,2));
		p_center.add(ID);
		p_center.add(IDT);
		p_center.add(PW);
		p_center.add(PWT);
		
		frame.add(p_center); // 생략해도 센터임
		
		p_south.add(login);
		p_south.add(join);
		
		frame.add(p_south, BorderLayout.SOUTH);
		

		
		frame.setSize(300,135);
		frame.setVisible(true);
	}
}

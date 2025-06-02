package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AniTest extends JFrame{
	
	JButton bt;
	JPanel p_center;	
	int x;
	int y;
	
	public AniTest() {
		bt = new JButton() {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				g.setColor(Color.RED);
				g.fillRect(0, 0, 70, 30);
			}
		};
		
		p_center = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.drawRect(x, y, 200, 200);
			}
		};
		

		add(bt, BorderLayout.NORTH);
		add(p_center);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x+=10;
				y+=10;
				p_center.repaint();
			}
		});
		
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		new AniTest();
	}

}

package chat.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame implements Runnable{

	JPanel p_north;
	
	JComboBox box_ip;
	JTextField t_port;
	JButton bt;
	
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	
	BufferedWriter buffw;
	
	BufferedReader buffr;
	
	Thread thread;
	
	/*대화용 소켓.. 이 객체를 메모리에 올릴 때 접속이 발생함
	  또한 접속이 성공되면 , 그 시점부터 연결이 이루어진 것이므로 스트림을 통해 
	  데이터를 주고받을 수 있음.*/
	Socket socket; 
	
	public EchoClient() {
		//생성
		p_north = new JPanel();
		
		box_ip = new JComboBox();
		t_port = new JTextField("9999", 8);
		bt = new JButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input = new JTextField();
		
		createIp();
		//스타일
		area.setBackground(Color.yellow);
		p_north.setPreferredSize(new Dimension(300,40));
		box_ip.setPreferredSize(new Dimension(200,30));
		t_port.setPreferredSize(new Dimension(60,30));
		area.setPreferredSize(new Dimension(300,220));
		t_input.setPreferredSize(new Dimension(300,40));
		
		
		//조립
		
		p_north.add(box_ip);
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(t_input, BorderLayout.SOUTH);
		
		//접속 버튼과 리스너 연결
		bt.addActionListener(e->{
			thread = new Thread(EchoClient.this);
			thread.start();
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					//서버로 내보내기 !!(출력)
					String msg = t_input.getText();
					send(msg);
					t_input.setText("");
				}
			}
		});
		
		
		setSize(450,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void connect() {
	//소켓 서버 접속해보기
		String ip = (String)box_ip.getSelectedItem();
		int port = Integer.parseInt(t_port.getText());
		
		try {
			socket = new Socket(ip,port);
			
			//소켓으로부터 스트림을 얻어오자
			
			//바이트 기반 , 출력 스트림(말하기)...
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					
			// 바이트 기반 , 입력 스트림(듣기)
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//실행중인 프로그램에서 , 데이터를 내보내야 하므로 , 필요한 스트림은 바로 출력 스트림 !! 	
	public void send(String msg) {
		//서버로 한줄 보내기..
		try {
			buffw.write(msg+"\n"); //보내기....
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public void run() {
		
		connect();
	}
	
	public void createIp() {
		for(int i=15;i<=47;i++) {
			box_ip.addItem("192.168.60."+i);
		}
	}
	

	
	public static void main(String[] args) {
		new EchoClient();
	}
}

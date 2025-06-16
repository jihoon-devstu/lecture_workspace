package com.sinse.NetworkApp.multicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//접속자 마다 , 1:1 대응하여 인스턴스가 생성될 대화용 쓰레드
//대화가 가능 하려면 , 입력 , 출력 스트림이 필요함
public class ServerThread extends Thread {
	GUIServer guiServer;
	Socket socket; //서버로부터 넘겨받을 소켓 , 스트림을 뽑을 수 있으므로...
	BufferedReader buffr;
	BufferedWriter buffw;

	// 소켓을 서버로부터 전달받으면 된다... 접속자가 들어올때마다
	public ServerThread(GUIServer guiServer, Socket socket) {
		this.guiServer = guiServer;
		this.socket = socket;

		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void listen() {
		String msg = null;

		try {
			msg = buffr.readLine();
			guiServer.area.append(msg + "\n");
			send(msg);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String msg) {

		try {
			buffw.write(msg + "\n");
			buffw.flush(); // 출력스트림중 버퍼처리 된 것만...
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true) {
			listen();
		}
	}

}

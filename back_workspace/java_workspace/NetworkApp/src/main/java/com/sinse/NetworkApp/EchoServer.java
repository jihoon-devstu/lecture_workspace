package com.sinse.NetworkApp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	ServerSocket server;
	public EchoServer() {
		//0~1023 시스템 점유 포트이므로 쓰면 안됨.
		try {
			server = new ServerSocket(9999);
			System.out.println("서버 객체 생성");
			Socket socket = server.accept(); 
			//사용자의 접속을 청취하는 메서드 , 접속이 발생할때까지
			//무한정 대기 상태에 빠짐
			//네트워크 접속자가 발생하면 , 그 접속자에 대응되는 소켓 객체를 반환받는다...
			//이 소켓이 있어야 통신 모든 구현이 가능
			InetAddress addr = socket.getInetAddress();
			String ip = addr.getHostAddress();
			
			System.out.println("접속자 발견 !!!"+ip);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new EchoServer();
	}
}

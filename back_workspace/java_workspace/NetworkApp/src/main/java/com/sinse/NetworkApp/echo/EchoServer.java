package com.sinse.NetworkApp.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	ServerSocket server;

	public EchoServer() {

		try {
			server = new ServerSocket(9999);
			Socket socket = server.accept();
			String ip = socket.getInetAddress().getHostAddress();

			System.out.println(ip + "접속 발견");

			// 소켓을 통해 데이터를 주고 받을 수 있는 스트림을 얻자 !!
			// stream은 방향에 따라 - 입력 , 출력
			// 데이터 처리 방식에 따라 - 바이트 ~~Strream , 문자 ~~Reader , Writer , 버퍼
			// 바이트 기반의 입력 스트림을 얻어옴..
			InputStream is = socket.getInputStream(); //바이트 기반의 입력 스트림을 얻어옴..
			OutputStream out = socket.getOutputStream(); //바이트 기반의 출력 스트림을 얻어옴
			
			//원래 무한 루프는 엄청난 속도이므로 , 프로그램에서 사용 시 주의해야함.
			//하지만 , 스트림 처리에서는 read() 메서드 자체가 상대방의 메시지를 받을때 까지 대기상태에 빠지므로
			//부하를 일으키지 않는다.
			while (true) {
				int data = is.read();
				System.out.println((char) data);
				out.write(data); //읽어들인 1바이트 데이터를 그대로 보내버림(출력)
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new EchoServer();
	}
}

package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static final int PORT = 7000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;

		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding): Socket Address(IP Address + Port) => 노트 필기
			serverSocket.bind(new InetSocketAddress("127.0.0.1", PORT));

//			System.out.println("blocking 안됨"); // blocking안되면 이거 출력됨
			// 3. accept

			while(true) {
				Socket socket = serverSocket.accept(); // blocking(프로그램 멈춤)
				new EchoServerReceiveThread(socket).start();
			}
			
		} catch (IOException e) { // server에서 exception발생시
			System.out.println("[server] error:" + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed())
					serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

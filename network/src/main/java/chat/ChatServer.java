package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	private static final int PORT = 9999;

	public static void main(String[] args) {
		List<Writer> listWriter = new ArrayList<Writer>();
		ServerSocket serverSocket = null;

		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩
			serverSocket.bind(new InetSocketAddress("127.0.0.1", PORT));
			System.out.println("[연결기다림] 127.0.0.1:"+PORT);

			// 3. accept()
			while (true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriter).start();
			}
		} catch (IOException e) {
			System.out.println("[server] error:" + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void log(String string) {
		// TODO Auto-generated method stub
		System.out.println("[server]"+string);
	}
}

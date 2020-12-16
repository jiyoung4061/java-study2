package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientThread extends Thread {
	private Socket socket = null;

	public ChatClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// reader를 통해 읽은 데이터 콘솔에 출력하기 - message로 처리
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

			while (true) {
				String data = null;
				try {
					data = br.readLine(); // socketexception발생
				} catch (SocketException e) {
					System.out.println("socket exception 발생");
				}
				if (data == null) {
					break;
				}
				System.out.println(data);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("[chat client] SocketException : Socket closed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

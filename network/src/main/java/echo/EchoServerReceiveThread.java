package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoServerReceiveThread extends Thread {
	private Socket socket;
	
	public EchoServerReceiveThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		InetSocketAddress remoteInetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		InetAddress remoteInetAddress = remoteInetSocketAddress.getAddress();
		String remoteHostAddress = remoteInetAddress.getHostAddress(); // 반대편의 ip주소
		int remotePort = remoteInetSocketAddress.getPort();
		System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");

		
		try {
			// 4. io stream 받아오기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true); 
			// flush? buffer가 차면 자동으로 비워줌

			while (true) {
				// 5. 데이터 읽기
				String data = br.readLine(); //bufferedreader에서 개행문자가 들어올때까지 읽음

				if (data == null) {
					// clinet가 socket을 정상적으로 공유했다는 뜻(정상 종료)
					System.out.println("[server] close by client");
					break;
				}

				System.out.println("[server] receive: " + data);

				// 6. 데이터 쓰기
				pw.println(data);
			}

		} catch (SocketException e) {
			// server 비정상 종료
			System.out.println("[client] suddenly closed by server");
		} catch (IOException e) {
			System.out.println("[server] error:" + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed())
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

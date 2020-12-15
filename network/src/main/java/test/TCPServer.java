package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;

		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding): Socket Address(IP Address + Port) => 노트 필기
			serverSocket.bind(new InetSocketAddress("127.0.0.1", 6000));

//			System.out.println("blocking 안됨"); // blocking안되면 이거 출력됨
			// 3. accept
			Socket socket = serverSocket.accept(); // blocking(프로그램 멈춤)

			InetSocketAddress remoteInetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			InetAddress remoteInetAddress = remoteInetSocketAddress.getAddress();
			String remoteHostAddress = remoteInetAddress.getHostAddress(); // 반대편의 ip주소
			int remotePort = remoteInetSocketAddress.getPort();
			System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");

			try {
				// 4. io stream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking

					if (readByteCount == -1) {
						// clinet가 socket을 정상적으로 공유했다는 뜻(정상 종료)
						System.out.println("[server] close by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount, "UTF-8");
					System.out.println("[server] receive: " + data);

					// 6. 데이터 쓰기
					os.write(data.getBytes("UTF-8"));
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

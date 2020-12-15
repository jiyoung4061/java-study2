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
			// 1. 서버 소켓 생성 (서버 소켓 : 요청을 받아주는 소켓!)
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding): Socket Address(IP Address + Port) => 노트 필기
//			InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 5000);
//			serverSocket.bind(inetSocketAddress);
			serverSocket.bind(new InetSocketAddress("127.0.0.1", 6000));

			// 3. accept : connect를 받아줌
			Socket socket = serverSocket.accept(); // blocking(프로그램 멈춤) 왜? 누군가 connect를 해줘야 accept를 하니까 connect 요청 올때까지 잠시 멈춤
			// System.out.println("blocking 안됨"); // blocking안되면 이거 출력됨

			InetSocketAddress remoteInetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			InetAddress remoteInetAddress = remoteInetSocketAddress.getAddress();
			String remoteHostAddress = remoteInetAddress.getHostAddress(); // 반대편의 ip주소
			int remotePort = remoteInetSocketAddress.getPort();
			System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");

			try {
				// 4. IOStream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking(쓸때까지 기다림)

					if (readByteCount == -1) {
						// clinet가 socket을 정상적으로 종료했다는 뜻
						System.out.println("[server] close by client");
						break;
					}

					// byte[] 배열을 String으로 인코딩하는 작업
					String data = new String(buffer, 0, readByteCount, "UTF-8"); // buffer배열을 0부터 readByteCount만큼 utf-8로 인코딩해라
					System.out.println("[server] receive: " + data);

					// 6. 데이터 쓰기
					os.write(data.getBytes("UTF-8")); //String을 byte[]로 
				}

			} catch (SocketException e) {
				// server 비정상 종료
				System.out.println("[client] suddenly closed by server");
			} catch (IOException e) { //데이터 통신시 소켓에서 exception발생할 때
				System.out.println("[server] error:" + e);
			} finally { 
				try {
					if (socket != null && !socket.isClosed())
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) { // 서버 소켓에대한 exception
			System.out.println("[server] error:" + e);
		} finally { // server socket close!
			try {
				if (serverSocket != null && !serverSocket.isClosed())// null일 경우 exception발생, 또 닫힌 socket을 또 닫으려고하면 오류나므로!
					serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

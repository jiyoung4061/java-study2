package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Chatclient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9999;
	
	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;
		
		try {
			// 키보드 연결
			scanner = new Scanner(System.in);
			// 소켓 생성	
			socket = new Socket();
			// 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			// reader, writer 생성
			PrintWriter pw = new PrintWriter( new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			// join 프로토콜
			System.out.print("닉네임>>");
			String nickName = scanner.nextLine();
			pw.println("join:"+nickName);
			String ack = br.readLine();
			System.out.println(ack);
			if("join:ok".equals(ack)) {
				System.out.println(nickName+"님이 입장하셨습니다.");
			}
			pw.flush();
			
			// chatclientThread 시작(데이터 수신 및 프로토콜 처리 작업)
			new ChatClientThread(socket).start();
			
			// 키보드 입력 처리
			while(true) {
				String input = scanner.nextLine();
				
				if("quit".equals(input)) {
					// quit프로토콜 처리
					pw.println(input);
					break;
				} else {
					// message 처리
					String data = "message:"+input;
					pw.println(data);
				}
			}
		} catch(IOException e) {
			log("error:"+e);
		}  finally {
			// 자원처리
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
				scanner.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static void log(String string) {
		System.out.println(string);
	}
}

package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ChatServerThread extends Thread {
	private Socket socket;
	private String nickName;
	private List<Writer> listWriter = null;

	public ChatServerThread(Socket socket, List<Writer> listWriter) {
		this.socket = socket;
		this.listWriter = listWriter;
	}

	@Override
	public void run() {
		InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
		InetAddress remoteInetAddress = remoteInetSocketAddress.getAddress();
		String remoteHostAddress = remoteInetAddress.getHostAddress();
		int remotePort = remoteInetSocketAddress.getPort();
		
		System.out.println("[server] connected by client["+remoteHostAddress+":"+remotePort+"]");
		
		try {
			// io stream 받아오기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			
			while(true) {
				// 데이터 읽기
				String request = br.readLine();
				if(request == null) {
					ChatServer.log("클라이언트로부터 연결 끊김");
					doQuit(pw);
					break;
				}
				// protocol 분석
				String[] tokens = request.split(":");
				System.out.println("token:"+tokens[0]);
				if("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if("quit".equals(tokens[0])) {
					doQuit(pw);
					break;
				} else {
					ChatServer.log("알수 없는 요청 :"+ tokens[0]);
				}
			}
			
		} catch(SocketException e) {
			System.out.println("[client] suddenly closed by server");
		} catch(IOException e) {
			System.out.println("[server] error:"+e);
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);
		
		String data = nickName+"님이 퇴장하셨습니다";
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		synchronized(writer) {
			listWriter.remove(writer);
		}
	}

	private void doMessage(String string) {
		/* 구현할 부분*/
		System.out.println("messaging");
		String data = nickName+">>"+string;
		broadcast(data);
	}

	private void doJoin(String nickName, PrintWriter writer) {
		this.nickName = nickName;
		
		String data = nickName+"님이 참여하셨습니다";
		broadcast(data);
		
		// writepool에 저장
		addWriter(writer);
	}

	private void addWriter(Writer writer) {
		synchronized(listWriter){
			listWriter.add(writer);
		}
	}

	private void broadcast(String data) {
		synchronized(listWriter) {
			for(Writer writer : listWriter) {
				PrintWriter pw = (PrintWriter)writer;
				pw.println(data);
				pw.flush();
			}
		}
	}
	
}

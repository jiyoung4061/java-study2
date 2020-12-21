package http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread { // Tread임!
	private Socket socket;
	private static final String DOCUMENT_ROOT="./webapp";
	
	public RequestHandler( Socket socket ) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// get IOStream 
			// outputstream사용하는 이유?
			// web서버는 string뿐만아니라 html, image등도 보내야함 그래서 byte로 처리하기위해 outputstream사용		
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			consoleLog( "connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort() );
			
			String request = null;
			while(true) {
				String line = br.readLine();

				if(line == null){ // 브라우저가 연결을 끊음
					consoleLog("closed by client");
					break;
				}
				// request Header의 첫번재 라인만 읽음
				if(request == null) {
					request = line;
					break;
				}
			}
			
			consoleLog(request);
			
			String[] tokens = request.split(" ");
			if("GET".equals(tokens[0])) {
				responseStaticResource(outputStream, tokens[1], tokens[2]);
			} else { // POST, DELETE, PUT, HEAD, CONNECT				
				response400Error(outputStream, tokens[1], tokens[2]);
			}

		} catch( Exception ex ) {
			consoleLog( "error:" + ex );
		} finally {
			// clean up
			try{
				if( socket != null && socket.isClosed() == false ) {
					socket.close();
				}
			} catch( IOException ex ) {
				consoleLog( "error:" + ex );
			}
		}			
	}

	public void response400Error(OutputStream outputStream, String uri, String protocol) throws IOException{
		uri = "/error/400.html";
		
		File file = new File(DOCUMENT_ROOT + uri);
		
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		/* 
		HTTP/1.1 400 Bad Request\r\n
		Content-Type:text/html; charset=utf-8\r\n
		\r\n
		html 에러 문서(./webapp/error/400.html)
		 */
		outputStream.write((protocol + " 400 Bad Request\r\n").getBytes( "UTF-8" ));
		outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		outputStream.write("\r\n".getBytes("UTF-8"));
		outputStream.write(body);
	}
	
	public void response404Error(OutputStream outputStream, String uri, String protocol) throws IOException{
		uri = "/error/404.html";
		File file = new File(DOCUMENT_ROOT+uri);
		
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		outputStream.write((protocol + " 404 BadRequest\r\n").getBytes( "UTF-8" ));
		outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		outputStream.write("\r\n".getBytes("UTF-8"));
		outputStream.write(body);
	}
	
	public void responseStaticResource(OutputStream outputStream, String uri, String protocol) throws IOException{ //uri : resource의 위치
		// throws IOException : 해당 함수를 호출하는 쪽에서 try catch해주어야함! 
		if("/".equals(uri)) {
			uri = "/index.html";
		}
		
		// file생성 후, 존재하지 않으면 404error
		File file = new File(DOCUMENT_ROOT + uri);
		if(!file.exists()) {
			// 404 Not Found
			response404Error(outputStream, uri, protocol);
			return;
		}
		
		// nio(new io)
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		//response
		outputStream.write((protocol + " 200 OK\r\n").getBytes( "UTF-8" ));
		outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		outputStream.write("\r\n".getBytes("UTF-8"));
		outputStream.write(body);
	}
	
	public void consoleLog( String message ) {
		System.out.println( "[RequestHandler#" + getId() + "] " + message );
	}
}

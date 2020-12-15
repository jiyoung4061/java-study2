package http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
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
			// web서버는 string뿐만아니라 html, image등도 보내야함 그래서 byte로 처리해야하므로			
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			consoleLog( "connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort() );
			
			String request = null;
			while(true) {
				String line = br.readLine();

				// 브라우저가 연결을 끊음
				if(line == null){
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
				// HTTP/1.1 400 Bad Request\r\n
				// Content-Type:text/html; charset=utf-8\r\n
				// \r\n
				// html 에러 문서(./webapp/error/400.html)
				
				//response404Error();
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

	public void responseStaticResource(OutputStream outputStream, String uri, String protocol) throws IOException{ //uri : resource의 위치
		if("/".equals(uri)) {
			uri = "/index.html";
		}
		
		File file = new File(DOCUMENT_ROOT + uri);
		if(!file.exists()) {
			// 404 Not Found
			// response404Error();
			return;
		}
		
		// nio
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

package chat.finish.client;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private Socket socket = null;

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}
	
	public ChatWindow(String name, Socket socket) {
		this.socket = socket;
	}

	public void show() {
		/*
		 * 1. UI 초기화
		 */
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("채팅방 나오기 프로토콜 구현...");
				System.exit(0);
			}
		});
		frame.setVisible(true);
		frame.pack();
		
		/*
		 * 2. IOStream 초기화
		 */
		
		
		/*
		 * 3. 쓰레드 생성
		 */
		new ChatClientThread(socket).start();
	}
	
	private void sendMessage() {
		String message = textField.getText();
		textField.setText("");
		textField.requestFocus();
		
		// 소켓을 통해 전송
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			while (true) {
				try {
					message = br.readLine(); // socketexception발생
				} catch (SocketException e) {
					System.out.println("socket exception 발생");
				}
				if (message == null) {
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
		// 소켓을 통해 메세지가 온 경우(실제로는 쓰레드에서 있어야 하는 코드...)
		textArea.append("둘리:" + message);
		textArea.append("\n");
		}
	}

	
	public class ChatClientThread extends Thread{
		Socket socket = null;
		
		public ChatClientThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			sendMessage();
		}
	}
}

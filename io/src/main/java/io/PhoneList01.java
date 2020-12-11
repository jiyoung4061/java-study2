package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BufferedReader br = null;
		try {
			// 기반 스트림(System.in, 표준입력, stdin)
			FileInputStream fis = new FileInputStream("phone.txt");
			// 보조 스트림1(byte|byte|bye => char)
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			
			// 보조 스트림2(char1|cahr2|char3|\n => "char1char2char3" //string으로 만들어줌 
			br = new BufferedReader(isr);
			
			//
			String line = null; 
					
			while((line = br.readLine()) != null) {
				System.out.println(line);
				while((line = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(line, "\t");
					
					int index = 0; 
					while(st.hasMoreElements()) {
						String token = st.nextToken();
						
						if(index == 0 ) { //이름
							System.out.print(token+":");
						} else if(index == 1) { // 전화번호 1
							System.out.print(token+"-");
						} else if(index ==2) {
							System.out.print(token+"-");
						} else {
							System.out.println(token);
						}
						
						index++;
					}
			};
			}
			
		} catch(UnsupportedEncodingException e) {
			System.out.println("error:"+e);
		} catch (IOException e) {
			System.out.println("error:"+e);
		} finally {
			try {				
				br.close();
			} catch (IOException e) {
				System.out.println("error:"+e);	
			}
		}
	}

}

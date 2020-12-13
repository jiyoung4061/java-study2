package prob03;

public class Book {
	private int no;

	private String title;
	private String author;
	private int stateCode; // 1: 재고있음, 0: 대여중
	
	public Book(int no, String title, String author) {
		this.no = no;
		this.title = title;
		this.author = author;
		stateCode = 1;
	}
	
	public void rent(int num) {
		stateCode = 0;
		System.out.println(title+"이(가) 대여되었습니다.");
	}
	
	public void print() {
		String state = stateCode == 1?"재고있음" : "대여중";
		System.out.println("책 제목:"+title+", 작가:"+author+", 대여 유무:"+state);
	}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
}

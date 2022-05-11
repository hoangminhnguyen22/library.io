package r1290;

public class BookDTO {
	String book_id;
	String book_title;
	String book_author;
	int book_status;
	public BookDTO(String book_id, String book_title, String book_author, int book_status) {
		this.book_id = book_id;
		this.book_title = book_title;
		this.book_author = book_author;
		this.book_status = book_status;
	}
	public BookDTO()
    {
        
    }
}

package r1290;

public class BorrowDetailDTO {
	String borrow_id;
	String book_id;
	public BorrowDetailDTO(String borrow_id, String book_id) {
		this.borrow_id = borrow_id;
		this.book_id = book_id;
	}
	public BorrowDetailDTO()
    {
        
    }
}

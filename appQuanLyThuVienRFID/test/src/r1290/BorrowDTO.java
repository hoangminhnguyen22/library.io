package r1290;

import java.sql.Timestamp;

public class BorrowDTO {
	String borrow_id;
	Timestamp borrow_begindate;
	Timestamp borrow_returndate;
	int borrow_status;
	int user_id;
	public BorrowDTO(String borrow_id, Timestamp borrow_begindate, Timestamp borrow_returndate, int borrow_status, int user_id) {
		this.borrow_id = borrow_id;
		this.borrow_begindate = borrow_begindate;
		this.borrow_returndate = borrow_returndate;
		this.borrow_status = borrow_status;
		this.user_id = user_id;
	}
	public BorrowDTO()
    {
        
    }
}

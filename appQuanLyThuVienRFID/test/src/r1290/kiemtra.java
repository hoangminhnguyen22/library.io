package r1290;

import java.util.ArrayList;
import java.util.Scanner;

import com.caen.RFIDLibrary.CAENRFIDLogicalSource;
import com.caen.RFIDLibrary.CAENRFIDLogicalSourceConstants;
import com.caen.RFIDLibrary.CAENRFIDPort;
import com.caen.RFIDLibrary.CAENRFIDReader;
import com.caen.RFIDLibrary.CAENRFIDReaderInfo;
import com.caen.RFIDLibrary.CAENRFIDTag;

//import javax.comm.UnsupportedCommOperationException;
import gnu.io.UnsupportedCommOperationException;

public class kiemtra {
	MyconnectUnit Connectunit;
    MySQLConnect connect;
	public static void main(String[] args) throws Exception {
		//khai báo mảng và classDAO
		ArrayList ArrIDBook = new ArrayList<>();
		BorrowDetailDAO dao = new BorrowDetailDAO();
		//khai báo mảng và class của book
		BookDAO book = new BookDAO();
		ArrayList<BookDTO> listBook = new ArrayList<BookDTO>();
		//khia báo mảng và class của borrow
		BorrowDAO borrow = new BorrowDAO();
		ArrayList<BorrowDTO> listBorrow = new ArrayList<BorrowDTO>();
		//gắn cờ
		int flag = 1;
		
		
		//nhập hóa đơn
		Scanner sc = new Scanner(System.in);
    	System.out.print("Nhập mã hóa đơn bạn đã đăng ký trên thư viện: ");
        String a = sc.nextLine();
    	
//		System.out.println(a);
        
        //lấy id book từ hóa đơn
        ArrIDBook = dao.LayMangBookDangKi(a);
        listBook = book.docDssp();
        listBorrow = borrow.docDssp();
        
        // kiểm tra trạng thái mã mượn qua status
        for(int i=0; i<listBorrow.size();i++) {
        	if(listBorrow.get(i).borrow_id.equals(a)) {
	        	if(listBorrow.get(i).borrow_status == 1) {
	        		System.out.println("Mã mượn này đã được duyệt");
	        		System.exit(0);
	        	}
        	}
        }
//        System.out.println(listBook.size());
        
//	    test mảng list book
//	    for (int i = 0; i < listBook.size(); i++)
//		{
//	    	System.out.println(listBook.get(i).book_id +" "+
//	    			listBook.get(i).book_title +" "+	
//	    			listBook.get(i).book_status +" "+
//	    			listBook.get(i).book_author);
//		}
        
//      test mảng bookid từ borrowdetail
//      for (int i = 0; i < ArrIDBook.size(); i++)
//		{
//			System.out.println(ArrIDBook.get(i));
//		}
         
		
		ArrayList<rfidDTO> MyTags = new ArrayList<rfidDTO>();
//		MyTags.add(new rfidDTO("E28011606000020958CD98FE", "ant0", null, 0));
//		MyTags.add(new rfidDTO("1", "ant0", null, 0));
		MyTags.add(new rfidDTO("2", "ant0", null, 0));
		MyTags.add(new rfidDTO("3", "ant0", null, 0));
		MyTags.add(new rfidDTO("4", "ant0", null, 0));
//		MyTags.add(new rfidDTO("5", "ant0", null, 0));
		
		ArrayList ArrTagID = new ArrayList<>();
		
		for (int i = 0; i < MyTags.size(); i++)
        {
//            System.out.println("EPC: "+ MyTags.get(i).EPC  +
//                    " | Antenna : " +MyTags.get(i).Antenna +
//                    " | TID:"+ (MyTags.get(i).TID) +
//                    " | RSSI : "+Integer.valueOf(MyTags.get(i).RSSI));
            
            ArrTagID.add(MyTags.get(i).EPC);
        }
		
//		test mảng id trong mytags
//		for (int i = 0; i < ArrTagID.size(); i++)
//		{
//			
//			System.out.println(ArrTagID.get(i));
//		}
		
		
//		test 2 mảng id book và id tag
//		for (int i = 0; i < ArrTagID.size(); i++) {
//			System.out.println(ArrTagID.get(i));
//			for (int j = 0; j < ArrIDBook.size(); j++) {
//				System.out.println(ArrIDBook.get(j));
//			}
//			System.out.println("---------");
//		}
		
		
//		mượn sách
		for (int i = 0; i < ArrTagID.size(); i++) {
			if(ArrIDBook.size() != 0) {
				for (int j = 0; j < ArrIDBook.size(); j++) {
						if(ArrIDBook.get(j).equals(ArrTagID.get(i))) {
							//duyệt mảng book
							for (int k = 0; k < listBook.size(); k++) {
								if(ArrIDBook.get(j).equals(listBook.get(k).book_id)) {
									System.out.println(ArrIDBook.get(j)+"| Bạn đã mượn thành công cuốn sách "+ listBook.get(k).book_title + "| sách đã hủy kích hoạt");
									//book.dataUpdateSL(listBook.get(k), 0);
								}								
							}
							ArrIDBook.remove(ArrIDBook.get(j));
							j--;
							ArrTagID.remove(ArrTagID.get(i));
							i--;
							break;
						}
						
				}
				
			}//else {
//			ArrTagID.remove(ArrTagID.get(i));
//			i--;
			//}
		}
		
		//tách ra từ else
		if(ArrTagID.size() != 0) {											// thêm
			for (int l = 0; l < ArrTagID.size(); l++) {						//thêm
				for (int k = 0; k < listBook.size(); k++) {
					if(ArrTagID.get(l).equals(listBook.get(k).book_id)) {
							System.out.println(ArrTagID.get(l)+"| cuốn sách " + listBook.get(k).book_title + " chưa được đăng kí | bạn không thể mang ra thư viện");
							flag = 0;
					}
				}
			}//thêm
		}//thêm
		
		
		if(ArrIDBook.size() != 0) {
			for (int i = 0; i < ArrIDBook.size(); i++)
			{
				for (int k = 0; k < listBook.size(); k++) {
					if(ArrIDBook.get(i).equals(listBook.get(k).book_id)) {
						System.out.println(ArrIDBook.get(i)+"| bạn chưa lấy cuốn sách "+ listBook.get(k).book_title);
					}
				}
			}
		}else {
			if(flag == 1) {
				for(int i = 0; i < listBorrow.size(); i++) {
					if(listBorrow.get(i).borrow_id.equals(a)) {
						//borrow.dataUpdateSL(listBorrow.get(i), 1);			//cật nhật trạng thái mượn
						System.out.println("hóa đơn của bạn đã được duyệt");
					}
				}
			}
		}

	}

}

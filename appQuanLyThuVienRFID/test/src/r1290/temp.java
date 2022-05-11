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

public class temp {
	MyconnectUnit Connectunit;
    MySQLConnect connect;
	public static void main(String[] args) throws Exception {
		//khai báo mảng và classDAO
		ArrayList ArrIDBook = new ArrayList<>();
		BorrowDetailDAO dao = new BorrowDetailDAO();
		BookDAO book = new BookDAO();
		ArrayList<BookDTO> listBook = new ArrayList<BookDTO>();
		
		//nhập hóa đơn
		Scanner sc = new Scanner(System.in);
    	System.out.print("Nhập mã hóa đơn bạn đã đăng ký trên thư viện: ");
        String a = sc.nextLine();
    	
//        System.out.println(a);
//        lấy id book từ hóa đơn
        ArrIDBook = dao.LayMangBookDangKi(a);
        listBook = book.docDssp();
//        System.out.println(listBook.size());
        
//        test mảng list book
//        for (int i = 0; i < listBook.size(); i++)
//    		{
//        	System.out.println(listBook.get(i).book_id +" "+
//        			listBook.get(i).book_title +" "+	
//        			listBook.get(i).book_status +" "+
//        			listBook.get(i).book_author);
//    		}
        
//        test mảng bookid từ borrowdetail
//        for (int i = 0; i < ArrIDBook.size(); i++)
//		{
//			System.out.println(ArrIDBook.get(i));
//		}
         
		
		ArrayList<rfidDTO> MyTags = new ArrayList<rfidDTO>();
		MyTags.add(new rfidDTO("123", "ant0", null, 0));
		MyTags.add(new rfidDTO("456", "ant0", null, 0));
//		MyTags.add(new rfidDTO("1", "ant0", null, 0));
//		MyTags.add(new rfidDTO("2", "ant0", null, 0));
		MyTags.add(new rfidDTO("12", "ant0", null, 0));
		
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
		
		
//		mượn sách thành công nhưng chưa có tên
		for (int i = 0; i < ArrTagID.size(); i++) {
			if(ArrIDBook.size() != 0) {
				for (int j = 0; j < ArrIDBook.size(); j++) {
						if(ArrIDBook.get(j).equals(ArrTagID.get(i))) {
							//duyệt mảng book
							for (int k = 0; k < listBook.size(); k++) {
								if(ArrIDBook.get(j).equals(listBook.get(k).book_id)) {
									System.out.println(ArrIDBook.get(j)+"| Bạn đã mượn thành công cuốn sách "+ listBook.get(k).book_title + "| sách đã hủy kích hoạt");
								}
							}
							ArrIDBook.remove(ArrIDBook.get(j));
							j--;
						}
				}
			}else {
//				for (int k = 0; k < listBook.size(); k++) {
//					if(ArrTagID.get(i).equals(listBook.get(k).book_id)) {
//							System.out.println(ArrTagID.get(i)+"| cuốn sách " + listBook.get(k).book_title + " chưa được đăng kí | bạn không thể mang ra thư viện");
//					}
//				}
			}
		}
		if(ArrIDBook != null) {
			for (int i = 0; i < ArrIDBook.size(); i++)
			{
				for (int k = 0; k < listBook.size(); k++) {
					if(ArrIDBook.get(i).equals(listBook.get(k).book_id)) {
						System.out.println(ArrIDBook.get(i)+"| bạn chưa lấy cuốn sách "+ listBook.get(k).book_title);
					}
				}
			}
		}

	}

}

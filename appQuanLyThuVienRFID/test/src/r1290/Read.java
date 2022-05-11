package r1290;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import com.caen.RFIDLibrary.CAENRFIDLogicalSource;
import com.caen.RFIDLibrary.CAENRFIDLogicalSourceConstants;
import com.caen.RFIDLibrary.CAENRFIDPort;
import com.caen.RFIDLibrary.CAENRFIDReader;
import com.caen.RFIDLibrary.CAENRFIDReaderInfo;
import com.caen.RFIDLibrary.CAENRFIDTag;

import gnu.io.UnsupportedCommOperationException;
//import javax.comm.UnsupportedCommOperationException;

public class Read {
	MyconnectUnit Connectunit;
    MySQLConnect connect;
    public static void main(String[] args) throws Exception {
    	
    	//------------------------------------------------------------------------------
//
//        //khai báo mảng và classDAO
//		ArrayList ArrIDBook = new ArrayList<>();
//		BorrowDetailDAO dao = new BorrowDetailDAO();
//		//khai báo mảng và class của book
//		BookDAO book = new BookDAO();
//		ArrayList<BookDTO> listBook = new ArrayList<BookDTO>();
//		//khia báo mảng và class của borrow
//		BorrowDAO borrow = new BorrowDAO();
//		ArrayList<BorrowDTO> listBorrow = new ArrayList<BorrowDTO>();
//		//gắn cờ
//		int flag = 1;
//		int kthd = 0;
//    			
//		//nhập hóa đơn
//		Scanner sc = new Scanner(System.in);
//    	System.out.print("Nhập mã hóa đơn bạn đã đăng ký trên thư viện: ");
//        String a = sc.nextLine();
//        
//	    //lấy id book từ hóa đơn
//	    ArrIDBook = dao.LayMangBookDangKi(a);
//	    listBook = book.docDssp();
//	    listBorrow = borrow.docDssp();
//	    
//	 // kiểm tra trạng thái mã mượn qua status
//        for(int i=0; i<listBorrow.size();i++) {
//        	if(listBorrow.get(i).borrow_id.equals(a)) {
//	        	if(listBorrow.get(i).borrow_status == 1) {
//	        		System.out.println("Mã mượn này đã được duyệt");
//	        		System.exit(0);
//	        	}
//        	}
//        }
//	    
////	    test mảng list book	    
////	    for (int i = 0; i < listBook.size(); i++)
////		{
////	    	System.out.println(listBook.get(i).book_id +" "+
////	    			listBook.get(i).book_title +" "+	
////	    			listBook.get(i).book_status +" "+
////	    			listBook.get(i).book_author);
////		}
//	    
////      test mảng bookid từ borrowdetail	    
////	    for (int i = 0; i < ArrIDBook.size(); i++)
////		{
////			System.out.println(ArrIDBook.get(i));
////		}
//	    
	    //-------------------------------------------------------------------------------
    	
        CAENRFIDReader MyReader = new CAENRFIDReader();
        try {
            MyReader.Connect(CAENRFIDPort.CAENRFID_TCP, "192.168.1.2");
            CAENRFIDLogicalSource MySource = MyReader.GetSource("Source_0");

            //get Reader Infor
            CAENRFIDReaderInfo Info = MyReader.GetReaderInfo();

            String Model = Info.GetModel();
            String SerialNumber = Info.GetSerialNumber();
            String FWRelease = MyReader.GetFirmwareRelease();
            // tinh theo cong de xac dinh khoang cach
            int power = MyReader.GetPower();

            // in ra thong tin
            System.out.println("Model: "+Model);
            System.out.println("SerialNumber: "+SerialNumber);
            System.out.println("FWRelease: "+FWRelease);
            System.out.println("power: "+power);

            System.out.println("");
            //thoi gian nhan
            MySource.SetSession_EPC_C1G2(CAENRFIDLogicalSourceConstants.EPC_C1G2_SESSION_S1);
            
            // chua thong tin cua cac tag
            // chua tat ca tong tin quet tren device
            CAENRFIDTag[] MyTags = MySource.InventoryTag();
            
            //tạo mảng chứa id tag
            ArrayList ArrTagID = new ArrayList<>();
            
            if (MyTags.length > 0)
            {
                // show list cac thong tin san pham
                //id san pham la duy nhat: exmple 48718273123
                //
                for (int i = 0; i < MyTags.length; i++)
                {
                    System.out.println("EPC: "+ hex(MyTags[i].GetId())  +
                            " | Antenna : " +MyTags[i].GetAntenna() +
                            " | TID:"+ (MyTags[i].GetTID()) +
                            " | RSSI : "+Integer.valueOf(MyTags[i].GetRSSI()));
                    //lấy id của tag
                    ArrTagID.add(hex(MyTags[i].GetId()));
                }
            }
            //---------------------------------------------------------------------------------
//        	test mảng id trong mytags
//    		for (int i = 0; i < ArrTagID.size(); i++)
//    		{
//    			System.out.println(ArrTagID.get(i));
//    		}
            
//            //mượn sách
////			if(kthd==0) {
//	    		for (int i = 0; i < ArrTagID.size(); i++) {
//	    			if(ArrIDBook.size() != 0) {
//	    				for (int j = 0; j < ArrIDBook.size(); j++) {
//	    						if(ArrIDBook.get(j).equals(ArrTagID.get(i))) {
//	    							//duyệt mảng book
//	    							for (int k = 0; k < listBook.size(); k++) {
//	    								if(ArrIDBook.get(j).equals(listBook.get(k).book_id)) {
//	    									System.out.println(ArrIDBook.get(j)+"| Bạn đã mượn thành công cuốn s "+ listBook.get(k).book_title + "| sách đã hủy kích hoạt");
//	    									book.dataUpdateSL(listBook.get(k), 0);
//	//    									Vector row = new Vector();
//	//								        row.add(ArrIDBook.get(j));
//	//								        row.add(listBook.get(k).book_title);	
//	//								        row.add("sách đã hủy kích hoạt");
//	//								        model.addRow(row);
//	    								}
//	    							}
//	    							ArrIDBook.remove(ArrIDBook.get(j));
//	    							j--;
//	    							ArrTagID.remove(ArrTagID.get(i));
//	    						}
//	    				}
//	    			}//else {
//	    				
//	    			//}
//	    		}
//	    		
//	    		//tách ra khỏi else
//	    		if(ArrTagID.size() != 0) {											
//	    			for (int l = 0; l < ArrTagID.size(); l++) {	
//			    		for (int k = 0; k < listBook.size(); k++) {
//							if(ArrTagID.get(l).equals(listBook.get(k).book_id)) {
//									System.out.println(ArrTagID.get(l)+"| cuốn sách " + listBook.get(k).book_title + " chưa được đăng kí | bạn không thể mang ra thư viện");
//	//								Vector row = new Vector();
//	//						        row.add(ArrTagID.get(l));
//	//						        row.add(listBook.get(k).book_title);	
//	//						        row.add("chưa được đăng kí");
//	//						        model.addRow(row);
//									flag = 0;
//							}
//						}
//	    			}
//	    		}
//	    		
//	    		if(ArrIDBook.size() != 0) {
//	    			for (int i = 0; i < ArrIDBook.size(); i++)
//	    			{
//	    				for (int k = 0; k < listBook.size(); k++) {
//	    					if(ArrIDBook.get(i).equals(listBook.get(k).book_id)) {
//	    						System.out.println(ArrIDBook.get(i)+"| bạn chưa lấy cuốn sách "+ listBook.get(k).book_title);
//	//    						Vector row = new Vector();
//	//					        row.add(ArrIDBook.get(i));
//	//					        row.add(listBook.get(k).book_title);	
//	//					        row.add("chưa lấy");
//	//					        model.addRow(row);
//	    					}
//	    				}
//	    			}
//	    		}else {
//	    			if(flag == 1) {
//	    				for(int i = 0; i < listBorrow.size(); i++) {
//	    					if(listBorrow.get(i).borrow_id.equals(a)) {
//	    						borrow.dataUpdateSL(listBorrow.get(i), 1);
//	    						System.out.println("hóa đơn của bạn đã được duyệt");
//	//    						thanhcong.setText("Mã mượn được duyệt");
//	    					}
//	    				}
//	    			}
//	    		}
////        }
//
//
//    		//----------------------------------------------------------------------------------------------
//    		
            MyReader.Disconnect();
        }catch(Exception ex) {
            System.out.println(ex);
            if(MyReader != null) {
                MyReader.Disconnect();
            }
        }

    }

    /**
     * @return tra ve moi chuoi duoc in hoa
     */
    public static String hex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
            // upper case
            // result.append(String.format("%02X", aByte));
        }
        return result.toString().toUpperCase();
    }


}

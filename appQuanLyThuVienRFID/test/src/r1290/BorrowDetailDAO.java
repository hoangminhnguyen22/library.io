package r1290;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowDetailDAO {
	MyconnectUnit Connectunit;
    MySQLConnect connect;
    
    public BorrowDetailDAO() throws Exception {
        this.Connectunit = new MyconnectUnit();
        this.connect = new MySQLConnect();
    }
    
    public  ArrayList<BorrowDetailDTO> docDssp()
    {
        ArrayList dssp = new ArrayList<BorrowDetailDTO>();
        
        try {
            ResultSet docsp = Connectunit.Select("borrowdetail");
            while (docsp.next()) {               
                BorrowDetailDTO sp = new BorrowDetailDTO();
                sp.borrow_id= docsp.getString("borrow_id");
                sp.book_id= docsp.getString("book_id");
                dssp.add(sp);
                
            }
        } catch (Exception e) {
            System.out.println("Loi nhap du lieu");
                    
        }
        return dssp;
    }
    
    public ArrayList LayMangBookDangKi(String condition)
    {      
    	ArrayList LayMang = new ArrayList<>();
         
         try {
             ResultSet docsp = Connectunit.Select("borrowdetail", "borrow_id"+"="+"'"+condition+"'");
             while (docsp.next()) {               
            	 //tim kiem moi
                 BorrowDetailDTO sp = new BorrowDetailDTO();
                 sp.book_id= docsp.getString("book_id");
                 LayMang.add(sp.book_id);                
             }
         } catch (Exception e) {
             System.out.println("Loi nhap du lieu");
                     
         }
         return LayMang;
    }
    
    public void Close() throws SQLException
    {
        this.connect.Close();
    }

    public static void main(String[] args) throws Exception {
        BorrowDetailDAO borrowdetaildao = new BorrowDetailDAO();
        
//        ArrayList<BorrowDetailDTO> arr = new ArrayList();
//        arr = borrowdetaildao.docDssp();
//        System.out.println("danh sach toàn bộ chi tiết mượn");
//        for (int i = 0; i < arr.size(); i++)
//		{
//			System.out.println(arr.get(i).borrow_id+" "+arr.get(i).book_id);
//		}
  
        
        ArrayList arr2 = new ArrayList();
        arr2 = borrowdetaildao.LayMangBookDangKi("1");
        System.out.println("danh sach chi tiết mượn của hóa đơn 1");
        //int k = arr2.size();
        for (int i = 0; i < arr2.size(); i++)
		{
			System.out.println(arr2.get(i));
			arr2.remove(arr2.get(i));
			i--;
			//arr2.add("1");
			//System.out.println(arr2.size());
		}
        System.out.println(arr2.size());
    }

}

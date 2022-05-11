package r1290;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowDAO {
	MyconnectUnit Connectunit;
    MySQLConnect connect;
    
    public BorrowDAO() throws Exception {
        this.Connectunit = new MyconnectUnit();
        this.connect = new MySQLConnect();
    }
    
    public  ArrayList<BorrowDTO> docDssp()
    {
        ArrayList dssp = new ArrayList<BorrowDTO>();
        //ArrayList dssptimkiem = new ArrayList<>();
        try {
            ResultSet docsp = Connectunit.Select("borrow");
            while (docsp.next()) {               
                BorrowDTO sp = new BorrowDTO();
                sp.borrow_id= docsp.getString("borrow_id");
                sp.user_id= docsp.getInt("user_id");
                sp.borrow_status= docsp.getInt("borrow_status");
                sp.borrow_begindate= docsp.getTimestamp("borrow_begindate");
                sp.borrow_returndate= docsp.getTimestamp("borrow_returndate");
                dssp.add(sp);
                
                /* tim kiem moi
                 * BorrowDTO sp = new BorrowDTO();
                 * sp.borrow_id= docsp.getString("borrow_id");
                 * dssp.add(sp.borrow_id);
                 * */
                
            }
        } catch (Exception e) {
            System.out.println("Loi nhap du lieu");
                    
        }
        return dssp;
    }
    
    public boolean dataUpdateSL(BorrowDTO sp, int status) throws SQLException, Exception
    {
        connect.getConnect();
        try {
             String query = "UPDATE borrow SET borrow_status="+ status +" "
                + "WHERE borrow_id=?";
        PreparedStatement ps = connect.getPreparedStatement(query);
        ps.setString(1, sp.borrow_id);
        //ps.setInt(1, sp.book_status);     
        
        
        return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void Close() throws SQLException
    {
        this.connect.Close();
    }

    public static void main(String[] args) throws Exception {
        BorrowDAO borrowdao = new BorrowDAO();
        ArrayList<BorrowDTO> arr = new ArrayList();
        
        arr = borrowdao.docDssp();
        System.out.println(arr.get(0).borrow_id +" "+
        					arr.get(0).user_id +" "+	
        					arr.get(0).borrow_status +" "+
        					arr.get(0).borrow_begindate+" "+
        					arr.get(0).borrow_returndate);
        
        //tìm kiếm bỏ vào mảng
//        ArrayList<BorrowDTO> arrHoadon = new ArrayList();
//        System.out.println(arr.get(0));
        
        //arr.get(0).book_status=1;
        borrowdao.dataUpdateSL(arr.get(0), 0);        
    }
}

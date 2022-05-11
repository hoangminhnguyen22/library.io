package r1290;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {
	MyconnectUnit Connectunit;
    MySQLConnect connect;
    
    public BookDAO() throws Exception {
        this.Connectunit = new MyconnectUnit();
        this.connect = new MySQLConnect();
    }
    
    public  ArrayList<BookDTO> docDssp()
    {
        ArrayList dssp = new ArrayList<BookDTO>();
        
        try {
            ResultSet docsp = Connectunit.Select("books");
            while (docsp.next()) {               
                BookDTO sp = new BookDTO();
                sp.book_id= docsp.getString("book_id");
                sp.book_title= docsp.getString("book_title");
                sp.book_author= docsp.getString("book_author");
                sp.book_status= docsp.getInt("book_status");
               
                dssp.add(sp);
                
            }
        } catch (Exception e) {
            System.out.println("Loi nhap du lieu");
                    
        }
        return dssp;
    }
    
    public boolean dataUpdateSL(BookDTO sp, int status) throws SQLException, Exception
    {
        connect.getConnect();
        try {
             String query = "UPDATE books SET book_status="+ status +" "
                + "WHERE book_id=?";
        PreparedStatement ps = connect.getPreparedStatement(query);
        ps.setString(1, sp.book_id);
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
        BookDAO bookdao = new BookDAO();
        ArrayList<BookDTO> arr = new ArrayList();
        
        arr = bookdao.docDssp();
        System.out.println(arr.get(0).book_id +" "+
        					arr.get(0).book_title +" "+	
        					arr.get(0).book_status +" "+
        					arr.get(0).book_author);
        
        //arr.get(0).book_status=1;
        bookdao.dataUpdateSL(arr.get(0), 0);        
    }
}

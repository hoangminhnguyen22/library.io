package r1290;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnect {

	private String hostName = "localhost";
    private String dbName = "dangnhap";
//	private String dbName = "seminarrfid";
	private String uName = "root";    
    private String passW ="";
    
    private Statement st = null;
    private ResultSet rs = null;
    private Connection connect = null;
    private PreparedStatement pst = null;
    
    
  
    
    public MySQLConnect() throws Exception
    {
    
    }
    
    public MySQLConnect(String hostname, 
            String dbname, 
            String uname, 
            String passw)
    {
        this.hostName=hostname;
        this.dbName=dbname;
        this.uName=uname;
        this.passW=passw;
    }
    
    protected Connection getConnect() throws Exception
    {
        if(this.connect == null)
        {
            
        String url = "jdbc:mysql://" +this.hostName+ ":3306/" + this.dbName+"?useUnicode=true&characterEncoding=UTF-8";
            try {
                this.connect = DriverManager.getConnection(url, uName, passW);
            } catch (Exception e) {
                throw new Exception("khong the ket noi den database");
            }
        }
        return connect;
        
    }
    protected Statement getStatement() throws SQLException
    {
       this.st = connect.createStatement();
       return st;
    }
    
    public ResultSet excuteQuery(String query) throws SQLException
    {
        //thuc thi cau lenh sql
        try {
            this.rs = this.getStatement().executeQuery(query);
        } catch (Exception e) {
            
        }
        
        return this.rs;
    }
    
    public PreparedStatement getPreparedStatement (String query) throws SQLException
    {
       this.pst = connect.prepareStatement(query);
       return pst;
    }
    
    public ResultSet excutePreparedStatement(String query) throws SQLException
    {
        return this.rs = this.getPreparedStatement(query).executeQuery();
    }

    public int executeUpdatePreparedStatement(String Query) throws Exception
    {
        //insert update dell;
        int res =Integer.MIN_VALUE;
        try{
            res = getPreparedStatement(Query).executeUpdate();
        }
        catch(Exception e)
        {
            throw new Exception("Erro: Khong the update ");
        }
        
        finally{
            this.Close();
        }
        return res;
    }
    
    public void Close() throws SQLException{
        if(this.rs != null && !this.rs.isClosed())
        {
            this.rs.close();
            this.rs = null;
        }
        
        if(this.st != null && !this.st.isClosed())
        {
            this.st.close();
            this.st = null;
        }
        
        if(this.connect != null && !this.connect.isClosed())
        {
            this.connect.close();
            this.connect = null;
        }
    }
    
    
    //test connect
    public static void main(String[] args) {
    	try {
			MySQLConnect conn = new MySQLConnect();
			System.out.println("thành công");
			System.out.println(conn.getConnect().getCatalog());
			conn.Close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

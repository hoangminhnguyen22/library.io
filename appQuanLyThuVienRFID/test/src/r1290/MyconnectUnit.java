package r1290;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MyconnectUnit {

	
	public MySQLConnect mysqlconnect;
    public MyconnectUnit() throws Exception
            {
        this.mysqlconnect = new MySQLConnect();
        this.mysqlconnect.getConnect();
  
            }
    
    public void AddCondition(StringBuilder query, String condition) throws Exception{//mô hình m học chỗ này khác tao r
        if(condition != null)
        {
            query.append(" Where ").append(condition);
        }
    }
    public void AddOrderby(StringBuilder query, String orderby)
    {
        if(orderby != null)
        {
            query.append(" Order By ").append(orderby);
        }
    }
    
    public ResultSet Select(String Tablename, String Columname, String condition, String orderby) throws Exception
    {
        StringBuilder query = new StringBuilder("SELECT "+Columname+" FROM "+Tablename);
        this.AddCondition(query, condition);
        this.AddOrderby(query, orderby);
        query.append(";");
        
        return this.mysqlconnect.excutePreparedStatement(query.toString());
    }
    public ResultSet Select(String tablename, String condition, String orderby) throws Exception
    {
        StringBuilder query = new StringBuilder( "SELECT * FROM " + tablename);
        
        this.AddCondition(query, condition);
        
        this.AddOrderby(query, orderby);
        
        query.append(";");
        
        return this.mysqlconnect.excutePreparedStatement(query.toString());
    }
    
    public ResultSet Select(String tablename, String condition) throws Exception
    {
        return this.Select(tablename, condition, null);
    }
    
    public ResultSet Select (String tablename) throws Exception
    {
        return this.Select(tablename, null);
    }
    
    public boolean Delect(String Tablename, String Condition) throws Exception
    {
        StringBuilder query = new StringBuilder("DELETE FROM "+Tablename);
        this.AddCondition(query, Condition);
        query.append(";");
        
//        return this.mysqlconnect.executeUpdatePreparedStatement(query.toString())>0;
        return this.mysqlconnect.getPreparedStatement(query.toString()).executeUpdate()>0;
    }
   
    public void Close() throws Exception
    {
    	this.mysqlconnect.Close();
        
    }
   
    PreparedStatement preparedStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

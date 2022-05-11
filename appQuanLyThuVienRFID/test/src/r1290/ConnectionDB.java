package r1290;

import java.sql.*;

public class ConnectionDB {
 
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/seminarrfid";
//		String url="jdbc:mysql://localhost:3306/dangnhap";
		var user= "root";
		var password="";
		try(Connection conn= DriverManager.getConnection(url, user, password)){
			System.out.println("keets noi thanhf cong");
			System.out.print(conn.getCatalog());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

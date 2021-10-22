import java.sql.*;

public class SQLiteJDBC {
	public void SQLite_creatDataBase() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:Inventory_system.db");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE COMPANY " +
	                   "(公司名稱," +
	                   " 公司電話, " + 
	                   " 公司信箱, " + 
	                   " 聯絡人姓名, " + 
	                   " 聯絡人電話, " +
	                   " 統一編號, " +
	                   " 公司地址, " +
	                   "顧客or廠商)" ;
	      stmt.executeUpdate(sql);
	      
//	      String sql1 = "CREATE TABLE BUY" + 
//	    		"(ID," +
//	    		"產品名稱," +
//	    		"單價," +
//	    		"數量," +
//	    		"金額," +
//	    		"備註," +
//	    		"日期," +
//	    		"進口公司)" ;
//	      stmt.executeUpdate(sql1);
//	      
//	      String sql2 = "CREATE TABLE SALES" + 
//		    		"(ID," +
//		    		"產品名稱," +
//		    		"單價," +
//		    		"數量," +
//		    		"金額," +
//		    		"折扣," +
//		    		"備註," +
//		    		"日期," +
//		    		"買家)" ;
//		  stmt.executeUpdate(sql2);
	      
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }    
	}
//	public static void main( String args[] ) {
//    	new SQLiteJDBC().SQLite_creatDataBase();
//    }
}

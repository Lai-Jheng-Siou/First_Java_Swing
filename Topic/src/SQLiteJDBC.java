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
	                   "(���q�W��," +
	                   " ���q�q��, " + 
	                   " ���q�H�c, " + 
	                   " �p���H�m�W, " + 
	                   " �p���H�q��, " +
	                   " �Τ@�s��, " +
	                   " ���q�a�}, " +
	                   "�U��or�t��)" ;
	      stmt.executeUpdate(sql);
	      
//	      String sql1 = "CREATE TABLE BUY" + 
//	    		"(ID," +
//	    		"���~�W��," +
//	    		"���," +
//	    		"�ƶq," +
//	    		"���B," +
//	    		"�Ƶ�," +
//	    		"���," +
//	    		"�i�f���q)" ;
//	      stmt.executeUpdate(sql1);
//	      
//	      String sql2 = "CREATE TABLE SALES" + 
//		    		"(ID," +
//		    		"���~�W��," +
//		    		"���," +
//		    		"�ƶq," +
//		    		"���B," +
//		    		"�馩," +
//		    		"�Ƶ�," +
//		    		"���," +
//		    		"�R�a)" ;
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

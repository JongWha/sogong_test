package gram.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {

   private static DataSource ds;
   
   static{

      InitialContext ctx = null;      
      try {
         ctx = new InitialContext();
         
     
         ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
      } catch (NamingException e) {
       
      }
   }
      public static Connection getConnection() throws SQLException{
      return ds.getConnection();
   }
   
   public static void close(Connection conn, Statement stmt) throws SQLException{      
      if(stmt!= null){
         stmt.close();
      }
      if(conn != null){
         conn.close();
      }
   }
   
   public static void close(Connection conn, Statement stmt, ResultSet rset) throws SQLException{
      if(rset != null){
         rset.close();
         rset = null;
      }
      if(stmt != null){
         stmt.close();
      }
      if(conn!=null){
         conn.close();
      }
   }   
}
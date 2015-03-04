package org.suntaxi.db;

import java.sql.*; 

public class DBConn {   
	public static String driver;
    public static String url;
    public static String user;  
    public static String password;  
    public static Connection conn;
    public static Statement stmt;  
    public ResultSet rs;  
      
    static{   
        try { 
	    	driver="com.mysql.jdbc.Driver";
	    	url="jdbc:mysql://localhost:3306/qpf8";
	    	user="root";
	    	password="lzlz";
            Class.forName(driver);   
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("-------connect successful------:"+conn);
        } catch(ClassNotFoundException classnotfoundexception) {
              classnotfoundexception.printStackTrace();   
              System.err.println("db: " + classnotfoundexception.getMessage());   
        } catch(SQLException sqlexception) {   
        	  System.err.println("db.getconn(): " + sqlexception.getMessage());   
        }   
    }   
  
    public DBConn(){   
    	this.conn=this.getConn();
    }   
    
    public Connection getConn(){   

    	return this.conn;   
    }   
    //do insert  
       public void doInsert(String sql) {   
        try {   
        	//Connection conn2=getConn();
            stmt = conn.createStatement();   
            int i = stmt.executeUpdate(sql);
            System.err.println("success:"+i);
        } catch(SQLException sqlexception) {
            System.err.println("db.executeInset:" + sqlexception.getMessage());   
        }finally{   
               
        }   
    }   
    //do delete
    public void doDelete(String sql) {   
        try {   
            stmt = conn.createStatement();   
            int i = stmt.executeUpdate(sql);   
        } catch(SQLException sqlexception) {   
            System.err.println("db.executeDelete:" + sqlexception.getMessage());   
        }   
    }   
    //update 
    public void doUpdate(String sql) {   
        try {   
            stmt = conn.createStatement();   
            int i = stmt.executeUpdate(sql);   
        } catch(SQLException sqlexception) {   
            System.err.println("db.executeUpdate:" + sqlexception.getMessage());   
        }   
    }   
    //select    
    public ResultSet doSelect(String sql) {   
        try {
        	conn=DriverManager.getConnection(url,user,password);
        	stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);     
            rs = stmt.executeQuery(sql); 
            System.out.println("");
        } catch(SQLException sqlexception) {   
            System.err.println("db.executeQuery: " + sqlexception.getMessage());   
        }   
        return rs;   
    }   
    /**  
     *
       @Function: Close all the statement and conn int this instance and close the parameter ResultSet  
       @Param: ResultSet  
       @Exception: SQLException,Exception  
      **/  
     public void close(ResultSet rs) throws SQLException, Exception {   
  
       if (rs != null) {   
         rs.close();   
         rs = null;   
       }   
  
       if (stmt != null) {   
         stmt.close();   
         stmt = null;   
       }   
  
       if (conn != null) {   
         conn.close();   
         conn = null;   
       }   
     }   
  
     /**  
      *
      * Close all the statement and conn int this instance  
      * @throws SQLException  
      * @throws Exception  
      */  
     public void close() throws SQLException, Exception {   
       if (stmt != null) {   
         stmt.close();   
         stmt = null;   
       }   
  
       if (conn != null) {   
         conn.close();   
         conn = null;   
       }   
     }   
   
     public static void main(String []args){
    	 DBConn db=new DBConn();
    	 db.getConn();

    	ResultSet rs = db.doSelect("select id,carnum from car;");
    	try {
			while(rs.next()){
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}  

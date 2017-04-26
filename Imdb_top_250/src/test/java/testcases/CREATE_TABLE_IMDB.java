package testcases;

import java.sql.*;

public class CREATE_TABLE_IMDB  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Connection c = null;
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      String sql = "CREATE TABLE IMDB_MOVIE " +
		                   "(RANK INT PRIMARY KEY     NOT NULL," +
		                   " NAME  varchar(255)    NOT NULL, " + 
		                   " YEAR   INT     NOT NULL, " + 
		                   " RATING REAL ) " ; 
		                 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Table created successfully");

	}

}

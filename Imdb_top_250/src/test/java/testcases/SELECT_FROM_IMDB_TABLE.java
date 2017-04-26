package testcases;

import java.sql.*;
public class SELECT_FROM_IMDB_TABLE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM IMDB_MOVIE;" );
	      while ( rs.next() ) {
	         int rank = rs.getInt("RANK");
	         String  name = rs.getString("name");
	         int year  = rs.getInt("YEAR");
	         float rating = rs.getFloat("rating");
	         System.out.println( "RANK = " + rank );
	         System.out.println( "NAME = " + name );
	         System.out.println( "YEAR = " + year );
	        
	         System.out.println( "RATING = " + rating );
	         System.out.println();
	         //"INSERT INTO IMDB_MOVIE (RANK,NAME,YEAR,RATING) " +
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	  }

	}



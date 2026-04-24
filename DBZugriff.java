package paket_1;

import java.sql.*;

public class DBZugriff
{ //Atribute
	private Connection dbVerbindung = null;
  private Statement stmtSQL = null;
  
  //Methoden
  public boolean oeffeneDB()
  {boolean mOK;
	  try 
	  {
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		  dbVerbindung = DriverManager.getConnection("jdbc:mysql://localhost/haro","root","");
		  stmtSQL = dbVerbindung.createStatement();
		  mOK = true;
	  }
	  catch (Exception fehler)
	  {
		  mOK = false;
	  }
	  return mOK;
  }
  public boolean schliesseDB()
  {	boolean mOK;
	  try {
	  stmtSQL.close();
	  dbVerbindung.close();
	  mOK = true;
  	}
	catch (Exception fehler)
	  {
		 mOK = false;
	  }
	  return mOK;
  }
  public boolean aendern(String pSQL)
  {  boolean mOK;
	  try {
		  stmtSQL.executeUpdate(pSQL);
		  mOK = true;
	  	}
		catch (Exception error)
	  {
			 mOK = false;
		  }
		  return mOK;
  }
  public ResultSet lesen(String pSQL)
  {ResultSet rs;
  	try {
	  rs = stmtSQL.executeQuery(pSQL);	  
  	}
	catch (Exception error)
  {	 rs = null;
	  }
	  return rs;
  }
}



import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class BackEnd {

public static void main(String[] args) {
	BackEnd n = new BackEnd();
	
	try {
	n.createTables("44076" , "World");
	
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 try {
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}	
// sqlite != sql

public  ArrayList<ArrayList<String>> selectall()  {
	try {
		Connection c = getConnection();
		
		PreparedStatement post = c.prepareStatement("SELECT * FROM EmployeeDetails");
		ResultSet result = post.executeQuery() ;
		
		ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
		
		while(result.next()){
			ArrayList<String> list = new ArrayList<String>();
			
			list.add(result.getString("ID"));
			list.add(result.getString("Name"));
			list.add(result.getString("Designation"));
			list.add(result.getString("Date_of_Birth"));
			list.add(result.getString("Joined_Date"));
			list.add(result.getString("Phone_Number"));
			list.add(result.getString("Email"));
			list.add(result.getString("Basic_Salary"));
			list.add(result.getString("Allowance"));
			list.add(result.getString("Tax"));
			list.add(result.getString("Leaves"));
			
			
			array.add(list);
		
			
		}
		c.close();
		return array;
	} catch (Exception e) {
		System.out.println(e);
	}
	return null;
}

public void insert( String Name, String Designation ,String Date_of_Birth ,String Joined_Date ,String Phone_Number ,String Email ,String Basic_Salary ,String Allowance ,String Tax ,String Leaves) throws Exception {

	
	try {
		
		Connection c = getConnection();
		PreparedStatement post = c.prepareStatement("INSERT INTO EmployeeDetails (Name,Designation,Date_of_Birth,Joined_Date,Phone_Number,Email,Basic_Salary,Allowance,Tax,Leaves) VALUES ('"+Name+"' ,'"+Designation+"' ,'"+Date_of_Birth+"','"+Joined_Date+"','"+Phone_Number+"','"+Email+"','"+Basic_Salary+"','"+Allowance+"','"+Tax+"','"+Leaves+"' )");
		post.executeUpdate();
		post = c.prepareStatement("SELECT * FROM EmployeeDetails ORDER BY ID DESC LIMIT 1");
		ResultSet id = post.executeQuery();
		post = c.prepareStatement("INSERT INTO Attendence (ID) Values ('"+id.getString("ID")+"')");
		post.executeUpdate();
		post = c.prepareStatement("INSERT INTO Accounts (ID) Values ('"+id.getString("ID")+"')");
		post.executeUpdate();
		post = c.prepareStatement("UPDATE Accounts SET Password='Company',Priority='0' WHERE ID="+id.getString("ID"));
		post.executeUpdate();
		c.close();
	} catch (Exception e) {
		System.out.println(e);
	}
}



public  Connection getConnection() throws Exception {
	 

		Connection c = null;
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			c= DriverManager.getConnection("jdbc:sqlite:Project.db");
			System.out.println("SQLite Db is connected");
		} catch(Exception e){
			System.out.println(e);
			
		}
		
		
		return c;
		 
 }

public void Update(String id , String Name, String Designation ,String Date_of_Birth ,String Joined_Date ,String Phone_Number ,String Email ,String Basic_Salary ,String Allowance ,String Tax ,String Leaves) {


	try {
		Connection c = getConnection();
		
		PreparedStatement post = c.prepareStatement("UPDATE EmployeeDetails Set Name='"+Name + "' , Designation='"+Designation+"' , Date_of_Birth=='"+Date_of_Birth+"' , Joined_Date=='"+Joined_Date+"', Phone_Number='"+Phone_Number+"' , Email='"+Email+"' , Basic_Salary='"+Basic_Salary+"' , Allowance='"+Allowance+"'  ,Tax='"+Tax+"' ,Leaves='"+Leaves+"' WHERE ID = "+id);
		post.executeUpdate();
		c.close();
	} catch (Exception e) {
		System.out.println(e);
	}
	
}

public void createTables(String id, String Pass) {
	try {
		Connection c = getConnection();
		PreparedStatement post = c.prepareStatement("CREATE TABLE IF NOT EXISTS EmployeeDetails (\n"+
				"	ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" + 
				"	Name	TEXT,\n" + 
				"	Designation	TEXT,\n" + 
				"	Date_of_Birth	TEXT,\n" + 
				"	Joined_Date	TEXT,\n" + 
				"	Phone_Number	INTEGER,\n" + 
				"	Email	INTEGER,\n" + 
				"	Basic_Salary	INTEGER,\n" + 
				"	Allowance	INTEGER,\n" + 
				"	Tax	INTEGER,\n" + 
				"	Leaves	INTEGER)");
		post.executeUpdate();
		post = c.prepareStatement("CREATE TABLE IF NOT EXISTS Attendence (\n" + 
				"	ID	INTEGER PRIMARY KEY) ");
		post.executeUpdate();
		post = c.prepareStatement("CREATE TABLE IF NOT EXISTS Accounts (\n" + 
				"	ID	INTEGER,\n" + 
				"	Priority	INTEGER,\n" + 
				"	Password	TEXT,\n" + 
				"	PRIMARY KEY(\"ID\"))");
		post.executeUpdate();
		int priority = 0;
		System.out.println(priority);
		post = c.prepareStatement("SELECT COUNT(1) AS count FROM Accounts  WHERE Priority='1'");
		System.out.println(priority);
		ResultSet rs= post.executeQuery();
		while (rs.next()) {
			priority = rs.getInt("count");
		}
		
		
		
		
		if (priority != 1) {
		post = c.prepareStatement("INSERT INTO Accounts (ID,Password,Priority) VALUES ('"+id+"', '"+Pass+"' , '1')");
		post.executeUpdate();
		post = c.prepareStatement("INSERT INTO EmployeeDetails (ID) VALUES ('"+id+"')");
		post.executeUpdate();
		post = c.prepareStatement("INSERT INTO Attendence (ID) VALUES ('"+id+"')");
		post.executeUpdate();
		}
		c.close();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}

public void Delete(String id) {

	
	try {
		Connection c = getConnection();
		
		PreparedStatement post = c.prepareStatement("DELETE FROM EmployeeDetails WHERE ID="+id);
		post.executeUpdate();
		post = c.prepareStatement("DELETE FROM Accounts WHERE ID="+id);
		post.executeUpdate();
		post = c.prepareStatement("DELETE FROM Attendence WHERE ID="+id);
		post.executeUpdate();
		c.close();
	} catch (Exception e) {
		System.out.println(e);
	}
	
}

public int createAttendenceSheet() {
	
	try {
		Connection c = getConnection();
		Date d  = new Date();
		int date = d.getDate();
		String Months[] =  {"Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec"};
		int Month = d.getMonth();
		String colname =  Months[Month]+"_" + String.valueOf(date);
		
			
				ResultSet rs;
			
				Statement st = c.createStatement();			

			  String sql = "select * from Attendence";
			  rs = st.executeQuery(sql);
			  ResultSetMetaData metaData = rs.getMetaData();
			  int rowCount = metaData.getColumnCount();

			  boolean isMyColumnPresent = false;
			  
			  for (int i = 1; i <= rowCount; i++) {
			    if (colname.equals(metaData.getColumnName(i))) {
			      isMyColumnPresent = true;
			    }
			  }

			  if (!isMyColumnPresent) {
			    String myColumnType = "int";
			    st.executeUpdate("ALTER TABLE Attendence ADD " + colname + " " + myColumnType + " DEFAULT '2'" );
			    st.executeUpdate("UPDATE Attendence SET "+colname+"="+0);
				c.close();
			    return 1;
			  }
				c.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
	return 0;
	}


public void AttendenceCheckOut(String id) {
	try {
		Connection c = getConnection();
		
		Date d  = new Date();
		int date = d.getDate();
		String Months[] =  {"Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec"};
		int Month = d.getMonth();
		String colname =  Months[Month]+"_" + String.valueOf(date);
		
		PreparedStatement post = c.prepareStatement("UPDATE Attendence Set "+colname+"=1 WHERE ID=" + id );
		post.executeUpdate();
		c.close();
	} catch (Exception e) {
		System.out.println(e);
	}
			
}


public boolean checkAcc(String Id , String oldPass , String newpassword) {

	try {
		Connection c = getConnection();
		PreparedStatement post = c.prepareStatement("SELECT * FROM Accounts WHERE ID="+Id);
		ResultSet rp = post.executeQuery();
		
		while(rp.next())
			if (rp.getString("Password").equals(oldPass)) {
			post =c.prepareStatement("UPDATE Accounts SET Password='"+newpassword+"' WHERE ID="+Id);
			post.executeUpdate();
			System.out.println("UPdated");
			c.close();
			 return true;
			}
	 
			
		
	} catch (Exception e) {
		System.out.println(e);
	}
	return false;

}


public int Loginacc(String Id , String Pass) {
	
	try {
		Connection c = getConnection();
		PreparedStatement post = c.prepareStatement("Select * FROM Accounts WHERE ID="+Id );
		ResultSet rs = post.executeQuery();
		
		while (rs.next()) {
			if (rs.getString("Password").equals(Pass)) {
				if(rs.getString("Priority").equals("1")) {
					c.close();
				return 1;
				}
				else {
					c.close();
					return 0;
				}
			}
			
		}
		c.close();
	} catch (Exception e) {
		System.out.println(e);
	}
	return 3;
}


public ArrayList<String> SelectOne(String id) {
	try {
		Connection c = getConnection();
		
		PreparedStatement post = c.prepareStatement("SELECT * FROM EmployeeDetails WHERE Id="+id);
		ResultSet result = post.executeQuery() ;
		
	
		
		ArrayList<String> list = new ArrayList<String>();
			
			list.add(result.getString("ID"));
			list.add(result.getString("Name"));
			list.add(result.getString("Designation"));
			list.add(result.getString("Date_of_Birth"));
			list.add(result.getString("Joined_Date"));
			list.add(result.getString("Phone_Number"));
			list.add(result.getString("Email"));
			list.add(result.getString("Basic_Salary"));
			list.add(result.getString("Allowance"));
			list.add(result.getString("Tax"));
			list.add(result.getString("Leaves"));
			

			System.out.println(list);

		c.close();
		return list;
	} catch (Exception e) {
		System.out.println(e);
	}
	return null;
}


@SuppressWarnings("deprecation")
public boolean checkTodaysAttendance(String id) {
	try {
		Connection c = getConnection();
		
		Date d  = new Date();
		int date = d.getDate();
		String Months[] =  {"Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec"};
		int Month = d.getMonth();
		String colname =  Months[Month]+"_" + String.valueOf(date);
		
		PreparedStatement post = c.prepareStatement("SELECT "+colname+" FROM Attendence WHERE ID=" + id );
		ResultSet rs = post.executeQuery();
		if (rs.getString(colname).equals("1")) {
			c.close();
			return true;
		}
		c.close();
	} catch (Exception e) {
		System.out.println(e);
	}
	return false;
}

public int calulateLeaves(String id) {
	int totalLeaves=0;
	
	try {
		Connection c = getConnection();
		
		Date d  = new Date();
		int date = d.getDate();
		String Months[] =  {"Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec"};
		int Month = d.getMonth();
		String colname =  Months[Month]+"_" + String.valueOf(date);
		
		PreparedStatement post = c.prepareStatement("SELECT * FROM Attendence WHERE ID=" + id);
		ResultSet rs = post.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsize = rsmd.getColumnCount();
				
		for (int i = 2; i <= columnsize ; i++) {
			if (rs.getString(i).equals("0")) {
				totalLeaves++;
			}
			
		}
		post = c.prepareStatement("UPDATE EmployeeDetails SET Leaves='"+totalLeaves+"' WHERE ID="+id);
		post.executeUpdate();
		c.close();
	} catch (Exception e) {
		System.out.println(e);
	}
	
	return totalLeaves;
	
}



}




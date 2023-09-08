package hospital_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class stafffunctions {
	private Scanner sc;
	public int staff_functions(int status){
        sc = new Scanner(System.in);
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con0=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        Statement stmt0=con0.createStatement();
	        ResultSet rs0=stmt0.executeQuery("select * from staff where Staff_id="+status);
	        if(rs0.next()) {
	        if(rs0.getString("Type").equals("general")) {
	        	do {
	        		System.out.println("----------General Staff Menu-----------");
	        		System.out.println("1)View Profile");
	        		System.out.println("2)Change Password");
	        		System.out.println("3)Current status");
	        		System.out.println("4)Logout");
	        		System.out.println("Enter your choice:-");
	        		int option=sc.nextInt();
	        		sc.nextLine();
	        		switch(option) {
	        		case 1:{
	        			try {
	        				Class.forName("com.mysql.cj.jdbc.Driver");
	        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        				Statement stmt=con.createStatement();
	        				ResultSet rs=stmt.executeQuery("select * from staff where Staff_id="+status);
	        				if(rs.next()) {
	        					System.out.println("\n\n");
	        					System.out.println("Username:-"+rs.getString("Username"));
	        					System.out.println("Password:-"+rs.getString("Password"));
	        					System.out.println("RegistrationDate: "+rs.getDate("RegistrationDate"));
	        					System.out.println("\n\n");
	        				}
	        				con.close();
	        			}
	        			catch(Exception e) {
	        				System.out.println("Invalid");
	        			}
	        			break;
	        		}
	        		case 2:{
	        			System.out.println("Enter new password");
	        			String pwd=sc.nextLine();
	        			try {
	        				Class.forName("com.mysql.cj.jdbc.Driver");
	        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        				PreparedStatement pstmt;
	        				Statement stmt=con.createStatement();
	        				ResultSet rs=stmt.executeQuery("select * from staff where Staff_id="+status);
	        				if(rs.next()) {
	        					pstmt=con.prepareStatement("update staff set Password=? where Staff_id=?");
	        					pstmt.setString(1,pwd);
	        					pstmt.setInt(2,status);
	        					pstmt.executeUpdate();
	        					System.out.println("Password updated");
	        				}
	        			con.close();
	        			}
	        			catch(Exception e) {
	        				System.out.println(e);
	        			}
	        			break;
	        		}
	        		case 3:{
	        			int roomid=0;
	        			int doctorid=0;
	        			int patientid=0;
	        			String patname=null;
	        			String doctname=null;
	        			int nod=0;
	        			System.out.println("Current working status");
	        			try {
	        				Class.forName("com.mysql.cj.jdbc.Driver");
	        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        				Statement stmt=con.createStatement();
	        				ResultSet rs=stmt.executeQuery("select * from staff where Staff_id="+status);
	        				if(rs.next()) {
	        					patientid=rs.getInt("currentpatientid");
	        					doctorid=rs.getInt("doctorid");
	        					roomid=rs.getInt("roomid");
	        				}
	        			con.close();
	        			}
	        			catch(Exception e) {
	        				System.out.println(e);
	        			}
	        			try {
	        				Class.forName("com.mysql.cj.jdbc.Driver");
	        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        				Statement stmt=con.createStatement();
	        				ResultSet rs=stmt.executeQuery("select * from doctor where Doctor_id="+doctorid);
	        				if(rs.next()) {
	        					doctname=rs.getString("Firstname")+" "+rs.getString("Lastname");
	        				}
	        				ResultSet rs1=stmt.executeQuery("select * from patient where Patient_id="+patientid);
	        				if(rs1.next()) {
	        					patname=rs.getString("Firstname")+" "+rs.getString("Lastname");
	        					nod=rs.getInt("NumberOfDays");
	        				}
	        				System.out.println("Your current reporting doctor is"+doctname+" in room "+roomid+" and the current patient name is "+patname+" for the next "+nod+" days");
	        			con.close();
	        			}
	        			catch(Exception e) {
	        				System.out.println("You are not currently assigned to any doctor");
	        			}
	        			break;
	        		}
	        		case 4:{
	        			System.out.println("----------------Logged Out SuccessFully-------------------\n\n");
	        			return -1;
	        		}
	        		}
	        	}while(true);
	        }
	        else if(rs0.getString("Type").equals("duty")) {
	        	int patid=0;
	        	int roomid=0;
	        	do {
	        		System.out.println("----------Duty Staff Menu-----------");
	        		System.out.println("1)View Profile");
	        		System.out.println("2)Change Password");
	        		System.out.println("3)Visit a patient");
	        		System.out.println("4)Current patient details");
	        		System.out.println("5)Discharge patient");
	        		System.out.println("6)Logout");
	        		System.out.println("Enter your choice:-");
	        		int option=sc.nextInt();
	        		sc.nextLine();
	        		switch(option) {
	        		case 1:{
	        			try {
	        				Class.forName("com.mysql.cj.jdbc.Driver");
	        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        				Statement stmt=con.createStatement();
	        				ResultSet rs=stmt.executeQuery("select * from staff where Staff_id="+status);
	        				if(rs.next()) {
	        					System.out.println("\n\n");
	        					System.out.println("Username:-"+rs.getString("Username"));
	        					System.out.println("Password:-"+rs.getString("Password"));
	        					System.out.println("RegistrationDate: "+rs.getDate("RegistrationDate"));
	        					System.out.println("\n\n");
	        				}
	        				con.close();
	        			}
	        			catch(Exception e) {
	        				System.out.println("Invalid");
	        			}
	        			break;
	        		}
	        		case 2:{
	        			System.out.println("Enter new password");
	        			String pwd=sc.nextLine();
	        			try {
	        				Class.forName("com.mysql.cj.jdbc.Driver");
	        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        				PreparedStatement pstmt;
	        				Statement stmt=con.createStatement();
	        				ResultSet rs=stmt.executeQuery("select * from staff where Staff_id="+status);
	        				if(rs.next()) {
	        					pstmt=con.prepareStatement("update staff set Password=? where Staff_id=?");
	        					pstmt.setString(1,pwd);
	        					pstmt.setInt(2,status);
	        					pstmt.executeUpdate();
	        					System.out.println("Password updated");
	        				}
	        				con.close();
	        			}
	        			catch(Exception e) {
	        				System.out.println(e);
	        			}
	        			break;
	        		}
	        		case 3:{
	        			String staffusername=null;
	        			try {
	        				Class.forName("com.mysql.cj.jdbc.Driver");
	        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        				Statement stmt=con.createStatement();
	        				ResultSet rs=stmt.executeQuery("select * from staff where Staff_id="+status);
	        				if(rs.next()) {
	        					staffusername=rs.getString("Username");
	        				}
	        				con.close();
	        			}
	        			catch(Exception e) {
	        				System.out.println(e);
	        			}
	        			try {
	        				Class.forName("com.mysql.cj.jdbc.Driver");
	        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        				PreparedStatement pstmt,pstmt1,pstmt2;
	        				Statement stmt=con.createStatement();
	        				ResultSet rs=stmt.executeQuery("select * from patient where TypeOfDoctorNeeded=\"duty\"");
	        				while(rs.next()) {
	        					patid=rs.getInt("Patient_id");
	        					roomid=rs.getInt("AdmittedRoomID");
	        					if(rs0.getString("Availability").equals("available")&&rs.getString("Status").equals("treated")) {
	        					pstmt=con.prepareStatement("update patient set AttendingStaffID=? where Patient_id=?");
	        					pstmt.setInt(1, status);
	        					pstmt.setInt(2, rs.getInt("Patient_id"));
	        					pstmt.executeUpdate();
	        					pstmt1=con.prepareStatement("update staff set Availability=?,currentpatientid=?,roomid=? where Staff_id=?");
	        					pstmt1.setString(1, "notavailable");
	        					pstmt1.setInt(2, rs.getInt("Patient_id"));
	        					pstmt1.setInt(3, rs.getInt("AdmittedRoomID"));
	        					pstmt1.setInt(4, status);
	        					pstmt1.executeUpdate();
	        					pstmt2=con.prepareStatement("update room set StaffID=?,StaffUsername=? where Roomid=?");
	        					pstmt2.setInt(1, status);
	        					pstmt2.setString(2, staffusername);
	        					pstmt2.setInt(3, rs.getInt("AdmittedRoomID"));
	        					pstmt2.executeUpdate();
	        					System.out.println("You are currently examining "+rs.getString("Firstname")+" "+rs.getString("Lastname")+" in room "+rs.getInt("AdmittedRoomID"));
	        					break;
	        					}
	        					else {
	        						System.out.println("You are currently not available");
	        					}
	        				}
	        				con.close();
	        			}
	        			catch(Exception e) {
	        				System.out.println(e);
	        			}
	        			break;
	        		}
	        		case 4:{
	        			try {
	            			Class.forName("com.mysql.cj.jdbc.Driver");
	            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	            	        Statement stmt=con.createStatement();
	            	        ResultSet rs=stmt.executeQuery("select * from patient where AttendingStaffID="+status);
	            	        if(rs.next()) {
	            	        	System.out.println("\n\n");
	            	        	System.out.println("Name:-"+rs.getString("Firstname")+" "+rs.getString("Lastname"));
	            	        	System.out.println("EmailID:-"+rs.getString("EmailID"));
	            	        	System.out.println("Age:-"+rs.getInt("Age"));
	            	        	System.out.println("Gender:-"+rs.getString("Gender"));
	            	        	System.out.println("BloodGroup:-"+rs.getString("BloodGroup"));
	            	        	System.out.println("\n\n");
	            	        }
	            	        con.close();
	            		}
	            		catch(Exception e) {
	            			System.out.println(e);
	            		}
	        			break;
	        		}
	        		case 5:{
	        			try {
	        				Class.forName("com.mysql.cj.jdbc.Driver");
	        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	        				PreparedStatement pstmt,pstmt1,pstmt2;
	        				Statement stmt=con.createStatement();
	        				ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+patid);
	        				while(rs.next()) {
	        					pstmt=con.prepareStatement("update patient set AttendingStaffID=?,PatientType=?,Status=?,NumberOfDays=?,TypeOfDoctorNeeded=? where Patient_id=?");
	        					pstmt.setInt(1, 0);
	        					pstmt.setString(2, "out");
	        					pstmt.setString(3, "confirmed");
	        					pstmt.setInt(4, 0);
	        					pstmt.setString(5, null);
	        					pstmt.setInt(6, patid);
	        					pstmt.executeUpdate();
	        					pstmt1=con.prepareStatement("update room set StayingPatientID=?,PatientUsername=?,StaffID=?,StaffUsername=?,NumberOfDays=?,Availability=? where Roomid=?");
	        					pstmt1.setInt(1, 0);
	        					pstmt1.setString(2, null);
	        					pstmt1.setInt(3, 0);
	        					pstmt1.setString(4, null);
	        					pstmt1.setInt(5, 0);
	        					pstmt1.setString(6, "available");
	        					pstmt1.setInt(7, roomid);
	        					pstmt1.executeUpdate();
	        					pstmt2=con.prepareStatement("update staff set availability=?,currentpatientid=?,roomid=? where Staff_id=?");
	        					pstmt2.setString(1, "available");
	        					pstmt2.setInt(2, 0);
	        					pstmt2.setInt(3, 0);
	        					pstmt2.setInt(4, status);
	        					pstmt2.executeUpdate();
	        					System.out.println("Patient has been prepped for discharge");
	        				}
	        				con.close();
	        				}
	        			catch(Exception e) {
	        				System.out.println(e);
	        			}
	        			break;
	        		}
	        		case 6:{
	        			System.out.println("----------------Logged Out SuccessFully-------------------\n\n");
	        			return -1;
	        		}
	        		}
	        	}while(true);        	
	        }
        }
	        con0.close();
        }
        catch(Exception e) {
        	System.out.println(e);
        }
        return 0;
	}
}
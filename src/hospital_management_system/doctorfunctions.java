package hospital_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class doctorfunctions {
	private Scanner sc;
	public int doctor_functions(int status){
        sc = new Scanner(System.in);
        int roomno=0;
        do {
        	System.out.println("----------Doctor Menu-----------");
        	System.out.println("1)View Profile");
        	System.out.println("2)Change Password");
        	System.out.println("3)Confirm appoinments");
        	System.out.println("4)Current patient treatment");
        	System.out.println("5)View Previous appoinments");
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
        	        ResultSet rs=stmt.executeQuery("select * from doctor where Doctor_id="+status);
        	        if(rs.next()) {
        	        	System.out.println("Username:-"+rs.getString("Username"));
        	        	System.out.println("Password:-"+rs.getString("Password"));
        	        	System.out.println("Fees:-"+rs.getInt("Fees"));
        	        	System.out.println("RegistrationDate: "+rs.getDate("RegistrationDate"));
        	        }
        	        con.close();
        		}
        		catch(Exception e) {
        			System.out.println(e);
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
        	        ResultSet rs=stmt.executeQuery("select * from doctor where Doctor_id="+status);
        	        if(rs.next()) {
        	        pstmt=con.prepareStatement("update doctor set Password=? where Doctor_id=?");
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
        		int c=0;
        		String spec = null;
        		String avail=null;
        		String doctusername=null;
        		String patusername=null;
        		int patid=0;
        		try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        Statement stmt=con.createStatement();
        	        ResultSet rs=stmt.executeQuery("select * from doctor where Doctor_id="+status);
        	        if(rs.next()) {
        	        	spec=rs.getString("Specialization");
        	        	avail=rs.getString("availability");  
        	        	doctusername=rs.getString("Username");
        	        }
        	        con.close();
        		}	
        		catch(Exception e) {
        				System.out.println(e);
        		}
        		try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        String sql="select * from patient where TypeOfDoctorNeeded=?";
        	        PreparedStatement statement = con.prepareStatement(sql);
        	        statement.setString(1,spec);
        	        ResultSet rs=statement.executeQuery();
        	        while(rs.next()) {
        	        	if(rs.getString("Status").equals("admitted") && avail.equals("available")) {
        	        		c=1;
        	        		patid=rs.getInt("Patient_id");
        	        		patusername=rs.getString("Username");
        	        		try {
        	        		Class.forName("com.mysql.cj.jdbc.Driver");
                	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                	        PreparedStatement pstmt;
                	        PreparedStatement pstmt1;
                	        pstmt=con1.prepareStatement("update doctor set availability=?,currentpatientid=? where Doctor_id=?");
                	        pstmt.setString(1,"notavailable");
                	        pstmt.setInt(2,patid);
                	        pstmt.setInt(3, status);
                	        pstmt.executeUpdate();
                	        pstmt1=con1.prepareStatement("update patient set AttendingDoctorID=?,Status=? where Patient_id=?");
                	        pstmt1.setInt(1, status);
                	        pstmt1.setString(2,"confirmed");
                	        pstmt1.setInt(3,patid);
                	        System.out.print("Patient "+rs.getString("Firstname")+" "+rs.getString("Lastname")+" Email:- "+rs.getString("EmailID")+" is your current patient");
                	        pstmt1.executeUpdate();
                	        con1.close();
        	        		}
        	        		catch(Exception e) {
        	        			System.out.println("Hi");
        	        		}
                	        break;
        	        	}
        	        }
        		}
        		catch(Exception e) {
        			System.out.println(e);
        		}
        		try {
        		Class.forName("com.mysql.cj.jdbc.Driver");
        	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");	
        		String sql1="select * from room where Roomname=?";
    	        PreparedStatement statement1 = con.prepareStatement(sql1);
    	        statement1.setString(1,spec);;
    	        ResultSet rs1=statement1.executeQuery();
    	        while(rs1.next()) {
    	        	if(c==1&&rs1.getString("Availability").equals("available")) {
    	        		try {
    	        		Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        PreparedStatement pstmt1;
            	        PreparedStatement pstmt2;
            	        pstmt=con1.prepareStatement("update room set Availability=?,StayingPatientID=?,PatientUsername=?,DoctorID=?,DoctorUsername=? where Roomid=?");
            	        pstmt.setString(1,"notavailable");
            	        pstmt.setInt(2,patid);
            	        pstmt.setString(3,patusername);
            	        pstmt.setInt(4, status);
            	        pstmt.setString(5, doctusername);
            	        pstmt.setInt(6, rs1.getInt("Roomid"));
            	        pstmt.executeUpdate();
            	        pstmt1=con1.prepareStatement("update doctor set Roomid=? where Doctor_id=?");
            	        pstmt1.setInt(1,rs1.getInt("Roomid"));
            	        pstmt1.setInt(2,status);
            	        pstmt1.executeUpdate();
            	        pstmt2=con1.prepareStatement("update patient set AdmittedRoomID=? where Patient_id=?");
            	        pstmt2.setInt(1,rs1.getInt("Roomid"));
            	        roomno=rs1.getInt("Roomid");
            	        System.out.println("In room id:-"+roomno);
            	        pstmt2.setInt(2,patid);
            	        pstmt2.executeUpdate();
            	        con1.close();
            	        c=0;
            	        break;
            	        }
    	        		catch(Exception e) {
    	        			System.out.println("hello");
    	        		}
    	        	}
    	        }
        		}
        		catch(Exception e) {
        			System.out.println(e+"hi");
    		}
        		break;
        	}
        	case 4:{
        		System.out.println("After pre checkup");
        		System.out.println("1)Discharge");
        		System.out.println("2)Further treatment");
        		System.out.println("3)Transfer to other doctor");
        		System.out.println("Enter your choice");
        		int opt=sc.nextInt();
        		sc.nextLine();
        		if(opt==1) {
        			int totalfees=0;
        			int specfees=0;
            		String spec = null;
            		String avail=null;
            		String doctusername = null;
            		String patusername=null;
            		int patid=0;
            		try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from doctor where Doctor_id="+status);
            	        if(rs.next()) {
            	        	specfees=rs.getInt("Fees");
            	        	totalfees+=specfees;
            	        	spec=rs.getString("Specialization");
            	        	avail=rs.getString("availability");  
            	        	doctusername=rs.getString("Username");
            	        }
            	        con.close();
            		}	
            		catch(Exception e) {
            				System.out.println(e);
            		}
            		try {
                		Class.forName("com.mysql.cj.jdbc.Driver");
                	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");	
                		String sql1="select * from room where Roomid=?";
            	        PreparedStatement statement1 = con.prepareStatement(sql1);
            	        statement1.setInt(1, roomno);
            	        System.out.println(roomno);
            	        ResultSet rs1=statement1.executeQuery();
            	        while(rs1.next()) {
            	        	if(rs1.getString("Availability").equals("notavailable")) {
            	        		totalfees+=rs1.getInt("NumberOfDays")*10*specfees;
            	        		try {
            	        		Class.forName("com.mysql.cj.jdbc.Driver");
                    	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                    	        PreparedStatement pstmt;
                    	        PreparedStatement pstmt1;
                    	        PreparedStatement pstmt2;
                    	        pstmt=con1.prepareStatement("update room set Availability=?,StayingPatientID=?,PatientUsername=?,DoctorID=?,DoctorUsername=? where Roomid=?");
                    	        pstmt.setString(1,"available");
                    	        pstmt.setInt(2,0);
                    	        pstmt.setString(3,null);
                    	        pstmt.setInt(4, 0);
                    	        pstmt.setString(5,null);
                    	        pstmt.setInt(6,roomno);
                    	        pstmt.executeUpdate();
                    	        pstmt1=con1.prepareStatement("update doctor set Roomid=? where Doctor_id=?");
                    	        pstmt1.setInt(1,0);
                    	        pstmt1.setInt(2,status);
                    	        pstmt1.executeUpdate();
                    	        pstmt2=con1.prepareStatement("update patient set AdmittedRoomID=? where Patient_id=?");
                    	        pstmt2.setInt(1,0);
                    	        pstmt2.setInt(2,patid);
                    	        pstmt2.executeUpdate();
                    	        con1.close();
                    	        break;
                    	        }
            	        		catch(Exception e) {
            	        			System.out.println("hello");
            	        		}
            	        	}
            	        }
            	        con.close();
                		}
                		catch(Exception e) {
                			System.out.println(e+"hi");
            		}
            		try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        String sql="select * from patient where TypeOfDoctorNeeded=? and Status=?";
            	        PreparedStatement statement = con.prepareStatement(sql);
            	        statement.setString(1,spec);
            	        statement.setString(2, "confirmed");
            	        ResultSet rs=statement.executeQuery();
            	        while(rs.next()) {
            	        	if(rs.getString("Status").equals("confirmed") && avail.equals("notavailable")) {
            	        		patid=rs.getInt("Patient_id");
            	        		patusername=rs.getString("Username");
            	        		String pattype = rs.getString("PatientType");
            	        		try {
            	        		Class.forName("com.mysql.cj.jdbc.Driver");
                    	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                    	        PreparedStatement pstmt;
                    	        PreparedStatement pstmt1;
                    	        pstmt=con1.prepareStatement("update doctor set availability=?,currentpatientid=? where Doctor_id=?");
                    	        pstmt.setString(1,"available");
                    	        pstmt.setInt(2,0);
                    	        pstmt.setInt(3, status);
                    	        pstmt.executeUpdate();
                    	        pstmt1=con1.prepareStatement("update patient set AttendingDoctorID=?,FeesToBePaid=?,TypeOfDoctorNeeded=? where Patient_id=?");
                    	        pstmt1.setInt(1, 0);
                    	        pstmt1.setInt(2, totalfees);
                    	        pstmt1.setString(3,null);
                    	        pstmt1.setInt(4,patid);
                    	        pstmt1.executeUpdate();
                    	        Statement stmt=con.createStatement(); 
                    	        int sta=status;
                    	        String s="insert into appoinment(DoctorID,DoctorUsername,DoctorSpecialization,PatientID,PatientUsername,PatientType,Fees) values ("+sta+",\""+doctusername+"\",\""+spec+"\","+patid+",\""+patusername+"\",\""+pattype+"\","+totalfees+");";
                    	        stmt.executeUpdate(s);
                    	        con1.close();
            	        		}
            	        		catch(Exception e) {
            	        			System.out.println("Hi");
            	        		}
                    	        break;
            	        	}
            	        }
            	        con.close();
            		}
            		catch(Exception e) {
            			System.out.println(e);
            		}
            		
        		}
        		else if(opt==2) {
        			System.out.println("Patient must be converted as in patient");
        		}
        		else if(opt==3) {
        			System.out.println("Transfer to other branch");
        		}
        		else {
        			System.out.println("Wrong option");
        		}
        		break;
        	}
        	case 5:{
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
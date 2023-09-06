package hospital_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
public class doctorfunctions {
	private Scanner sc;
	int check=0;
	public int doctor_functions(int status){
		int currentpatid=0;
		float totalfees=0;
        sc = new Scanner(System.in);
        int roomno=0;
        do {
        	System.out.println("----------Doctor Menu-----------");
        	System.out.println("1)View Profile");
        	System.out.println("2)Change Password");
        	System.out.println("3)Confirm appoinments and precheckup");
        	System.out.println("4)View current patient details");
        	System.out.println("5)Current patient treatment");
        	System.out.println("6)View Previous appoinments");
        	System.out.println("7)Logout");
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
        	        	System.out.println("Fees:-"+rs.getFloat("Fees"));
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
        	        		currentpatid=patid;
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
        		if(c==0) {
        			System.out.println("No patient to be treated");
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
            	        System.out.println(" In room id:-"+roomno);
            	        pstmt2.setInt(2,patid);
            	        pstmt2.executeUpdate();
            	        System.out.println("Scheduled Appoinment date  "+LocalDate.now()+" and time  "+LocalTime.now().plusHours(2));
            	        System.out.println("The scheduled appoinment details have been mailed to the patient");
            	        con1.close();
            	        c=0;
            	        check=1;
            	        break;
            	        }
    	        		catch(Exception e) {
    	        			System.out.println(e);
    	        		}
    	        	}
    	        }
        		}
        		catch(Exception e) {
        			System.out.println(e);
    		}
        		break;
        	}
        	case 4:{
        		if(check==1) {
        		System.out.println("Patient "+currentpatid+" details are");
        		try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        Statement stmt=con.createStatement();
        	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+currentpatid);
        	        if(rs.next()) {
        	        	System.out.println("Name:-"+rs.getString("Firstname")+" "+rs.getString("Lastname"));
        	        	System.out.println("EmailID:-"+rs.getString("EmailID"));
        	        	System.out.println("Age:-"+rs.getInt("Age"));
        	        	System.out.println("Gender:-"+rs.getString("Gender"));
        	        	System.out.println("BloodGroup:-"+rs.getString("BloodGroup"));
        	        }
        	        con.close();
        		}
        		catch(Exception e) {
        			System.out.println(e);
        		}
        		}
        		else {
        			System.out.println("You didnt confirm the appoinment of any patient");
        		}
        		break;
        	}
        	case 5:{
        		if(check==1) {
        		System.out.println("After pre checkup");
        		System.out.println("1)Discharge");
        		System.out.println("2)Further treatment");
        		System.out.println("3)Transfer to other doctor");
        		System.out.println("Enter your choice");
        		int opt=sc.nextInt();
        		sc.nextLine();
        		if(opt==1) {
        			totalfees=0;
        			float specfees=0;
        			int yoe=0;
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
            	        	specfees=rs.getFloat("Fees");
            	        	yoe=rs.getInt("YearsOfExperience");
            	        	totalfees+=specfees*(yoe/2);
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
            	        ResultSet rs1=statement1.executeQuery();
            	        while(rs1.next()) {
            	        	if(rs1.getString("Availability").equals("notavailable")) {
            	        		totalfees+=rs1.getInt("NumberOfDays")*10*specfees;
            	        		try {
            	        		Class.forName("com.mysql.cj.jdbc.Driver");
                    	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                    	        PreparedStatement pstmt;
                    	        PreparedStatement pstmt1;
                    	        pstmt=con1.prepareStatement("update room set DoctorID=?,DoctorUsername=?,StaffID=?,StaffUsername=? where Roomid=?");
                    	        pstmt.setInt(1,0);
                    	        pstmt.setString(2,null);
                    	        pstmt.setInt(3, 0);
                    	        pstmt.setString(4,null);
                    	        pstmt.setInt(5, roomno);
                    	        pstmt.executeUpdate();
                    	        pstmt1=con1.prepareStatement("update doctor set roomid=? where Doctor_id=?");
                    	        pstmt1.setInt(1,0);
                    	        pstmt1.setInt(2,status);
                    	        pstmt1.executeUpdate();
                    	        con1.close();
                    	        break;
                    	        }
            	        		catch(Exception e) {
            	        			System.out.println(e);
            	        		}
            	        	}
            	        }
            	        con.close();
                		}
                		catch(Exception e) {
                			System.out.println(e);
            		}
            		try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        String sql="select * from patient where TypeOfDoctorNeeded=? and Status=? and AttendingDoctorID=?";
            	        int currstaffid=0;
            	        PreparedStatement statement = con.prepareStatement(sql);
            	        statement.setString(1,spec);
            	        statement.setString(2, "confirmed");
            	        statement.setInt(3, status);
            	        ResultSet rs=statement.executeQuery();
            	        while(rs.next()) {
            	        	if(rs.getString("Status").equals("confirmed") && avail.equals("notavailable")) {
            	        		patid=rs.getInt("Patient_id");
            	        		patusername=rs.getString("Username");
            	        		String pattype=rs.getString("PatientType");
            	        		try {
            	        		Class.forName("com.mysql.cj.jdbc.Driver");
                    	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                    	        PreparedStatement pstmt;
                    	        PreparedStatement pstmt1,pstmt2;
                    	        pstmt=con1.prepareStatement("update doctor set availability=?,currentpatientid=?,staffid=? where Doctor_id=?");
                    	        pstmt.setString(1,"available");
                    	        pstmt.setInt(2,0);
                    	        pstmt.setInt(3, 0);
                    	        pstmt.setInt(4, status);
                    	        pstmt.executeUpdate();
                    	        if(pattype.equals("in")) {
                    	        	try {
                    	        		Class.forName("com.mysql.cj.jdbc.Driver");
                            	        Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                            	        PreparedStatement pst,pst1;
                            	        pst=con2.prepareStatement("update staff set availability=?,currentpatientid=?,roomid=?,doctorid=? where Staff_id=?");
                            	        pst.setString(1,"available");
                            	        pst.setInt(2,0);
                            	        pst.setInt(3,0);
                            	        pst.setInt(4,0);
                            	        pst.setInt(5, currstaffid);
                            	        pst.executeUpdate();
                            	        pst1=con2.prepareStatement("update room set StaffID=?,StaffUsername=?,NumberOfDays=? where StayingPatientID=?");
                            	        pst1.setInt(1, 0);
                            	        pst1.setString(2,null);
                            	        pst1.setInt(3, 0);
                            	        pst1.setInt(4, patid);
                            	        pst1.executeUpdate();
                            	        con2.close();
                    	        	}
                    	        	catch(Exception e) {
                    	        		System.out.println(e);
                    	        	}
                    	        }
                    	        pstmt1=con1.prepareStatement("update patient set AttendingDoctorID=?,AttendingStaffID=?,FeesToBePaid=?,TypeOfDoctorNeeded=? where Patient_id=?");
                    	        pstmt1.setInt(1, 0);
                    	        pstmt1.setInt(2, 0);
                    	        pstmt1.setFloat(3, totalfees);
                    	        pstmt1.setString(4,null);
                    	        pstmt1.setInt(5,patid);
                    	        pstmt1.executeUpdate();
                    	        Statement stmt=con.createStatement(); 
                    	        int sta=status;
                    	        LocalDate date=LocalDate.now();
                    	        String s="insert into appoinment(DoctorID,DoctorUsername,DoctorSpecialization,PatientID,PatientUsername,PatientType,Fees,appoinmentdate) values ("+sta+",\""+doctusername+"\",\""+spec+"\","+patid+",\""+patusername+"\",\""+pattype+"\","+totalfees+",\""+date+"\");";
                    	        stmt.executeUpdate(s);
                    	        pstmt2=con1.prepareStatement("update patient set PatientType=? where Patient_id=?");
                    	        pstmt2.setString(1,"out");
                    	        pstmt2.setInt(2, patid);
                    	        pstmt2.executeUpdate();
                    	        con1.close();
                    	        System.out.println("Patient "+rs.getString("Firstname")+" has been prepared and ready for discharge");
                    	        check=0;
            	        		}
            	        		catch(Exception e) {
            	        			System.out.println(e);
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
        			System.out.println("Patient must be converted as 'IN' patient");
        			System.out.println("Enter the number of days the patient needs to stay in the hospital:");
        			int nod=sc.nextInt();
        			sc.nextLine();
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt,pstmt1;
            	        pstmt=con1.prepareStatement("update patient set NumberOfDays=?,PatientType=? where Patient_id=?");
            	        pstmt.setInt(1,nod);
            	        pstmt.setString(2,"in");
            	        pstmt.setInt(3, currentpatid);
            	        pstmt.executeUpdate();
            	        pstmt1=con1.prepareStatement("update room set NumberOfDays=? where Roomid=?");
            	        pstmt1.setInt(1, nod);
            	        pstmt1.setInt(2, roomno);
            	        pstmt1.executeUpdate();
            	        con1.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        			System.out.println("Treatment requirements");
        			System.out.println("1)Requires doctor's expertise the whole time");
        			System.out.println("2)Doesn't require doctor's expertise the whole time");
        			int choice=sc.nextInt();
        			sc.nextLine();
        			if(choice==1) {
        				try {
        	        		Class.forName("com.mysql.cj.jdbc.Driver");
        	        	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");	
        	        		String sql1="select * from staff where Type=? and availability=?";
        	    	        PreparedStatement statement1 = con.prepareStatement(sql1);
        	    	        statement1.setString(1, "general");
        	    	        statement1.setString(2,"available");
        	    	        ResultSet rs=statement1.executeQuery();
        	    	        while(rs.next()) {
        	    	        	try {
        	    	        		Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	    	        		PreparedStatement pstmt,pstmt1,pstmt2,pstmt3;
        	    	        		pstmt=con1.prepareStatement("update doctor set staffid=? where Doctor_id=?");
        	    	        		pstmt.setInt(1, rs.getInt("Staff_id"));
        	    	        		pstmt.setInt(2, status);
        	    	        		pstmt.executeUpdate();
        	    	        		pstmt1=con1.prepareStatement("update patient set AttendingStaffID=? where Patient_id=?");
        	    	        		pstmt1.setInt(1, rs.getInt("Staff_id"));
        	    	        		pstmt1.setInt(2, currentpatid);
        	    	        		pstmt1.executeUpdate();
        	    	        		pstmt2=con1.prepareStatement("update room set StaffID=?,StaffUsername=? where Roomid=?");
        	    	        		pstmt2.setInt(1, rs.getInt("Staff_id"));
        	    	        		pstmt2.setString(2, rs.getString("Username"));
        	    	        		pstmt2.setInt(3, roomno);
        	    	        		pstmt2.executeUpdate();
        	    	        		pstmt3=con1.prepareStatement("update staff set availability=?,currentpatientid=?,roomid=?,doctorid=? where Staff_id=?");
        	    	        		pstmt3.setString(1, "notavailable");
        	    	        		pstmt3.setInt(2, currentpatid);
        	    	        		pstmt3.setInt(3, roomno);
        	    	        		pstmt3.setInt(4, status);
        	    	        		pstmt3.setInt(5, rs.getInt("Staff_id"));
        	    	        		pstmt3.executeUpdate();
        	    	        		System.out.println("Patient is under your care for the next "+nod+" days and the staff "+rs.getString("Firstname")+rs.getString("Lastname")+" will work under your guidance");
        	    	        		con1.close();
        	    	        	}
        	    	        	catch(Exception e) {
        	    	        		System.out.println(e);
        	    	        	}
        	    	        	break;
        	    	        	}
        	    	       con.close();
        				}
        				catch(Exception e) {
        					System.out.println(e);
        	    	        }
        			}
        			else if(choice==2) {
        				totalfees=0;
        				float specfees=0;
        				int yoe=0;
        				String patusername=null;
        				String doctusername=null;
        				int patid=0;
        				String pattype=null;
        				String spec=null;
        				try {
                			Class.forName("com.mysql.cj.jdbc.Driver");
                	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                	        Statement stmt=con.createStatement();
                	        ResultSet rs=stmt.executeQuery("select * from doctor where Doctor_id="+status);
                	        if(rs.next()) {
                	        	specfees=rs.getFloat("Fees");
                	        	yoe=rs.getInt("YearsOfExperience");
                	        	totalfees+=specfees*(yoe/2)+specfees*nod;
                	        	doctusername=rs.getString("Username");
                	        	spec=rs.getString("Specialization");
                	        	patid=rs.getInt("currentpatientid");
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
                	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+patid);
                	        if(rs.next()) {
                	        	patusername=rs.getString("Username");
                	        	pattype=rs.getString("PatientType");
                	        }
                	        con.close();
        				}
        				catch(Exception e) {
        					System.out.println(e);
        				}
        				try {
        					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
	    	        		PreparedStatement pstmt,pstmt1,pstmt2;
	    	        		Statement stmt=con.createStatement(); 
	    	        		LocalDate date=LocalDate.now();
                	        String s="insert into appoinment(DoctorID,DoctorUsername,DoctorSpecialization,PatientID,PatientUsername,PatientType,Fees,appoinmentdate) values ("+status+",\""+doctusername+"\",\""+spec+"\","+patid+",\""+patusername+"\",\""+pattype+"\","+totalfees+",\""+date+"\");";
                	        stmt.executeUpdate(s);
                	        pstmt=con.prepareStatement("update doctor set availability=?,currentpatientid=?,roomid=?,staffid=? where Doctor_id=?");
	    	        		pstmt.setString(1,"available");
	    	        		pstmt.setInt(2,0);
	    	        		pstmt.setInt(3, 0);
	    	        		pstmt.setInt(4, 0);
	    	        		pstmt.setInt(5, status);
	    	        		pstmt.executeUpdate();
	    	        		pstmt1=con.prepareStatement("update patient set TypeOfDoctorNeeded=?,AttendingDoctorID=?,AttendingStaffID=?,FeesToBePaid=?,Status=? where Patient_id=?");
	    	        		pstmt1.setString(1,"duty");
	    	        		pstmt1.setInt(2, 0);
	    	        		pstmt1.setInt(3,0);
	    	        		pstmt1.setFloat(4, totalfees);
	    	        		pstmt1.setString(5, "treated");
	    	        		pstmt1.setInt(6, currentpatid);
	    	        		pstmt1.executeUpdate();
	    	        		pstmt2=con.prepareStatement("update room set StaffID=?,StaffUsername=?,DoctorID=?,DoctorUsername=? where Roomid=?");
	    	        		pstmt2.setInt(1, 0);
	    	        		pstmt2.setString(2, null);
	    	        		pstmt2.setInt(3, 0);
	    	        		pstmt2.setString(4, null);
	    	        		pstmt2.setInt(5, roomno);
	    	        		pstmt2.executeUpdate();
	    	        		System.out.println("The patient has been assigned to duty staff");
        				}
        				catch(Exception e) {
        					System.out.println(e);
        				}
        				check=0;
        			}
        			else {
        				System.out.println("Wrong option");
        			}
        		}
        		else if(opt==3) {
        			System.out.println("Transfer to other branch");
        			System.out.println("Enter the type of doctor the patient needs:-");
        			String spec=sc.nextLine();
        			try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        PreparedStatement pstmt,pstmt1,pstmt2;
        	        pstmt=con1.prepareStatement("update patient set TypeOfDoctorNeeded=?,AttendingDoctorID=?,AdmittedRoomID=?,status=? where Patient_id=?");
        	        pstmt.setString(1,spec);
        	        pstmt.setInt(2,0);
        	        pstmt.setInt(3,0);
        	        pstmt.setString(4, "admitted");
        	        pstmt.setInt(5, currentpatid);
        	        pstmt.executeUpdate();
        	        pstmt1=con1.prepareStatement("update doctor set availability=?,currentpatientid=?,roomid=? where Doctor_id=?");
        	        pstmt1.setString(1, "available");
        	        pstmt1.setInt(2, 0);
        	        pstmt1.setInt(3, 0);
        	        pstmt1.setInt(4, status);
        	        pstmt1.executeUpdate();
        	        pstmt2=con1.prepareStatement("update room set StayingPatientID=?,PatientUsername=?,DoctorID=?,DoctorUsername=?,Availability=? where Roomid=?");
        	        pstmt2.setInt(1, 0);
        	        pstmt2.setString(2, null);
        	        pstmt2.setInt(3, 0);
        	        pstmt2.setString(4, null);
        	        pstmt2.setString(5,"available");
        	        pstmt2.setInt(6, roomno);
        	        pstmt2.executeUpdate();
        	        System.out.println("The patient has been transferred to the "+spec+" department");
        	        con1.close();
        	        check=0;
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else {
        			System.out.println("Wrong option");
        		}
        		}
        		else {
        			System.out.println("You didnt confirm the appoinment of any patient");
        		}
        		break;
        	}
        	case 6:{
        		try{
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        String sql="select * from appoinment where DoctorID="+status;
        	        PreparedStatement statement = con.prepareStatement(sql);
        	        ResultSet rs=statement.executeQuery();
        	        while(rs.next()){
        	        	System.out.println("AppoinmentID:-"+rs.getInt("AppoinmentID"));
        	        	System.out.println("PatientID:-"+rs.getInt("PatientID"));
        	        	System.out.println("Patient Username:-"+rs.getString("PatientUsername"));
        	        	System.out.println("Patient type:-"+rs.getString("PatientType"));
        	        	System.out.println("Fees:-"+rs.getFloat("Fees"));
        	        	System.out.println("Appoinment Date:-"+rs.getString("appoinmentdate"));
        	        	System.out.println("\n\n\n");
        	        	}
        	        con.close();
        			}
        			catch(Exception e){
        				System.out.println("No such appoinment has been made");
        			}
        		break;
        	}
        	case 7:{
        		System.out.println("----------------Logged Out SuccessFully-------------------\n\n");
        		return -1;
        	}
        	}
        }while(true);
	}
}
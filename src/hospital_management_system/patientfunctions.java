package hospital_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDate;
public class patientfunctions {
	private Scanner sc;
	int stat=0;
	float rating=0;
	public int patient_functions(int status){
        sc = new Scanner(System.in);
        do {
        	System.out.println("----------Patient Menu-----------");
        	System.out.println("1)View Profile");
        	System.out.println("2)Change Password");
        	System.out.println("3)Book appointments");
        	System.out.println("4)View Current doctor details");
        	System.out.println("5)Fees");
        	System.out.println("6)View Previous appointments");
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
        	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
        	        if(rs.next()) {
        	        	System.out.println("\n\n");
        	        	System.out.println("Username:-"+rs.getString("Username"));
        	        	System.out.println("Password:-"+rs.getString("Password"));
        	        	System.out.println("Blood Group:-"+rs.getString("BloodGroup"));
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
        	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
        	        if(rs.next()) {
        	        pstmt=con.prepareStatement("update patient set Password=? where Patient_id=?");
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
        		try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        Statement stmt=con.createStatement();
        	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
        	        if(rs.next()) {
        	        	if(rs.getString("Status").equals("admitted")||rs.getString("Status").equals("confirmed")) {
        	        		stat=1;
        	        	}
        	        }
        	        con.close();
        		}
        		catch(Exception e) {
        			System.out.println(e);
        		}
        		if(stat==1) {
        			System.out.println("You already have an appointment");
        			break;
        		}
        		else {
        		System.out.println("\n\n");
        		System.out.println("What kind of doctor do you want based on your current problem");
        		System.out.println("If you dont know which doctor to pick kindly select 1)General ");
        		System.out.println("They will guide you to the correct treatment procedure");
        		System.out.println("1)General");
        		System.out.println("2)Gynecologist");
        		System.out.println("3)Heart specialist");
        		System.out.println("4)Cancer specialist");
        		System.out.println("5)ENT");
        		System.out.println("6)Skin specialist");
        		System.out.println("7)Psychiatrist");
        		System.out.println("8)Lung specialist");
        		System.out.println("9)Kidney specialist");
        		System.out.println("10)Eye specialist");
        		System.out.println("11)Dentist");        		
        		System.out.println("Enter your choice");
        		int opt=sc.nextInt();
        		sc.nextLine();
        		if(opt==1) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"general");
            	        pstmt.setString(2, "admitted");
            	        pstmt.setString(3, "out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==2) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"gynecologist");
            	        pstmt.setString(2, "admitted");
            	        pstmt.setString(3, "out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==3) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"cardiologist");
            	        pstmt.setString(2, "admitted");
            	        pstmt.setString(3, "out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==4) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"oncologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==5) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"ent");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==6) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"dermatologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==7) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"psychiatrist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==8) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"pulmonologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==9) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"nephrologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==10) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"ophthalmologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else if(opt==11) {
        			try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt;
            	        Statement stmt=con.createStatement();
            	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
            	        if(rs.next()) {
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=?,FeesPaid=? where Patient_id=?");
            	        pstmt.setString(1,"dentist");
            	        pstmt.setString(2, "admitted");
            	        pstmt.setString(3, "out");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5,status);
            	        pstmt.executeUpdate();
            	        }
            	        con.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else {
        			System.out.println("Wrong Option");
        			break;
        		}
        		System.out.println("\n\n");
        		System.out.println("Appointment made on "+LocalDate.now()+". Please wait for the doctor to confirm");
        		System.out.println("You will receive the details of the appointment through mail when the doctor has confirmed your appointment ");
        		System.out.println("\n\n");
        		}
        		break;
        	}
        	case 4:{
        		int docid=0;
        		try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        Statement stmt=con.createStatement();
        	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
        	        if(rs.next()) {
        	        	docid=rs.getInt("AttendingDoctorID");
        	        }
        	        ResultSet rs1=stmt.executeQuery("select * from doctor where Doctor_id="+docid);
        	        if(rs1.next()) {
        	        	System.out.println("\n\n");
        	        	System.out.println("The details of current doctor as follows:-");
        	        	System.out.println("Name:-"+rs1.getString("Firstname")+" "+rs1.getString("Lastname"));
        	        	System.out.println("EmailID:-"+rs1.getString("EmailID"));
        	        	System.out.println("Age:-"+rs1.getInt("Age"));
        	        	System.out.println("Experience:- "+rs.getString("YearsOfExperience")+" years of expreince in "+rs.getString("Specialization")+" department");
        	        	System.out.println("\n\n");
        	        }
        	        else {
        	        	System.out.println("You have been scheduled for discharge");
        	        }
        	        con.close();
        		}
        		catch(Exception e) {
        			System.out.println("Your appointment hasn't been confirmed yet");
        		}
        		break;
        	}
        	case 5:{
        		float fees=0;
        		String statusofpat=null;
        		try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        String sql="select * from patient where Patient_id=?";
        	        PreparedStatement statement = con.prepareStatement(sql);
        	        statement.setInt(1,status);
        	        ResultSet rs=statement.executeQuery();
        	        if(rs.next()) {
        	        	fees=rs.getFloat("FeesToBePaid");
        	        	statusofpat=rs.getString("Status");
        	        }
        		con.close();
        		}
        		catch(Exception e) {
        			System.out.println(e);
        		}
        		if(fees==0) {
        			System.out.println("You are not discharged");
        		}
        		else if(fees!=0 && statusofpat.equals("treated")) {
        			System.out.println("You are not discharged");
        		}
        		else {
        		System.out.println("Fees to be paid="+fees);
        		System.out.println("1)Pay fees");
        		int opt=sc.nextInt();
        		sc.nextLine();
        		if(opt==1) {
        			try {
        				Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        PreparedStatement pstmt,pstmt1;
            	        pstmt=con1.prepareStatement("update patient set FeesPaid=?,FeesToBePaid=?,Status=?,NumberOfDays=?,AdmittedRoomID=? where Patient_id=?");
            	        pstmt.setFloat(1,fees);
            	        pstmt.setFloat(2,0);
            	        pstmt.setString(3,"discharged");
            	        pstmt.setInt(4, 0);
            	        pstmt.setInt(5, 0);
            	        pstmt.setInt(6,status);
            	        pstmt.executeUpdate();
            	        pstmt1=con1.prepareStatement("update room set Availability=?,StayingPatientID=?,PatientUsername=?,NumberOfDays=? where StayingPatientID=?");
            	        pstmt1.setString(1, "available");
            	        pstmt1.setInt(2, 0);
            	        pstmt1.setString(3, null);
            	        pstmt1.setInt(4, 0);
            	        pstmt1.setInt(5, status);
            	        pstmt1.executeUpdate();
            	        System.out.println("Payment successful");
            	        System.out.println("Please leave a rating(0 is the worst and 5 is the best) for our services");
            	        rating=sc.nextFloat();
            	        sc.nextLine();
            	        stat=0;
            	        con1.close();
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        		}
        		else {
        			System.out.println("Wrong option");
        		}
        		try {
        			int count=0;
        			float avg=0;
        			String hname=null;
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        Statement stmt=con1.createStatement();
        	        PreparedStatement pstmt;
        	        ResultSet rs=stmt.executeQuery("select * from Hospital");
        	        if(rs.next()) {
        	        	float oldrating=rs.getFloat("Rating");
        	        	count=rs.getInt("counter");
        	        	avg=(rating+oldrating)/count;
        	        	hname=rs.getString("Name");
        	        	count=count+1;
        	        }
        	        pstmt=con1.prepareStatement("update Hospital set Rating=?,counter=? where Name=?");
        	        pstmt.setFloat(1, avg);
        	        pstmt.setInt(2, count);
        	        pstmt.setString(3, hname); 
        	        pstmt.executeUpdate();
        	        System.out.println("Thanks for your feedback");
        	        con1.close();
        		}
        		catch(Exception e) {
        			System.out.println(e);
        		}
        		}
        		break;
        	}
        	case 6:{
        		try{
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        String sql="select * from appoinment where PatientID="+status;
        	        PreparedStatement statement = con.prepareStatement(sql);
        	        ResultSet rs=statement.executeQuery();
        	        while(rs.next()){
        	        	System.out.println("\n\n");
        	        	System.out.println("AppointmentID:-"+rs.getInt("AppoinmentID"));
        	        	System.out.println("Doctor Username:-"+rs.getString("DoctorUsername"));
        	        	System.out.println("Doctor Specialization:-"+rs.getString("DoctorSpecialization"));
        	        	System.out.println("Fees:-"+rs.getFloat("Fees"));
        	        	System.out.println("Appointment Date:-"+rs.getString("appoinmentdate"));
        	        	System.out.println("Number of days spent in the hospital: "+rs.getInt("numberofdays"));
        	        	System.out.println("\n\n\n");
        	        	System.out.println("\n\n");
        	        }
        	        con.close();
        			}
        			catch(Exception e){
        				System.out.println("No such appointment has been made");
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
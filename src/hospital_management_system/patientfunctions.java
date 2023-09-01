package hospital_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class patientfunctions {
	private Scanner sc;
	int stat=0;
	public int patient_functions(int status){
        sc = new Scanner(System.in);
        do {
        	System.out.println("----------Patient Menu-----------");
        	System.out.println("1)View Profile");
        	System.out.println("2)Change Password");
        	System.out.println("3)Book appoinments");
        	System.out.println("4)Fees");
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
        	        ResultSet rs=stmt.executeQuery("select * from patient where Patient_id="+status);
        	        if(rs.next()) {
        	        	System.out.println("Username:-"+rs.getString("Username"));
        	        	System.out.println("Password:-"+rs.getString("Password"));
        	        	System.out.println("Blood Group:-"+rs.getString("BloodGroup"));
        	        	System.out.println("RegistrationDate: "+rs.getDate("RegistrationDate"));
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
        			System.out.println("You already have an appoinment");
        			break;
        		}
        		else {
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"general");
            	        pstmt.setString(2, "admitted");
            	        pstmt.setString(3, "out");
            	        pstmt.setInt(4,status);
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"gynecologist");
            	        pstmt.setString(2, "admitted");
            	        pstmt.setString(3, "out");
            	        pstmt.setInt(4,status);
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"cardiologist");
            	        pstmt.setString(2, "admitted");
            	        pstmt.setString(3, "out");
            	        pstmt.setInt(4,status);
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"oncologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4,status);
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"ent");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4,status);
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"dermatologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4,status);
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"psychiatrist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4,status);
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"pulmonologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4,status);
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"nephrologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4,status);
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
            	        pstmt=con.prepareStatement("update Patient set TypeOfDoctorNeeded=?,Status=?,PatientType=? where Patient_id=?");
            	        pstmt.setString(1,"ophthalmologist");
            	        pstmt.setString(2,"admitted");
            	        pstmt.setString(3,"out");
            	        pstmt.setInt(4,status);
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
        		System.out.println("Appoinment made. Please wait for the doctor to confirm");
        		}
        		break;
        	}
        	case 4:{
        		int fees=0;
        		try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        String sql="select FeesToBePaid from patient where Patient_id=?";
        	        PreparedStatement statement = con.prepareStatement(sql);
        	        statement.setInt(1,status);
        	        ResultSet rs=statement.executeQuery();
        	        if(rs.next()) {
        	        	fees=rs.getInt("FeesToBePaid");
        	        }
        		con.close();
        		}
        		catch(Exception e) {
        			System.out.println(e);
        		}
        		if(fees==0) {
        			System.out.println("You are not not discharged");
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
            	        PreparedStatement pstmt	;
            	        pstmt=con1.prepareStatement("update patient set FeesPaid=?,FeesToBePaid=?,Status=? where Patient_id=?");
            	        pstmt.setInt(1,fees);
            	        pstmt.setInt(2,0);
            	        pstmt.setString(3,"discharged");
            	        pstmt.setInt(4,status);
            	        pstmt.executeUpdate();
            	        System.out.println("Payment successful");
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
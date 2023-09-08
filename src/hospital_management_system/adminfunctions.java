package hospital_management_system;
import java.sql.*;
import java.util.Scanner;
public class adminfunctions {
	private Scanner sc;
	public int doctorfees(String specialization) {
		if(specialization.equals("general")) return 200;
		else if(specialization.equals("gynecologist")) return 300;
		else if(specialization.equals("cardiologist")) return 250;
		else if(specialization.equals("oncologist")) return 300;
		else if(specialization.equals("ent")) return 200;
		else if(specialization.equals("dermatologist")) return 200;
		else if(specialization.equals("psychiatrist")) return 300;
		else if(specialization.equals("pulmonologist")) return 250;
		else if(specialization.equals("nephrologist")) return 250;
		else if(specialization.equals("ophthalmologist")) return 200;
		else if(specialization.equals("dentist")) return 250;
		return 0;
	}
	public int admin_functions(){
        sc = new Scanner(System.in);
        do {
        	System.out.println("----------Admin Menu-----------");
        	System.out.println("1)View Profile");
        	System.out.println("2)Change Password");
        	System.out.println("3)Specialization Types");
        	System.out.println("4)Add a new User");
        	System.out.println("5)View User Details");
        	System.out.println("6)Rooms History");
        	System.out.println("7)Appointments History");
        	System.out.println("8)Change Hospital contact info");
        	System.out.println("9)Logout");
        	System.out.println("Enter your choice:-");
        	int option=sc.nextInt();
        	sc.nextLine();
        	switch(option) 
        	{
        		case 1:{
        		try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        Statement stmt=con.createStatement();
        	        ResultSet rs=stmt.executeQuery("select * from admin");
        	        if(rs.next()) {
        	        	System.out.println("Username:-"+rs.getString("login_id"));
        	        	System.out.println("Password:-"+rs.getString("password"));
        	        	System.out.println("Phone Number:-"+rs.getString("phone"));
        	        	System.out.println("Email:-"+rs.getString("email"));
        	        	System.out.println("Address:-"+rs.getString("address"));
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
            	        pstmt=con.prepareStatement("update admin set password=? where login_id=?");
            	        pstmt.setString(1,pwd);
            	        pstmt.setString(2,"admin");
            	        pstmt.executeUpdate();
            	        pstmt.close();
            	        con.close();
            	        System.out.println("Password updated");
        			}
        			catch(Exception e) {
        				System.out.println(e);
        			}
        			break;
        		}
        		case 3:{
        			System.out.println("Department in out hospital are as follows:-");
        			System.out.println("general");
        			System.out.println("gynecologist");
        			System.out.println("cardiologist (heart specialist)");
        			System.out.println("oncologist (cancer specialist)");
        			System.out.println("ent (Ear Nose Tounge)");
        			System.out.println("dermatologist (skin specialist)");
        			System.out.println("psychiatrist");
        			System.out.println("pulmonologist (lung specialist)");
        			System.out.println("nephrologist (kidney specialist)");
        			System.out.println("ophthalmologist (eye specialist)");
        			System.out.println("dentist");
        			break;
        		}
        		case 4:{
        			System.out.println("1)Add a Doctor");
        			System.out.println("2)Add a Staff");
        			System.out.println("3)Add a patient");
        			int opt=sc.nextInt();
        			sc.nextLine();
        			if(opt==1) {
        			System.out.println("Enter First name:-");
        			String firstname=sc.nextLine();
        			System.out.println("Enter Last name:-");
        			String lastname=sc.nextLine();
        			System.out.println("Age:-");
        			int age=sc.nextInt();
        			sc.nextLine();
        			System.out.println("Gender:-");
        			String gender=sc.nextLine();
        			System.out.println("EmailID:-");
        			String emailid=sc.nextLine();
        			System.out.println("Address:-");
        			String address=sc.nextLine();
        			System.out.println("Qualification");
        			String qualification=sc.nextLine();
        			System.out.println("Specialization:-");
        			String specialization=sc.nextLine();
        			System.out.println("Registration Date(YYYY-MM-DD)format:-");
        			String registrationdate=sc.nextLine();
        			System.out.println("YearsOfExperience:-");
        			int yearsofexperience=sc.nextInt();
        			sc.nextLine();
        			System.out.println("Username for this doctor:-"+emailid);
        			String username=emailid;
        			String availability="available";
        			float fees=doctorfees(specialization);
        			System.out.println();
        			System.out.println("Password for this doctor:-welcome@abc");
        			String password="welcome@abc";
        			System.out.println();
        			try{
        				Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        Statement stmt=con.createStatement();
            	        String sql="insert into doctor(Firstname,Lastname,Age,Gender,EmailID,Address,Qualification,Specialization,RegistrationDate,YearsOfExperience,Username,Password,availability,fees) values (\""+firstname+"\",\""+lastname+"\","+age+",\""+gender+"\",\""+emailid+"\",\""+address+"\",\""+qualification+"\",\""+specialization+"\",\""+registrationdate+"\","+yearsofexperience+",\""+username+"\",\""+password+"\",\""+availability+"\","+fees+");";
            	        stmt.executeUpdate(sql);
            	        System.out.println("Details of the doctor added successfully");
            	        con.close();
        			}
        			catch(Exception e){
        				System.out.println(e);
        			}
        			}
        			else if(opt==2) {
        				System.out.println("Enter First name:-");
            			String firstname=sc.nextLine();
            			System.out.println("Enter Last name:-");
            			String lastname=sc.nextLine();
            			System.out.println("Age:-");
            			int age=sc.nextInt();
            			sc.nextLine();
            			System.out.println("Gender:-");
            			String gender=sc.nextLine();
            			System.out.println("EmailID:-");
            			String emailid=sc.nextLine();
            			System.out.println("Address:-");
            			String address=sc.nextLine();
            			System.out.println("Qualification");
            			String qualification=sc.nextLine();
            			System.out.println("Staff type:-");
            			String specialization=sc.nextLine();
            			System.out.println("Registration Date(YYYY-MM-DD)format:-");
            			String registrationdate=sc.nextLine();
            			System.out.println("YearsOfExperience:-");
            			int yearsofexperience=sc.nextInt();
            			sc.nextLine();
            			System.out.println("Username for this staff:-"+emailid);
            			String username=emailid;
            			String availability="available";
            			System.out.println("Password for this staff:-welcome@abc");
            			String password="welcome@abc";
            			System.out.println();
            			try{
            				Class.forName("com.mysql.cj.jdbc.Driver");
                	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                	        Statement stmt=con.createStatement();
                	        String sql="insert into staff(Firstname,Lastname,Age,Gender,EmailID,Address,Qualification,Type,RegistrationDate,YearsOfExperience,Username,Password,availability) values (\""+firstname+"\",\""+lastname+"\","+age+",\""+gender+"\",\""+emailid+"\",\""+address+"\",\""+qualification+"\",\""+specialization+"\",\""+registrationdate+"\","+yearsofexperience+",\""+username+"\",\""+password+"\",\""+availability+"\");";
                	        stmt.executeUpdate(sql);
                	        System.out.println("Details of the staff added successfully");
                	        con.close();
            			}
            			catch(Exception e){
            				System.out.println(e);
            			}
        			}
        			else if(opt==3) {
        				System.out.println("Enter First name:-");
            			String firstname=sc.nextLine();
            			System.out.println("Enter Last name:-");
            			String lastname=sc.nextLine();
            			System.out.println("Age:-");
            			int age=sc.nextInt();
            			sc.nextLine();
            			System.out.println("Gender:-");
            			String gender=sc.nextLine();
            			System.out.println("EmailID:-");
            			String emailid=sc.nextLine();
            			System.out.println("Address:-");
            			String address=sc.nextLine();
            			System.out.println("Enter blood group");
            			String bloodgroup=sc.nextLine();
            			System.out.println("Registration Date(YYYY-MM-DD)format:-");
            			String registrationdate=sc.nextLine();
            			System.out.println("Username for this patient:-"+emailid);
            			String username=emailid;
            			System.out.println();
            			System.out.println("Password for this patient:-welcome@abc");
            			String password="welcome@abc";
            			System.out.println();
            			try{
            				Class.forName("com.mysql.cj.jdbc.Driver");
                	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                	        Statement stmt=con.createStatement();
                	        String sql="insert into patient(Firstname,Lastname,Age,Gender,EmailID,Address,BloodGroup,RegistrationDate,Username,Password) values (\""+firstname+"\",\""+lastname+"\","+age+",\""+gender+"\",\""+emailid+"\",\""+address+"\",\""+bloodgroup+"\",\""+registrationdate+"\",\""+username+"\",\""+password+"\");";
                	        stmt.executeUpdate(sql);
                	        System.out.println("Details of the patient added successfully");
                	        con.close();
            			}
            			catch(Exception e){
            				System.out.println(e);
            			}
        			}
        			else {
        				System.out.println("Wrong option Try again");
        			}
        			break;
        		}
        		case 5:{
        			System.out.println("1)View details of doctors");
        			System.out.println("2)View details of staffs");
        			System.out.println("3)View details of patients");
        			int opt=sc.nextInt();
        			sc.nextLine();
        			if(opt==1) {
        			System.out.println("Details of the doctor based on specialization");
        			System.out.println("Enter specialization:-");
        			String spec=sc.nextLine();
        			try{
        			Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        String sql="select * from doctor where Specialization=?";
        	        PreparedStatement statement = con.prepareStatement(sql);
        	        statement.setString(1,spec);
        	        ResultSet rs=statement.executeQuery();
        	        while(rs.next()){
        	        	System.out.println("FirstName:-"+rs.getString("FirstName"));
        	        	System.out.println("LastName:-"+rs.getString("LastName"));
        	        	System.out.println("Age:-"+rs.getInt("Age"));
        	        	System.out.println("EmailID:-"+rs.getString("EmailID"));
        	        	System.out.println("Username:-"+rs.getString("Username"));
        	        	System.out.println("Availability:-"+rs.getString("availability"));
        	        	System.out.println("Fees:-"+rs.getFloat("fees"));
        	        	System.out.println("\n\n\n");
        	        	}
        	        con.close();
        			}
        			catch(Exception e){
        				System.out.println("Invalid Specialization");
        			}
        			}
        			else if(opt==2) {
        				System.out.println("Details of the staff based on specialization");
            			System.out.println("Enter specialization:-");
            			String spec=sc.nextLine();
            			try{
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        String sql="select * from staff where Specialization=?";
            	        PreparedStatement statement = con.prepareStatement(sql);
            	        statement.setString(1,spec);
            	        ResultSet rs=statement.executeQuery();
            	        while(rs.next()){
            	        	System.out.println("FirstName:-"+rs.getString("FirstName"));
            	        	System.out.println("LastName:-"+rs.getString("LastName"));
            	        	System.out.println("Age:-"+rs.getInt("Age"));
            	        	System.out.println("EmailID:-"+rs.getString("EmailID"));
            	        	System.out.println("Username:-"+rs.getString("Username"));
            	        	System.out.println("Availability:-"+rs.getString("availability"));
            	        	System.out.println("\n\n\n");
            	        	}
            	        con.close();
            			}
            			catch(Exception e){
            				System.out.println("Invalid Specialization");
            			}
        			}
        			else if(opt==3) {
        				System.out.println("Details of the patients based on type(in/out)");
            			System.out.println("Enter type:-");
            			String ptype=sc.nextLine();
            			try{
                			Class.forName("com.mysql.cj.jdbc.Driver");
                	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                	        String sql="select * from patient where PatientType=?";
                	        PreparedStatement statement = con.prepareStatement(sql);
                	        statement.setString(1,ptype);
                	        ResultSet rs=statement.executeQuery();
                	        while(rs.next()){
                	        	System.out.println("FirstName:-"+rs.getString("FirstName"));
                	        	System.out.println("LastName:-"+rs.getString("LastName"));
                	        	System.out.println("Age:-"+rs.getInt("Age"));
                	        	System.out.println("EmailID:-"+rs.getString("EmailID"));
                	        	System.out.println("Username:-"+rs.getString("Username"));
                	        	System.out.println("Blood Group:-"+rs.getString("BloodGroup"));
                	        	System.out.println("\n\n\n");
                	        	}
                	        con.close();
            			}
                			catch(Exception e){
                				System.out.println("Invalid patient type");
                			}
        			}
        			else {
        				System.out.println("Wrong option");
        			}
        			break;
        		}
        		case 6:{
        			System.out.println("1)Rooms that are available");
        			System.out.println("2)Rooms that are occupied");
        			int opt=sc.nextInt();
        			if(opt==1) {
        			String rtype="available";
        			try{
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        String sql="select * from room where availability=?";
            	        PreparedStatement statement = con.prepareStatement(sql);
            	        statement.setString(1,rtype);
            	        ResultSet rs=statement.executeQuery();
            	        while(rs.next()){
            	        	System.out.println("RoomID:-"+rs.getInt("Roomid"));
            	        	System.out.println("DoctorUsername:-"+rs.getString("DoctorUsername"));
            	        	System.out.println("\n\n\n");
            	        	}
            	        con.close();
        			}
            			catch(Exception e){
            				System.out.println("Room does not exist");
            			}
        			}
        			else if(opt==2) {
        				String rtype="notavailable";
            			try{
                			Class.forName("com.mysql.cj.jdbc.Driver");
                	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                	        String sql="select * from room where availability=?";
                	        PreparedStatement statement = con.prepareStatement(sql);
                	        statement.setString(1,rtype);
                	        ResultSet rs=statement.executeQuery();
                	        while(rs.next()){
                	        	System.out.println("RoomID:-"+rs.getInt("Roomid"));
                	        	System.out.println("Doctor Username:-"+rs.getString("DoctorUsername"));
                	        	System.out.println("Staff Username:-"+rs.getString("StaffUsername"));
                	        	System.out.println("Patient Username:-"+rs.getString("PatientUsername"));
                	            System.out.println("Number Of Days:-"+rs.getInt("NumberOfDays"));
                	        	System.out.println("\n\n\n");
                	        	}
                	        con.close();
            			}
                			catch(Exception e){
                				System.out.println("Room does not exist");
                			}
        			}
        			break;
        		}
        		case 7:{
        			try{
            			Class.forName("com.mysql.cj.jdbc.Driver");
            	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
            	        String sql="select * from appoinment";
            	        PreparedStatement statement = con.prepareStatement(sql);
            	        ResultSet rs=statement.executeQuery();
            	        while(rs.next()){
            	        	System.out.println("AppointmentID:-"+rs.getInt("AppoinmentID"));
            	        	System.out.println("Doctor Username:-"+rs.getString("DoctorUsername"));
            	        	System.out.println("Doctor Specialization:-"+rs.getString("DoctorSpecialization"));
            	        	System.out.println("Patient Username:-"+rs.getString("PatientUsername"));
            	        	System.out.println("Patient Type:-"+rs.getString("PatientType"));
            	        	System.out.println("Fees:-"+rs.getFloat("Fees"));
            	        	System.out.println("Appoinment Date:-"+rs.getString("appoinmentdate"));
            	        	System.out.println("Number of days: "+rs.getInt("numberofdays"));
            	        	System.out.println("\n\n\n");
            	        	}
            	        con.close();
            			}
            			catch(Exception e){
            				System.out.println("No such appointment has been made");
            			}
        			break;
        		}
        		case 8:{
        			System.out.println("1)Change Hospital MailID");
        			System.out.println("2)Change Hospital contact number");
        			int choice=sc.nextInt();
        			sc.nextLine();
        			if(choice==1) {
        				System.out.println("Enter new EMailID for hospital:");
        				String mail=sc.nextLine();
        				try {
        					Class.forName("com.mysql.cj.jdbc.Driver");
                	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                	        PreparedStatement pstmt;
                	        pstmt=con.prepareStatement("update Hospital set Email=?");
                	        pstmt.setString(1, mail);
                	        pstmt.executeUpdate();
                	        System.out.println("EMail changed successfully");
                	        con.close();
        				}
        				catch(Exception e) {
        					System.out.println(e);
        				}
        			}
        			else if(choice==2) {
        				System.out.println("Enter new Landline number for hospital:");
        				String landline=sc.nextLine();
        				try {
        					Class.forName("com.mysql.cj.jdbc.Driver");
                	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
                	        PreparedStatement pstmt;
                	        pstmt=con.prepareStatement("update Hospital set LandLine=?");
                	        pstmt.setString(1, landline);
                	        System.out.println("LandLine number changed successfully");
                	        pstmt.executeUpdate();
                	        con.close();
        				}
        				catch(Exception e) {
        					System.out.println(e);
        				}
        			}
        			else {
        				System.out.println("Wrong option");
        			}
        			break;
        		}
        		case 9:{
        			return -1;
        		}
        	}
        }while(true);
	}
}
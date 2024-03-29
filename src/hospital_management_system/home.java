package hospital_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class home {
	private static Scanner sc;
	public static void main(String[] args) throws Exception {
		sc = new Scanner(System.in);
		System.out.println("Welcome to ABC Hospital");
		boolean flag=true;
		do{
			System.out.println("Home page menu:-");
			System.out.println("1)Admin login");
			System.out.println("2)Doctor login");
			System.out.println("3)Staff login");
			System.out.println("4)Patient login");
			System.out.println("5)About US");
			System.out.println("6)Exit");
			System.out.println("Enter your choice");
			int n=sc.nextInt();
			switch(n) {
			case 1:{
				Admin_login admin=new Admin_login();
				int status=admin.adlogin(args);
				if(status==1) {
					System.out.println("Login successful");
					adminfunctions adminfunc=new adminfunctions();
					int s=adminfunc.admin_functions();
					if(s==-1) break;
				}
				else if(status==-1) {
					System.out.println("Wrong admin ID or password");
				}
				break;
			}
			case 2:{
				Doctor_login doctor=new Doctor_login();
				int status=doctor.doclogin(args);
				if(status!=-1&&status!=0) {
					System.out.println("Login successful");
					doctorfunctions docfunc=new doctorfunctions();
					int s=docfunc.doctor_functions(status);
					if(s==-1) break;
				}
				else if(status==-1) {
					System.out.println("Wrong admin ID or password");
				}
				break;
			}
			case 3:{
				Staff_login staff=new Staff_login();
				int status=staff.stalogin(args);
				if(status!=-1&&status!=0) {
					System.out.println("Login successful");
					stafffunctions stafunc=new stafffunctions();
					int s=stafunc.staff_functions(status);
					if(s==-1) break;
				}
				else if(status==-1) {
					System.out.println("Wrong staff ID or password");
				}
				break;
			}
			case 4:{
				Patient_login patient=new Patient_login();
				int status=patient.patlogin(args);
				if(status!=-1&&status!=0) {
					System.out.println("Login successful");
					patientfunctions patfunc=new patientfunctions();
					int s=patfunc.patient_functions(status);
					if(s==-1) break;
				}
				else if(status==-1) {
					System.out.println("Wrong patient ID or password");
				}
				break;
			}
			case 5:{
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
        	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        	        Statement stmt=con.createStatement();
        	        ResultSet rs=stmt.executeQuery("select * from Hospital");
        	        if(rs.next()) {
        	        	System.out.println("\n\n\n");
        	        	System.out.println("Hospital name: "+rs.getString("Name"));
        	        	System.out.println("Address of the hospital: "+rs.getString("Address"));
        	        	System.out.println("Landline number: "+rs.getString("LandLine"));
        	        	System.out.println("Rating(out of 5): "+rs.getFloat("Rating"));
        	        	System.out.println("About the hospital: "+rs.getString("Description"));
        	        	System.out.println("\n\n\n\n");
        	        }
        	        con.close();
				}
				catch(Exception e) {
					System.out.println(e);
				}
				break;
			}
			case 6:{
				System.out.println("Thank you!");
				flag=false;
				break;
			}
			}	
		}while(flag);
	}
}
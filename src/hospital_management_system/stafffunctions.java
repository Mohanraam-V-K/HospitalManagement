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
        do {
        	System.out.println("----------Patient Menu-----------");
        	System.out.println("1)View Profile");
        	System.out.println("2)Change Password");
        	System.out.println("3)Logout");
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
        	        con.close();
        	        if(rs.next()) {
        	        	System.out.println("Username:-"+rs.getString("Username"));
        	        	System.out.println("Password:-"+rs.getString("Password"));
        	        	System.out.println("RegistrationDate: "+rs.getDate("RegistrationDate"));
        	        }
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
        	        pstmt.close();
        	        System.out.println("Password updated");
    			}
    			}
    			catch(Exception e) {
    				System.out.println(e);
    			}
    			break;
        	}
        	case 3:{
        		
        	}
        	case 4:{
        		
        	}
        	case 5:{
        		
        	}
        	case 6:{
        		System.out.println("----------------Logged Out SuccessFully-------------------\n\n");
        		return -1;
        	}
        	}
        }while(true);
	}
}
package hospital_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Admin_login {
	private Scanner sc;
	public int adlogin(String[] args) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        Statement stmt=con.createStatement();
        sc = new Scanner(System.in);
        System.out.println("Enter admin username:");
        String s1=sc.next();
        System.out.println("Enter password");
        String s2=sc.next();
        try {
        ResultSet rs=stmt.executeQuery("select login_id,password from admin where login_id='"+s1+"' and password='"+s2+"'");
        if(rs.next()) {
        	return 1;
        	}
        else {
        	return -1;
        	}   	
        }
        catch(Exception e) {
        System.out.println("Invalid");}
        con.close();
        return 0;
	}
}
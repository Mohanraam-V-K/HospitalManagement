package hospital_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Doctor_login {
	private Scanner sc;
	public int doclogin(String[] args) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
        Statement stmt=con.createStatement();
        sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String s1=sc.next();
        System.out.println("Enter password");
        String s2=sc.next();
        try {
        ResultSet rs=stmt.executeQuery("select * from doctor where Username='"+s1+"' and Password='"+s2+"'");
        if(rs.next()) {
        	int a=rs.getInt("Doctor_id");
        	return a;
        }
        else {
        	return -1;
        }  	
        }
        catch(Exception e) {
        System.out.println("No records exists");}
        con.close();
        return 0;
	}
}
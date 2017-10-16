package Actions;
//github ÉÏ´«
import com.opensymphony.xwork2.ActionSupport;

import java.sql.*;

public class delete {
	private String ISBN;
	
	public void setISBN(String ISBN){
		this.ISBN = new String(ISBN);
	}
	public String getISBN(){
		return ISBN;
	}
	
	Connection con;
	Statement st;
	public static Connection getConnection(){
		Connection con = null;
		try{
			visitDatebase db=new visitDatebase();
			db.visit();
			con = db.conn;
		}catch(Exception e){
			System.out.println("Connect Fail:"+e.getMessage());
		}
		return con;
	}
	
	public String execute()  throws Exception{
		con = getConnection();
		try{
			String sql = "delete from Book where ISBN="+"'"+ISBN+"'";
			System.out.println(sql);
			st = (Statement)con.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("Delete Successful.Delete DataNum:"+count);
			con.close();
			return "Success";
		}catch(SQLException e){
			return "Failed";
		}
	}
}
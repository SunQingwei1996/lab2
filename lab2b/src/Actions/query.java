package Actions;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class query extends ActionSupport {
	
	// JDBC �����������ݿ� URL
	
	ServletRequest request = ServletActionContext.getRequest();
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
	
	private String name;
	private String ISBN;
	private String Title;
	private String AuthorID;
	private String Publisher;
	private String PublishDate;
	private String Price;
	private ArrayList<String> BookList = new ArrayList<String>();
	//
    public String getAuthorID() {return this.name 

;}
    public void setAuthorID(String AuthorID){this.AuthorID =AuthorID; }
//
	public String execute() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		//String name1 = name;
		
		System.out.print(conn);
		try{
			// ע�� JDBC ����
		
			// ������
			System.out.println("�������ݿ�...");
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			visitDatebase db=new visitDatebase();
			db.visit();
			conn=db.conn;
			// ִ�в�ѯ
			System.out.println(" ʵ����Statement��...");
			//stmt = conn.createStatement();

			
			String sql = "SELECT AuthorID FROM Author WHERE";
			sql += " Name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rsl = ps.executeQuery();
			
			int id =0;
			if(rsl.next()){
				 id  = rsl.getInt("AuthorID");
			}else {
				return ERROR;
			}
			System.out.println("AuthorID"+ " "+id);
			rsl.close();
			String s = Integer.toString(id);
			
			sql = "SELECT * FROM Book WHERE";
			sql += " AuthorID = ?";
			PreparedStatement pss = conn.prepareStatement(sql);
			pss.setString(1, s);
			ResultSet rs = pss.executeQuery();
			// չ����������ݿ�
			String title  = new String();
			String PublishDate = new String();
			String ISBN = new String();
			String AuthorID = new String();
			String Publisher = new String();
			String Price = new String();
			while(rs.next()){	
				
				ISBN = rs.getString("ISBN");
				title = rs.getString("Title");
				AuthorID = rs.getString("AuthorID");
				Publisher = rs.getString("Publisher");
				PublishDate = rs.getDate("PublishDate").toString();
				Price = rs.getString("Price");
				
			}
			session.setAttribute("ISBN", ISBN);
			session.setAttribute("title", title);
			session.setAttribute("AuthorID", AuthorID);
			session.setAttribute("Publisher", Publisher);
			session.setAttribute("PublishDate",PublishDate);
			session.setAttribute("Price",Price);
			
			// ��ɺ�� ��
			rs.close();
			//stmt.close();
			conn.close();
//		}catch(SQLException se){
//			// ���� JDBC ����
//			se.printStackTrace();
//			return ERROR;
//		}catch(Exception e){
//			// ���� Class.forName ����
//			e.printStackTrace();
//			return ERROR;
		}finally{
			// �ر���Դ
			try{
				if(stmt!=null) stmt.close();
			}catch(SQLException se2){
			}// ʲô������
			try{
				if(conn!=null) conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		return SUCCESS;
	}
	
	public String getName() {
	      return name;
	   }
	public void setName(String name) {
	      this.name = name;
	   }
	public ArrayList<String> getBookList(){
		return BookList;
	}
	
}
package Actions;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class visitDatebase {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
//	static final String DB_URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_sunqingwei0lab2b";
//
//	// ���ݿ���û��������룬��Ҫ�����Լ�������
//	static final String USER = "n0kxokxl0o";
//	static final String PASS = "j3xyxw0m3m3x14izjk22lkl1x3hyi5y1jhxkl3yi";
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/bookdb";

	// ���ݿ���û��������룬��Ҫ�����Լ�������
	static final String USER = "root";
	static final String PASS = "710521";
	public Connection conn = null;
	public void visit() throws ClassNotFoundException, SQLException {
		// ע�� JDBC ����
					Class.forName("com.mysql.jdbc.Driver");
				
					// ������
					System.out.println("�������ݿ�...");
					conn = DriverManager.getConnection(DB_URL,USER,PASS);
	}
}

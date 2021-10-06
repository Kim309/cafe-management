package Model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class DBUtill {
	public static Connection getMySqlConnection(){
//		DB���Ḹ�� ���� Ŭ����.
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://192.168.55.5:3306/coffeemanagement?useUnicode=true&characterEncoding=UTF-8";
			String user = "coffee";
			String password = "0000";
			conn = DriverManager.getConnection(url, user, password);
//			System.out.println("DB���Ἲ��!");
			
		}catch(ClassNotFoundException e) {
			System.out.println("����̹� Ŭ������ ���ų� �ε��� �� �����ϴ�.<br/>");
		} catch(MySQLSyntaxErrorException e) {
			System.out.println("�����ͺ��̽��� ���ų� �̸��� �ùٸ��� �ʽ��ϴ�.<br/>");
		} catch(SQLException e) {
			System.out.println("�����ͺ��̽� ���� ������ �ùٸ��� �ʽ��ϴ�.<br/>");
		}
		return conn;
	}
//	�������� �����ϴ� �۾��� ������ close�Լ��� ȣ���Ͽ� ������ �����ش�.
	public static void close(Connection conn) {
		if(conn != null) { try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} }
	}
	
}

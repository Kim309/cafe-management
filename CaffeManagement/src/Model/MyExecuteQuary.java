package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyExecuteQuary {
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public MyExecuteQuary() {
		// TODO Auto-generated constructor stub
		conn = DBUtill.getMySqlConnection();
	}

//	���� ���� ������ �ʿ��� sql���� �����ָ� ó���Ѵ�.
	public void myExecuteUpdate(String sql) {
	      Connection conn = DBUtill.getMySqlConnection();
	      try {
	         stmt = conn.createStatement();
	         stmt.executeUpdate(sql);
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            stmt.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	         DBUtill.close(conn);
	      }
	   }
	
//	���� ��ü ������ ���
	public ResultSet salesSelect(String sql){
		Connection conn = DBUtill.getMySqlConnection();
		ResultSet rs = null;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
//		close()�� rs�� ����ϴ� �ʿ��� �����Ѵ�.
		
		return rs;
	}
//	��ü �޴��� ��ȯ�ϴ� �޼���
	public ResultSet menuSelect() {
        ResultSet rs = null;
        Statement stmt = null;
        try {
        	stmt = conn.createStatement();
        	String sql = "select * from menu";
        	rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
//	�Ű������� ���� �޴� �̸��� �����ϸ� ��ȯ�Ѵ�.
	public String menuInfo(String menu) {
        ResultSet rs = null;
        Statement stmt;
		String info = "";
		boolean isEmpty = true;
		String sql = "select info from menu where menu = '"+menu+"'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				info = rs.getString("info");
			}else {return "���� �޴��Դϴ�.";}
			
		}catch (Exception e) {}
		return info;
	}
//	�޴��� ���� ������ üũ�ϴ� �޼����Դϴ�.
	public boolean getQuantityCheck(String sql, int quantity) {
        ResultSet rs = null;
        Statement stmt;
        int q = 0;
        try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			q = rs.getInt("remain");
		}catch (Exception e) {}
        if (q >= quantity) {
			return true;
		}else {return false;}
	}
	public int getPrice(String menu) {
		// �ֹ� �ݾ��� ���ϴ� �޼���
        ResultSet rs = null;
        Statement stmt;
        String sql = "select price from menu where menu = '"+menu+"'";
        int price=0;
        try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			price = rs.getInt("price");
		}catch (Exception e) {}
		return price;
	}
	
	
	public void close() {                     // ���� �Լ� �������� �� ÷���ؾ���
	      try {
	         rs.close();
	         stmt.close();
	         pstmt.close();
	         conn.close();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
//	         e.printStackTrace();
	      }finally {
	         System.out.println("����");
	      }
	   }

	

	

	

	
	}


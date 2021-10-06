package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Menu extends MyExecuteQuary{
	   private String menu;
	   private int price;
	   private String info;
	   private int remain;

	   public Menu() {
	   } // �⺻ ������

	   public Menu(String menu, int price, String info, int remain) { // �⺻������
	      this.menu = menu;
	      this.price = price;
	      this.info = info;
	      this.remain = remain;
	   }

	   public ResultSet select() { // menuShowAll ����� new menu().select();
//	      �ش� �޼����  ��� �޴������� ���� �������ݴϴ�.
	       return super.menuSelect();
	   }
//		�޴� ��ȯ �޼���
	   public String getMenuInfo(String menu2) {
		   String s = super.menuInfo(menu2);
		   return s;
		}
	   
	   public void insert() { // �޴� �߰� ����� new Menu(menu, price, info, remain).Insert();
	      Connection conn = DBUtill.getMySqlConnection();
	      PreparedStatement pstmt = null;
	      try {
//	         ����� new Menu(menu, price, info, remain).Insert();
	         String sql = "insert into menu values(?, ?, ?, ?)";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, menu);
	         pstmt.setInt(2, price);
	         pstmt.setString(3, info);
	         pstmt.setInt(4, remain);
	         pstmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            pstmt.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	         DBUtill.close(conn);
	      }
	   }
	   
	   
	   public void remainMinus(String menu, int num) { // ��� ���� ��� ��� new Menu().remainMinus(�޴� �̸�, ������ ��� ��);
	      Connection conn = DBUtill.getMySqlConnection();
	      Statement stmt;
	      try {
	         stmt = conn.createStatement();
	         String sql = "update menu set remain= remain -" + num + " where menu ='" + menu + "'";
	         stmt.executeUpdate(sql);
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         DBUtill.close(conn);
	      }
	   }

	   public void delete(String menu) { // �޴� ���� ����� new Menu().delete(�޴��̸�);
	      Connection conn = DBUtill.getMySqlConnection();
	      PreparedStatement pstmt = null;
	      try {
//	         ����� new Menu(menu, price, info, remain).delete();
	         String sql = "delete from menu where menu = ?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, menu);
	         pstmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            pstmt.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	         DBUtill.close(conn);
	      }

	   }

	   private boolean validationMenu() { // insert�� ���� ���۵Ǵ� �Լ� // ��ȿ�� �˻� ����ϸ� true
	      if (price < 0)
	         return false; // ������ 0���� ������ false
	      if (remain < 0)
	         return false; // ��� 0���� ������ false
	      // �޴� �̸� �ߺ� �˻�
	      if (!checkMenu())
	         return false;// �ߺ� �˻� ��� ���ϸ� false
	      return true;
	   }

	   private boolean checkMenu() { // �ߺ��� �˻�Ǹ� false �ߺ��� �ƴϰų� ��������� true
	      Connection conn = DBUtill.getMySqlConnection();
	      ResultSet rs = null;
	      Statement stmt;

	      try {
	         stmt = conn.createStatement();
	         String sql = "select menu from menu";
	         rs = stmt.executeQuery(sql);
	         if (rs.next())
	            return true; // ��������� true
	         do {
	            if (rs.getString("menu").equals(this.menu))
	               return false;
	         } while (rs.next());
	      } catch (Exception e) {
	         // TODO: handle exception
	      }
	      return true;
	   }

	public boolean quantityCk(String menu, int quantity) {
		String sql = "select remain from menu where menu = '"+menu+"'";
		boolean ch = super.getQuantityCheck(sql, quantity);
		return ch;
	}


	
	}
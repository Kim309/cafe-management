package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class manucapy {
   private String menu;
   private int price;
   private String info;
   private int remain;

   manucapy() {
   } // �⺻ ������

   public manucapy(String menu, int price, String info, int remain) { // �⺻������
      this.menu = menu;
      this.price = price;
      this.info = info;
      this.remain = remain;
   }

   public void select() { // menuShowAll ����� new menu().select();
//	      �ش� �޼����  ��� �޴������� ���� �������ݴϴ�.
	      if (!validationMenu()) {
	         System.out.println("�Է��� ���� �������� �ʽ��ϴ�.");
	      } else {
	         Connection conn = DBUtill.getMySqlConnection();
	         ResultSet rs = null;
	         Statement stmt;

	         try {
	            stmt = conn.createStatement();
	            String sql = "select * from menu";
	            rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	            	System.out.println(rs.getString("menu"));
	            	System.out.println("����");
	            	System.out.println(rs.getString("info"));
	            	System.out.println("����:"+rs.getInt("price")+ "\t ���" + rs.getInt("remain"));
	            	System.out.println();
	            }
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         } finally {
	            try {
	               rs.close();
	               DBUtill.close(conn);
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }
	      }
	   }

   public void insert() { // �޴� �߰� ����� new Menu(menu, price, info, remain).Insert();
      if (!validationMenu()) {
         System.out.println("�Է��� ���� �������� �ʽ��ϴ�.");
      } else {
         Connection conn = DBUtill.getMySqlConnection();
         PreparedStatement pstmt = null;
         try {
//         ����� new Menu(menu, price, info, remain).Insert();
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
   }

   public void remainMinus(String menu, int num) { // ��� ���� ��� ��� new Menu().remainMinus(�޴� �̸�, ������ ��� ��);
      if (isEmpty()) {
         System.out.println("�޴� ���̺��� �������");
      }
      if (checkRemain(menu) < num) {
         System.out.println("������� �Ǹŷ��� ����");
      } else {
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
   }

   public void delete(String menu) { // �޴� ���� ����� new Menu().delete(�޴��̸�);
      if (isinTable(menu)) {
         Connection conn = DBUtill.getMySqlConnection();
         PreparedStatement pstmt = null;
         try {
//         ����� new Menu(menu, price, info, remain).delete();
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
      } else {
         System.out.println("���̺� �ȿ� " + menu + " ���� �����ϴ�.");
      }

   }

   private boolean isinTable(String menu) { // ���̺� menu ���� �ִ��� Ȯ�� ������ true; ������ false;
      Connection conn = DBUtill.getMySqlConnection();
      ResultSet rs = null;
      Statement stmt;

      try {
         stmt = conn.createStatement();
         String sql = "select menu from menu";
         rs = stmt.executeQuery(sql);
         while (rs.next()) {
            if (rs.getString("menu").equals(menu))
               return true;
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return false;
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

   private boolean checkMenu() { // �ߺ��� �˻�Ǹ� false �ߺ��� �ƴϸ� true
      Connection conn = DBUtill.getMySqlConnection();
      ResultSet rs = null;
      Statement stmt;

      try {
         stmt = conn.createStatement();
         String sql = "select menu from menu";
         rs = stmt.executeQuery(sql);
         do {
            if (rs.getString("menu").equals(this.menu))
               return false;
         } while (rs.next());
      } catch (Exception e) {
         // TODO: handle exception
      }
      return true;
   }

   private boolean isEmpty() { // ��������� true
      Connection conn = DBUtill.getMySqlConnection();
      ResultSet rs = null;
      Statement stmt;

      try {
         stmt = conn.createStatement();
         String sql = "select menu from menu";
         rs = stmt.executeQuery(sql);
         if (!rs.next())
            return true; // ��������� true
      } catch (Exception e) {
         // TODO: handle exception
      }
      return false;
   }

   private int checkRemain(String menu) {
      Connection conn = DBUtill.getMySqlConnection();
      ResultSet rs = null;
      Statement stmt;

      try {
         stmt = conn.createStatement();
         String sql = "select remain from menu where menu='" + menu + "'";
         rs = stmt.executeQuery(sql);
         rs.next();
         return rs.getInt("remain");
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
   }
}

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
	   } // 기본 생성자

	   public Menu(String menu, int price, String info, int remain) { // 기본생성자
	      this.menu = menu;
	      this.price = price;
	      this.info = info;
	      this.remain = remain;
	   }

	   public ResultSet select() { // menuShowAll 사용방법 new menu().select();
//	      해당 메서드는  모든 메뉴에대해 값을 전달해줍니다.
	       return super.menuSelect();
	   }
//		메뉴 반환 메서드
	   public String getMenuInfo(String menu2) {
		   String s = super.menuInfo(menu2);
		   return s;
		}
	   
	   public void insert() { // 메뉴 추가 사용방법 new Menu(menu, price, info, remain).Insert();
	      Connection conn = DBUtill.getMySqlConnection();
	      PreparedStatement pstmt = null;
	      try {
//	         사용방법 new Menu(menu, price, info, remain).Insert();
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
	   
	   
	   public void remainMinus(String menu, int num) { // 재고 삭제 사용 방법 new Menu().remainMinus(메뉴 이름, 삭제할 재고 량);
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

	   public void delete(String menu) { // 메뉴 삭제 사용방법 new Menu().delete(메뉴이름);
	      Connection conn = DBUtill.getMySqlConnection();
	      PreparedStatement pstmt = null;
	      try {
//	         사용방법 new Menu(menu, price, info, remain).delete();
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

	   private boolean validationMenu() { // insert시 먼저 동작되는 함수 // 유효성 검사 통과하면 true
	      if (price < 0)
	         return false; // 가격이 0보다 작으면 false
	      if (remain < 0)
	         return false; // 재고가 0보다 작으면 false
	      // 메뉴 이름 중복 검사
	      if (!checkMenu())
	         return false;// 중복 검사 통과 못하면 false
	      return true;
	   }

	   private boolean checkMenu() { // 중복이 검사되면 false 중복이 아니거나 비어있으면 true
	      Connection conn = DBUtill.getMySqlConnection();
	      ResultSet rs = null;
	      Statement stmt;

	      try {
	         stmt = conn.createStatement();
	         String sql = "select menu from menu";
	         rs = stmt.executeQuery(sql);
	         if (rs.next())
	            return true; // 비어있으면 true
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
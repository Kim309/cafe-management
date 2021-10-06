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
   } // 기본 생성자

   public manucapy(String menu, int price, String info, int remain) { // 기본생성자
      this.menu = menu;
      this.price = price;
      this.info = info;
      this.remain = remain;
   }

   public void select() { // menuShowAll 사용방법 new menu().select();
//	      해당 메서드는  모든 메뉴에대해 값을 전달해줍니다.
	      if (!validationMenu()) {
	         System.out.println("입력한 값이 적합하지 않습니다.");
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
	            	System.out.println("설명");
	            	System.out.println(rs.getString("info"));
	            	System.out.println("가격:"+rs.getInt("price")+ "\t 재고" + rs.getInt("remain"));
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

   public void insert() { // 메뉴 추가 사용방법 new Menu(menu, price, info, remain).Insert();
      if (!validationMenu()) {
         System.out.println("입력한 값이 적합하지 않습니다.");
      } else {
         Connection conn = DBUtill.getMySqlConnection();
         PreparedStatement pstmt = null;
         try {
//         사용방법 new Menu(menu, price, info, remain).Insert();
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

   public void remainMinus(String menu, int num) { // 재고 삭제 사용 방법 new Menu().remainMinus(메뉴 이름, 삭제할 재고 량);
      if (isEmpty()) {
         System.out.println("메뉴 테이블이 비어있음");
      }
      if (checkRemain(menu) < num) {
         System.out.println("재고량보다 판매량이 많음");
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

   public void delete(String menu) { // 메뉴 삭제 사용방법 new Menu().delete(메뉴이름);
      if (isinTable(menu)) {
         Connection conn = DBUtill.getMySqlConnection();
         PreparedStatement pstmt = null;
         try {
//         사용방법 new Menu(menu, price, info, remain).delete();
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
         System.out.println("테이블 안에 " + menu + " 값이 없습니다.");
      }

   }

   private boolean isinTable(String menu) { // 테이블에 menu 값이 있는지 확인 있으면 true; 없으면 false;
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

   private boolean checkMenu() { // 중복이 검사되면 false 중복이 아니면 true
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

   private boolean isEmpty() { // 비어있으면 true
      Connection conn = DBUtill.getMySqlConnection();
      ResultSet rs = null;
      Statement stmt;

      try {
         stmt = conn.createStatement();
         String sql = "select menu from menu";
         rs = stmt.executeQuery(sql);
         if (!rs.next())
            return true; // 비어있으면 true
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

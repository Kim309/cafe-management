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

//	삭제 생성 수정이 필요한 sql문을 보내주면 처리한다.
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
	
//	매출 전체 내용을 출력
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
//		close()는 rs를 사용하는 쪽에서 실행한다.
		
		return rs;
	}
//	전체 메뉴를 반환하는 메서드
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
//	매개변수로 들어온 메뉴 이름이 존재하면 반환한다.
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
			}else {return "없는 메뉴입니다.";}
			
		}catch (Exception e) {}
		return info;
	}
//	메뉴의 남은 수량을 체크하는 메서드입니다.
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
		// 주문 금액을 구하는 메서드
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
	
	
	public void close() {                     // 종료 함수 마지막에 꼭 첨부해야함
	      try {
	         rs.close();
	         stmt.close();
	         pstmt.close();
	         conn.close();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
//	         e.printStackTrace();
	      }finally {
	         System.out.println("종료");
	      }
	   }

	

	

	

	
	}


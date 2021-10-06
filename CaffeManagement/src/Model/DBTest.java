package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest{

	public static void main(String[] args) {
		// sql쿼리 실핼 필요시 사용방법.
//		Connection conn = DBUtill.getMySqlConnection();
//		
//		쿼리작업란
//		new Sales(1, 12000, 2, "돌체 라떼").Insert();//확인완료
//		new Sales(1).Delete();//확인완료
//		new Sales().Select();//확인완료
//		new Sales().totalPrice();//확인완료
//		new Menu().select();
		Customer c = new Customer();
		Menu m = new Menu();
		Sales s = new Sales();
//		ResultSet rs = s.select();
//		System.out.println("주문번호\t메뉴\t\t수량\t합계\t날짜");
//		try {
//			while (rs.next()) {
//				System.out.println(rs.getInt("ordernum")+"\t"+rs.getString("menu")+"\t\t"+
//			rs.getInt("quantity")+"\t"+rs.getInt("psum")+"\t"+rs.getTimestamp("ordate"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		s.close();
//		ResultSet rs = m.menuSelect();
//		try {
//			while(rs.next()) {
//				System.out.println(rs.getString("menu"));
//	        	System.out.println(rs.getString("info"));
//	        	System.out.println("가격:"+rs.getInt("price")+ "\t 재고" + rs.getInt("remain"));
//	        	System.out.println();
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		String rs = m.menuInfo("카페 라떼");
		System.out.println(rs);
		
		
//		DB연결해제
//		DBUtill.close(conn);
	}
}

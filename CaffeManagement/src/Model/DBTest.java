package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest{

	public static void main(String[] args) {
		// sql���� ���� �ʿ�� �����.
//		Connection conn = DBUtill.getMySqlConnection();
//		
//		�����۾���
//		new Sales(1, 12000, 2, "��ü ��").Insert();//Ȯ�οϷ�
//		new Sales(1).Delete();//Ȯ�οϷ�
//		new Sales().Select();//Ȯ�οϷ�
//		new Sales().totalPrice();//Ȯ�οϷ�
//		new Menu().select();
		Customer c = new Customer();
		Menu m = new Menu();
		Sales s = new Sales();
//		ResultSet rs = s.select();
//		System.out.println("�ֹ���ȣ\t�޴�\t\t����\t�հ�\t��¥");
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
//	        	System.out.println("����:"+rs.getInt("price")+ "\t ���" + rs.getInt("remain"));
//	        	System.out.println();
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		String rs = m.menuInfo("ī�� ��");
		System.out.println(rs);
		
		
//		DB��������
//		DBUtill.close(conn);
	}
}

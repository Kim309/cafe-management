package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sales extends MyExecuteQuary{
	private int ordernum;
	private int price;
	private int quantity;
	private String menu;
	private Date ortime;
//  �ֹ��ð��� sql insert�ÿ� �ڵ����� ������ �ð��� �Էµǵ��� �߽��ϴ�.
	public Sales() {super();}
	public Sales(int ordernum) {this.ordernum = ordernum;}
	public Sales(int ordernum, int price, int quantity, String menu) {
		this.ordernum = ordernum;
		this.price = price;
		this.quantity = quantity;
		this.menu = menu;

	}
	
	
	public int getOrdernum() {return ordernum;}
	public void setOrdernum(int ordernum) {this.ordernum = ordernum;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public int getQuantity() {return quantity;}
	public void setQuantity(int quantity) {this.quantity = quantity;}
	public String getMenu() {return menu;}
	public void setMenu(String menu) {this.menu = menu;}
	public Date getOrtime() {return ortime;}
	public void setOrtime(Date ortime) {this.ortime = ortime;}
	
	public void totalPrice() {
		Connection conn = DBUtill.getMySqlConnection();
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
//			���� �� ������ �˻��Ѵ�.
			String sql = "select ordernum, menu, sum(quantity) as quantity, sum(price) as psum from sales group by ordernum, menu";
			rs =  stmt.executeQuery(sql);
			int totalprice = 0;
			while (rs.next()) {
				totalprice += rs.getInt("psum");
			}
			System.out.println("�� ����: "+totalprice+"��");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				DBUtill.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet select() {
//		�ش� �޼����  ��� ���⿡���� ���� �������ݴϴ�.
		String sql = "select ordernum, menu, quantity, sum(price) as psum, ordate from sales group by ordernum, menu";
		return super.salesSelect(sql);
	}
	
	public void insert() {
//			����� new Sales(�ֹ���ȣ, ����, �ֹ�����, �޴��̸�).Insert();
			String sql = "insert into sales(ordernum, price, quantity, menu) values("+ordernum+", "+price+", "+quantity+", '"+menu+"')";
			super.myExecuteUpdate(sql);
	}
	public void delete(int num) {
		//��Ʈ�Ѵܿ��� �����ͻ����� ��û�ϸ� �����մϴ�.
		//�����ڸ� ������ �Ѱܹ޴� �ֹ���ȣ�� �ش��ϴ� �����͸� ���� ����_������� ������ ���� �ڵ�
//			����� new Sales().Delete(�ֹ���ȣ);
			String sql = "delete from sales where ordernum = "+num;
			super.myExecuteUpdate(sql);
	}
}

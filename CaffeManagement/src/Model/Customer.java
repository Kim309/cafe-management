package Model;

public class Customer extends MyExecuteQuary{
	private int ordernum;
	private String menu;
	private int price;
	private int quantity;
	
	public Customer() {
		super();
	}
	public void insert() {
//		����� new Sales(�ֹ���ȣ, ����, �ֹ�����, �޴��̸�).Insert();
		String sql = "insert into customer(ordernum, menu, price, quantity) values("+ordernum+", "+menu+", "+price+", '"+quantity+"')";
		super.myExecuteUpdate(sql);
	}
	public void delete(int num) {
		//��Ʈ�Ѵܿ��� �����ͻ����� ��û�ϸ� �����մϴ�.
		//�����ڸ� ������ �Ѱܹ޴� �ֹ���ȣ�� �ش��ϴ� �����͸� ���� ����_������� ������ ���� �ڵ�
//		����� new Sales().Delete(�ֹ���ȣ);
		String sql = "delete from customer where ordernum = "+num;
		super.myExecuteUpdate(sql);
	}
	
	public void setOrdernum(int ordernum) {this.ordernum = ordernum;}
	public void setMenu(String menu) {this.menu = menu;}
	public void setPrice(int price) {this.price = price;}
	public void setQuantity(int quantity) {this.quantity = quantity;}
	public int getOrdernum() {return ordernum;}
	public String getMenu() {return menu;}
	public int getPrice() {return price;}
	public int getQuantity() {return quantity;}
	
}

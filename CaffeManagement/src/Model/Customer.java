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
//		사용방법 new Sales(주문번호, 가격, 주문수량, 메뉴이름).Insert();
		String sql = "insert into customer(ordernum, menu, price, quantity) values("+ordernum+", "+menu+", "+price+", '"+quantity+"')";
		super.myExecuteUpdate(sql);
	}
	public void delete(int num) {
		//컨트롤단에서 데이터삭제를 요청하면 실행합니다.
		//생성자를 생성해 넘겨받는 주문번호에 해당하는 데이터를 전부 삭제_사용하지 않을것 같은 코드
//		사용방법 new Sales().Delete(주문번호);
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

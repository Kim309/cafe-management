package Controll;

import java.sql.ResultSet;

public class testcon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("주문번호\t\t메뉴\t수량\t합계");
//		new Controll().showSalesTable();
//		new Controll().insertSales(2, 12000, 2, "카푸치노");
		Controll c = new Controll();
		c.remainCheck("카푸치노", 5);
		if (!c.remainCheck("카푸치노", 5)) {								//종료조건
			System.out.println("재고가 부족합니다.");
		}else {System.out.println("가능");}
	}

}

package Controll;

import java.sql.ResultSet;

public class testcon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("�ֹ���ȣ\t\t�޴�\t����\t�հ�");
//		new Controll().showSalesTable();
//		new Controll().insertSales(2, 12000, 2, "īǪġ��");
		Controll c = new Controll();
		c.remainCheck("īǪġ��", 5);
		if (!c.remainCheck("īǪġ��", 5)) {								//��������
			System.out.println("��� �����մϴ�.");
		}else {System.out.println("����");}
	}

}

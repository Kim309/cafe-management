package Controll;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.*;

public class Controll {
   Connection connection = null;
   Statement statement = null;
   ResultSet resultSet = null;
   Menu m = null;
   Sales s = null;
   public Controll() {
	 connection = DBUtill.getMySqlConnection();
	 m = new Menu();
	 s = new Sales();
//   String DName = "com.mysql.jdbc.Driver";
//
//   try {
//      Class.forName(DName);
//
//      String url = "jdbc:mysql://192.168.55.5:3306/coffeemanagement";
//      String user = "coffee";
//      String password = "0000";
//
//      connection = DriverManager.getConnection(url, user, password);
//      statement = connection.createStatement();
//      System.out.println("DB���� ����");          //Ȯ�ο� ���� ����� �ּ�ó�� �ٶ�
//   } catch (Exception e) {
//      e.printStackTrace();
//   }

   }
// 0501����
   public void showMenuTable() {      //�޴� ���̺� ��ü Show
      if (tableIsEmpty("menu")) {
    	  System.out.println("���� �޴��Դϴ�.");
      }else {
    	  ResultSet rs = m.menuSelect();
    	  System.out.println("����\t�޴�");
			try {
				while (rs.next()) {
					System.out.println(rs.getInt("price")+"��\t"+rs.getString("menu"));											
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
      }
   }
// 0501����
   public String getMenuInfo(String menu) {
//	   �޴� ������ ��ȯ�ϴ� �޼���		   
	   return m.getMenuInfo(menu);		   
   }
//   0501����
   public ResultSet showSalesTable() {   //���� ���� ���̺� ��ü Show
//	   return new Sales().select();
	   return s.select();
   }
   
   
   public void showCustomerTable() {      //�� ���̺� ��ü Show
      if (tableIsEmpty("customer")) {
         String sql="select * from customer";
         try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
               System.out.println("ordernum : " + resultSet.getInt(1) + " menu : " + resultSet.getString(2) + " price : "
                     + resultSet.getInt(3) + " quantity : " + resultSet.getInt(4));
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }else {
         System.out.println("���̺� ���� �����ϴ�.");
      }
   }
   
   public void insertMenu(String menuName, int price, String info, int remain) {      //admin�� �޴� �߰��Ҷ� ����ϴ��Լ�
      String sql = "insert into menu values('"+menuName+"', "+price+", '"+info+"', "+remain+")";
      try {
         statement.executeUpdate(sql);
         System.out.println("�޴� �߰� ����");      // �޴� �߰� Ȯ�ο� ���� ����� �ּ� �� ���� �ٶ�
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void insertSales(int ordernum, int price, int quantity, String menu) {      //customer�� �޴� �ֹ� �� �� ����ϴ� �Լ�
//      String sql = "insert into sales(ordernum,price,quantity,orderdate) values("+ordernum+", "+price+", "+quantity+", '"+orderdate+"')";
//      try {
//         statement.executeUpdate(sql);
//         System.out.println("������� ���̺� �߰� ����");      // ����������̺� �߰� Ȯ�ο� ���� ����� �ּ� �� ���� �ٶ�
//      } catch (SQLException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
	   new Sales(ordernum, price, quantity, menu).insert();
   }
   
   public boolean remainCheck(String menu, int quantity) {
	   boolean ck = m.quantityCk(menu, quantity);
	   return ck;
   }
   public int getMenuPrice(String menu) {
		// �ֹ��� �޴��� �� ������ ����ϱ� ���� �޼���
		return m.getPrice(menu);
	}
   public boolean menuCheck(String menu) {
		return false;
	}
//   0501����
   
   
   public void insertCustomer(String menu, int price, int quantity) {      //admin�� �޴� �߰��Ҷ� ����ϴ��Լ�
      String sql = "insert into Customer values('"+menu+"', "+price+", "+quantity+")";
      try {
         statement.executeUpdate(sql);
         System.out.println("�ֹ� �߰� ����");      // �� �ֹ����̺� �߰� Ȯ�ο� ���� ����� �ּ� �� ���� �ٶ�
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void insertCustomer(String menu, int quantity) {      //admin�� �޴� �߰��Ҷ� ����ϴ��Լ�
	      String sql = "insert into Customer values('"+menu+"', "+quantity+")";
	      try {
	         statement.executeUpdate(sql);
	         System.out.println("�ֹ� �߰� ����");      // �� �ֹ����̺� �߰� Ȯ�ο� ���� ����� �ּ� �� ���� �ٶ�
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }

   public void deleteMenu(String menu) {               //admin �޴� ����
      String sql="delete from menu where menu="+menu;
      try {
         statement.executeUpdate(sql);
         System.out.println("�޴� :"+menu+" ���� ����");         //���� ���� Ȯ�� �������� �ּ� �� �����ٶ�
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void deleteCustomer(int ordernum) {            //�ֹ� �Ϸ� �� ������ �� ���̺��� �ֹ� ����
      String sql="delete from customer where customerordernum = "+ordernum;
      try {
         statement.executeUpdate(sql);
         System.out.println("customer ���̺��� �ֹ� ���� �Ϸ�");       //�ֹ� ���� Ȯ�� �޼��� �������� ���� �� �ּ� �ٶ�
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void selectBetweenDate(String date1,String date2) {   //�Ⱓ������ ������� ���̺� �����ִ� �Լ� date�� StringŸ������ YYYY-MM-DD�������� �ٶ�
      String sql="select * from sales where orderdate between '"+date1+" 00:00:00' and '"+date2+" 23:59:59'";
      try {
         resultSet=statement.executeQuery(sql);
         while(resultSet.next()) {
            System.out.println("orderIdx : " + resultSet.getInt(1) + "Ordernum : " + resultSet.getInt(2) + " Price : "
                  + resultSet.getInt(3) + " quantity : " + resultSet.getInt(4)+" orderdate : "+resultSet.getDate(5));
            System.out.println("Ȯ�� �޼���");
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }

   public boolean tableIsEmpty(String tableName) { // ���̺��� ������� Ȯ���ϴ� �Լ�
      boolean isEmpty = true;
      String sql = "select * from " + tableName;
      try {
    	 statement = connection.createStatement();
         resultSet = statement.executeQuery(sql);
         if (resultSet.next())
            isEmpty = false;
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return isEmpty;
   }
   
   
   public void close() {                     // ���� �Լ� �������� �� ÷���ؾ���
      try {
         resultSet.close();
         statement.close();
         connection.close();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         System.out.println("����");
      }
   }


}
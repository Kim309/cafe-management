package VIEW;

import java.util.Scanner;
import Controll.Controll;

public class View {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("========= ī�� �޴�����Ʈ �ý��� ==========");
		Controll c = new Controll();
		
			
	
		System.out.println("1.�� 2.������");
		int num = sc.nextInt();
		if (num == 1) {			//��(Ű����ũ)
			while(true) {
				System.out.println("Ű����ũ �Դϴ�. �޴�â�� Ȯ���Ͻð� �޴��� �Է��Ͽ� �ּ���.");
				c.showMenuTable();
				String menu = "";
//				if (!c.menuCheck(menu)) {								//��������
//					System.out.println("���� �޴��Դϴ�.");
//					break;
//				}
				System.out.print("�޴��Է�: ");
				sc.nextLine();
				menu = sc.nextLine();
				System.out.println(c.getMenuInfo(menu));//�޴� ������ �ҷ��� �޴��� ���� ������ ����Ѵ�.
				
//				=========================

				System.out.println(menu + "��(��) �����ϼ̽��ϴ�. ������ ���� �ּ���.");
				int quantity = sc.nextInt();						//����.
				
				if (!c.remainCheck(menu, quantity)) {								//��������
					System.out.println("��� �����մϴ�.");
					break;
				}
//				�ֹ� �� �ݾ� ���ϱ�
				int price = c.getMenuPrice(menu)*quantity;
				
				System.out.println("�ֹ� ����: '"+ menu + "' '"+ quantity +" �����ø� y Ʋ���� �ٸ�Ű�� �����ּ���.");		//Ȯ��.
				sc.nextLine();
				char chk = sc.next().charAt(0);
				System.out.println(chk);
				if (chk == 'y') {
					System.out.println(menu + " " + quantity + "�� ������ " +price + "�� �Դϴ�.");				// ���� price ������ ���� �������??
				}
				else {
					System.out.println("�ʱ� ȭ������ ���ư��ϴ�.\nó������ �ٽ� �ֹ��� �ּ���.");
					continue;
				}
				System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ�! \n�̿��� �ּż� �����մϴ� :)");
			}
		}
		else if (num == 2) {	//������
			while (true) {			
				System.out.println("������ ȭ���Դϴ�. ");
				System.out.println("1.�޴� ���� 2.�ֹ� ���� 3.���� ���� 4.����");
				num = sc.nextInt();
				if (num == 1) {					//�޴�����.
					System.out.println("�޴� ���� â�Դϴ�.");
					c.showMenuTable();
					System.out.println();
					System.out.println("1.�޴��߰� 2.�޴�����");
					num = sc.nextInt();
					if (num == 1) {
						System.out.println("�޴��� ���� �߰��մϴ�.");
						System.out.print("�޴� �̸��� �ۼ��� �ּ��� : ");
						String menuName = sc.next();
						System.out.print("������ �ۼ��� �ּ��� : ");
						int price = sc.nextInt();
						System.out.println(menuName + "�� ������ �����ּ��� (���ͽ� �Ϸ�)");
						String info = sc.nextLine();
						System.out.print("������ �ۼ��� �ּ��� : ");
						int remain = sc.nextInt();
						c.insertMenu(menuName, price, info, remain);
						
						System.out.println("�޴� �߰��� �Ϸ�Ǿ����ϴ�!\n������ �ʱ�ȭ������ ���ư��ϴ�.");
					}
					else if (num == 2) {
						System.out.println("�޴��� �����մϴ�.");
						c.showMenuTable();
						System.out.print("������ �޴� �̸��� �ۼ��� �ּ��� : ");
						String menu = sc.next();
						c.deleteMenu(menu);
						
						System.out.println("������ �Ϸ�Ǿ����ϴ�. \n������ �ʱ�ȭ������ ���ư��ϴ�.");
						
					}
					else {
						System.out.println("�߸� �Է��ϼ̽��ϴ�. ������ �ʱ�ȭ������ ���ư��ϴ�.");
						continue;
					}

				}
				else if (num == 2) {				//�ֹ�����
					
				}
				else if (num == 3) {				//�������.
					
				} 
				else if (num == 4) {			//��������
					break;
				}
				else {
					System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���.");
				} 
			}
		}
		else {
			System.out.println("�߸� �Է��Ͽ����ϴ�. ���α׷��� ���� �մϴ�.");
		}
	}
}

package VIEW;

import java.util.Scanner;
import Controll.Controll;

public class View {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("========= 카페 메니지먼트 시스템 ==========");
		Controll c = new Controll();
		
			
	
		System.out.println("1.고객 2.관리자");
		int num = sc.nextInt();
		if (num == 1) {			//고객(키오스크)
			while(true) {
				System.out.println("키오스크 입니다. 메뉴창을 확인하시고 메뉴를 입력하여 주세요.");
				c.showMenuTable();
				String menu = "";
//				if (!c.menuCheck(menu)) {								//종료조건
//					System.out.println("없는 메뉴입니다.");
//					break;
//				}
				System.out.print("메뉴입력: ");
				sc.nextLine();
				menu = sc.nextLine();
				System.out.println(c.getMenuInfo(menu));//메뉴 정보를 불러와 메뉴에 대한 설명을 출력한다.
				
//				=========================

				System.out.println(menu + "를(을) 선택하셨습니다. 수량을 적어 주세요.");
				int quantity = sc.nextInt();						//수량.
				
				if (!c.remainCheck(menu, quantity)) {								//종료조건
					System.out.println("재고가 부족합니다.");
					break;
				}
//				주문 총 금액 구하기
				int price = c.getMenuPrice(menu)*quantity;
				
				System.out.println("주문 내역: '"+ menu + "' '"+ quantity +" 맞으시면 y 틀리면 다른키를 눌러주세요.");		//확인.
				sc.nextLine();
				char chk = sc.next().charAt(0);
				System.out.println(chk);
				if (chk == 'y') {
					System.out.println(menu + " " + quantity + "개 가격은 " +price + "원 입니다.");				// 여기 price 빠져야 하지 않을까요??
				}
				else {
					System.out.println("초기 화면으로 돌아갑니다.\n처음부터 다시 주문해 주세요.");
					continue;
				}
				System.out.println("주문이 완료되었습니다! \n이용해 주셔서 감사합니다 :)");
			}
		}
		else if (num == 2) {	//관리자
			while (true) {			
				System.out.println("관리자 화면입니다. ");
				System.out.println("1.메뉴 관리 2.주문 관리 3.매출 관리 4.종료");
				num = sc.nextInt();
				if (num == 1) {					//메뉴관리.
					System.out.println("메뉴 관리 창입니다.");
					c.showMenuTable();
					System.out.println();
					System.out.println("1.메뉴추가 2.메뉴제거");
					num = sc.nextInt();
					if (num == 1) {
						System.out.println("메뉴를 새로 추가합니다.");
						System.out.print("메뉴 이름을 작성해 주세요 : ");
						String menuName = sc.next();
						System.out.print("가격을 작성해 주세요 : ");
						int price = sc.nextInt();
						System.out.println(menuName + "의 설명을 적어주세요 (엔터시 완료)");
						String info = sc.nextLine();
						System.out.print("수량을 작성해 주세요 : ");
						int remain = sc.nextInt();
						c.insertMenu(menuName, price, info, remain);
						
						System.out.println("메뉴 추가가 완료되었습니다!\n관리자 초기화면으로 돌아갑니다.");
					}
					else if (num == 2) {
						System.out.println("메뉴를 삭제합니다.");
						c.showMenuTable();
						System.out.print("삭제할 메뉴 이름을 작성해 주세요 : ");
						String menu = sc.next();
						c.deleteMenu(menu);
						
						System.out.println("삭제가 완료되었습니다. \n관리자 초기화면으로 돌아갑니다.");
						
					}
					else {
						System.out.println("잘못 입력하셨습니다. 관리자 초기화면으로 돌아갑니다.");
						continue;
					}

				}
				else if (num == 2) {				//주문관리
					
				}
				else if (num == 3) {				//매출관리.
					
				} 
				else if (num == 4) {			//종료조건
					break;
				}
				else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
				} 
			}
		}
		else {
			System.out.println("잘못 입력하였습니다. 프로그램을 종료 합니다.");
		}
	}
}

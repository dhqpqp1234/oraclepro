package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		int personId ;
		String name ;
		String hp ;
		String company ;
		
		Scanner sc = new Scanner(System.in);
		
		int num = 0;
		
		PhoneDao phoneDao = new PhoneDao();
		
		//phoneDao.phoneInsert("서장훈", "010-5555-5555", "02-5555-5555");
		
		
		System.out.println("***************************");
		System.out.println("*     전화번호 관리 프로그램     *");
		System.out.println("***************************");
		
		
		while(true) {
			System.out.println("1.리스트 2.등록 3.수정 4.삭제 5.검색 6.종료");
			System.out.println("-----------------------------------");
			System.out.print(">메뉴번호:");
			
			
			num = sc.nextInt();
			if(num==1) {
				System.out.println("<1.리스트>");
				List<PersonVo> phoneList = phoneDao.phoneSelect();
				for(int i=0; i<phoneList.size(); i++) {
					//PersonVo personVo = phoneList.get(i);
					System.out.println(phoneList.get(i).getPersonId() + "." +
							phoneList.get(i).getName() + "  " +
							phoneList.get(i).getHp() + "  " +
							phoneList.get(i).getCompany());
				}
			}else if(num==2) {
				System.out.println("<2.등록>");
				
				System.out.print("이름 > ");
				name = sc.next();
				
				System.out.print("휴대전화 > ");
				hp = sc.next();
				
				System.out.print("회사번호 > ");
				company = sc.next();
				
				System.out.println("[1건 등록 되었습니다.]");
				
				phoneDao.phoneInsert(name, hp, company);
			}else if(num==3) {
				
				System.out.println("<3.수정>");
				
				System.out.print("번호 > ");
				personId = sc.nextInt();
				
				System.out.print("이름 > ");
				name = sc.next();
				
				System.out.print("휴대전화 > ");
				hp = sc.next();
				
				System.out.print("회사번호 > ");
				company = sc.next();
				
				System.out.println("[1건이 수정 되었습니다.]");
				
				phoneDao.phoneUpdate(personId, name, hp, company);
			}else if(num ==4) {
				System.out.println("<4.삭제>");
				
				System.out.print(">번호 :");
				personId = sc.nextInt();
				
				System.out.println("[1건이 삭제 되었습니다.]");
				
				phoneDao.phoneDelete(personId);
			}else if(num==5) {
				System.out.println("<5.검색>");
				
				System.out.print("검색어 :");
				
				List<PersonVo> phoneList = phoneDao.phoneSelect();
				String search = sc.next();
				for(int i=0; i<phoneList.size(); i++) {
					
					
					if(phoneList.get(i).toString().contains(search)) {
						System.out.println(phoneList.get(i).getPersonId() + ". "+
											phoneList.get(i).getName() + " " +
											phoneList.get(i).getHp() + " "+
											phoneList.get(i).getCompany());
					}
				}
			}else if(num==6) {
				System.out.println("종료");
				break;
			}
			
			
		}
		
		sc.close();
	}

}

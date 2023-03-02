package com.koreaIT.java.BAM.controller;


import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Login;
import com.koreaIT.java.BAM.service.MemberService;
import com.koreaIT.java.BAM.util.Util;

public class MemeberController extends Controller {
	
    private Scanner sc;
    private MemberService memberService;
    public MemeberController(Scanner sc) {
	    this.sc = sc;
	    this.memberService = Container.memberService;
	}
	@Override
	public void doAction(String cmd, String methodName) {
		
		 switch(methodName) {
		   case "logout":
				 doLogout();
				 break;
		   case "profile":
			     showProfile();
			     break;
		  case "join":
			 doJoin();
			 break;
		  case "login":
			 doLogin();
			 break;
		 
		 default:
				System.out.println("존재하지 않는 명령어입니다");
				break;
		 }
	    
	}
	private void doJoin() {
		int id = memberService.plusId();
		String regDate = Util.getDate();
		String loginId = null;
		
		while( true ) {
			System.out.printf("로그인 아이디: ");
			loginId = sc.nextLine();
			if (memberService.loginIdDupChk(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			System.out.printf("%s은(는) 사용 가능한 아이디입니다.\n", loginId);
			break;
		}
		String pwd = null;
		while( true ) {
			System.out.printf("로그인 비밀번호: ");
		    pwd = sc.nextLine();
		    System.out.printf("로그인 비밀번호 확인: ");
		    String checkPwd = sc.nextLine();
		    // checkPwd != pwd로 하면 안 됨
		    // 주소값으로 비교하기 때문에 계속 비밀번호가 같지 않다고 나옴
		    // equals로 비교하면 값으로 비교하기 때문에 옳은 결과가 생성됨 
		    if(checkPwd.equals(pwd) == false) {
		    	System.out.println("비밀번호를 확인해주세요.");
		    	continue;
		    }
		    	break;
		    
		}
	    System.out.printf("이름: ");
	    String name = sc.nextLine().trim();
	    Login login = new Login(id, regDate, loginId, pwd, name);
	    memberService.add(login);
	    System.out.printf("%s회원님 환영합니다.\n", name);
	}
	private void doLogin() {
		Login login = null;
		String loginId = null;
		String doPwd = null;
	     while(true) {
	    	 System.out.printf("로그인 아이디 : ");
	         loginId = sc.nextLine();
	         
	         if(loginId.trim().length() == 0) {
	        	 System.out.println("로그인 아이디를 입력해주세요");
	        	 continue;
	         }
	         while(true) {
		    	 System.out.printf("로그인 비밀번호: ");
		         doPwd = sc.nextLine(); 
		         
		         if(doPwd.trim().length() == 0) {
		        	 System.out.println("로그인 비밀번호를 입력해주세요");
		        	 continue;
		         }
		         break;
		      
		     }
	         login = memberService.getLoginById(loginId);
	         if(login == null) {
	        	 //로그인하려는 사용자가 입력한 로그인 아이디 정보가 존재하지 않음
	        	 //해당 아이디로 회원가입을 한 적이 없음
	        	 System.out.println("존재하지 않는 아이디입니다.");
	             return;
	         }
	         if(login.pwd.equals(doPwd) == false) {
	        	 //로그인하려는 사용자가 입력한 로그인 아이디 정보는 존재한다
	        	 //하지만 로그인 아이디와 같이 저장된 비밀번호가 존재하지 않음(해당 로그인 아이디 인덱스의 비밀번호와 입력한 비밀번호 불일치)
	        	 System.out.println("비밀번호를 확인해주세요.");
	        	 return;
	         }
	         break;
	        
	     }
	    
         //this.loginedMember로는 사용하면 안 됨
         //loginedMember는 static이므로 이 클래스 내부에 존재하는 것이 아니기 때문
         loginedMember = login;
         
         System.out.printf("로그인 성공! %s님 환영합니다\n", login.name);
        	 
	}
	private void showProfile() {
		System.out.println("== 내 정보 ==");
		System.out.printf("로그인 아이디: %s\n", loginedMember.loginId);
		System.out.printf("이름: %s\n", loginedMember.name);
		
		
	}
	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 성공!");
        
	}
	
   
	public void makeTestData() {
 		System.out.println("회원가입 테스트 데이터를 생성합니다");
 		memberService.add(new Login(memberService.plusId(), Util.getDate(), "kho3155", "12345", "김혜원"));
 		memberService.add(new Login(memberService.plusId(), Util.getDate(), "kho3156", "12345", "이혜원"));
 		memberService.add(new Login(memberService.plusId(), Util.getDate(), "kho3157", "12345", "박혜원"));
 		
     }
	
}
package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Login;
import com.koreaIT.java.BAM.util.Util;

public class MemeberController extends Controller {
	
	private List<Login> logins;
    private Scanner sc;
    private int lastMemberId;

	public MemeberController(Scanner sc) {
		this.logins = new ArrayList<>();
		// 리스트를 받아오지 않고 클래스 내부에서 생성
	    this.sc = sc;
	    this.lastMemberId = 0;
	}
	@Override
	public void doAction(String cmd, String methodName) {
		 
		 switch(methodName) {
		 case "join":
			 doJoin();
			 break;
		 default:
				System.out.println("존재하지 않는 명령어입니다");
				break;
		 }
	    
	}
	private void doJoin() {
		int id = lastMemberId + 1;
		lastMemberId = id;
		String regDate = Util.getDate();
		String loginId = null;
		
		while( true ) {
			System.out.printf("로그인 아이디: ");
			loginId = sc.nextLine();
			if (loginIdDupChk(loginId) == false) {
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
	    logins.add(login);
	    System.out.printf("%s회원님 환영합니다.\n", name);
	}
	private boolean loginIdDupChk(String loginId) {
		for( Login login : logins ) {
			if(login.loginId.equals(loginId) ) 
				return false;
	     }
		
		return true;
		
		
	}
	
}
package com.koreaIT.java.BAM;


import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemeberController;



public class App {
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		
		
		Scanner sc = new Scanner(System.in);
		
		MemeberController memberController = new MemeberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();
		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			} 
			String[] cmdBits = cmd.split(" ");
			
			if(cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요");
		        continue;
			}
			
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			Controller controller = null;
			
			if(controllerName.equals("member")){
				controller = memberController;
			}
			else if(controllerName.equals("article")){
				controller = articleController;
			}else{
			     System.out.println("존재하지 않는 명령어 입니다");
			     continue;
		    }
			//Controller의 controller라는 변수를 만들어서 권한을 부여해줌
			//만약 member라고 친다면 controller에 memberController를 넣어주고,
			//만약 article이라고 친다면 controller에 articleController를 넣어줌
			//그리고 마지막에 controller에 있는 doAction을 실행
			//그러면 member라고 쳤을 경우 memberController의 doAction을 실행
		    String actionName = controllerName + "/" + methodName;
		    switch(actionName) {
		    case "article/write":
		    case "article/modify":
		    case "article/delete":
		    case "member/logout":
		    case "member/profile":
		        if(Controller.isLogined() == false) {
	    		   System.out.println("로그인 후 이용해주세요");
	    	    }
	    	     break;
		    case "member/join":
		    case "member/login":
		    	if(Controller.isLogined()) {
		    		   System.out.println("로그아웃 후 이용해주세요");
		    	}
		    	 break;
		    	
	    	     
		    	
		    }
				//controller.isLogined()도 가능함
				//하지만 현재 controller는 Controller의 변수이며
				//객체 생성 없이 controller의 isLogined()가 사용가능한 이유 -> controller에 memberController나 articleController가 연결되어 있기 때문에
				//하지만 isLogined()를 static으로 선언하고 Controller라는 본체에 연결하는 이유는 isLogined의 쓰임이 나중에 memberController나 articleController에서 변화할 수 있어서
				//그러므로 원래의 isLogined()(정적)으로 사용하기 위해 본체에 연결
				
			controller.doAction(cmd, methodName);
			

		}
		System.out.println("== 프로그램 끝 ==");

		sc.close();
	}
	


}

	
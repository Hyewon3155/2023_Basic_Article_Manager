package com.koreaIT.java.BAM.controller;

import com.koreaIT.java.BAM.dto.Login;

public abstract class Controller {
	// 추상 매소드로 만들어줌
	// 추상 매소드는 반드시 상속받는 클래스에서 재정의를 해주어야지만 사용할 수 있음
	// 추생 매소드의 안에서 정의하는 것이 불가능
	public static Login loginedMember;
   //공유자원으로 만들어서 MemberController와 ArticleController에서 사용할 수 있도록 함
   //동일한 값으로
	public static boolean isLogined() {
		return loginedMember != null;
	}
	public abstract void doAction(String cmd, String methodName);
	
	public abstract void makeTestData();
	//어차피 재정의를 해주어야 하지만 ArticleController와 MemberController에 동일하게 사용됨
	//그러므로 추상매소드로 선언한다.

}

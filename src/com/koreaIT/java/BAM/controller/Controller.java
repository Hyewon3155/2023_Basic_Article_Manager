package com.koreaIT.java.BAM.controller;

public abstract class Controller {
	// 추상 매소드로 만들어줌
	// 추상 매소드는 반드시 상속받는 클래스에서 재정의를 해주어야지만 사용할 수 있음
	// 추생 매소드의 안에서 정의하는 것이 불가능
	public abstract void doAction(String cmd, String methodName);

}

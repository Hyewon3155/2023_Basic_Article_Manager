package com.koreaIT.java.BAM.dto;

// Dto의 id, regDate 상속 받음
public class Login extends Dto {
	public String loginId;
	public String pwd;
	public String name;
	
	public Login(int id, String regDate, String loginId, String pwd, String name){
		this.id = id;
		this.regDate = regDate;
		this.loginId = loginId;
		this.pwd = pwd;
		this.name = name;
	}

}
package com.koreaIT.java.BAM.service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.MemberDao;
import com.koreaIT.java.BAM.dto.Login;

public class MemberService {
  
	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}
	public int plusId() {
		return memberDao.plusId();
	}

	public Login getLoginById(String loginId) {
		return memberDao.getLoginById(loginId);
	}

	public boolean loginIdDupChk(String loginId) {
		return memberDao.loginIdDupChk(loginId);
	}

	public void add(Login login) {
		memberDao.add(login);
		
	}

	public String getWriterName(int memberId) {
		return memberDao.getWriterName(memberId);
	}
}

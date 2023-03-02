package com.koreaIT.java.BAM.service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Login;

public class MemberService {

	public int plusId() {
		return Container.memberDao.plusId();
	}

	public Login getLoginById(String loginId) {
		return Container.memberDao.getLoginById(loginId);
	}

	public boolean loginIdDupChk(String loginId) {
		return Container.memberDao.loginIdDupChk(loginId);
	}

	public void add(Login login) {
		Container.memberDao.add(login);
		
	}

	public String getWriterName(int memberId) {
		return Container.memberDao.getWriterName(memberId);
	}
}

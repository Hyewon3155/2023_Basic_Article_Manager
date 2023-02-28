package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Login;

public class MemberDao extends Dao {
	public List<Login> logins;
	public int id;
	 public MemberDao() {
		 this.logins = new ArrayList<>();
	 }
	
	 public void add(Login login) {
			logins.add(login);
		    id++;
		}

}

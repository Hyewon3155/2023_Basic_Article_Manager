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
	 
	//*로그인 관련 함수*
		public Login getLoginById(String loginId) {
			//*로그인하려는* 사용자가 입력한 로그인 아이디와 같은 아이디가 존재하는지 체크하는 함수
			for( Login login : logins ) {
	            if(login.loginId.equals(login.loginId)) {
	            	return login;
	            	//로그인하려는 사용자가 입력한 로그인 아이디와 같은 아이디(가입한 아이디 정보)가 존재하면 가입 정보를 리턴함
	            }
	        }
			return null;
			//로그인하려는 사용자가 입력한 로그인 아이디와 같은 아이디(가입한 아이디)가 존재하지 않으면 null을 리턴함
	     }
	 
	 //*회원가입 관련 함수*
		public boolean loginIdDupChk(String loginId) {
			//*가입하려고 하는* 로그인 아이디가 존재하는지 확인하는 함수
			//가입하려고 하는 로그인 아이디가 존재하면 다른 사람이 같은 아이디로 회원가입할 수 없음 
			Login login = getLoginById(loginId);
			
			if(login == null)
			   return true;
			//가입하려고 하는 사용자가 입력한 아이디가 존재하지 않기 때문에 true를 리턴
			//-> 해당 로그인 아이디로 회원가입할 수 있음
			return false;
			
			
		}

		public String getWriterName(int memberId) {	
			for(Login login : logins) {
				if(memberId == login.id) {
					return login.name;
				}
			}
			return null;
		}


}

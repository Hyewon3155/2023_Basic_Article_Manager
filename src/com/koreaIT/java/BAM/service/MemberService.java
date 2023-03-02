package com.koreaIT.java.BAM.service;

import java.util.List;
import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Login;

public class MemberService {
     public List<Login> getPrintArticles(String searchKeyword){
    	 return Container.memberDao.getPrintArticles(searchKeyword);
     }
}

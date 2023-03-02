package com.koreaIT.java.BAM.container;

import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dao.MemberDao;
import com.koreaIT.java.BAM.service.ArticleService;
import com.koreaIT.java.BAM.service.MemberService;

public class Container{
	public static ArticleDao articleDao;
    public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
    
    static {
    	//static은 일반 생성자로 초기화할 수 없음
    	//반드시 static으로 초기화를 해야 함
    	articleDao = new ArticleDao();
    	memberDao = new MemberDao();
    	articleService = new ArticleService();
    	memberService = new MemberService();
    }
}
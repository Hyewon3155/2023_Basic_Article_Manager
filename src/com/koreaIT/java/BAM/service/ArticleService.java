package com.koreaIT.java.BAM.service;

import java.util.List;
import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {
     public List<Article> getPrintArticles(String searchKeyword){
    	 return Container.articleDao.getPrintArticles(searchKeyword);
     }

	public Article getfindIndex(int id) {
		return Container.articleDao.getfindIndex(id);
	}

	public int plusId() {
		// Controller -> articleService -> articleDao
		return Container.articleDao.plusId();
	}

	public void add(Article article) {
		// articleService -> articleDao로 일을 시키는 방식으로
		// articleService가 Controller에게 일을 받아 articleDao에게 일을 시킴
		Container.articleDao.add(article);
		
	}

	public void remove(Article foundArticle) {
	   Container.articleDao.remove(foundArticle);
		
	}

	public void articleModify(Article foundArticle, String title, String body) {
		Container.articleDao.articleModify(foundArticle, title, body);
		
	}
	
	
}

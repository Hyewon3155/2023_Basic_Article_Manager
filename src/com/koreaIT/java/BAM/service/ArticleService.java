package com.koreaIT.java.BAM.service;

import java.util.List;
import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = new ArticleDao();
	}
     public List<Article> getPrintArticles(String searchKeyword){
    	 return articleDao.getPrintArticles(searchKeyword);
     }

	public Article getfindIndex(int id) {
		return articleDao.getfindIndex(id);
	}

	public int plusId() {
		// Controller -> articleService -> articleDao
		return articleDao.plusId();
	}

	public void add(Article article) {
		// articleService -> articleDao로 일을 시키는 방식으로
		// articleService가 Controller에게 일을 받아 articleDao에게 일을 시킴
		articleDao.add(article);
		
	}

	public void remove(Article foundArticle) {
	   articleDao.remove(foundArticle);
		
	}

	public void articleModify(Article foundArticle, String title, String body) {
		articleDao.articleModify(foundArticle, title, body);
		
	}
	
	
}

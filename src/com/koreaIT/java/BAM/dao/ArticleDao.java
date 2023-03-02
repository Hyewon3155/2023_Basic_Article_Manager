package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Article;

public class ArticleDao extends Dao {
	 public List<Article> articles;
	 public int id;
	 public ArticleDao() {
		 this.articles = new ArrayList<>();
		// 리스트를 앱에서 받아오지 말고, 아예 클래스 자체에서 생성
	 }


	public void add(Article article) {
		articles.add(article);
	    id++;
	}


	public List<Article> getPrintArticles(String searchKeyword) {
		if(searchKeyword.length() > 0) {
			List<Article> printArticles = new ArrayList<>();
		    
			for(Article article : articles) {
				if(article.title.contains(searchKeyword)) {
					printArticles.add(article);
					// 검색어가 있는 경우 해당 검색어(제목)이 포함되어 있는 article의 값들을 담음
				}
		    }
			return printArticles;
		}
		return articles;
	}
    public Article getfindIndex(int id) {
		
		for(Article article : articles) {
			if(article.id == id) {
				return article;
			}
		}
		return null;
		
	}

}

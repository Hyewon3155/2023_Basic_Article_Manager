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
			//searchKeyword는 null값을 가질 수 없음
			//searchKeyword는 ""가 들어가 있음
			//따라서 길이가 없다가 맞고, 값이 없는 것이 아님
			System.out.println("검색어: " + searchKeyword);
			
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
	//Controller에서도 사용할 수 있도록 public으로 변경함
	//만약 찾는 번호의 글이 있으면 article을 return
	//찾는 번호의 글이 존재하지 않으면 null을 리턴
    public Article getfindIndex(int id) {
		
		for(Article article : articles) {
			if(article.id == id) {
				return article;
			}
		}
		return null;
		
	}



	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
		
	}


	public void articleModify(Article foundArticle, String title, String body) {
		foundArticle.title = title;
		foundArticle.body = body;
		
	}


	

}

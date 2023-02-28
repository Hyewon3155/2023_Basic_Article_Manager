package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Article;

public class ArticleDao {
	 public List<Article> articles;
	 
	 public ArticleDao() {
		 this.articles = new ArrayList<>();
		// 리스트를 앱에서 받아오지 말고, 아예 클래스 자체에서 생성
	 }
}

package com.koreaIT.java.BAM.controller;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Login;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController extends Controller{
    private Scanner sc;
    private String cmd;
    private List<Article> articles;
	public ArticleController(Scanner sc) {
	    this.articles = Container.articleDao.articles;
		this.sc = sc;
	}
	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		
		switch(methodName) {
		case "write":
		     write();
		     break;
		case "list":
			 list();
			 break;
		case "detail":
			 detail();
			 break;
		case "modify": 
			 modify();
			 break;
		case "delete":
			 delete();
			 break;
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		
	    
		}
		
	}
	private void write() {
		int id = Container.articleDao.plusId();
		String regDate = Util.getDate();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, loginedMember.id, title, body);
		
		Container.articleDao.add(article);
	    //article.add(article)의 과정을 Container로 역할 이전
		//DB(리스트)를 건드리는 일을 Container가 모두 할 수 있도록 함

		System.out.printf("%d번 글이 생성되었습니다\n", id);

	}
	private void list() {
		String searchKeyword = cmd.substring("article list".length()).trim();
		// 앞에 있는 공백을 없애주기 위해 trim()을 사용
		// article list의 길이만큼 잘라주고, 그 다음의 공백은 삭제하고 그 뒤부터 searchKeyword에 저장한다.
		System.out.println("검색어: " + searchKeyword);
		
		List<Article> printArticles = Container.articleService.getPrintArticles(searchKeyword);
		// printArticles에 원래 articles의 값들을 담아줌
		// 새로운 객체가 갖고 있는 리모컨을 넘겨줌
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return; // -> 리턴으로 함수를 종료시키되 넘겨주는 값은 없다.
		}
			
		

		System.out.println("번호	|	제목	|		날짜		|	작성자  | 조회");
		//Collections.reverse(printArticles);
		// printArticles에 있는 값들의 순서를 반대로 바꾸어 줌
		for (int i = printArticles.size() - 1; i>=0 ; i--) {
			Article article = printArticles.get(i);
			String writerName = null;
			
			List<Login> logins = Container.memberDao.logins;
			
			for(Login login : logins) {
				if(article.memberId == login.id) {
					writerName = login.name;
					break;
				}
			}
			if(printArticles.size() == 0) {
				System.out.println("검색결과가 없습니다.");
				// 검색어가 있지만, 해당 검색어(제목)이 포함되어 있는 article의 값이 없어서 printArticles.size()의 크기가 0임. 
			    return ;
			}
			System.out.printf("%d	|	%s	|	%s	|	%d\n", article.id, article.title, article.regDate, writerName, article.viewCnt);
			// 검색어가 있는 경우에는 해당 제목이 포함되어 있는 article의 값들을 출력
			// 검색어가 없는 경우 article에 담겨있는 모든 값들을 출력(리스트 출력)
		}
	  
	}
	private void detail() {
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		int id = Integer.parseInt(cmdBits[2]);
		
		Article foundArticle = Container.articleService.getfindIndex(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		
		String writerName = null;
		
		List<Login> logins = Container.memberDao.logins;
		//순회하는 로직을 따로 함수로 만들어서 빼주어야 함.
		for(Login login : logins) {
			if(foundArticle.memberId == login.id) {
				writerName = login.name;
				break;
			}
		}
		
		Container.articleService.getfindIndex(id).addViewCnt();
		
		System.out.printf("번호 : %d\n", Container.articleService.getfindIndex(id).id);
		System.out.printf("날짜 : %s\n", Container.articleService.getfindIndex(id).regDate);
		System.out.printf("작성자: %s\n", writerName);
		System.out.printf("제목 : %s\n", Container.articleService.getfindIndex(id).title);
		System.out.printf("내용 : %s\n", Container.articleService.getfindIndex(id).body);
		System.out.printf("조회수 : %d\n", Container.articleService.getfindIndex(id).viewCnt);
		
		
	}
	public void modify() {
		String[] cmdBits = cmd.split(" ");
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		int id = Integer.parseInt(cmdBits[2]);
		
		Article foundArticle = Container.articleService.getfindIndex(id);
		
		//순회하는 로직이므로 따로 함수로 빼주어야 함
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			
			if(article.id == id) {
				foundArticle = article;
				break;
			}
		}
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		
		if(foundArticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다.");
			return;
		}
		
		
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();
		
		foundArticle.title = title;
		foundArticle.body = body;
		
		System.out.printf("%d번글이 수정되었습니다\n", id);
		
	}
	private void delete() {
		String[] cmdBits = cmd.split(" ");
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		int id = Integer.parseInt(cmdBits[2]);
		
		Article foundArticle = Container.articleService.getfindIndex(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		
		articles.remove(articles.indexOf(foundArticle));
		
		System.out.printf("%d번 게시글을 삭제했습니다\n", id);
		
	} 
  
     public void makeTestData() {
 		System.out.println("게시물 테스트 데이터를 생성합니다");
 		Container.articleDao.add(new Article(Container.articleDao.plusId(), Util.getDate(), 1,"제목1", "내용1", 10));
 		Container.articleDao.add(new Article(Container.articleDao.plusId(), Util.getDate(), 2,"제목2", "내용2", 20));
 		Container.articleDao.add(new Article(Container.articleDao.plusId(), Util.getDate(), 3, "제목3", "내용3", 30));
 		
     }
       
}



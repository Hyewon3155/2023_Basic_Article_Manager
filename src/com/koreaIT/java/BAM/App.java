package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class App {
	private List<Article> articles;
	
	App(){
		articles = new ArrayList<>();
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();
		
		Scanner sc = new Scanner(System.in);

		int lastArticleId = 3;
		
		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			} else if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				String regDate = Util.getDate();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);
				
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			} else if (cmd.startsWith("article list ")) {
				
					if (articles.size() == 0) {
						System.out.println("게시글이 없습니다");
						continue;
					}
					
					String searchKeyword = cmd.substring("article list".length()).trim();
					// 앞에 있는 공백을 없애주기 위해 trim()을 사용
					// article list의 길이만큼 잘라주고, 그 다음의 공백은 삭제하고 그 뒤부터 searchKeyword에 저장한다.
					
					List<Article> printArticles = new ArrayList<>(articles);
					// printArticles에 원래 articles의 값들을 담아줌
					// 새로운 객체가 갖고 있는 리모컨을 넘겨줌
					if(searchKeyword.length() > 0) {
						System.out.println("검색어: " + searchKeyword);
						
					    printArticles.clear();
						// printArticles를 빈 객체로 만들어 줌 
					    
						for(Article article : articles) {
							if(article.title.contains(searchKeyword)) {
								printArticles.add(article);
								// 검색어가 있는 경우 해당 검색어(제목)이 포함되어 있는 article의 값들을 담음
							}
					    }
						if(printArticles.size() == 0) {
							System.out.println("검색결과가 없습니다.");
							// 검색어가 있지만, 해당 검색어(제목)이 포함되어 있는 article의 값이 없어서 printArticles.size()의 크기가 0임. 
						}
					

					System.out.println("번호	|	제목	|		날짜		|	조회");
					Collections.reverse(printArticles);
					// printArticles에 있는 값들의 순서를 반대로 바꾸어 줌
					for (Article article : printArticles) {
						System.out.printf("%d	|	%s	|	%s	|	%d\n", article.id, article.title, article.regDate, article.viewCnt);
						// 검색어가 있는 경우에는 해당 제목이 포함되어 있는 article의 값들을 출력
						// 검색어가 없는 경우 article에 담겨있는 모든 값들을 출력(리스트 출력)
					}
				}
				
			}else if (cmd.startsWith("article detail ")) {
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = getfindIndex(id);
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				getfindIndex(id).addViewCnt();
				
				System.out.printf("번호 : %d\n", getfindIndex(id).id);
				System.out.printf("날짜 : %s\n", getfindIndex(id).regDate);
				System.out.printf("제목 : %s\n", getfindIndex(id).title);
				System.out.printf("내용 : %s\n", getfindIndex(id).body);
				System.out.printf("조회수 : %d\n", getfindIndex(id).viewCnt);
				
			} else if (cmd.startsWith("article modify ")) {
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = getfindIndex(id);
				
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
				
				System.out.printf("%d번글이 수정되었습니다\n", id);
				
			} else if (cmd.startsWith("article delete ")) {
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = getfindIndex(id);
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				
				articles.remove(articles.indexOf(foundArticle));
				
				System.out.printf("%d번 게시글을 삭제했습니다\n", id);
				
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();

	}


	private void makeTestData() {
		System.out.println("게시물 테스트 데이터를 생성합니다");
		articles.add(new Article(1, Util.getDate(), "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getDate(), "제목2", "내용2", 20));
		articles.add(new Article(3, Util.getDate(), "제목3", "내용3", 30));
	}
	
	private Article getfindIndex(int id) {
		
		for(Article article : articles) {
			if(article.id == id) {
				return article;
			}
		}
		return null;
		
	}
}

package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.MemeberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Login;
import com.koreaIT.java.BAM.util.Util;

public class App {
	private List<Article> articles;
	private List<Login> logins;
	
	App(){
		articles = new ArrayList<>();
		logins = new ArrayList<>();
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();
		
		Scanner sc = new Scanner(System.in);
		
		MemeberController memberController = new MemeberController(logins, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		
		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			} 
			if (cmd.equals("member join")) {
				memberController.doJoin();
			} else if (cmd.equals("article write")) {
				articleController.write();
			} else if (cmd.startsWith("article list ")) {
				articleController.list(cmd);
			}else if (cmd.startsWith("article detail ")) {
				articleController.detail(cmd);
				
			} else if (cmd.startsWith("article modify ")) {
				articleController.modify(cmd);
			} else if (cmd.startsWith("article delete ")) {
				articleController.delete(cmd);
		}else {
			System.out.println("존재하지 않는 명령어 입니다");
		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();

	    }
	}
		private void makeTestData() {
			System.out.println("게시물 테스트 데이터를 생성합니다");
			articles.add(new Article(1, Util.getDate(), "제목1", "내용1", 10));
			articles.add(new Article(2, Util.getDate(), "제목2", "내용2", 20));
			articles.add(new Article(3, Util.getDate(), "제목3", "내용3", 30));
			
		}

}

	
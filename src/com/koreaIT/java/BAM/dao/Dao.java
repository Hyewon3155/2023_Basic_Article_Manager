package com.koreaIT.java.BAM.dao;

public abstract class Dao {
	
    public int id;
    //static으로 선언할 수 없는 이유
    //->static으로 선언하면 공유자원으로 활용돼서 AritcleDao와 MemberDao에서 같은 값으로 활용됨
    //ArticleDao와 MemberDao에서 각각 다른 값으로 사용하기 위해서 static으로 선언하지 않음
	public int plusId() {
	   return id+1;
	}
    //추상매소드로 만들어서 ArticleDao와 MemberDao에서 상속받아 재정의해서 사용할 수 있도록 함
}

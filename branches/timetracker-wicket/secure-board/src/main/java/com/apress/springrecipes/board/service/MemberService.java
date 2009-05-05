package com.apress.springrecipes.board.service;

import java.util.List;

import com.apress.springrecipes.board.domain.Member;


public interface MemberService {

    public void add(Member member);
    public void remove(String memberName);
    public List<Member> list();
}

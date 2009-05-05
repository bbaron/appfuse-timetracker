package com.apress.springrecipes.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.apress.springrecipes.board.domain.Member;


public class MemberServiceImpl implements MemberService {

    private Map<String, Member> members = new TreeMap<String, Member>();

    public MemberServiceImpl() {
    	Member member1 = new Member();
    	member1.setEmail("member1@mail.com");
    	member1.setName("Member1");
    	member1.setPhone("1234567890");
		members.put("1", member1 );
    	Member member2 = new Member();
    	member2.setEmail("member2@mail.com");
    	member2.setName("Member2");
    	member2.setPhone("1234567890");
    	members.put("2", member2);
    	Member member3 = new Member();
    	member3.setEmail("member3@mail.com");
    	member3.setName("Member3");
    	member3.setPhone("1234567890");
    	members.put("3", member3);
    }
    public void add(Member member) {
        members.put(member.getName(), member);
    }

    public void remove(String memberName) {
        members.remove(memberName);
    }

    public List<Member> list() {
        return new ArrayList<Member>(members.values());
    }
}

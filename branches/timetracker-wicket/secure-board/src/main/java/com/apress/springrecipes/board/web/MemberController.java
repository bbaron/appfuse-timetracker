package com.apress.springrecipes.board.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.apress.springrecipes.board.domain.Member;
import com.apress.springrecipes.board.service.MemberService;


public class MemberController extends AbstractController {

    private MemberService memberService;

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public ModelAndView addMember(HttpServletRequest request,
            HttpServletResponse response, Member member) throws Exception {
        memberService.add(member);
        return new ModelAndView("redirect:list.htm");
    }

    public ModelAndView removeMember(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String memberName = ServletRequestUtils.getRequiredStringParameter(
                request, "memberName");
        memberService.remove(memberName);
        return new ModelAndView("redirect:list.htm");
    }

    public ModelAndView listMember(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        List<Member> members = memberService.list();
        return new ModelAndView("memberList", "members", members);
    }

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        List<Member> members = memberService.list();
        return new ModelAndView("memberList", "members", members);
	}
}

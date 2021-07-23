package com.d_time.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.member.service.MemberService;
import com.d_time.member.serviceImpl.MemberServiceImpl;
import com.d_time.member.vo.MemberVO;

public class MemberSearchCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo = dao.memberSelect(vo);
		if(vo.getName()!=null) {
			request.setAttribute("member", vo);
		} else {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		return "member/memberSearch";
	}

}

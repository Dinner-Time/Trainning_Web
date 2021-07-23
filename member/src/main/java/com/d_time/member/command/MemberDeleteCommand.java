package com.d_time.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.member.service.MemberService;
import com.d_time.member.serviceImpl.MemberServiceImpl;
import com.d_time.member.vo.MemberVO;

public class MemberDeleteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		int r = dao.memberDelete(vo);
		if(r != 0) {
			return "memberList.do";
		} else {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		return "member/memberFailPage";
	}

}

package com.d_time.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.member.service.MemberService;
import com.d_time.member.serviceImpl.MemberServiceImpl;
import com.d_time.member.vo.MemberVO;

public class MemberSignUpCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		String[] hobbys = request.getParameterValues("hobbys");
		String hobby = "";
		for(String str : hobbys) {
			hobby += (str+",");
		}
		hobby = hobby.substring(0, hobby.length() - 1);
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		vo.setName(request.getParameter("name"));
		vo.setAge(Integer.parseInt(request.getParameter("age")));
		vo.setHobby(hobby);
		
		int n = dao.memberInsert(vo);
		String view ="";
		if(n!=0) {
			view = "memberList.do";
		} else {
			request.setAttribute("message", "회원가입에 실패하였습니다...");
			view = "member/memberFailPage";
		}
		return view;
	}

}

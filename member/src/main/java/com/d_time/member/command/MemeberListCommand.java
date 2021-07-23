package com.d_time.member.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.member.service.MemberService;
import com.d_time.member.serviceImpl.MemberServiceImpl;
import com.d_time.member.vo.MemberVO;

public class MemeberListCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 회원 전체 목록 보기
		MemberService dao = new MemberServiceImpl();
		List<MemberVO> members = dao.memberSelectList(); // 데이터를 가져옴
		
		request.setAttribute("list", members); // request에 가져온 데이터를 담음
		
		return "member/memberList"; // 데이터를 보여줄 페이지
	}

}

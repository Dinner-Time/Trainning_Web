package com.d_time.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberDeleteFormCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "member/memberDeleteForm";
	}

}

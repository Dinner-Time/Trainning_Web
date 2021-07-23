package com.d_time.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberSignUpFormCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "member/memberSignUpForm";
	}

}

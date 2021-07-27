package com.d_time.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.d_time.prj.common.Command;

public class Logout implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String message = session.getAttribute("name") + "님 정상적으로 로그 아웃 되었습니다.";
//		session.invalidate(); // 세션 완전 삭제 (remove() => 값들만 삭제)
		session.removeAttribute("name");
		session.removeAttribute("author");
		request.setAttribute("message", message);
		return "member/memberError";
	}

}

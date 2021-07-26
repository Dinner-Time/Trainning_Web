package com.d_time.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateForm implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("bId", request.getParameter("bId"));
		request.setAttribute("bTitle", request.getParameter("bTitle"));
		request.setAttribute("bContent", request.getParameter("bContent"));
		return "board/updateForm";
	}

}

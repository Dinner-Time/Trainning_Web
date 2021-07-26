package com.d_time.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "home";
	}

}

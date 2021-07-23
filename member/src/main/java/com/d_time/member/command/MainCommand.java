package com.d_time.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// main.do 요청 처리
		// DB작업 => 결과를 request에 담음
		return "main/home"; // request를 전송할 페이지
	}

}

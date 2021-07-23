package com.d_time.member.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.member.command.Command;
import com.d_time.member.command.MainCommand;
import com.d_time.member.command.MemberDeleteCommand;
import com.d_time.member.command.MemberDeleteFormCommand;
import com.d_time.member.command.MemberSearchCommand;
import com.d_time.member.command.MemberSearchFormCommand;
import com.d_time.member.command.MemberSignUpCommand;
import com.d_time.member.command.MemberSignUpFormCommand;
import com.d_time.member.command.MemberUpdateCommand;
import com.d_time.member.command.MemberUpdateFormCommand;
import com.d_time.member.command.MemeberListCommand;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
       
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// command 객체 등록 => HashMap<>
		map.put("/main.do", new MainCommand()); // 메인 페이지
		map.put("/memberList.do", new MemeberListCommand()); // 회원 전체 조회 결과 페이지
		map.put("/memberSearchForm.do", new MemberSearchFormCommand()); // 회원 조회 form 페이지
		map.put("/memberSearch.do", new MemberSearchCommand()); // 회원 조회 결과 페이지
		map.put("/memberSignUpForm.do", new MemberSignUpFormCommand());// 회원 가입 form 페이지
		map.put("/memberSignUp.do", new MemberSignUpCommand());// 회원 가입 결과 페이지
		map.put("/memberDeleteForm.do", new MemberDeleteFormCommand()); // 회원 탈퇴 form 페이지
		map.put("/memberDelete.do", new MemberDeleteCommand()); // 회원 탈퇴 결과 페이지
		map.put("/memberUpdateForm.do", new MemberUpdateFormCommand()); // 회원 정보 수정 form 페이지
		map.put("/memberUpdate.do", new MemberUpdateCommand()); // 회원 정보 수정 결과 페이지
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 분석 및 처리 후 view Page 선택
		request.setCharacterEncoding("utf-8"); // 요청 내용 한글깨짐 방지

		// 실제 요청 분석
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String path = uri.substring(context.length()); // 실제 요청 페이지
		
		Command command = map.get(path); // => Command command = new MainCommand();
		String viewPage = command.execute(request, response); // 요청 처리 후 페이지
		
		if(!viewPage.endsWith(".do")) {
			viewPage = "WEB-INF/views/" + viewPage + ".jsp"; 
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	
	}

}

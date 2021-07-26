package com.d_time.board.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.board.command.BoardList;
import com.d_time.board.command.BoardSelect;
import com.d_time.board.command.Command;
import com.d_time.board.command.DeleteBoard;
import com.d_time.board.command.Home;
import com.d_time.board.command.InsertBoard;
import com.d_time.board.command.InsertForm;
import com.d_time.board.command.UpdateBoard;
import com.d_time.board.command.UpdateForm;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
       

	public void init(ServletConfig config) throws ServletException {
		map.put("/home.do", new Home()); // 메인 페이지
		
		map.put("/boardList.do", new BoardList()); // 전체 목록 보기
		map.put("/boardSelect.do", new BoardSelect()); // 한 건 조회
		
		map.put("/insertForm.do", new InsertForm()); // 새 글 작성 폼
		map.put("/insertBoard.do", new InsertBoard()); // 새 글 작성 기능
		
		map.put("/updateForm.do", new UpdateForm()); // 글 수정 폼
		map.put("/updateBoard.do", new UpdateBoard()); // 글 수정 기능
		
		map.put("/deleteBoard.do", new DeleteBoard()); // 글 수정 기능
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");

		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String path = uri.substring(context.length());
		
		Command command = map.get(path);
		String viewPage = command.execute(request, response);
		
		if(!viewPage.endsWith(".do")) {
			viewPage = "WEB-INF/views/" + viewPage + ".jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}

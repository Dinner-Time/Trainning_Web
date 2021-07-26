package com.d_time.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.board.service.BoardService;
import com.d_time.board.serviceImpl.BoardServiceImpl;
import com.d_time.board.vo.BoardVO;

public class BoardList implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 전체 목록 보기
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardService dao = new BoardServiceImpl();
		list = dao.boardSelectList();
		request.setAttribute("boards", list);
		
		return "board/boardList";
	}

}

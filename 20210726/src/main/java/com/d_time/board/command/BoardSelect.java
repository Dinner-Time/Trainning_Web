package com.d_time.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.board.service.BoardService;
import com.d_time.board.serviceImpl.BoardServiceImpl;
import com.d_time.board.vo.BoardVO;

public class BoardSelect implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 한 건 조회
		BoardService dao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setbId(Integer.parseInt(request.getParameter("bId")));
		vo = dao.boardSelect(vo);
		request.setAttribute("board", vo);
		return "board/boardSelect";
	}

}

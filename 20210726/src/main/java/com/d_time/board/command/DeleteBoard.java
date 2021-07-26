package com.d_time.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.board.service.BoardService;
import com.d_time.board.serviceImpl.BoardServiceImpl;
import com.d_time.board.vo.BoardVO;

public class DeleteBoard implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		BoardService dao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setbId(Integer.parseInt(request.getParameter("bId")));
		int r = dao.boardDelete(vo);
		if (r!=0) {
			return "boardList.do";
		}
		return "board/deleteFail";
	}

}

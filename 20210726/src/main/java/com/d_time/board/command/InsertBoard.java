package com.d_time.board.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_time.board.service.BoardService;
import com.d_time.board.serviceImpl.BoardServiceImpl;
import com.d_time.board.vo.BoardVO;

public class InsertBoard implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		BoardService dao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setbWriter(request.getParameter("bWriter"));
		vo.setbTitle(request.getParameter("bTitle"));
		vo.setbContent(request.getParameter("bContent"));
		vo.setbDate(Date.valueOf(request.getParameter("bDate")));
		int n = dao.boardInsert(vo);
		if(n!=0) {
			return "boardList.do";
		}
		return "board/insertFail";
	}

}

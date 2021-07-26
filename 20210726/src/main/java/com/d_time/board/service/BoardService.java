package com.d_time.board.service;

import java.util.List;

import com.d_time.board.vo.BoardVO;

public interface BoardService {
	List<BoardVO> boardSelectList(); // 전체 조회
	BoardVO boardSelect(BoardVO vo); // 한 건 조회
	int boardInsert(BoardVO vo); // 한 건 입력
	int boardDelete(BoardVO vo); // 한 건 삭제
	int boardUpdate(BoardVO vo); // 한 건 수정
}

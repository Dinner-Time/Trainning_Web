package com.d_time.board.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.d_time.board.DAO;
import com.d_time.board.service.BoardService;
import com.d_time.board.vo.BoardVO;

public class BoardServiceImpl extends DAO implements BoardService {

	@Override
	public List<BoardVO> boardSelectList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			conn = connect();
			sql = "select * from board order by bid desc";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setbId(rs.getInt("bid"));
				vo.setbTitle(rs.getString("btitle"));
//				vo.setbContent(rs.getString("bcontent"));
				vo.setbWriter(rs.getString("bwriter"));
				vo.setbDate(rs.getDate("bdate"));
				vo.setbHit(rs.getInt("bhit"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	@Override
	public BoardVO boardSelect(BoardVO vo) {
		try {
			conn = connect();
			sql = "select * from board where bid = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setbTitle(rs.getString("btitle"));
				vo.setbContent(rs.getString("bcontent"));
				vo.setbWriter(rs.getString("bwriter"));
				vo.setbDate(rs.getDate("bdate"));
				vo.setbHit(rs.getInt("bhit"));
				
				hitUpdate(vo.getbId());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}

	@Override
	public int boardInsert(BoardVO vo) {
		int result = 0;
		try {
			conn = connect();
			sql = "insert into board(bid, btitle, bcontent, bwriter, bdate) values (b_seq.nextVal, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getbTitle());
			psmt.setString(2, vo.getbContent());
			psmt.setString(3, vo.getbWriter());
			psmt.setDate(4, vo.getbDate());
			result = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	@Override
	public int boardDelete(BoardVO vo) {
		int result = 0;
		try {
			conn = connect();
			sql = "delete from board where bid = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());
			result = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		int result = 0;
		try {
			conn = connect();
			sql = "update board set btitle = ?, bcontent = ? where bid = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getbTitle());
			psmt.setString(2, vo.getbContent());
			psmt.setInt(3, vo.getbId());
			result = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	private void hitUpdate(int id) {
		try {
			conn = connect();
			sql = "update board set bhit = bhit + 1 where bid = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
}

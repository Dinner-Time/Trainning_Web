package com.d_time.prj.member.service;

import java.util.List;

import com.d_time.prj.common.DAO;
import com.d_time.prj.member.vo.MemberVO;

public class MemberServiceImpl extends DAO implements MemberService {

	@Override
	public List<MemberVO> memberSelectList() {
		return null;
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		return null;
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		try {
			connect();
			sql = "select name, author from member where id = ? and password = ? and state = 'Y'";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAuthor(rs.getString("author"));
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}
	
	@Override
	public int memberInsert(MemberVO vo) {
		return 0;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		return 0;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		return 0;
	}

}

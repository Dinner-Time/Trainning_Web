package com.d_time.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.d_time.member.command.DAO;
import com.d_time.member.service.MemberService;
import com.d_time.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private String sql;

	// connection 종료
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 전체 회원 목록
	@Override
	public List<MemberVO> memberSelectList() {
		List<MemberVO> members = new ArrayList<MemberVO>();
		MemberVO vo;
		sql = "select * from member";
		try {
			conn = DAO.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setHobby(rs.getString("hobby"));

				members.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return members;
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {

		sql = "select * from member where id = ?";
		try {
			conn = DAO.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();

			if (rs.next()) {
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setHobby(rs.getString("hobby"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		int n = 0;
		sql = "insert into member values(?,?,?,?,?)";
		try {
			conn = DAO.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getName());
			psmt.setInt(4, vo.getAge());
			psmt.setString(5, vo.getHobby());
			n = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		int n = 0;
		sql = "delete from member where id = ?";
		try {
			conn = DAO.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		int n = 0;
		sql = "update member set password = ?, age = ?, hobby = ? where id = ?";
		try {
			conn = DAO.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getPassword());
			psmt.setInt(2, vo.getAge());
			psmt.setString(3, vo.getHobby());
			psmt.setString(4, vo.getId());
			n = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

}

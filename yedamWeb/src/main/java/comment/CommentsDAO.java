package comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.DAO;

public class CommentsDAO extends DAO {
	private static CommentsDAO instance;

	public static CommentsDAO getInstance() {
		if (instance != null) {
			return instance;
		}
		return new CommentsDAO();
	}
	
	// 삭제 
	public void delete(String id) {
		connect();
		try {
			sql = "delete from comments where id = ?";
			conn.setAutoCommit(false); // 자동 커밋하지 않음
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.executeUpdate();
			conn.commit(); // 커밋
		} catch(Exception e) {
			
			// error 발생
			e.printStackTrace();
			System.out.println("커밋 도중 에러");
			try {
				conn.rollback(); // rollback => try내부에서 error발생시 rollback 실행
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("롤백 도중 에러");
			}
		} finally {
			disconnect();
		}
	}

	// 수정
	public HashMap<String, Object> update(Comments comment) {
		connect();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			sql = "update comments set name = ?, content = ? where id = ?";
			conn.setAutoCommit(false); // 자동 커밋하지 않음
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, comment.getName());
			psmt.setString(2, comment.getContent());
			psmt.setString(3, comment.getId());
			psmt.executeUpdate();
			conn.commit(); // commit

			// servlet으로 보내는 data
			map.put("id", comment.getId());
			map.put("name", comment.getName());
			map.put("content", comment.getContent());
			map.put("code", "success");

		} catch (Exception e) {
			
			// error 발생
			e.printStackTrace();
			System.out.println("커밋 도중 에러");
			try {
				conn.rollback(); // rollback => try내부에서 error발생시 rollback 실행
				map.put("code", "error");
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("롤백 도중 에러");
			}
		} finally {
			disconnect();
		}
		return map;
	}

	// 입력
	public HashMap<String, Object> insert(Comments comment) {
		// 현재 시퀀스 - 번호+1 - 입력 - 시퀀스+1
		connect();
		int currentId = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {

			// select
			sql = "select value from id_repository where name = 'COMMENT'";
			conn.setAutoCommit(false); // 자동 커밋하지 않음
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				currentId = rs.getInt(1);
			}
			currentId++; // 새로운 시퀀스번호

			// update
			sql = "update id_repository set value = ? where name = 'COMMENT'";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, currentId);
			psmt.executeUpdate();

			// insert
			sql = "insert into comments(id, name, content) values(?, ?, ?) ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, currentId);
			psmt.setString(2, comment.getName());
			psmt.setString(3, comment.getContent());
			psmt.executeUpdate();
			conn.commit(); // commit

			// servlet으로 보내는 data
			map.put("id", currentId);
			map.put("name", comment.getName());
			map.put("content", comment.getContent());
			map.put("code", "success");

		} catch (Exception e) {

			// error 발생
			e.printStackTrace();
			System.out.println("커밋 도중 에러");
			try {
				conn.rollback(); // rollback => try내부에서 error발생시 rollback 실행
				map.put("code", "error");
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("롤백 도중 에러");
			}

		} finally {
			disconnect();
		}
		return map;
	}

	// 댓글목록
	public List<HashMap<String, Object>> selectAll() {
		connect();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		sql = "select * from comments order by id";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("id", rs.getInt(1));
				map.put("name", rs.getString(2));
				map.put("content", rs.getString(3));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

}

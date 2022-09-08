package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db_connection.JDBC;
import member.MemberDTO;

public class BoardDAOImpl implements BoardDAO {

	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL조회(Select) 결과 저장 객체

	// 게시글 전체 목록 출력
	@Override
	public List<BoardDTO> selectList(Connection conn) {

		String sql = "Select * From memberBoard";

		List<BoardDTO> res = new ArrayList<BoardDTO>();

		try {
			ps = conn.prepareStatement(sql); // 쿼리문 실행
			rs = ps.executeQuery(); // pstm의 실행 결과 저장

			while (rs.next()) {
				BoardDTO tmp = new BoardDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getDate(7));
				res.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	// 게시글 하나 조회
	@Override
	public BoardDTO selectOne(Connection conn, int boardNo) {
		String sql = "Select * From memberBoard Where boardNo = ?";

		BoardDTO res = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo); // 쿼리문 첫 번째 ?에 boardNo이 전달됨

			rs = ps.executeQuery();

			while (rs.next()) {
				res = new BoardDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getDate(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rs);
			JDBC.close(ps);
		}

		return res;
	}

	// 조회수 증가
	@Override
	public boolean countingView(Connection conn, BoardDTO boardDto) {
		String sql = "Update memberBoard Set viewCount = viewCount + 1 Where boardNo=?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardDto.getBoardNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rs);
			JDBC.close(ps);
		}

		return (res>0) ? true : false;
	}

	//게시글 작성
	@Override
	public boolean insert(Connection conn, BoardDTO boardDto) {
		String sql = "insert into "
				   + "memberBoard(UID, title, content, memberNickname)"
				   + "value("
				   + "	(select UID from members Where UID=?),"
				   + "?, ?, "
				   + "(select memberNickname from members where UID = ?)"
				   + ")" ;
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardDto.getUID());
			ps.setString(2, boardDto.getTitle());
			ps.setString(3, boardDto.getContent());
			ps.setInt(4, boardDto.getUID());
			
			res = ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rs);
			JDBC.close(ps);
		}
		
		return (res>0) ? true : false;
	}

	//사용자 UID 검사
	@Override
	public boolean checkUID(Connection conn, BoardDTO boardDto) {
		System.out.println("UID 확인 시작");
		
		String sql = "Select UID From memberBoard Where boardNo = ?";
		
		int boardUID = 0;    //게시글의 UID
		boolean res = false; //결과 보고용 boolean
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardDto.getBoardNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				boardUID = rs.getInt(1);
			}
			
			System.out.println("게시글의 UID : "+ boardUID);
			System.out.println("유저의 UID : "+ boardDto.getUID());
			
			//게시글의 UID와 요청한 사용자의 UID가 같다면
			if(boardUID == boardDto.getUID()) {
				//OK, true 반환
				res = true;
				System.out.println("UID 일치");
			} else {
				//No, false 반환
				res = false;
				System.out.println("UID 불일치");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//글 수정
	@Override
	public boolean update(Connection conn, BoardDTO boardDto) {
		String sql = "Update memberBoard Set title=?, content=? Where boardNo=?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, boardDto.getTitle());
			ps.setString(2, boardDto.getContent());
			ps.setInt(3, boardDto.getBoardNo());
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rs);
			JDBC.close(ps);
		}
		
		return (res>0) ? true : false;
	}

	//글 삭제
	@Override
	public boolean delete(Connection conn, int boardNo) {
		String sql = "Delete From memberBoard Where boardNo=?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rs);
			JDBC.close(ps);
		}

		return (res>0) ? true : false;
	}

}

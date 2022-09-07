package board;

import java.sql.Connection;
import java.util.List;

import db_connection.JDBC;
import member.MemberDAO;
import member.MemberDAOImpl;

public class BoardServiceImpl implements BoardService {

	// DAO 객체
	private BoardDAO dao = new BoardDAOImpl();
	private MemberDAO memberDao = new MemberDAOImpl();

	// 게시글 전체 출력
	@Override
	public List<BoardDTO> selectList() {

		Connection conn = JDBC.getConnection();
		List<BoardDTO> res = dao.selectList(conn);

		return res;
	}

	// 게시글 하나 보기
	@Override
	public BoardDTO selectOne(int boardNo) {
		Connection conn = JDBC.getConnection();

		// boardNo을 PK로 하여 DAO를 통해 DB에서 데이터 추출
		BoardDTO boardDto = dao.selectOne(conn, boardNo);
		JDBC.close(conn);

		return boardDto;
	}

	// 조회수 증가
	@Override
	public boolean countingView(BoardDTO boardDto) {
		Connection conn = JDBC.getConnection();

		boolean res = dao.countingView(conn, boardDto);

		if (res) {
			JDBC.commit(conn);
		}
		JDBC.close(conn);

		return res;
	}

	// 게시글 작성
	@Override
	public boolean insert(BoardDTO boardDto) {
		Connection conn = JDBC.getConnection();
		
		// DAO, DB 작업수행
		boolean res = dao.insert(conn, boardDto);

		// DAO에서 작업이 올바르게 수행됐다면 커밋
		if (res) {
			JDBC.commit(conn);
		}
		JDBC.close(conn);
		return res;
	}

	@Override
	public boolean update(BoardDTO boardDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int boardNo) {
		// TODO Auto-generated method stub
		return false;
	}

}

package comment;

import java.sql.Connection;
import java.util.List;

import db_connection.JDBC;

public class CommentServiceImpl implements CommentService {

	CommentDAO dao = new CommentDAOImpl();
	
	//댓글 출력
	@Override
	public List<CommentDTO> commentList(int boardNo) {
		
		Connection conn = JDBC.getConnection();
		List<CommentDTO> res = dao.commentList(conn, boardNo);
		
		return res;
	}
	
	//댓글 작성
	@Override
	public boolean comment(CommentDTO dto) {
		Connection conn = JDBC.getConnection();
		
		boolean res = dao.comment(conn, dto);
		
		if (res) {
			JDBC.commit(conn);
		}
		JDBC.close(conn);
		return res;
	}
	
	//댓글 삭제
	@Override
	public boolean commentDelete(CommentDTO dto) {
		Connection conn = JDBC.getConnection();
		
		boolean res = dao.commentDelete(conn, dto);
		
		if (res) {
			JDBC.commit(conn);
		}
		JDBC.close(conn);
		return res;
	}
}

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

}

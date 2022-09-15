package comment;

import java.sql.Connection;
import java.util.List;

public interface CommentDAO {
	public List<CommentDTO> commentList(Connection conn, int boardNo);
	
	//댓글 작성
	public boolean comment(Connection conn, CommentDTO dto);
	
	//댓글 삭제
	public boolean commentDelete(Connection conn, CommentDTO dto);
}

package comment;

import java.sql.Connection;
import java.util.List;

public interface CommentDAO {
	public List<CommentDTO> commentList(Connection conn, int boardNo);
}

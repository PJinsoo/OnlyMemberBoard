package comment;

import java.util.List;

public interface CommentService {
	public List<CommentDTO> commentList(int boardNo);
}

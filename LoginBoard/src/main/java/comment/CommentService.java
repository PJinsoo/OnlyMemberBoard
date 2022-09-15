package comment;

import java.util.List;

public interface CommentService {
	public List<CommentDTO> commentList(int boardNo);
	
	public boolean comment(CommentDTO dto);
}

package board;

import java.util.List;

public interface BoardService {
	//게시글 목록 출력
	public List<BoardDTO> selectList();
	
	//게시글 하나 출력
	public BoardDTO selectOne(int boardNo);
	
	//조회수 카운팅
	public boolean countingView(BoardDTO dto);
	
	//게시글 작성
	public boolean insert(BoardDTO dto);
	
	//게시글 수정
	public boolean update(BoardDTO dto);
	
	//게시글 삭제
	public boolean delete(int boardNo);	
}

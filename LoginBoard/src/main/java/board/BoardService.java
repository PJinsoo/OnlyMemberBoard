package board;

import java.util.List;

public interface BoardService {
	//게시글 목록 출력
	public List<BoardDTO> selectList();
	
	//게시글 검색
	public List<BoardDTO> search(String searchOption, String searchWord);
	
	//게시글 조회
	public BoardDTO selectOne(int boardNo);
	
	//조회수 카운팅
	public boolean countingView(BoardDTO dto);
	
	//추천하기
	public boolean recommend(int boardNo);
	
	//추천 시 증가하는 조회수를 원상복구
	public boolean downView(int boardNo);
	
	//게시글 작성
	public boolean insert(BoardDTO dto);
	
	//UID 체크
	public boolean checkUID(BoardDTO dto);
	
	//게시글 수정
	public boolean update(BoardDTO dto);
	
	//게시글 삭제
	public boolean delete(int boardNo);	
}

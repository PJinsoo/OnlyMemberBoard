package board;

import java.sql.Connection;
import java.util.List;

public interface BoardDAO {
//	String selectListSQL = "Select * From board";
//	String selectOneSQL = "Select * From board Where boardNo = ?";
//	String viewCountUpSQL = "Update board Set viewCount = viewCount + 1 Where boardNo=?";
//	String insertSQL = "Insert into board(writer, title, content) Value(?, ?, ?)";
//	String updateSQL = "Update board Set title=?, content=? Where boardNo=?";
//	String deleteSQL = "Delete From board Where boardNo=?";

	//게시글 목록 출력
	public List<BoardDTO> selectList(Connection conn);
	
	//게시판 검색
	public List<BoardDTO> search(Connection conn, String searchOption, String searchWord);
	
	//게시글 하나 출력
	public BoardDTO selectOne(Connection conn, int boardNo);
	
	//조회수 카운팅
	public boolean countingView(Connection conn, BoardDTO dto);
	
	//추천하기
	public boolean recommend(Connection conn, int boardNo);
	
	//추천 시 증가하는 조회수를 감소시키기
	public boolean downView(Connection conn, int boardNo);
	
	//게시글 작성
	public boolean insert(Connection conn, BoardDTO dto);
	
	//UID 체크
	public boolean checkUID(Connection conn, BoardDTO dto);
	
	//게시글 수정
	public boolean update(Connection conn, BoardDTO dto);
	
	//게시글 삭제
	public boolean delete(Connection conn, int boardNo);
}

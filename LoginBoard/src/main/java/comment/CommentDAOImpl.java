package comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db_connection.JDBC;

public class CommentDAOImpl implements CommentDAO{
	
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL조회(Select) 결과 저장 객체
	
	//댓글 불러오기
	public List<CommentDTO> commentList(Connection conn, int boardNo){
		String sql = "Select * From memberComment Where boardNo = ?";
		
		List<CommentDTO> res = new ArrayList<CommentDTO>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CommentDTO tmp = new CommentDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), 
						rs.getString(5), rs.getDate(6));
				res.add(tmp);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	//댓글 작성
	@Override
	public boolean comment(Connection conn, CommentDTO dto) {
		String sql = "Insert Into memberComment(boardNo, UID, memberNickname, commentContent)"
				   + "value(?, ?, "
				   + "(Select memberNickname From members Where UID = ?), "
				   + "?)";
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getBoardNo());
			ps.setInt(2, dto.getUID());
			ps.setInt(3, dto.getUID());
			ps.setString(4, dto.getCommentContent());
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rs);
			JDBC.close(ps);
		}

		return (res > 0) ? true : false;
	}
}

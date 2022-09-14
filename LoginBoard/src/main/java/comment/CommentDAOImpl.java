package comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}

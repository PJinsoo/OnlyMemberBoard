package comment;

import java.sql.Date;

public class CommentDTO {
	private int commentNo;
	private int boardNo;
	private int UID;
	private String memberNickname;
	private String commentContent;
	private Date commentTime;
	
	public CommentDTO() {
		super();
	}
	
	public CommentDTO(int commentNo, int boardNo, int uID, String memberNickname, String commentContent,
			Date commentTime) {
		super();
		this.commentNo = commentNo;
		this.boardNo = boardNo;
		UID = uID;
		this.memberNickname = memberNickname;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
	}

	//댓글 작성 생성자
	public CommentDTO(int UID, int boardNo, String commentContent) {
		super();
		this.UID = UID;
		this.boardNo = boardNo;
		this.commentContent = commentContent;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getUID() {
		return UID;
	}

	public void setUID(int uID) {
		UID = uID;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
}

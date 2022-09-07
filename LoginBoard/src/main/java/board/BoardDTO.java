package board;

import java.sql.Date;

public class BoardDTO {
	private int boardNo;
	private int UID;
	private String title;
	private String content;
	private String memberNickname;
	private int viewCount;
	private Date postTime;
	
	public BoardDTO() {
		super();
	}
	
	public BoardDTO(int boardNo, int UID, String title, String content, String memberNickname, int viewCount,
			Date postTime) {
		super();
		this.boardNo = boardNo;
		this.UID = UID;
		this.title = title;
		this.content = content;
		this.memberNickname = memberNickname;
		this.viewCount = viewCount;
		this.postTime = postTime;
	}
	
	public BoardDTO(int boardNo) {
		super();
		this.boardNo = boardNo;
	}

	public BoardDTO(int UID, String memberNickname, String title, String content) {
		super();
		this.UID = UID;
		this.memberNickname = memberNickname;
		this.title = title;
		this.content = content;
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
	public void setUID(int UID) {
		this.UID = UID;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
}

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
	private int recommend;
	
	public BoardDTO() {
		super();
	}

	public BoardDTO(int boardNo, int UID, String title, String content, String memberNickname, int viewCount,
			Date postTime, int recommend) {
		super();
		this.boardNo = boardNo;
		this.UID = UID;
		this.title = title;
		this.content = content;
		this.memberNickname = memberNickname;
		this.viewCount = viewCount;
		this.postTime = postTime;
		this.recommend = recommend;
	}
	
	//게시글 조회용 생성자
	public BoardDTO(int boardNo) {
		super();
		this.boardNo = boardNo;
	}

	//게시글 작성용 생성자
	public BoardDTO(int UID, String memberNickname, String title, String content) {
		super();
		this.UID = UID;
		this.memberNickname = memberNickname;
		this.title = title;
		this.content = content;
	}

	//게시글 수정용 생성자
	public BoardDTO(int boardNo, String title, String content) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
	}

	//사용자 신뢰성 검사용 생성자, UID의 경우 사용자의 UID가 들어오게 됨
	public BoardDTO(int UID, int boardNo) {
		super();
		this.UID = UID;
		this.boardNo = boardNo;
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
	
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
}

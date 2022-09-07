package member;

import java.sql.Date;

public class MemberDTO {
	private int UID;
	private String memberID;
	private String memberPW;
	private String memberNickname;
	private Date joinDate;
	
	public MemberDTO() {
		super();
	}
	
	public MemberDTO(int uid, String memberID, String memberPW, String memberNickname, Date joinDate) {
		super();
		this.UID = uid;
		this.memberID = memberID;
		this.memberPW = memberPW;
		this.memberNickname = memberNickname;
		this.joinDate = joinDate;
	}

	public MemberDTO(String memberID, String memberPW, String memberNickname) {
		super();
		this.memberID = memberID;
		this.memberPW = memberPW;
		this.memberNickname = memberNickname;
	}
	
	public MemberDTO(String memberID, String memberPW) {
		super();
		this.memberID = memberID;
		this.memberPW = memberPW;
	}
	
	public MemberDTO(int UID) {
		super();
		this.UID = UID;
	}

	public int getUID() {
		return UID;
	}
	public void setUID(int UID) {
		this.UID = UID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberPW() {
		return memberPW;
	}
	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}

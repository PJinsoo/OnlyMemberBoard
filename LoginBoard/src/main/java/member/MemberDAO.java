package member;

import java.sql.Connection;

public interface MemberDAO {
	//회원가입(insert)
	public boolean register(Connection conn, MemberDTO member);
	
	//로그인
	public boolean login(Connection conn, MemberDTO member);

	//로그인 유저정보 저장(세션 유지)
	public MemberDTO userInfo(Connection conn, MemberDTO member);
	
	//회원 정보 수정(update)
	public boolean memberUpdate(Connection conn, MemberDTO member);
	
	//회원 비밀번호 검증
	public boolean checkPW(Connection conn, MemberDTO member);
	
	//회원 탈퇴(Delete)
	public boolean withdraw(Connection conn, int UID);
}

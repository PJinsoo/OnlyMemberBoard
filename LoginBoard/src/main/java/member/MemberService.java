package member;

public interface MemberService {

	//회원가입(member Insert)
	public boolean register(MemberDTO member);
	
	//로그인
	public boolean login(MemberDTO member);
	
	//유저정보
	public MemberDTO info(MemberDTO member);
	
	//회원 정보 수정(member Update)
	public boolean memberUpdate(MemberDTO member);
	
	//사용자 비밀번호 확인
	public boolean checkPW(MemberDTO member);
	
	//회원 탈퇴(member Delete)
	public boolean withdraw(int UID);
}

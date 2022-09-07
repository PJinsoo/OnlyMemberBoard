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
}

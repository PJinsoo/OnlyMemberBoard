package member;

import java.sql.Connection;
import db_connection.JDBC;

public class MemberServiceImpl implements MemberService {
	//MemberDAO 객체
	private MemberDAO memberDao = new MemberDAOImpl();

	//회원가입
	@Override
	public boolean register(MemberDTO member) {
		Connection conn = JDBC.getConnection();
		
		boolean res = memberDao.register(conn, member);
		
		if(res) {
			JDBC.commit(conn);
		}
		JDBC.close(conn);
		
		return res;
	}

	//로그인
	@Override
	public boolean login(MemberDTO member) {
		Connection conn = JDBC.getConnection();
		boolean res = memberDao.login(conn, member);
		
		if(res) { //로그인 인증 성공
			JDBC.commit(conn);
		}
		JDBC.close(conn);
		
		return res;
	}

	//유저 정보
	@Override
	public MemberDTO info(MemberDTO member) {
		return memberDao.userInfo(JDBC.getConnection(), member);
	}

	//회원 정보 수정
	@Override
	public boolean memberUpdate(MemberDTO member) {
		Connection conn = JDBC.getConnection();
		
		boolean res = memberDao.memberUpdate(conn, member);
		
		if(res) { //회원 정보 수정 성공
			JDBC.commit(conn);
		}
		JDBC.close(conn);
		
		return res;
	}
	
	
	
	
}

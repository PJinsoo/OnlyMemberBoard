package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_connection.JDBC;

public class MemberDAOImpl implements MemberDAO{

	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체

	//회원가입
	@Override
	public boolean register(Connection conn, MemberDTO member) {
		//회원가입 SQL
		String sql = "Insert into members(memberID, memberPW, memberNickname) Value(?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getMemberID());
			ps.setString(2, member.getMemberPW());
			ps.setString(3, member.getMemberNickname());
			
			res = ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return (res>0) ? true : false;
	}
	
	//로그인
	@Override
	public boolean login(Connection conn, MemberDTO member) {
		String sql = "Select count(*) From members Where memberID= ? And memberPW= ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getMemberID());
			ps.setString(2, member.getMemberPW());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rs);
			JDBC.close(ps);
		}
		
		return (res>0) ? true : false;
		
	}

	//로그인 유저정보 저장
	@Override
	public MemberDTO userInfo(Connection conn, MemberDTO member) {
		String sql = "Select * From members Where memberID = ?";
		
		MemberDTO res = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getMemberID());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				res = new MemberDTO();
				
				res.setUID(rs.getInt("UID"));
				res.setMemberID(rs.getString("memberID"));
				res.setMemberPW(rs.getString("memberPW"));
				res.setMemberNickname(rs.getString("memberNickname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rs);
			JDBC.close(ps);
		}
		
		return res;
	}

	//회원 정보 수정
	@Override
	public boolean memberUpdate(Connection conn, MemberDTO member) {
		String sql = "Update members Set memberPW=?, memberNickname=? Where memberID=?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getMemberPW());
			ps.setString(2, member.getMemberNickname());
			ps.setString(3, member.getMemberID());
			
			res = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(ps);
		}
		return (res>0) ? true : false;
	}

	//회원의 비밀번호 검증
	@Override
	public boolean checkPW(Connection conn, MemberDTO member) {
		System.out.println("비밀번호 검증 시작");
		System.out.println("탈퇴 요청 UID : " + member.getUID());
		
		String sql = "Select memberPW From members Where UID = ?";
		String PW = null;
		boolean res = false;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,member.getUID());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PW = rs.getString(1);
			}
			
			System.out.println("입력된 PW : " + PW);
			System.out.println("실제 PW : " + member.getMemberPW());
			
			if(PW.equals(member.getMemberPW())) {
				res = true;
			} else {
				res = false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("리턴 : " + res);
		return res;
	}
	
	//회원 탈퇴
	public boolean withdraw(Connection conn, int UID) {
		String sql = "Delete From members Where UID = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, UID);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(rs);
			JDBC.close(ps);
		}
		
		return (res>0) ? true : false;
	}
	
	
}

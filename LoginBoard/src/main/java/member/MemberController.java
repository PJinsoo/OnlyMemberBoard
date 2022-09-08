package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//회원 관리 컨트롤러


@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 요청받을 변수
		// command에게 건물 이름을 알려주어 이동하는 느낌
		String command = request.getParameter("command");

		MemberService memberService = new MemberServiceImpl();

		// 시작화면 출력
		if (command.equals("index")) {
			System.out.println("index.jsp 접속");

			response.sendRedirect("index.jsp");
		}

		// 회원가입 버튼 클릭 시 회원가입 화면 '단순' 출력
		else if (command.equals("register")) {
			System.out.println("회원가입 화면 출력");

			response.sendRedirect("member_view/register.jsp");
		}

		// 가입하기 버튼을 클릭한 회원 등록하기
		else if (command.equals("regiComplete")) {
			System.out.println("회원가입 실행(회원DB에 insert)");

			String memberID = request.getParameter("memberID");
			String memberPW = request.getParameter("memberPW");
			String memberNickname = request.getParameter("memberNickname");

			MemberDTO member = new MemberDTO(memberID, memberPW, memberNickname);
			boolean res = memberService.register(member);

			if (res) { // 가입에 성공했다면 성공 alert 창을 띄워줌
				jsResponse("가입을 축하드립니다!", "member.do?command=index", response);
			} else { // 작성에 실패했다면 다시 가입 페이지로 돌려보냄
				jsResponse("가입에 실패하셨습니다. 다시 시도해주세요.", "member.do?command=index", response);
			}
		}

		// 로그인 화면 출력
		else if (command.equals("login")) {
			System.out.println("로그인 화면 출력");
			response.sendRedirect("member_view/login.jsp");
		}

		// 로그인 체크
		else if (command.equals("loginCheck")) {
			System.out.println("사용자 계정 체크");

			String memberID = request.getParameter("memberID");
			String memberPW = request.getParameter("memberPW");

			MemberDTO member = new MemberDTO(memberID, memberPW);
			boolean login = memberService.login(member);

			if (login) {
				System.out.println(memberID + " 유저의 로그인 인증 완료");

				member = memberService.info(member);

				//로그인한 사용자의 세션 저장하기
				HttpSession session = request.getSession();
				session.setAttribute("login", login);
				session.setAttribute("UID", member.getUID());
				session.setAttribute("memberID", member.getMemberID());
				session.setAttribute("memberPW", member.getMemberPW());
				session.setAttribute("memberNickname", member.getMemberNickname());

				System.out.println(memberID + " 유저의 정보 확인 완료");
				jsResponse(memberID + "님 환영합니다!", "member.do?command=index", response);
			} else {
				jsResponse("유저 정보가 틀립니다.", "member.do?command=index", response);
			}
		}

		// 로그아웃 요청
		else if (command.equals("logout")) {

			System.out.println("로그아웃 요청");

			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}
		
		//회원 정보 수정 페이지 출력
		else if(command.equals("memberUpdatePage")) {
			System.out.println("회원 정보 수정 화면 출력");
			response.sendRedirect("member_view/memberUpdatePage.jsp");
		}
		
		
		//회원 정보 수정(Member Update)
		else if(command.equals("memberUpdate")) {
			//회원의 ID를 저장
			String memberID = request.getParameter("memberID");
			
			//수정 가능한 항목의 정보를 저장
			String memberPW = request.getParameter("memberPW");
			String memberNickname = request.getParameter("memberNickname");
			
			MemberDTO member = new MemberDTO(memberID, memberPW, memberNickname);
			
			boolean res = memberService.memberUpdate(member);
			
			if(res) {
				jsResponse("회원 정보가 수정되었습니다.", "member.do?command=index", response);
			}
		}
		
		//회원 탈퇴 페이지 로드
		else if(command.equals("withdraw")) {
			System.out.println("회원 탈퇴 페이지 로드");
			response.sendRedirect("member_view/withdraw.jsp");
		}
		
		//회원 탈퇴 실행
		else if(command.equals("leave")) {
			int UID = Integer.parseInt(request.getParameter("UID"));
			String memberPW = request.getParameter("memberPW");
			
			MemberDTO member = new MemberDTO(UID, memberPW);
			
			//비밀번호 확인
			boolean check = memberService.checkPW(member);
			
			if(check) {
				//비밀번호 확인 완료, 탈퇴 승인
				
				//로그아웃(사용자 세션 해제)
				request.getSession().invalidate();
				
				//회원탈퇴 실행
				memberService.withdraw(member.getUID());
				jsResponse("탈퇴되셨습니다.", "member.do?command=index", response);
			}
			else {
				//비밀번호가 틀림, 탈퇴 거부
				jsResponse("비밀번호가 다릅니다!", "member.do?command=withdraw", response);
			}
		}
		
	} //doGet()의 끝

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 알림창 alert 메서드
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String alert = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";
		PrintWriter out = response.getWriter();
		out.print(alert);
	}
}

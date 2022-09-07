package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//게시판 관리 컨트롤러


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
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
		
		//User <-> View <-> Controller <-> Service <-> DAO <-> DB
		//이 작업을 위한 서비스 객체 생성
		BoardService service = new BoardServiceImpl();

		//게시판 접속, 전체 게시글 목록 출력
		if (command.equals("boardList")) {
			System.out.println("게시판 접속");

			List<BoardDTO> list = service.selectList();
			
			request.setAttribute("list", list);
			
			dispatch("board_view/boardList.jsp", request, response);
		}
		
		//사용자 게시글 제목 클릭, 게시글 조회 요청
		else if (command.equals("boardOne")){
			
			//사용자가 클릭한 게시글의 no 저장
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			System.out.println("게시판 번호" + boardNo + " 클릭");
			
			//사용자가 클릭한 게시글의 정보를 dto객체에 담고 서비스에 요청
			BoardDTO boardDto = service.selectOne(boardNo);
			
			//조회수 증가 구현
			BoardDTO counting = new BoardDTO(boardNo);
			service.countingView(counting);
			System.out.println("게시글 번호" + boardNo + " 조회수 증가");
			
			request.setAttribute("dto", boardDto);
			dispatch("board_view/selectOne.jsp", request, response);
		}
		
		//새 글 쓰기 페이지 요청
		else if(command.equals("boardInsert")) {
			response.sendRedirect("board_view/boardInsert.jsp");
			System.out.println("게시글 작성 페이지 로드 완료");
		}
		
		//새 글 쓰기 실행
		else if(command.equals("insertExecute")) {
			System.out.println("게시글 작성 수행 시작");
			
			//게시글 작성에 필요한 정보를 boardInsert페이지로 부터 받기
			int UID = Integer.parseInt(request.getParameter("UID"));
			String memberNickname = request.getParameter("memberNickname");
			String boardTitle = request.getParameter("boardTitle");
			String boardContent = request.getParameter("boardContent");
			
			BoardDTO boardDto = new BoardDTO(UID, memberNickname, boardTitle, boardContent);
			
			boolean res = service.insert(boardDto);
			
			if(res) { //작성에 성공했다면 성공 alert 창을 띄워줌
				jsResponse("새 게시글이 작성되었습니다.", "board.do?command=boardList", response);
				System.out.println("게시글 작성 수행 완료");
			} else { //작성에 실패했다면 다시 insert.jsp 페이지로 돌려보냄
				dispatch("board.do?command=boardInsert", request, response);
				System.out.println("게시글 작성 실패");
			}
		}
		
		/* 게시글 수정, 삭제의 경우
		 * 해당 게시글을 작성한 사용자만 가능한 기능으로 구현해야함.
		 * 누구나 게시글을 수정, 삭제할 수 있다면 의미가 없음.
		 * 
		 * BoardService 객체의 Check() 메소드에서 사용자의 신뢰성 검사.
		 */
		
		//게시글 수정 페이지 로딩
		else if(command.equals("boardUpdate")) {
			System.out.println("사용자의 게시글 수정 요청");
			
			//사용자 신뢰성 검사를 위한 UID
			int UID = Integer.parseInt(request.getParameter("UID"));
			
			//수정을 요청한 게시글의 BoardNO
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			//사용자와 게시글의 UID의 동일성을 체크하기 위한 객체 
			BoardDTO boardDto = new BoardDTO(UID, boardNo);
			
			//사용자의 신뢰성 검사
			boolean check = service.checkUID(boardDto);
			
			//사용자의 신뢰성이 참이라면
			if(check) {
				//수정을 요구한 게시글의 정보를 그대로 저장
				BoardDTO dto = service.selectOne(boardNo);
				
				//boardUpdate.jsp로 이동하면서 dto객체에 저장된 정보를 그대로 출력
				request.setAttribute("dto", dto);
				dispatch("board_view/boardUpdate.jsp", request, response);
			}
			else { //사용자의 신뢰성이 유효하지않음
				jsResponse("게시글의 작성자가 아닙니다!", "board.do?command=boardList", response);
			}
		}
		
		//게시글 수정 실행
		else if(command.equals("updateExecute")) {
			System.out.println("게시글 수정 실행");
			
			//수정에 필요한 정보
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String title = request.getParameter("boardTitle");
			String content = request.getParameter("boardContent");
			
			//수정용 객체
			BoardDTO boardDto = new BoardDTO(boardNo, title, content);
			
			//Service에 전달(DB 실행)
			boolean res = service.update(boardDto);
			
			if(res) {
				jsResponse("게시글이 수정되었습니다.", "board.do?command=boardOne&boardNo="+boardNo, response);
				System.out.println("게시글 수정 완료");
			} else {
				dispatch("board.do?command=boardUpdate&boardNo="+boardNo, request, response);
				System.out.println("게시글 수정 실패");
			}
		}
		
		else if(command.equals("boardDelete")) {
			System.out.println("게시글 삭제 수행 시작");
		}
		
		
		
		
	} //doGet()의 끝

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// dispatcher -> forward 방식
	// 클라이언트 요청에 전송한 데이터 유지
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	// 알림창 alert 메서드
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String alert = "<script type='text/javascript'>"
					 + "alert('" + msg + "');"
				     + "location.href='" + url + "';"
				     + "</script>";
		PrintWriter out = response.getWriter();
		out.print(alert);
	}
	
} //컨트롤러의 끝

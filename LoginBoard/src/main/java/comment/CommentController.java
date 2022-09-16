package comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.BoardService;
import board.BoardServiceImpl;


//댓글 관리 컨트롤러


@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentController() {
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
		
		//서비스 객체
		CommentService service = new CommentServiceImpl();
		//조회수 관리용 게시판 서비스 객체
		BoardService boardService = new BoardServiceImpl();
		
		
		//댓글 작성
		if(command.equals("commentInsert")) {
			System.out.println("댓글작성");
			
			//댓글 작성자의 UID
			int UID = Integer.parseInt(request.getParameter("UID"));
			//댓글을 남길 게시판의 번호
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			//댓글 내용
			String commentContent = request.getParameter("commentContent");
			
			//댓글 insert용 DTO객체 생성
			CommentDTO commentDto = new CommentDTO(UID, boardNo, commentContent);
			
			//insert 서비스 호출
			service.comment(commentDto);
			//조회수 관리
			boardService.downView(boardNo);
		}
		
		//댓글 삭제
		//사용자 검증은 자바스크립트에서 진행하고 넘어옴
		else if(command.equals("commentDelete")) {
			System.out.println("댓글 삭제 시도");
			
			//삭제를 요구한 유저의 UID
			int UID = Integer.parseInt(request.getParameter("UID"));
			//삭제할 댓글 번호
			int commentNo = Integer.parseInt(request.getParameter("comNo"));
			//삭제할 댓글이 있는 게시글 번호
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			CommentDTO commentDto = new CommentDTO(UID, commentNo);
			service.commentDelete(commentDto);
			
			boardService.downView(boardNo);
			
		}
		
	}// doGet의 끝

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

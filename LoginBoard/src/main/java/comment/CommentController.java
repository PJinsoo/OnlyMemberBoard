package comment;

import java.io.IOException;

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
		
		CommentService service = new CommentServiceImpl();
		BoardService boardService = new BoardServiceImpl();
		
		
		//댓글 작성
		if(command.equals("commentInsert")) {
			System.out.println("댓글작성");
			
			int UID = Integer.parseInt(request.getParameter("UID"));
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String commentContent = request.getParameter("commentContent");
			
			CommentDTO commentDto = new CommentDTO(UID, boardNo, commentContent);
			
			service.comment(commentDto);
			boardService.downView(boardNo);
		}
		
	}// doGet의 끝

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

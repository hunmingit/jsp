package org.joonzis.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joonzis.service.BoardService;
import org.joonzis.service.BoardServiceImpl;
import org.joonzis.service.ProjService;
import org.joonzis.service.ProjServiceImpl;
import org.joonzis.util.PageMaker;
import org.joonzis.vo.BoardVO;
import org.joonzis.vo.ProjVO;


@WebServlet("/ProjController")
public class ProjController extends HttpServlet {
	private static final long serialVersionUID = 1L; // 직렬화 관련 경고 제거용, 의식 안 하고 항상 넣는 값
	private BoardService boardService = new BoardServiceImpl();
    public ProjController() {
        super();
    }
    //GET/POST 통합 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//cmd가 없으면 기본 화면 = 로그인 페이지
		String cmd = request.getParameter("cmd");
		if(cmd == null) {
			cmd = "loginPage";
		}
		String path = "";
		
		switch (cmd) {
			case "loginPage":
				path = "proj/login.jsp";
				break;

			case "idCheck": {
								String pId = request.getParameter("pId");

								ProjService service = ProjServiceImpl.getInstance();
								boolean exists = service.isExistId(pId);
								//AJAX 데이터용 
								response.setContentType("text/plain; charset=utf-8");
								response.getWriter().print(exists ? "fail" : "ok");
								return; 
			}
			
			case "join": {
								//form submit으로 넘어온 값
	    						String pId = request.getParameter("pId");
	    						String pPw = request.getParameter("pPw");
	
								// Service 호출
								ProjService service = ProjServiceImpl.getInstance();
								//컨트롤러는 성공/실패만 알면 되기 때문에 boolean으로 받음
								boolean result = service.join(pId, pPw);
								HttpSession session = request.getSession();
								//회원가입 완료 session + redirect
								if(result) { 
									session.setAttribute("msg", "회원가입 성공!");
								} else {
									session.setAttribute("msg", "회원가입 실패 ");
	
								}
								
								response.sendRedirect("ProjController?cmd=loginPage");
								return;
			}
			
			case "login" : {
								String pId = request.getParameter("pId");
								String pPw = request.getParameter("pPw");						     	
								
								ProjService service = ProjServiceImpl.getInstance();
								ProjVO returnVO = service.doLogin(pId, pPw);
								
								if(returnVO!=null) {
									HttpSession session = request.getSession();
									session.setAttribute("returnVO", returnVO);
									response.sendRedirect("ProjController?cmd=boardList");
									return; // 로그인 성공
								}else { 
									//로그인 실패는 재시도 목적 forward가 적합 (새로고침) request + forward
									//sql에서 데이터 넣을때도 암호값으로 넣어야한다. 그냥 admin/admin 넣었다가 개고생함
									//근데 왜 또 오류가.. -> boardList에서 forward 사용 했는데 break사용해서 밑에서 또 forward탐
									request.setAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
									path = "proj/login.jsp";
								}
								break;
			}
			
			case "boardList": {
							    int page = 1; //1로 줘서 case "boardList" 타면 1페이지로 감
							    //페이지 클릭 시 ?cmd=boardList&page=3
							    //request.getParameter("page") 코드가 문자열 "3"반환 Integer로 int 3 확정
							    if (request.getParameter("page") != null) {
							        page = Integer.parseInt(request.getParameter("page"));
							    }
							    //isNotice = 'Y' 인 관리자 공지글만 가져오는 서비스 호출 페이지 필요없음
							    List<BoardVO> noticeList = boardService.getNoticeList();
							    //현재 페이지에 해당하는 일반 게시글 목록 내부에서 시작 글 번호, 끝 글 번호(LIMIT/ROWNUM 계산)
							    List<BoardVO> postList = boardService.getBoardList(page);
							    //페이지네이션 정보 전용 객체
							    PageMaker pageMaker = boardService.getPageMaker(page);
				
							    request.setAttribute("noticeList", noticeList);
							    request.setAttribute("postList", postList);
							    request.setAttribute("pageMaker", pageMaker);
				
							    request.getRequestDispatcher("proj/allList.jsp").forward(request, response);
							    return; //break주면 switch문만 종료 또 doget() forward()를 해 오류남 path만 설정할 때 break를 쓰자
							    		//return주면 현재 매소드 즉시 종료 뒤 코드는 실행 안됨
				
			}


		
		}
		

		if(path != null) {
		    request.getRequestDispatcher(path).forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





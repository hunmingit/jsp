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
									//sql에서 데이터 넣을때도 암호값으로 넣어야한다. 그냥 admin/admin 넣었다가 개고생함 비밀번호 암호화 했다면 기억!
									//근데 왜 또 오류가.. -> boardList에서 forward 사용 했는데 break사용해서 밑에서 또 forward탐
									request.setAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
									path = "proj/login.jsp";
								}
								break;
			}
			
			case "boardList": {
							    int page = 1; //1로 줘서 case "boardList" 타면 1페이지로 감
							    HttpSession session = request.getSession();
							    ProjVO loginVO = (ProjVO) session.getAttribute("returnVO");
				
							    if (loginVO == null) {
							        response.sendRedirect("ProjController?cmd=loginPage");
							        return;
							    }
							    int todayLike = boardService.todayLikeCount(loginVO.getpId());
							    request.setAttribute("todayLike", todayLike);			    
							    //페이지 클릭 시 ?cmd=boardList&page=3
							    //request.getParameter("page") 코드가 문자열 "3"반환 Integer로 int 3 확정
							    if (request.getParameter("page") != null) {
							        page = Integer.parseInt(request.getParameter("page"));
							    }
							    //isNotice = 'Y' 인 관리자 공지글만 가져오는 서비스 호출 페이지 필요없음
							    List<BoardVO> noticeList = boardService.NoticeList();
							    //현재 페이지에 해당하는 일반 게시글 목록 내부에서 시작 글 번호, 끝 글 번호(LIMIT/ROWNUM 계산)
							    List<BoardVO> postList = boardService.BoardList(page);
							    //페이지네이션 정보 전용 객체
							    PageMaker pageMaker = boardService.PageMaker(page);
				
							    request.setAttribute("noticeList", noticeList);
							    request.setAttribute("postList", postList);
							    request.setAttribute("pageMaker", pageMaker);
							    
				
							    request.getRequestDispatcher("proj/allList.jsp").forward(request, response);
							    return; //break주면 switch문만 종료 또 doget() forward()를 해 오류남 path만 설정할 때 break를 쓰자
							    		//return주면 현재 매소드 즉시 종료 뒤 코드는 실행 안됨				
			}
			
			case "writePage": {	
								//단순 화면 이동 그래서 break사용
							    path = "proj/write.jsp";
							    break;
								
			}
			
			case "write": 	  {	
								//리턴vo는 case "login" 에서 로그인 성공 시 담은 사용자 정보
								//login에서 session에 담아둠
								HttpSession session = request.getSession();
								//그래서 이렇게 꺼낼 수 있다.
								ProjVO loginVO = (ProjVO) session.getAttribute("returnVO");
								//그래서 뭐함? 로그인 했는지 확인 null이면 로그인 페이지로 이동
								if(loginVO == null) {
									response.sendRedirect("ProjController?cmd=loginPage");
									return;
								}
								//jsp에서 보낸 데이터 수집 write.jsp의 <form> 안 input들의 name들
								String title = request.getParameter("title");
								String content = request.getParameter("content");
								String category = request.getParameter("category");
								//checkbox는 체크하지 않으면 request로 넘어오지 않음(null) 그래서 기본값을 n으로
								String isNotice = request.getParameter("isNotice");
								if (isNotice == null) isNotice = "N"; //체크 안하면 null
								
								BoardVO vo = new BoardVO();
								vo.setTitle(title);
								vo.setContent(content);
								vo.setCategory(category);
								vo.setIsNotice(isNotice);
								//세션에 저장된 로그인 정보를 받는다 
								//왜 request로 안받나? -> 사용자가 html 수정 해서 계정을 admin으로 바꿀 수 있음 보안 취약
								vo.setWriter(loginVO.getpId());
								
								boardService.writeBoard(vo);
								//redirect쓰는 이유? 새 글 등록 후 새 요청. 새로고침 시 중복 등록 방지
								response.sendRedirect("ProjController?cmd=boardList");
								return;								
								
			}
			
			case "view":	 {
								//게시글 번호 받기 어디서 받았지? allList.jsp -> onclick="location.href='ProjController?cmd=view&bIdx=${post.bIdx}'">
								//게시판 목록 클릭 하게 되면 밑 코드로 bIdx를 받아온다.
								//request.getParameter("bIdx") ->bIdx라는 이름의 값을 문자열로 꺼내라 그래서 int로 바꿔줌
								int bIdx = Integer.parseInt(request.getParameter("bIdx"));
								
								//조회수 증가 새로고침 했을때 조회수 안늘어나게 or 같은 계정일 경우 조회수 안늘어나게 추가해야함
								boardService.increaseViews(bIdx);
								
								//게시글 상세 조회 (bIdx로 게시글 가져오고 board에 담자)
								BoardVO board = boardService.boardById(bIdx);
								
								//request에 데이터 담아 (왜 session안씀? 게시글은 1회성 화면 출력 데이터 → request 사용
								//session은 로그인 상태처럼 장기간 유지 데이터에 사용)
							    request.setAttribute("board", board);
							    //jsp에 전달
							    request.getRequestDispatcher("proj/view.jsp")
							           .forward(request, response);
							    return;
			}
			
			
			case "editPage": {	
								//로그인 정보 확인
								HttpSession session = request.getSession();
								ProjVO loginVO = (ProjVO) session.getAttribute("returnVO");
								//로그인 하지 않았다면 로그인 페이지로 이동
								if(loginVO == null) {
									response.sendRedirect("ProjController?cmd=loginPage");
									return;
								}
								//글 번호 들고 가서 수정할 글 가져와서 글 작성자와 로그인 정보 확인
								int bIdx = Integer.parseInt(request.getParameter("bIdx"));
								BoardVO board = boardService.boardById(bIdx);								
								//작성자거나 관리자인지 확인
								if (!loginVO.getpId().equals(board.getWriter()) && !"ADMIN".equals(loginVO.getRole())) {
									response.sendRedirect("ProjController?cmd=view&bIdx=" + bIdx);
									return;
								}
								
							    request.setAttribute("board", board);
							    request.getRequestDispatcher("proj/edit.jsp")
							           .forward(request, response);
							    return;
								
								
			}
			
			case "edit":	{	
								HttpSession session = request.getSession();
								ProjVO loginVO = (ProjVO) session.getAttribute("returnVO");
								
								if(loginVO == null) {
									response.sendRedirect("ProjController?cmd=loginPage");
									return;
								}
								int bIdx = Integer.parseInt(request.getParameter("bIdx"));
								
								String title = request.getParameter("title");
								String content = request.getParameter("content");
								String category = request.getParameter("category");
								
								String isNotice = request.getParameter("isNotice");
								//왜 안넘어올까...mapper에서 bIdx -> bidx 로 오타
								System.out.println("title = " + title);
								System.out.println("content = " + content);
								System.out.println("category = " + category);
								System.out.println("isNotice = " + isNotice);
								
								if (isNotice == null) isNotice = "N"; //체크 안하면 null
								
								BoardVO vo = new BoardVO();
								vo.setbIdx(bIdx);
								vo.setTitle(title);
								vo.setContent(content);
								vo.setCategory(category);
								vo.setIsNotice(isNotice);
								
								boardService.editBoard(vo);
								
								response.sendRedirect("ProjController?cmd=view&bIdx=" + bIdx);
								return;	

			}
			
			case "delete":	{
								HttpSession session = request.getSession();
								ProjVO loginVO = (ProjVO) session.getAttribute("returnVO");
								
								if(loginVO == null) {
									response.sendRedirect("ProjController?cmd=loginPage");
									return;
								}
								//글번호 들고가서 이 번호 글 삭제하기
								int bIdx = Integer.parseInt(request.getParameter("bIdx"));
								boardService.deleteBoard(bIdx);
								
								response.sendRedirect("ProjController?cmd=boardList");
								return;
													
			}
			
			case "like":  	{
							    HttpSession session = request.getSession();
							    ProjVO loginVO = (ProjVO) session.getAttribute("returnVO");
				
							    if (loginVO == null) {
							        response.sendRedirect("ProjController?cmd=loginPage");
							        return;
							    }
							    
							    int bIdx = Integer.parseInt(request.getParameter("bIdx"));
							    //이 로그인 계정 몇 번 좋아요 눌렀는지 확인

							    //좋아요 누르기
							    //외 않되 false 잘넘어옴 false일때 알럿 
							    //view.jsp 에 msg를 안뿌림 ;
							    boolean success = boardService.likeBoard(loginVO.getpId(), bIdx);
							    System.out.println("success = " + success);
							    //false면 session에 메시지 저장
							    if (!success) {
							        session.setAttribute("msg", "오늘 추천 가능 횟수(3회)를 모두 사용했습니다.");
							        
							    }
							    
							    response.sendRedirect("ProjController?cmd=view&bIdx=" + bIdx);
							    return;
			}
			
//			case "serch":	{
//				
//			}
			//case "category":
			
			
			
			
			
			
			
			
			
			
			
		}
		

		if(path != null) {
		    request.getRequestDispatcher(path).forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





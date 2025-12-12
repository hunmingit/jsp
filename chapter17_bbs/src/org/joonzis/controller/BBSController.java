package org.joonzis.controller;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joonzis.model.FileDownload;
import org.joonzis.service.BBSService;
import org.joonzis.service.BBSServiceImpl;
import org.joonzis.vo.BVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.joonzis.model.Criteria;
import org.joonzis.model.PageDTO;


@WebServlet("/BBSController")
public class BBSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BBSController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//파일 업로드
		String realPath = 
				request.getServletContext().getRealPath("/upload"); //업로드 경로
				MultipartRequest mr = null;


		// 분기 판단 cmd
		String cmd = request.getParameter("cmd");
		if(cmd == null) {
			//파일 업로드 시 일반 request가 아닌 multipartRequest로 받아야 하기 때문에 
			//mr 객체 생성해서 파라미터를 받는다
			 mr = new MultipartRequest(
				request, realPath, 1024*1024*10, "utf-8", 
				new DefaultFileRenamePolicy());

			 cmd = mr.getParameter("cmd");
		}
		boolean isForward = true;
		String path = "";
		BBSService bservice = new BBSServiceImpl();
		List<BVO>list = null;
		BVO bvo = null;
		HttpSession  session = request.getSession();
		String open = null; // 세션 정보 저장용 변수

		//페이지 처리를 위한 cri 파라미터 변수
		String pageNum = "";
		String amount = "";
		int parsePageNum = 0;
		int parseAmount = 0;


		switch (cmd) {
		//목록 전체보기
		case "allList":
			//전체 목록으로 나왔을때 세션 초기화 해서 hit수 증가 가능
			open = (String) session.getAttribute("open");
			if (open != null) { // 세션에 open 정보가 있으면
				session.removeAttribute("open"); // 세션 정보 제거
			}
			//페이지 처리 파라미터 받기	
			pageNum = request.getParameter("pageNum");
			amount = request.getParameter("amount");

			if(pageNum != null && amount != null) {
				//파라미터를 잘 전달 받으면 적용
				parsePageNum = Integer.parseInt(pageNum);
				parseAmount = Integer.parseInt(amount);
			} else {
				//파라미터를 전달 받지 못하면 기본 값으로 초기화
				parsePageNum = 1;
				parseAmount = 5;
			}
			Criteria cri = new Criteria(parsePageNum, parseAmount);

			//페이징 게시글 가져오기
			//list = bservice.getList();
			list = bservice.getListWithPaging(cri);

			//전체 게시글 수 가져오기 pageDTO에서 사용할 용도인 total
			int total = bservice.getTotalRecordCount();			
			//id : total _count_of_bbs

			//PageDTO 객체 생성
			PageDTO pdto = new PageDTO(cri, total);

			//게시글 및 페이징 객체 request객체로 전달
			request.setAttribute("list", list);
			request.setAttribute("pageMaker", pdto);
			

			path = "bbs/allList.jsp";
			break;
		//게시글 작성 페이지
		case "insertBBSPage":
			path = "bbs/insert_page.jsp"; //서블릿과 jsp는 같은 형제라인 둘 다 바로 상대경로로 접근 가능  
			break;						  //jsp는 forward로 접근해야함, 서블릿은 insert와 같이 리다이렉트 처리
		//게시글 작성
		case "insertBBS":
			//파라미터들을 꺼내서 vo에 저장
			//vo를 db까지 전달해야함
			bvo = new BVO();
			bvo.setWriter(mr.getParameter("writer"));
			bvo.setTitle(mr.getParameter("title"));
			bvo.setContent(mr.getParameter("content"));
			bvo.setPw(mr.getParameter("pw"));
			//bvo.setIp(request.getRemoteAddr()); //IPv6 
			bvo.setIp(Inet4Address.getLocalHost().getHostAddress()); //IPv4

			//첨부 파일 유무에 따라서 fileName 값을 설정
			if(mr.getFile("filename") != null) {
				bvo.setFilename(mr.getFilesystemName("filename"));
			}else{
				bvo.setFilename("");
			}
			//InsertBBS() 메소드를 이용하여 실행
			//mapper와의 연계 ID = insertBBS
			int result = bservice.InsertBBS(bvo); //int 값이 돌아옴 일단 result로 받아 이제부터 이걸로 뭐할까?
			if(result > 0) {
				path = "BBSController?cmd=allList";
				isForward = false; //리다이렉트 dml 후에 보여줄 화면을 list로 간다면 
			}					//기존 list로 가는 서블릿 경로를 리다이렉트 해준다.
			break;
		
		case "view":
			// 게시글 상세보기
			int b_idx = Integer.parseInt(request.getParameter("b_idx"));
			//1. 서비스 메소드를 통해 데이터 받아오기 -getBBS
			//2. mapper와 연동 id : bbs_by_idx
			//3. 가져온 데이터 session에 저장 저장 이름은 "bvo"
			//4. bba/view.jsp로 이동
			//* view.jsp 및 view.css파일은 드라이브에 추가됨*/			
			bvo = bservice.getBBS(b_idx);

			// 조회수 증가
			// 1.상세 페이지에 접근시 세션에 정보를 저장
			// 2.세션이 만료되기 전까지 조회수의 증가를 더 이상 하지 않는다.
			// (새로고침 등으로 조회 수 증가 방지)
			// 3.메인 화면(allList.jsp)로 이동하게 되면 세션을 만료
			open = (String) session.getAttribute("open");
			if (open == null) { // 세션에 open 정보가 없으면 조회수 올라감
				session.setAttribute("open", "yes"); // 세션에 open 정보 저장
				int hit = bvo.getHit() + 1; // 조회수 1 증가
				bvo.setHit(hit);
				bservice.updateHit(bvo); 
				// 서비스에 조회수 증가 메소드 추가
				// mapper id : update_hit
			}
			
			session.setAttribute("bvo", bvo);
			path = "bbs/view.jsp"; //forward 해줌
			break;	
			
		//게시글 수정 페이지 이동
		case "updatePage" : 
			
			path = "bbs/update_page.jsp";
			break;

		//게시글 수정하기
		case "update" :
			//메소드 이름 updateBBS
			//mapper id bbs_update
			//수정 완료 후 해당 글 상세 페이지 이동
			bvo = new BVO();
			
			int b_idx_update = Integer.parseInt(mr.getParameter("b_idx"));
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");

			bvo.setB_idx(b_idx_update);
			bvo.setTitle(title);
			bvo.setContent(content);

			//새 첨부 파일
			File newFile = mr.getFile("filename");
			//기존 첨부 파일
			String oldFile = mr.getParameter("oldfile");

			if(newFile != null) { // 새 파일이 있으면
				if(oldFile != null) {
					//기존 파일 삭제
					File removeFile = new File(realPath + "/" + oldFile);
					if(removeFile.exists()) { //기존 첨부 파일 유무 확인
						removeFile.delete(); // 기존 첨부 파일 삭제
					}
				}
				bvo.setFilename(newFile.getName());
			}else{
				//새 파일 x
				if(oldFile != null) {
					//기존 파일 O
					bvo.setFilename(oldFile);
			}else {
				//기존 첨부 파일 x, 새 파일 x
				bvo.setFilename("");
			}
			}
			bservice.updateBBS(bvo);
			int pageNum_update = Integer.parseInt(request.getParameter("pageNum"));

			path ="BBSController?cmd=view&b_idx="+pageNum_update+"&amount=5";
			isForward = false; //리다이렉트 처리
			break;
				
			// bvo.setTitle(mr.getParameter("title"));
			// bvo.setContent(mr.getParameter("content"));
			// bvo.setB_idx(Integer.parseInt(mr.getParameter("b_idx")));
		case "download" :
				FileDownload fd = new FileDownload();
				fd.download(request, response);
			break;

		case "removeBBS" :
			// 게시글 삭제하기
			// 컨트롤로 전달 할 파라미터 == b.idx /cmd
			// 서비스의 메소드 이름 removeBBS
			// 매퍼 id delete_bbs
			// 삭제 완료 후 allList로 이동
			int b_idx_remove = Integer.parseInt(request.getParameter("b_idx"));
			bservice.removeBBS(b_idx_remove); //리턴되는값 int가 있지만 사용처가 없기 때문에 굳이 받지 않는다.
			//기존 페이지로 돌아가기
			int pageNum_remove = Integer.parseInt(request.getParameter("pageNum"));
			
			path = "BBSController?cmd=allList&pageNum="+pageNum_remove+"&amount=5"; 
			isForward = false;
		}	


		

		
		if(isForward) {
			request
			.getRequestDispatcher(path)
			.forward(request, response);
		//리다이렉트 처리 
		}else {
			response.sendRedirect(path);
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





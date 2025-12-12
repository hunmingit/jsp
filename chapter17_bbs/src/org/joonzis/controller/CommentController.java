package org.joonzis.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.Comment;

import org.joonzis.service.CommentService;
import org.joonzis.service.CommentServiceImpl;
import org.joonzis.vo.CVO;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		// 분기 판단 cmd
		String cmd = request.getParameter("cmd");

		//비동기 처리를 위한 객체들
		ObjectMapper objectMapper = null;
		String jsonString = null;
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();

		//DB 처리를 위한 객체들
		CVO cvo = null;
		CommentService cservice = new CommentServiceImpl();

		switch (cmd) {
			case "insertComment": //댓글 등록
				cvo = new CVO();
				cvo.setWriter(request.getParameter("writer"));
				cvo.setContent(request.getParameter("content"));
				cvo.setPw(request.getParameter("pw"));
				cvo.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				cvo.setIp(Inet4Address.getLocalHost().getHostAddress());

				//메소드 insertComment(cvo) 
				//아이디 insert_comment로 저장
				cservice.insertComment(cvo);
				obj.put("result", "success");				
				break;
			//전체 댓글 목록 보기	
			case "commList":
				int b_idx = 
				Integer.parseInt(request.getParameter("b_idx"));
				//cservice.getCommList 메소드 호출
				//cList 라는 참조 변수에 저장
					
				List<CVO> cList = cservice.getCommList(b_idx);
				objectMapper = new ObjectMapper();
				jsonString = objectMapper.writeValueAsString(cList);
				
				System.out.println(jsonString);
							
				obj.put("cList", jsonString);
			break;
			//댓글 삭제
			case "removeComment":
				int c_idx = 
				Integer.parseInt(request.getParameter("c_idx"));
				cservice.removeComment(c_idx); 
				
				int result = cservice.removeComment(c_idx);
				obj.put("result", result);

				break;
		}
		out.print(obj);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

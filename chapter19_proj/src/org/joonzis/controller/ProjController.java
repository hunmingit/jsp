package org.joonzis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ProjController")
public class ProjController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProjController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
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

								MemberService service = MemberServiceImpl.getInstance();
								boolean exists = service.isExistId(pId);

								response.setContentType("text/plain; charset=utf-8");
								response.getWriter().print(exists ? "fail" : "ok");
								return; 
			}
			
			case "join": {
    						String pId = request.getParameter("pId");
    						String pPw = request.getParameter("pPw");

							// Service 호출
							ProjService service = ProjServiceImpl.getInstance();
							boolean result = service.join(pId, pPw);

							if(result) {
								request.setAttribute("msg", "회원가입 성공! 로그인 해주세요.");
								path = "proj/login.jsp";
							} else {
								request.setAttribute("msg", "회원가입 실패 (중복 아이디)");
								path = "proj/login.jsp";
							}
							break;
			}


		
		}
		
		request
			.getRequestDispatcher(path)
			.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





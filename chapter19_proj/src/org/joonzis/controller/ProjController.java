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

		}
		
		request
			.getRequestDispatcher(path)
			.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





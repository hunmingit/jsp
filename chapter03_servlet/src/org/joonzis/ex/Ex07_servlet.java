package org.joonzis.ex;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/pagemove") //이름 변경
public class Ex07_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex07_servlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");	
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
		
		request.getRequestDispatcher("Ex07_output.jsp").forward(request, response); //output으로 보냄
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

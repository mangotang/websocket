package com.snb.websocket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserNameServlet
 */
@WebServlet("/userName")
public class UserNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget");
		response.setContentType("text/html");
		
		PrintWriter p = response.getWriter();
		HttpSession session = request.getSession(true);
		String username = request.getParameter("username");
		session.setAttribute("username", username);
		if (null != username) {
		    response.sendRedirect(request.getContextPath() + "/websocket.jsp");
		}
		
		System.out.println("done");
	}

}

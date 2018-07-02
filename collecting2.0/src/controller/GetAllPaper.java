package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.User;
import tool.PaperUtil;

/**
 * Servlet implementation class GetAllPaper
 */
@WebServlet("/GetAllPaper")
public class GetAllPaper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		int userId = user.getUserId();
		String data = PaperUtil.getData(userId);
		String type = request.getParameter("type");
		if(type==null) {
			request.getRequestDispatcher("allPaper.jsp").forward(request, response);
		}else if(type.equals("getData")){
			response.getWriter().write(data);
		}else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}

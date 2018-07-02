package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.PaperUtil;


@WebServlet("/ShowResult")
public class ShowResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uk = request.getParameter("uk");
		String column = PaperUtil.getColumn(uk);
		String data = PaperUtil.getPaperSubmitData(uk);
		String type = request.getParameter("type");
		if(type==null) {
			request.setAttribute("column", column);
			request.setAttribute("uk", uk);
			request.getRequestDispatcher("paperResult.jsp").forward(request, response);
		}else if(type.equals("getPaperSubmitData")) {
			response.getWriter().write(data);
		}else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}

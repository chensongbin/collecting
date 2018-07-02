package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.PaperUtil;

/**
 * Servlet implementation class PaperOperator
 */
@WebServlet("/PaperOperator")
public class PaperOperator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type.equals("changePaperStatus")) {
			String uk = request.getParameter("uk");
			String status = request.getParameter("status");
			if(PaperUtil.changePaperStatusByUK(uk, status)) {
				response.getWriter().write("success");
			}else {
				response.getWriter().write("DB error!");
			}
		}else if(type.equals("deletePaper")) {
			String uk = request.getParameter("uk");
			PaperUtil.deleteByUK(uk);
		}else if(type.equals("deleteRow")) {
			String uk = request.getParameter("uk");
			String id = request.getParameter("id");
			if(PaperUtil.deleteRowByUK(uk, id)) {
				response.getWriter().write("success");
			}else {
				response.getWriter().write("DB error!");
			}
		}else {
			request.getRequestDispatcher("/error.jsp");
		}
	}

}

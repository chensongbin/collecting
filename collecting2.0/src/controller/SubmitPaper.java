package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.PaperUtil;

/**
 * Servlet implementation class SubmitPaper
 */
@WebServlet("/SubmitPaper")
public class SubmitPaper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(PaperUtil.insertRow(request) == 0) {
			response.getWriter().write("提交失败！");
		}else {
			response.getWriter().write("提交成功！");
		}
	}

}

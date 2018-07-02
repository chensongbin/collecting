package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.Paper;
import tool.PaperUtil;
/**
 * 
 * @author Saber
 * 问卷展示
 * 
 */

@WebServlet("/ShowPaper")
public class ShowPaper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uk = request.getParameter("uniqueKey");
		request.setAttribute("uniqueKey", uk.trim());
		Paper paper = PaperUtil.getPaperByUk(uk.trim());
		if(paper.getFlag() == 0) {	
			request.setAttribute("msg", "该问卷已经暂停收集！");
			RequestDispatcher rd = request.getRequestDispatcher("tips/msg.jsp");
			rd.forward(request, response);
		} else if(paper.getFlag() == 1) {
			request.setAttribute("paperTitle", paper.getTitle());
			request.setAttribute("paperHTML", paper.getHtml());
			RequestDispatcher rd = request.getRequestDispatcher("paper.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("msg", "没有该问卷，请确认问卷识别码准确无误！！");
			RequestDispatcher rd = request.getRequestDispatcher("tips/msg.jsp");
			rd.forward(request, response);
		}
	}
}

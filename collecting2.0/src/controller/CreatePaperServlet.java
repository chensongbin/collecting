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
 * 创建问卷控制器
 */
@WebServlet("/CreatePaperServlet")
public class CreatePaperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paperName = request.getParameter("paperName");
		String paperHTML = request.getParameter("paperHTML");
		String paperStatus = request.getParameter("paperStatus");
		String createDate = request.getParameter("createDate");
		String createTimeStamp = request.getParameter("createTimeStamp");
		String titles = request.getParameter("titles");
		int userId = ((User)request.getSession().getAttribute("user")).getUserId();
		PaperUtil.saveToDatabase(userId ,paperName, paperHTML, paperStatus, createDate, createTimeStamp, titles);
		
		/*System.out.println(paperName);
		System.out.println(paperHTML);
		System.out.println(paperStatus);
		System.out.println(createDate);
		System.out.println(createTimeStamp);
		System.out.println(titles);*/
		
		
	}

}

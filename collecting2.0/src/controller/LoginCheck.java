package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import javabean.User;

/**
 * 登录控制器
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String userName = request.getParameter("user");
		String pw = request.getParameter("pw");
		if(userName==null || pw==null) response.getWriter().println("userName is null or password is null");
		User user = new User(userName, pw);
		
		// System.out.println("登录调试"UserDao.isExist(user));
		
		if(UserDao.isExist(user)) {
			request.getSession().setAttribute("user", user);
			response.getWriter().print("success");
		}else {
			response.getWriter().print("密码错误或用户名不存在");
		}
	}
}

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
 * 注册控制器
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String registerUser = request.getParameter("registerUser");
	    String registerPw = request.getParameter("registerPw");
	    String registerEmail = request.getParameter("registerEmail");
	    User user = new User(registerUser, registerPw, registerEmail);
	    if(UserDao.isUserNameExist(user)) {
	    	response.getWriter().print("该用户名已被注册！");
	    }else{
	    	if(UserDao.addUser(user)) {
	    		response.getWriter().print("success");
	    	}else{
	    		response.getWriter().print("注册失败！");
	    	}
	    }
	}
}

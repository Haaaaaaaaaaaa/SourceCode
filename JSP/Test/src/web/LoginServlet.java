package web;
import java.sql.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.User;
import service.UserService;

public class LoginServlet extends HttpServlet {
@Override
	public void init() throws ServletException {
	super.init();
}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	String username = req.getParameter("username");
	String password = req.getParameter("password");
//	����JavaBean
	User bean=new User();
	bean.setUsername(username);
	bean.setPassword(password);
	UserService service = new UserService();
//	�ж��Ƿ��¼�ɹ�
	if(service.checkLogin(bean)){
//		�ض���
//		resp.sendRedirect("../ok.jsp");
//		ת��
		RequestDispatcher dis=req.getRequestDispatcher("ok.jsp"); 
		dis.forward(req, resp); 
	}else{
//		�ض���
		resp.sendRedirect("login.jsp");	
	}
}

@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
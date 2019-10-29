package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserService;

public class AddServlet extends HttpServlet {
@Override
public void init() throws ServletException {
	super.init();
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	String username = req.getParameter("username");
	String password = req.getParameter("password");
//	���ֵ
	System.out.println(username);
	System.out.println(password);
	System.out.println("addservelt��ȡ�ɹ�");
//	д��JavaBean
	User bean=new User();
	HttpSession session=req.getSession(true);
	session.setAttribute("bean",bean);
	bean.setUsername(username);
	bean.setPassword(password);
	UserService addservice = new UserService();
//	�ж��Ƿ�ע��ɹ�
	if(addservice.add(bean)){	
//		ת��
		RequestDispatcher dis=req.getRequestDispatcher("list.jsp"); 
		dis.forward(req, resp); 
	}else{
//		�ض���
		resp.sendRedirect("register.jsp");	
	}
	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
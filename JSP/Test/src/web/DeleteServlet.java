package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserService;

public class DeleteServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		super.init();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
//        int id = Integer.parseInt(req.getParameter("id"));
//        String name = req.getParameter("username");
//        String word = req.getParameter("password");

//        User user = new User();
//        HttpSession session=req.getSession(true);
//    	session.setAttribute("bean",user);
//        user.setId(id);
//        user.setUsername(name);
//        user.setPassword(word);
    	
        int id = Integer.parseInt(req.getParameter("id"));
        User user = null;
        UserService userService = new UserService();
        try {
            user = userService.Search(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
      HttpSession session=req.getSession(true);
  	  session.setAttribute("bean",user);
        try {
            if (userService.Delete(user)){
            	RequestDispatcher dis=req.getRequestDispatcher("list.jsp"); 
        		dis.forward(req, resp); 
            }else{
                resp.sendRedirect("edit.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}

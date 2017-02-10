package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.StuffDao;
import com.erp.entry.StuffEntry;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/userList")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG="UserListServlet";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type  = request.getParameter("type");
		List<StuffEntry> userList = StuffDao.getAllStuffWithPwd(type);
		request.setAttribute("userList", userList);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理添加新用户
		String account =new String( request.getParameter("account").getBytes("ISO8859-1"),"utf-8");
		String pwd= request.getParameter("pwd");
		String name=new String (request.getParameter("name").getBytes("ISO8859-1"),"utf-8");
		String type=request.getParameter("type");
		int actionType = Integer.parseInt(request.getParameter("actiontype"));
		synchronized (this) {
			if(actionType == 1){
				StuffDao.insert(account, pwd, name, type);				
			}else if(actionType == 2){
				StuffDao.updateStuff(account, pwd, name, type);
			}
		}
		List<StuffEntry> userList = StuffDao.getAllStuffWithPwd(type);
		request.setAttribute("userList", userList);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(request, response);
	}

}

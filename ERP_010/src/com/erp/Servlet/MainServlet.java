package com.erp.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartClassDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.StuffEntry;
import com.erp.Log.Log;


@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String TAG = MainServlet.class.getName();

    
    public MainServlet() {
        super();

    }

    /**
     * 判断是否已经登录
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Log.logInfo(TAG, "doGet");
		
		StuffEntry stuff = (StuffEntry) request.getSession().getAttribute("stuff");
		if(stuff == null){
			response.sendRedirect("Login");
		}else{
			switch (stuff.getType()) {
			case "0":  //监督者
				List<DepartClassEntry> departClassEntries = DepartClassDao.getAllDepartClass(true);
				request.setAttribute("departClassEntries", departClassEntries);
				System.out.println(departClassEntries);
				request.setAttribute("stuff", stuff);
				request.getRequestDispatcher("/WEB-INF/jsp/jianduzhe.jsp").forward(request, response);
				break;
			case "1": //管理者
				departClassEntries = DepartClassDao.getAllDepartClass(true);
				request.setAttribute("departClassEntries", departClassEntries);
				request.getRequestDispatcher("/WEB-INF/jsp/guanlizhe.jsp").forward(request, response);
				break;
			case "2":
				
				break;
			default:
				response.sendRedirect("Login");
				break;
			}
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

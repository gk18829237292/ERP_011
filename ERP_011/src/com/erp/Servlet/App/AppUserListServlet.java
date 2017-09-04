package com.erp.Servlet.App;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartDao;
import com.erp.Dao.StuffDao;
import com.erp.Dao.Stuff_1_DepartDao;
import com.erp.Entry.DepartEntry;
import com.erp.Entry.StuffEntry;
import com.erp.utils.StringUtils;

/**
 * Servlet implementation class AppUserListServlet
 */
@WebServlet("/api/userList")
public class AppUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AppUserListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionType = request.getParameter("actiontype");
		String type = request.getParameter("type");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String telNum = request.getParameter("telNum");
		String[] departIds = request.getParameter("departId").split(";");
		List<DepartEntry> departEntries = DepartDao.getAllDepart();
		List<StuffEntry> stuffEntries = StuffDao.getAllStuffByType(type);
		
		if(departIds != null  && departIds.length > 0){
			switch (actionType) {
			case "0": //添加
				StuffDao.insert(account, password, name, telNum, type);
				Stuff_1_DepartDao.insert(account, departIds);
				break;
			case "1": //修改
				StuffDao.update(account, password, name, telNum, type);
				Stuff_1_DepartDao.delete(account);
				Stuff_1_DepartDao.insert(account, departIds);
				break;
			}
		}
		
	}

}

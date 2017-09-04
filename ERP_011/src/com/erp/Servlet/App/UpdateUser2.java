package com.erp.Servlet.App;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.StuffDao;
import com.erp.Dao.Stuff_1_DepartDao;
import com.erp.Dao.Stuff_DepartDao;
import com.erp.utils.StringUtils;


/**
 * Servlet implementation class UpdateUser2
 */
@WebServlet("/api/updateUser2")
public class UpdateUser2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUser2() {
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
		System.out.println("UpdateUser2.doPost()");
		String actionType = request.getParameter("actionType");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String telNum = request.getParameter("telNum");
		String type = request.getParameter("type");
		switch (actionType) {
		case "0": //添加
			switch (type) {
			case "1":
				String[] departIds = request.getParameter("departId").split(";");
				StuffDao.insert(account, password, name, telNum, type);
				Stuff_1_DepartDao.insert(account, departIds);
				break;
			case "2":
				String departId = request.getParameter("departId");
				StuffDao.insert(account, password, name, telNum, type);
				Stuff_DepartDao.insert(account, departId);
				break;
			case "3":
				departIds = request.getParameter("departId").split(";");
				StuffDao.insert_leader(account, password, name, telNum);
				Stuff_DepartDao.insert(account, departIds);
				break;
			}

			break;
		case "1": //修改
			switch (type) {
			case "1":
				String[] departIds = request.getParameter("departId").split(";");
				StuffDao.update(account, password, name, telNum, type);
				Stuff_1_DepartDao.delete(account);
				Stuff_1_DepartDao.insert(account, departIds);
				break;
			case "2":
				String departId = request.getParameter("departId");
				StuffDao.update(account, password, name, telNum, type);
				if(!Stuff_DepartDao.update(account, departId)){
					Stuff_DepartDao.insert(account, departId);
				}
				break;
			case "3":
				departIds = request.getParameter("departId").split(";");
				StuffDao.update(account, password, name, telNum, "2");
				Stuff_DepartDao.delete(account);
				Stuff_DepartDao.insert(account, departIds);
				break;
			}
			break;
		}
		
		AppUtils.sendSuccess(response);
	}

}

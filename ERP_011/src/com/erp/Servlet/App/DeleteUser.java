package com.erp.Servlet.App;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.erp.Dao.DepartDao;
import com.erp.Dao.StuffDao;
import com.erp.Entry.DepartEntry;
import com.erp.Entry.StuffEntry;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/api/deleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteUser.doPost()");
		String account = request.getParameter("account");
		String type = request.getParameter("type");
		StuffDao.deleteStuffByAccount(account, type);
		
		
		List<DepartEntry> departEntries = DepartDao.getAllDepart();
		System.out.println("type " + type);
		List<StuffEntry> stuffEntries = null;
		switch(type){
			case "1":
				stuffEntries = StuffDao.getAllStuffByType(type);
				break;
			case "2":
				 stuffEntries = StuffDao.getAllStuffByType_Depart(type);
				 break;
			case "3":
				 stuffEntries = StuffDao.getAllStuffByType_Depart_Leader("2");
				break;
		}
		
		for(StuffEntry entry:stuffEntries){
			entry.setType(type);
		}
				
		
		System.out.println(stuffEntries);
		response.setCharacterEncoding("utf-8");
		JSONObject jsonObject;
		try {
			jsonObject = StuffEntry.convertList2Json(stuffEntries);
			System.out.println(jsonObject.toString());
			response.getWriter().write(jsonObject.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}

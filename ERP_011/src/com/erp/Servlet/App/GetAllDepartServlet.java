package com.erp.Servlet.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationSessionCookieConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.DepartDao;
import com.erp.Dao.Stuff_1_DepartDao;
import com.erp.Dao.Stuff_DepartDao;
import com.erp.Dao.TaskDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.Entry.DepartEntry;
import com.erp.utils.JsonManager;


/**
 * Servlet implementation class GetAllTaskServlet
 */
@WebServlet("/api/getAllDepart")
public class GetAllDepartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetAllDepartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAllDepartServlet.doGet()");
		String account = request.getParameter("account");
		String type = request.getParameter("type");
		System.out.println("account : " + account +"  type: " + type);
		if(!type.equals("0"))return;
		List<DepartClassEntry>  entryList = DepartClassDao.getAllDepartClass(true);
		
		JSONArray jsonArray = new JSONArray();
		for(DepartClassEntry entry:entryList){
			try {
				jsonArray.put(entry.write2Json());
			} catch (JSONException e) {
				jsonArray = new JSONArray();
				e.printStackTrace();
			}
		}
	
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("task", jsonArray);
			System.out.println("result : " + jsonObject.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

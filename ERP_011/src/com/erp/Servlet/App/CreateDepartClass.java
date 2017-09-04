package com.erp.Servlet.App;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erp.Dao.DepartClassDao;
import com.erp.Entry.DepartClassEntry;
import com.erp.utils.StringUtils;

/**
 * Servlet implementation class createDepartClass
 */
@WebServlet("/api/createDepartClass")
public class CreateDepartClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDepartClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionType = request.getParameter("actionType");
		String departClassName =request.getParameter("departClassName");
		switch (actionType) {
		case "0":
			DepartClassDao.insert(departClassName);
			break;
		case "1":
			String departClassId = request.getParameter("departClassId");
			DepartClassDao.update(departClassName, departClassId);
			break;
		}
		
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

}

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
import com.erp.Dao.DepartDao;
import com.erp.Entry.DepartClassEntry;


/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/api/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
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
		System.out.println("DeleteServlet.doPost()");
		String actiontype = request.getParameter("actionType");//0 departClass 1 depart
		String id = request.getParameter("id");
		switch (actiontype) {
		case "0"://departClass
			 DepartClassDao.delete(id);
			break;
		case "1":
			DepartDao.delete(id);
			break;
		default:
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

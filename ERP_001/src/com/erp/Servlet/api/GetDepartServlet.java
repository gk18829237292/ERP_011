package com.erp.Servlet.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.erp.Dao.DepartmentDao;
import com.erp.entry.DepartmentEntry;
import com.erp.entry.ResponseJson;
import com.erp.json.DepartJson;

/**
 * Servlet implementation class GetDepartServlet
 */
@WebServlet("/api/getDepart")
public class GetDepartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDepartServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		
		response.setCharacterEncoding("utf-8");
		List<DepartmentEntry> departs = DepartmentDao.getAllDepartment();
		
		try {
			JSONObject json = ResponseJson.getCurrentJson();
			json.put("departs", DepartJson.convert(departs));
			System.out.println(json.toString());
			response.getWriter().append(json.toString());
		} catch (JSONException e) {
			response.getWriter().append(ResponseJson.getErrorJson().toString());
		}
		
	}

}

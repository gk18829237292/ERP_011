package com.erp.Servlet.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.erp.Dao.AssignmentDao;
import com.erp.Dao.DepartmentDao;
import com.erp.entry.ResponseJson;

/**
 * Servlet implementation class DeleteDepart
 */
@WebServlet("/api/deleteDepart")
public class DeleteDepart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDepart() {
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
		String departId=  request.getParameter("departId");
		
		JSONObject json = null;
		if(departId != null){
			json = ResponseJson.getErrorJson();
			try {
				json.put("msg", "删除失败，该项目可能不存在");
			} catch (JSONException e) {
				
			}
		}else{
			DepartmentDao.deleteDepart(departId);
			json = ResponseJson.getCurrentJson();
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(json.toString());
		
		
		
	}

}

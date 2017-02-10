package com.erp.Servlet.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.erp.Dao.DepartmentDao;

/**
 * Servlet implementation class CreateDepartServlet
 */
@WebServlet("/api/CreateDepart")
public class CreateDepartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDepartServlet() {
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
		System.out.println("post in");
		String departName = request.getParameter("departName");
		JSONObject jsonObject = new JSONObject();
		synchronized (this) {
			if(DepartmentDao.query(departName)){
				try {
					jsonObject.put("type", 2);
					jsonObject.put("msg", "部门已存在，请检查");
				} catch (JSONException e) {
				}
			}else{
				DepartmentDao.insert(departName);
				try {
					jsonObject.put("type", 1);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(jsonObject.toString());
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(jsonObject.toString());
		
	}

}

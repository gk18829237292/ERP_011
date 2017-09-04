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
import com.erp.Entry.DepartEntry;

/**
 * Servlet implementation class GetAllDepart2Servlet
 */
@WebServlet("/api/getAllDepart2")
public class GetAllDepart2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllDepart2Servlet() {
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
		List<DepartEntry> departEntries = DepartDao.getAllDepart();
		response.setCharacterEncoding("utf-8");
		JSONObject jsonObject = null;
		try {
			jsonObject = DepartEntry.convert2ListJson(departEntries);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		response.getWriter().write(jsonObject.toString());
	}

}

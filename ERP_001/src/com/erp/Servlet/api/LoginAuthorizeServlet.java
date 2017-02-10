package com.erp.Servlet.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.erp.Dao.StuffDao;
import com.erp.Log.Log;
import com.erp.entry.StuffEntry;
import com.erp.utils.JsonManager;

/**
 * Servlet implementation class LoginAuthorizeServlet
 */
@WebServlet("/api/LoginAuthorize")
public class LoginAuthorizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG ="LoginAuthorizeServlet";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAuthorizeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post in");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		Log.logInfo(TAG, account);
		Log.logInfo(TAG, password);
		Map<String,String> map = new HashMap<String,String>();
		if(account.length() == 0 || password.length() == 0){
			map.put("error", "");
		}else{
			StuffEntry stuff = StuffDao.getStuff(account, password);
			if( stuff != null){
				map.put("name", stuff.getName());
				map.put("type", stuff.getType()+"");
				if(stuff.getType() == 2){
					map.put("departId", stuff.getDepartment().getDepartmentId());
					map.put("departName", stuff.getDepartment().getDepartmentName());
				}
			}else{
				map.put("error", "");
			}
		}
		try {
			Log.logInfo(TAG, map.toString());
			response.setCharacterEncoding("utf-8");
			response.getWriter().append(JsonManager.createJson(map));
		} catch (JSONException e) {
			Log.logError(TAG, e.getMessage());
		}
	}

}

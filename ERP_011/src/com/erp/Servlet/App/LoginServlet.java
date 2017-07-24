package com.erp.Servlet.App;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.erp.Dao.StuffDao;
import com.erp.Entry.StuffEntry;
import com.erp.Log.Log;
import com.erp.utils.JsonManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/api/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String TAG ="LoginServlet";
	
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("is me");
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
				map.put("account", stuff.getName());
				map.put("password", stuff.getPwd());
				map.put("telNum", stuff.getTelNum());
				map.put("name", stuff.getName());
				if(stuff.isType2_leader()){
					map.put("type", "3");
				}else{
					map.put("type", stuff.getType());
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

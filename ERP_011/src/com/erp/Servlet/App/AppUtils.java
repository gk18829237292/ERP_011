package com.erp.Servlet.App;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class AppUtils {
	
	public static void sendSuccess(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data", "success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append(jsonObject.toString());
	}
}

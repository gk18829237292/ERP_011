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
@WebServlet("/api/getAllTask")
public class GetAllTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetAllTaskServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String type = request.getParameter("type");
		System.out.println("account : " + account +"  type: " + type);
		List<DepartClassEntry> entryList = null;
		switch (type) {
		case "0":
			entryList = DepartClassDao.getAllDepartClass_edt();
			break;
		case "1":
			List<String> departIDs = Stuff_1_DepartDao.getDeparts(account);
			entryList = DepartClassDao.getAllDepartClass_edt(departIDs);
			break;
		case "2":
			List<DepartClassEntry> departClassEntries = DepartClassDao.getAllDepartClass_edt();
			DepartEntry depart = Stuff_DepartDao.getDepartByStuffAccount(account);
			Iterator iter =  departClassEntries.iterator();
			System.out.println(depart);
			while(iter.hasNext()){
				DepartClassEntry entry = (DepartClassEntry) iter.next();
				boolean flag = true;
				Iterator<DepartEntry> iter2 = entry.getDeparts().iterator();
				while(iter2.hasNext()){
					DepartEntry departEntry = iter2.next();
					if(!departEntry.getDepartId().equals(depart.getDepartId())){
						iter2.remove();
					}else{
						flag = false;
					}
				}
				if(flag){
					iter.remove();
				}	
			}
			entryList = departClassEntries;
		case "3":
			departIDs = Stuff_DepartDao.getDeparts(account);
			entryList = DepartClassDao.getAllDepartClass_edt(departIDs);
			break;
		}
		
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

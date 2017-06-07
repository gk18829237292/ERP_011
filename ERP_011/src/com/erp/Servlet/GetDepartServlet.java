package com.erp.Servlet;

import java.io.Console;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartDao;
import com.erp.Entry.DepartEntry;

/**
 * Servlet implementation class GetDepartServlet
 */
@WebServlet("/GetDepartServlet")
public class GetDepartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetDepartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 <option value="${entry.departId}">${entry.departName}</option>
		String departClassId = request.getParameter("departClassId");
		List<DepartEntry> departEntries = DepartDao.getAllDepartByClassId(departClassId);
		System.out.println("get get get");
		StringBuilder sb = new StringBuilder(" <option >请选择部门</option>");
		for(DepartEntry entry:departEntries){
			sb.append(" <option value=\"");
			sb.append(entry.getDepartId() + "\">" + entry.getDepartName() + "</option>\n");
		}
		System.out.println(sb.toString());
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(sb.toString());
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

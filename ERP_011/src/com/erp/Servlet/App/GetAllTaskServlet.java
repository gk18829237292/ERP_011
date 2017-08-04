package com.erp.Servlet.App;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erp.Dao.DepartClassDao;
import com.erp.Dao.Stuff_1_DepartDao;
import com.erp.Dao.TaskDao;
import com.erp.Entry.DepartClassEntry;

import sun.security.provider.JavaKeyStore.CaseExactJKS;

/**
 * Servlet implementation class GetAllTaskServlet
 */
@WebServlet("/getAllTask")
public class GetAllTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetAllTaskServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String type = request.getParameter("type");
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
		case "3":
			
			break;
		default:
//			entryList = DepartClassDao.getAllDepartClass(departIDs)
			break;
		}
		DepartClassDao.getAllDepartClass(true);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

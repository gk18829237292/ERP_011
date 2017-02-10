package com.erp.Servlet.api;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import com.erp.Dao.AssignmentDao;
import com.erp.Dao.ReportDao;
import com.erp.Dao.SuperviseDao;
import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.entry.ReportEntry;
import com.erp.entry.ResponseJson;
import com.erp.entry.StuffEntry;
import com.erp.utils.FileUploadUtils;

/**
 * Servlet implementation class CreateSupervise
 */
@WebServlet("/api/createSuper")
public class CreateSupervise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG ="CreateSupervise";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSupervise() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//处理文件上传
		String filePath = getServletContext().getRealPath("/img");
		String tempFilePath = getServletContext().getRealPath("/tmp");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4 * 1024);
		factory.setRepository(new File(tempFilePath));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(40 * 1024 * 1024);
		StringBuilder sb= new StringBuilder();
		HashMap<String,String> map = new HashMap<String,String>();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item:list){
				if(!item.isFormField()){
					sb.append(FileUploadUtils.processUploadFiles(filePath,item));
					sb.append(";");
				}else{
					System.out.println(item.getFieldName() +"\n"+ item.getString());
					map.put(item.getFieldName(), item.getString());
				}
			}
		} catch (Exception e) {
			Log.logError(TAG, e.getMessage());
		}

		Date date = new Date();
		StuffEntry stuff = (StuffEntry) request.getSession().getAttribute("stuff");
		synchronized (this) {//添加监督信息
//			SuperviseDao.insert(map.get, account, comment, picture, superviseTime)
			SuperviseDao.insert(map.get("reportId"), map.get("account"), map.get("comment"), sb.toString(), date.getTime());
//			ReportDao.insert(stuff.getAccount(), map.get("taskid"), map.get("comment"), sb.toString(), date.getTime(), map.get("num"));
//			AssignmentDao.updateIfSmall(map.get("taskid"), Integer.parseInt(map.get("num")));
		}
		JSONObject json = ResponseJson.getCurrentJson();
		response.getWriter().append(json.toString());
//		AssignmentEntry entry = AssignmentDao.getAssignment(map.get("taskid"));
//		List<ReportEntry> reports = ReportDao.getAllReport(map.get("taskid"));
//		request.setAttribute("reports", reports);
//		request.setAttribute("task", entry);
//		request.getRequestDispatcher("/WEB-INF/jsp/projectDetailSupervise.jsp").forward(request, response);			
	}

}

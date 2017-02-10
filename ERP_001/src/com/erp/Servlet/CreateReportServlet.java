package com.erp.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.erp.Dao.AssignmentDao;
import com.erp.Dao.ReportDao;
import com.erp.Log.Log;
import com.erp.entry.AssignmentEntry;
import com.erp.entry.StuffEntry;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * Servlet implementation class CreateReportServlet
 */
@WebServlet("/CreateReport")
public class CreateReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG ="CreateReportServlet";
       
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("taskId");
		AssignmentEntry task =  AssignmentDao.getAssignment(id);
		request.setAttribute("task", task);
		request.getRequestDispatcher("/WEB-INF/jsp/createReport.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = getServletContext().getRealPath("/img");
		String tempFilePath = getServletContext().getRealPath("/tmp");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
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
					sb.append(processUploadFiles(filePath,item));
					sb.append(";");
				}else{
					map.put(item.getFieldName(), item.getString());
				}
			}
		} catch (Exception e) {
			Log.logError(TAG, e.getMessage());
		}

		map.put("comment",new String(map.get("comment").getBytes("ISO8859-1"),"utf-8"));
		System.out.println(map);
		Date date = new Date();
		StuffEntry stuff = (StuffEntry) request.getSession().getAttribute("stuff");
		synchronized (this) {
			ReportDao.insert(stuff.getAccount(), map.get("taskid"), map.get("comment"), sb.toString(), date.getTime(), map.get("num"));
			AssignmentDao.updateIfSmall(map.get("taskid"), Integer.parseInt(map.get("num")));
		}
		response.sendRedirect("guest1");
	}
	

	
	private String processUploadFiles(String path ,FileItem item) throws Exception {

		long sizeInBytes = item.getSize();
		
		if ( sizeInBytes == 0){
			return "";
		}
		
		String fileName =UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
		File uploadedFile = new File(path + "/" + fileName);
		while(uploadedFile.exists()){
			fileName =UUID.randomUUID().toString().replaceAll("-", "");
			uploadedFile = new File(path + "/" + fileName);
		}
		
		
		item.write(uploadedFile);
		return fileName;
	}
	

}

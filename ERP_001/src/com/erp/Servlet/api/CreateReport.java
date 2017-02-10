package com.erp.Servlet.api;

import java.io.File;
import java.io.IOException;
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
import com.erp.entry.ResponseJson;
import com.erp.entry.StuffEntry;

/**
 * Servlet implementation class CreateReport
 */
@WebServlet("/api/createReport")
public class CreateReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG ="CreateReport";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReport() {
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
		String filePath = getServletContext().getRealPath("/img");
		String tempFilePath = getServletContext().getRealPath("/tmp");
		request.setCharacterEncoding("utf-8");
		

		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(64 * 1024);
		factory.setRepository(new File(tempFilePath));
		
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(40 * 1024 * 1024);
		StringBuilder sb= new StringBuilder();
		HashMap<String,String> map = new HashMap<String,String>();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item:list){
				System.out.println(item.toString());
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

		Date date = new Date();
		System.out.println(map);
		synchronized (this) {
			ReportDao.insert(map.get("account"), map.get("taskId"), map.get("comment"), sb.toString(), date.getTime(), map.get("num"));
			AssignmentDao.updateIfSmall(map.get("taskId"), Integer.parseInt(map.get("num")));
		}
		response.getWriter().append(ResponseJson.getCurrentJson().toString());
//		response.getWriter().append(ResponseJson.getCurrentJson().toString());
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

package com.erp.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.erp.Log.Log;

public class ImageUtils {
	
	private static final String TAG ="ImageUtils";

	public static HashMap<String, String> getMap(HttpServletRequest request, String tempFilePath,String filePath){
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4 * 1024);
		factory.setRepository(new File(tempFilePath));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(40 * 1024 * 1024);
		StringBuilder sb= new StringBuilder();
		HashMap<String,String> map = new HashMap<String,String>();
		try{
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item:list){
				if(!item.isFormField()){
					sb.append(processUploadFiles(filePath,item));
					sb.append(";");
				}else{
					map.put(item.getFieldName(), StringUtils.change2Utf8(item.getString()));
				}
			}
		} catch (Exception e) {
			Log.logError(TAG, e.getMessage());
		}
		return map;
	}
	
	
	public static String processUploadFiles(String path ,FileItem item) throws Exception {

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

package com.erp.utils;

import java.io.File;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

public class FileUploadUtils {
	
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

package com.nirvasoft.fi.util;

import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFile extends HttpServlet {
	
	
	
	  	private static final long serialVersionUID = 1L;
	  	private String UPLOAD_DIRECTORY = "";

	    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	    	
			    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			    UPLOAD_DIRECTORY =  request.getRealPath("/")+ "\\uploads\\";
			    // process only if its multipart content
			    if (isMultipart) {
			            // Create a factory for disk-based file items
			            FileItemFactory factory = new DiskFileItemFactory();
			
			            // Create a new file upload handler
			            ServletFileUpload upload = new ServletFileUpload(factory);
			            try {
		                    // Parse the request
		                    List<FileItem> multiparts = upload.parseRequest(request);
		
		                    for (FileItem item : multiparts) {
		                      if (!item.isFormField()) {
		                         String name = new File(item.getName()).getName();
		                         System.out.println(UPLOAD_DIRECTORY + File.separator + name);
		                         item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
		                         
		                      }
		                      System.out.println("Upload Sucessfully");
		                    }
			            } 
			            catch (Exception e) {
			              System.out.println("File upload failed");
		                }
		        }
	}
}

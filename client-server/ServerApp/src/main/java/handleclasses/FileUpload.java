package handleclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import messageclasses.ResponseCode;
import messageclasses.WebRequest;
import messageclasses.WebResponse;
import webserver.WebServer;

public class FileUpload extends Handle {

	public FileUpload(WebRequest request) {
		super(request);
	}

	@Override
	public WebResponse serve() throws IOException, InterruptedException {
		
		this.waitThePathFile();
		
		try {
		   synchronized(Handle.lockUpload) {
		        upload();
	    	}
		}finally{
			this.freePathFile();
        }
		
		WebResponse response = new WebResponse();
		response.getResHeader().setRequestId(super.getRequest().getReqHeader().getRequestId());
		response.getResHeader().setResponseCode(ResponseCode.SUCCESS);
		response.setContentType(super.getRequest().getContentType());
        return response;
	}
	
	private void upload() throws IOException  
	{
		
		
		      //create all the path
			  File file = new File(WebServer.directoryPath + super.getRequest().getFilePath());
			  file.getParentFile().mkdirs();
			  
			  FileWriter myWriter = new FileWriter(file);    
		      myWriter.write(super.getRequest().getReqtBody().getReqbody());
		      myWriter.close();
		   
		
	}

}

package handleclasses;

import java.io.File;
import java.io.FileNotFoundException;

import messageclasses.ResponseCode;
import messageclasses.WebRequest;
import messageclasses.WebResponse;
import webserver.WebServer;

public class FileDelete extends Handle {

	public FileDelete(WebRequest request) {
		super(request);
	}

	@Override
	public WebResponse serve() throws FileNotFoundException, InterruptedException {
		
		
		this.waitThePathFile();
		
		try {
		
	    	synchronized(Handle.lockDelete){
		    	synchronized(Handle.lockFContent){
		           delete();
		    	 }
		    }

		}finally{
			this.freePathFile();
        }
		
		WebResponse response = new WebResponse(super.getRequest().getReqHeader().getRequestId(),ResponseCode.SUCCESS);
		response.setContentType(super.getRequest().getContentType());
		return response;
	}

	private void delete() throws FileNotFoundException {
		
		String pathName = super.getRequest().getFilePath();
		if(!super.CheckFilePathroot(pathName))
	    {
			pathName = WebServer.directoryPath+pathName;	 
		}
		File myObj = new File(pathName);
	    if(!myObj.exists() || myObj.isDirectory()) {
			throw new FileNotFoundException(); 
		}
		myObj.delete();
		
	}

}

package handleclasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import messageclasses.ResponseCode;
import messageclasses.WebRequest;
import messageclasses.WebResponse;
import webserver.WebServer;


public class ViewFolderContent extends Handle {

	public ViewFolderContent(WebRequest request) {
		super(request);
	}

	@Override                                                
	public WebResponse serve() throws FileNotFoundException{
		
		WebResponse response = new WebResponse();
		
		synchronized(Handle.lockFContent){
			
				response.getResBody().setresbody(folderContent());	
		}

		response.getResHeader().setRequestId(super.getRequest().getReqHeader().getRequestId());
		response.getResHeader().setResponseCode(ResponseCode.SUCCESS);
		response.setContentType(super.getRequest().getContentType());

		return response;
	}
	
	private String folderContent() throws FileNotFoundException {
		
		String pathName = super.getRequest().getFilePath();
		if(!super.CheckFilePathroot(pathName))
	    {
			pathName = WebServer.directoryPath + pathName;	 
		}
		
		File directoryPath = new File(pathName);
		if(!directoryPath.exists() || !directoryPath.isDirectory()) {
			 throw new FileNotFoundException();
	    }
		String contents[] = directoryPath.list();
		String data = Arrays.toString(contents);
		 
		return data;
	}
	


}

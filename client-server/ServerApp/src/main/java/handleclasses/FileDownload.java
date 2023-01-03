package handleclasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import messageclasses.ResponseCode;
import messageclasses.WebRequest;
import messageclasses.WebResponse;
import webserver.WebServer;

public class FileDownload extends Handle {

	public FileDownload(WebRequest request) {
		super(request);
	}

	@Override
	public WebResponse serve() throws FileNotFoundException, InterruptedException {
		
		this.waitThePathFile();
		String body ="";
		
		try {
			body = download();
		
	    }finally{
		   this.freePathFile();
        }
	
		WebResponse response = new WebResponse();
		response.getResBody().setresbody(body);
		response.getResHeader().setRequestId(super.getRequest().getReqHeader().getRequestId());
		response.getResHeader().setResponseCode(ResponseCode.SUCCESS);
		response.setContentType(super.getRequest().getContentType());
		
		return response;
	}
	
	
	private String download() throws FileNotFoundException 
	{
		
		String pathName = super.getRequest().getFilePath();
		if(!super.CheckFilePathroot(pathName))
	    {
			pathName = WebServer.directoryPath+pathName;	 
		}
		File myObj = new File(pathName);
		Scanner myReader = new Scanner(myObj);
	
		String data = "" ;
		while (myReader.hasNextLine()) {
            data += myReader.nextLine()+"\r\n";		      
        }
        myReader.close();
		
		return data;

	}
	
	
	
}

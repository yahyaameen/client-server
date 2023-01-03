package buildclasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import messageclasses.Action;
import messageclasses.ContentType;
import messageclasses.NotContentTypeException;
import messageclasses.WebRequest;
import messageclasses.WebResponse;

public class FileUpload extends BuildRequest {

	@Override
	public WebRequest build() throws FileNotFoundException, NotContentTypeException {
		System.out.println("Enter a file path name");
		Scanner scanner = new Scanner(System.in);
		super.filePathName = scanner.nextLine();
		super.action = Action.FILE_UPLOAD;
		int requestId = BuildRequest.randomInt();
		ContentType contentType = null;
		try {
			contentType = ContentType.fromString(ContentType.getContentTypeFromPathName(filePathName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebRequest webRequest = new WebRequest();
		webRequest.setAction(super.action);
		webRequest.setContentType(contentType);
		webRequest.getReqHeader().setRequestId(requestId);
		
		String data = "";
		File myObj = new File(super.filePathName);
		Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) 
        {
		      data += myReader.nextLine()+"\r\n";
		}
	    myReader.close();
	    
		webRequest.getReqtBody().setReqbody(data);
		
		if(filePathName.startsWith("C") || filePathName.startsWith("D"))
		{
			filePathName = filePathName.substring(2);
		}
		webRequest.setFilePath(filePathName);
		
		return webRequest;
	
		
	}

	@Override
	public void handleResponse(String jsonResponse) {
		WebResponse webResponse = new WebResponse();
		webResponse.fromJSON(jsonResponse);
		System.out.println(webResponse.getResHeader().getResponseCode());
		
	}

}

package buildclasses;

import java.io.IOException;
import java.util.Scanner;

import messageclasses.Action;
import messageclasses.ContentType;
import messageclasses.NotContentTypeException;
import messageclasses.WebRequest;
import messageclasses.WebResponse;

public class FileDelete extends BuildRequest {

	@Override
	public WebRequest build()throws NotContentTypeException {
		System.out.println("Enter a file path name");
		Scanner scanner = new Scanner(System.in);
		super.filePathName = scanner.nextLine();
		super.action = Action.FILE_DELETE;
		int requestId =BuildRequest.randomInt();
		ContentType contentType = null;
		try {
			contentType = ContentType.fromString(ContentType.getContentTypeFromPathName(filePathName));
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebRequest webRequest = new WebRequest();
		webRequest.setAction(super.action);
		webRequest.setFilePath(filePathName);
		webRequest.setContentType(contentType);
		webRequest.getReqHeader().setRequestId(requestId);
		return webRequest;
		
	}

	@Override
	public void handleResponse(String jsonResponse) {
		WebResponse webResponse = new WebResponse();
		webResponse.fromJSON(jsonResponse);
		System.out.println(webResponse.getResHeader().getResponseCode());
		
	}
	
	
	
	

}

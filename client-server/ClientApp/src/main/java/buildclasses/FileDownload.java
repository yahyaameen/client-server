package buildclasses;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import messageclasses.Action;
import messageclasses.ContentType;
import messageclasses.NotContentTypeException;
import messageclasses.ResponseCode;
import messageclasses.WebRequest;
import messageclasses.WebResponse;
import webclient.WebClient;

public class FileDownload extends BuildRequest{

	@Override
	public WebRequest build() throws NotContentTypeException {
		System.out.println("Enter a file path name");
		Scanner scanner = new Scanner(System.in);
		
		super.filePathName = scanner.nextLine();
		super.action = Action.FILE_DOWNLOAD;
		int requestId = BuildRequest.randomInt();
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
		if(webResponse.getResHeader().getResponseCode() == ResponseCode.SUCCESS)
		{
			try {
				  //get File name
			      String filePath = super.filePathName;
				  String[] tokens = filePath.split("[\\\\]");
			   	  String fileName = tokens[tokens.length - 1];
			      
			      FileWriter myWriter = new FileWriter(WebClient.clientDir + fileName);
			      myWriter.write(webResponse.getResBody().getresbody());
			      myWriter.close();
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
		
	}

}

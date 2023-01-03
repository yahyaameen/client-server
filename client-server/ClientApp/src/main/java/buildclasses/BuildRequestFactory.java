package buildclasses;


public class BuildRequestFactory {
	public static BuildRequest create(int action)
	{
		BuildRequest buildRequest = null;
		switch(action)
		{
		    case 1:
		    	buildRequest = new FileDownload(); 
		    	break;
		    case 2:
		    	buildRequest = new FileUpload(); 
		    	break;
		    case 3:
		    	buildRequest = new FileDelete(); 
		    	break;
		    case 4:
		    	buildRequest = new ViewFolderContent(); 
		    	break;
		
		}
		return buildRequest;
	}


}

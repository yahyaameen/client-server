package handleclasses;

//import messageclasses.Action;
import messageclasses.WebRequest;

public class HandleFactory {
	public static Handle create(WebRequest request)
	{
		Handle handle = null;
		switch(request.getAction())
		{
		    case FILE_DOWNLOAD:
		    	handle = new FileDownload(request); 
		    	break;
		    case FILE_UPLOAD:
		    	handle = new FileUpload(request); 
		    	break;
		    case VIEW_FOLDER_LIST:
		    	handle = new ViewFolderList(request); 
		    	break;
		    case VIEW_FOLDER_CONTENT:
		    	handle = new ViewFolderContent(request); 
		    	break;
		    case FILE_DELETE:
		    	handle = new FileDelete(request); 
		    	break;
		
		}
		return handle;
	}

}

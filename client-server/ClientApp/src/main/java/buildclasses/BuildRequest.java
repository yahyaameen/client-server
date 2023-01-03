package buildclasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import messageclasses.Action;
import messageclasses.ContentType;
import messageclasses.NotContentTypeException;
import messageclasses.WebRequest;

public abstract class BuildRequest {
	
	protected Action action;
	protected String filePathName;
	

	public abstract WebRequest build() throws FileNotFoundException, NotContentTypeException;
	public abstract void handleResponse(String jsonResponse);
	
	
	public static WebRequest getServerFolderList()
	{
		WebRequest request = new WebRequest();
		request.setAction(Action.VIEW_FOLDER_LIST);
		request.getReqHeader().setRequestId(BuildRequest.randomInt());
		return request;
	}
	
	public static int randomInt() {
		Random ran = new Random();
		int x = ran.nextInt(6) + 5;
		return x;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((filePathName == null) ? 0 : filePathName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuildRequest other = (BuildRequest) obj;
		if (action != other.action)
			return false;
		if (filePathName == null) {
			if (other.filePathName != null)
				return false;
		} else if (!filePathName.equals(other.filePathName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BuildRequest [action=" + action + ", filePathName=" + filePathName + "]";
	}
	
	
	
	
}

package messageclasses;

import org.json.JSONObject;

public class WebRequest extends WebMessage {
	
	private String filePath;
	
	private Action action;
	
	private RequestHeader reqHeader;    //HEADER 
	
	private RequestBody reqBody;       //BODY

	
	public WebRequest() {
		this.reqHeader = new RequestHeader();
		this.reqBody = new RequestBody();
		
	}
	
	
	
	
	public String getFilePath() {
		return filePath.toLowerCase();
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath.toLowerCase();
	}
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public RequestHeader getReqHeader() {
		return reqHeader;
	}

	public void setReqHeader(RequestHeader reqHeader) {
		this.reqHeader = reqHeader;
	}

	public RequestBody getReqtBody() {
		return reqBody;
	}

	public void setReqtBody(RequestBody reqtBody) {
		this.reqBody = reqtBody;
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("filePath", filePath );
		json.put("contentType", super.getContentType());
		json.put("action", action);
		json.put("reqHeader",reqHeader.toJson());
		json.put("reqBody",reqBody.toJson());
		
		return json;
	}
	
	public void fromJSON(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		if(!json.isNull("filePath"))
		{
			this.filePath = json.getString("filePath").toLowerCase();                
		}
		if(!json.isNull("contentType"))
		{
			super.contentType = json.getEnum(ContentType.class, "contentType");
		}
		
		this.action = json.getEnum(Action.class, "action");
		this.reqHeader.fromJSON(json.getJSONObject("reqHeader").toString());
		this.reqBody.fromJSON(json.getJSONObject("reqBody").toString());
		
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((reqBody == null) ? 0 : reqBody.hashCode());
		result = prime * result + ((reqHeader == null) ? 0 : reqHeader.hashCode());
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
		WebRequest other = (WebRequest) obj;
		if (action != other.action)
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (reqBody == null) {
			if (other.reqBody != null)
				return false;
		} else if (!reqBody.equals(other.reqBody))
			return false;
		if (reqHeader == null) {
			if (other.reqHeader != null)
				return false;
		} else if (!reqHeader.equals(other.reqHeader))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "WebRequest [filePath=" + filePath + ", action=" + action + ", reqHeader=" + reqHeader + ", reqBody="
				+ reqBody + "]";
	}
	
	

	
	
	
	
	
	
	

}

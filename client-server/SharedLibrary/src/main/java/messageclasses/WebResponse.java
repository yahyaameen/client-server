package messageclasses;

import org.json.JSONObject;

public class WebResponse extends WebMessage{
	
	
	private ResponseHeader resHeader;        //HEADER 
	private ResponseBody resBody;             //BODY
	

	public WebResponse() {
		super();
		this.resHeader = new ResponseHeader();
		this.resBody = new ResponseBody();
	}
	public WebResponse(int requestID,ResponseCode responsecode) {
		super();
		this.resHeader = new ResponseHeader(requestID,responsecode);
		this.resBody = new ResponseBody();
	}

	public ResponseHeader getResHeader() {
		return resHeader;
	}


	public void setResHeader(ResponseHeader resHeader) {
		this.resHeader = resHeader;
	}


	public ResponseBody getResBody() {
		return resBody;
	}


	public void setResBody(ResponseBody resBody) {
		this.resBody = resBody;
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("contentType", super.getContentType());
		json.put("resHeader",resHeader.toJson());
		json.put("resBody",resBody.toJson());
		
		return json;
	}
	
	public void fromJSON(String jsonStr)  {
		JSONObject json = new JSONObject(jsonStr);
		if(!json.isNull("contentType"))
		{
			super.contentType = json.getEnum(ContentType.class, "contentType");

		}
		this.resHeader.fromJSON(json.getJSONObject("resHeader").toString());
		this.resBody.fromJSON(json.getJSONObject("resBody").toString());
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resBody == null) ? 0 : resBody.hashCode());
		result = prime * result + ((resHeader == null) ? 0 : resHeader.hashCode());
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
		WebResponse other = (WebResponse) obj;
		if (resBody == null) {
			if (other.resBody != null)
				return false;
		} else if (!resBody.equals(other.resBody))
			return false;
		if (resHeader == null) {
			if (other.resHeader != null)
				return false;
		} else if (!resHeader.equals(other.resHeader))
			return false;
		return true;
	}
	

	
	

}

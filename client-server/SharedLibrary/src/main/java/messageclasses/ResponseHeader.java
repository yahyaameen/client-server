package messageclasses;

import org.json.JSONObject;


public class ResponseHeader {
	
	private  int requestId;
	private ResponseCode responseCode;
	
	public ResponseHeader() {}

	public ResponseHeader(int requestId, ResponseCode responseCode) {
		super();
		this.requestId = requestId;
		this.responseCode = responseCode;
	}
	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId){
		this.requestId = requestId;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("requestId", requestId);
		json.put("responseCode", responseCode);
		return json;
	}
	
	public void fromJSON(String jsonStr){
		JSONObject json = new JSONObject(jsonStr);
		this.requestId = json.getInt("requestId");
		this.responseCode = json.getEnum(ResponseCode.class, "responseCode");

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + requestId;
		result = prime * result + ((responseCode == null) ? 0 : responseCode.hashCode());
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
		ResponseHeader other = (ResponseHeader) obj;
		if (requestId != other.requestId)
			return false;
		if (responseCode != other.responseCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResponseHeader [requestId=" + requestId + ", responseCode=" + responseCode + "]";
	}
	
	
	
	

}

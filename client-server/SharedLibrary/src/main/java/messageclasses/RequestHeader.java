package messageclasses;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;


public class RequestHeader {
	
	private  int requestId;
	Set<Integer> hashset ;
	
	public RequestHeader() {}
	
	
	public RequestHeader(int requestId) {
		super();
		this.requestId = requestId;
		this.hashset = new HashSet<Integer>();
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("requestId", requestId);
		return json;
	}
	
	public void fromJSON(String jsonStr){
		JSONObject json = new JSONObject(jsonStr);
		this.requestId = json.getInt("requestId");
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashset == null) ? 0 : hashset.hashCode());
		result = prime * result + requestId;
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
		RequestHeader other = (RequestHeader) obj;
		if (hashset == null) {
			if (other.hashset != null)
				return false;
		} else if (!hashset.equals(other.hashset))
			return false;
		if (requestId != other.requestId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "RequestHeader [requestId=" + requestId + ", hashset=" + hashset + "]";
	}
	
	
	
	
	
	
	
	
	
	

}

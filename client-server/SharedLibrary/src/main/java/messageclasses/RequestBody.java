package messageclasses;

import org.json.JSONObject;

public class RequestBody {
	
	private String reqbody;

	public RequestBody() {}

	public RequestBody(String reqbody) {
		super();
		this.reqbody = reqbody;
	}

	public String getReqbody() {
		return reqbody;
	}

	public void setReqbody(String reqbody) {
		this.reqbody = reqbody;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reqbody == null) ? 0 : reqbody.hashCode());
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
		RequestBody other = (RequestBody) obj;
		if (reqbody == null) {
			if (other.reqbody != null)
				return false;
		} else if (!reqbody.equals(other.reqbody))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "RequestBody [reqbody=" + reqbody + "]";
	}
	
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("reqbody", reqbody);
		return json;
	}
	
	public void fromJSON(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		if(!json.isNull("reqbody"))
		{
			this.reqbody = json.getString("reqbody");
		}
	}
	
	
	

}

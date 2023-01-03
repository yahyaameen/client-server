package messageclasses;

import org.json.JSONObject;

public class ResponseBody {
	

	private String resbody;

	public ResponseBody() {}	

	public ResponseBody(String resbody) {
		super();
		this.resbody = resbody;
	}

	public String getresbody() {
		return resbody;
	}
		
	public void setresbody(String resbody) {
		this.resbody = resbody;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resbody == null) ? 0 : resbody.hashCode());
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
		ResponseBody other = (ResponseBody) obj;
		if (resbody == null) {
			if (other.resbody != null)
				return false;
		} else if (!resbody.equals(other.resbody))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestBody [resbody=" + resbody + "]";
	}
	
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("resbody", resbody);
		return json;
	}
	
	public void fromJSON(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		if(!json.isNull("resbody"))
		{
			this.resbody = json.getString("resbody");
		}
	}
	
	
	


}

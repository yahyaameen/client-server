package messageclasses;

import org.json.JSONException;

public class WebParser {
	
	
	public static WebRequest parseWebRequest(String json) throws JSONException {
		WebRequest request = new WebRequest();
		request.fromJSON(json);		
		return request ;
	}
	
	
	

}

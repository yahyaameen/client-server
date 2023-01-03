package webserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.json.JSONException;
import java.io.FileNotFoundException;


import handleclasses.Handle;
import handleclasses.HandleFactory;
import handleclasses.NotImplementedContentTypeException;
import messageclasses.ContentType;
import messageclasses.ResponseCode;
import messageclasses.WebParser;
import messageclasses.WebRequest;
import messageclasses.WebResponse;


public class ConnectionWorkerThread extends Thread {
	
    private Socket socket;
    
    public ConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        
        try {
        	while(true) {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            
            DataInputStream dis = new  DataInputStream(inputStream);
            DataOutputStream dos = new DataOutputStream(outputStream);
            String input="";
            
            try {
            	 // parsing the request
            	 input = dis.readUTF();
                 WebRequest req = WebParser.parseWebRequest(input);
                 if(req.getContentType() != ContentType.asc 
                		 && req.getContentType() != ContentType.pot
                		 && req.getContentType() != ContentType.text
                		 && req.getContentType() != ContentType.txt
                		 && req.getContentType() != null) { 
                	throw new NotImplementedContentTypeException(); 
                 }
                 
                 // handling the request
                 Handle handle = HandleFactory.create(req);
                 String jsonResponse = handle.serve().toJson().toString();
                 
                 //return response
                 dos.writeUTF(jsonResponse);
       
                  
            }
            catch(JSONException e){
            	
            	WebRequest request = new WebRequest();
        		request.fromJSON(input);
        		WebResponse response = new WebResponse(request.getReqHeader().getRequestId(),ResponseCode.CLIENT_ERROR_405_UNKNOWN_REQUEST);
            	
            	dos.writeUTF(response.toJson().toString());
            	
            } catch(FileNotFoundException e) {
            	
            	WebRequest request = new WebRequest();
        		request.fromJSON(input);		
        		WebResponse response = new WebResponse(request.getReqHeader().getRequestId(),ResponseCode.CLIENT_ERROR_404_FILE_NOT_FOUND);
            	
            	dos.writeUTF(response.toJson().toString());
       
            }
            catch(NotImplementedContentTypeException e){
            	
            	WebRequest request = new WebRequest();
        		request.fromJSON(input);
        		WebResponse response = new WebResponse(request.getReqHeader().getRequestId(),ResponseCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
            	
            	dos.writeUTF(response.toJson().toString());
            	
            }
           catch(Exception e) {
            	WebRequest request = new WebRequest();
        		request.fromJSON(input);
        		WebResponse response = new WebResponse(request.getReqHeader().getRequestId(),ResponseCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
            	
            	dos.writeUTF(response.toJson().toString());
            	
            }
        	}
            
        
        } catch (IOException e) {
        	
        } finally {
            if (inputStream!= null) {
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
            if (outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            if (socket!= null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
    
    


}




package webclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import buildclasses.BuildRequest;
import buildclasses.BuildRequestFactory;
import messageclasses.Action;
import messageclasses.NotContentTypeException;
import messageclasses.WebResponse;

public class ClientThread extends Thread {
	
	private static String serverIP = "127.0.0.1";
    private static int serverPort = 6224;
    private Socket socket;
	
	private ClientThread() {
		
	}
	
	private static class Holder {
	    private static final ClientThread instance = new ClientThread();
	}
	
	public static ClientThread getInstace() {
		return Holder.instance;
	}

    @Override
    public void run() {
       System.out.println("Client started");
    
       try {
    	
        socket = new Socket(serverIP,serverPort);
        	
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        DataOutputStream dos = new DataOutputStream(os);
        DataInputStream dis = new DataInputStream(is);
        
        while(true) {
        System.out.println("Hi!\r\nSelect an action number\r\n"+Action.getActionList()+"5 Exit\r\n");
        Scanner scanner = new Scanner(System.in);
        
        int choice;
        while(true) {
         while(!scanner.hasNextInt() )
         {
        	System.out.println("Please enter an action number\r\n"+Action.getActionList()+"5 Exit\r\n");
            scanner.next();
         }
         choice = scanner.nextInt();
         if(choice >= 1 && choice <= 5)
        	 break;
        }
        if(choice == 5)
        {
        	System.out.println("Have a good day!");
        	break;
        }
        	
        
        BuildRequest request = BuildRequestFactory.create(choice);
        if(choice != 2) {
        	String json = BuildRequest.getServerFolderList().toJson().toString();
        	dos.writeUTF(json);
        	WebResponse response = new WebResponse();
        	response.fromJSON(dis.readUTF());
        	System.out.println(response.getResBody().getresbody());
        }
        
        String jsonRequest = request.build().toJson().toString();
        dos.writeUTF(jsonRequest);
        request.handleResponse(dis.readUTF());
      
      }
       }catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       catch (NotContentTypeException e) {
			System.out.println("Wrong content type");
			e.printStackTrace();
			
		} catch (IOException e) {
        e.printStackTrace();
      
	}finally {
    	  if (socket!= null) {
              try {
                  socket.close();
              } catch (IOException e) {}
          }
      }
    }

}

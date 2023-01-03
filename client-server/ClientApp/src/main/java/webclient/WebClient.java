package webclient;


public class WebClient {
	
	    public static String clientDir = "downloads/";
	    
	    public static void main(String[] args) {
	    	
	    	try {
	    		ClientThread clientThread = ClientThread.getInstace();
	            clientThread.start();
	        } catch (IllegalThreadStateException e) {
	            e.printStackTrace();
	        }
	    }
}


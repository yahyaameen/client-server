package webserver;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {

	private static int port = 6224;
    private ServerSocket serverSocket;
	
	private ServerListenerThread() {
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class Holder {
	    private static final ServerListenerThread instance = new ServerListenerThread();
	}
	
	public static ServerListenerThread getInstace() {
		return Holder.instance;
	}
    
    @Override
    public void run() {

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ConnectionWorkerThread workerThread = new ConnectionWorkerThread(socket);
                workerThread.start();
            }
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
            if (serverSocket!=null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {e.printStackTrace();}
            }
        }

    }

}

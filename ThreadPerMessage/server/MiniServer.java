package ThreadPerMessage.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MiniServer {
	private final int portnumber;

	public MiniServer(int portnumber){
		this.portnumber = portnumber;
	}
	public void execute() throws IOException {
		ServerSocket serverSocket = new ServerSocket(portnumber);
		ExecutorService executorService = Executors.newCachedThreadPool();
		System.out.println("Listening on " + serverSocket);
		try{
			while(true){
				System.out.println("Accepting...");
				final Socket clientSocket = serverSocket.accept();//無名の中で使うからfinal
				System.out.println("Connected to " + clientSocket);
				executorService.execute(
					new Runnable(){
						public void run(){
							try{
								Service.service(clientSocket);
							}catch(IOException e){

							}
						}
					}
				);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			executorService.shutdown();
			serverSocket.close();
		}

	}
}

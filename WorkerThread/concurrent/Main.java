package WorkerThread.concurrent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		try{
			new ClientThread("Alice",executorService).start();
			new ClientThread("Yuta",executorService).start();
			new ClientThread("Daemon",executorService).start();

			Thread.sleep(5000);
		}catch(InterruptedException e){

		}finally{
			executorService.shutdown();
		}
	}
}


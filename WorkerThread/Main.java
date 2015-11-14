package WorkerThread;

public class Main {
	public static void main(String[] args) {
		Channel channel = new Channel(5);//Worker Threadの数
		channel.startWorkers();

		ClientThread alice = new ClientThread("Alice",channel);
		ClientThread bobby = new ClientThread("Yuta",channel);
		ClientThread chris = new ClientThread("Daemon",channel);
		alice.start();
		bobby.start();
		chris.start();

		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){

		}
		alice.stopThread();
		bobby.stopThread();
		chris.stopThread();
		channel.stopAllWorkers();
	}
}

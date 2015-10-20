package WorkerThread;

public class Main {
	public static void main(String[] args) {
		Channel channel = new Channel(5);//Worker Threadの数
		channel.startWorkers();
		new ClientThread("Alice",channel).start();
		new ClientThread("Yuta",channel).start();
		new ClientThread("Daemon",channel).start();
	}
}

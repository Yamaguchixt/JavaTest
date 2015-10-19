package ThreadSpecificStorage;

public class Main {
	public static void main(String[] args) {
		System.out.println("BEGIN");
		new ClientThread("Alice").start();
		new ClientThread("Bob").start();
		new ClientThread("Yuta").start();
		System.out.println("END");
	}
}

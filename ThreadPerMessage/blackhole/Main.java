package ThreadPerMessage.blackhole;

public class Main {
	public static void main(String[] args) {
		System.out.println("BEGIn");
		Object obj = new Object();
		Blackhole.enter(obj);
		System.out.println("END");
	}
}

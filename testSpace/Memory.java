package testSpace;

public class Memory {

	public static void main(String[] args) {
		long free = Runtime.getRuntime().freeMemory() /1024 /1024;
		long total = Runtime.getRuntime().totalMemory() /1024 /1024;
		long max = Runtime.getRuntime().maxMemory() /1024 /1024;

		System.out.println("free:"+free+"\n"+"total: "+total+"\n"+"max: "+max);
	}
}

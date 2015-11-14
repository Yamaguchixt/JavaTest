package TwoPhaseTermination.shutDown;

public class Main {
	public static void main(String[] args) {
		System.out.println("main:BEGIN");
		Thread.setDefaultUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler(){
					public void uncaughtException(Thread thread, Throwable exception){
						System.out.println("****");
						System.out.println("UncaughtExceptionHandler:BEGIN");
						System.out.println();
					}
				}
	}
}

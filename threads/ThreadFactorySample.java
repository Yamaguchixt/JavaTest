package threads;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
public class ThreadFactorySample {
	public static void main(String[] args) {
		ThreadFactory factory = Executors.defaultThreadFactory();
		factory.newThread(new Runnable(){
			public void run(){
				for(int i=0;i <100; i++){
					System.out.println("Nice");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

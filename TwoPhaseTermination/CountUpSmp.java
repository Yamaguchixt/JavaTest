package TwoPhaseTermination;

public class CountUpSmp {
	public static void main(String[] args) {
		System.out.println("main: BEGIN");
		try{
			CountupThread t = new CountupThread();
			t.start();
			Thread.sleep(10000);
			System.out.println("main: shutdownRequest");
			t.shutdownRequest();
			System.out.println("main: join");
			t.join();
		}catch(InterruptedException e){

		}
		System.out.println("main: END");
	}
}

class CountupThread extends Thread{
	private long counter =0;
	private volatile boolean shutdownRequested = false;
	public void shutdownRequest(){
		shutdownRequested = true;
		interrupt(); //threadがsleep,waitしているなら起こす
	}
	public boolean isShutdownRequested(){
		return shutdownRequested;
	}
	public final void run(){
		try{
			while(!isShutdownRequested()){
				doWork();
			}
		}catch(InterruptedException e){

		}finally{
			doShutdown();
		}
	}
	private void doWork() throws InterruptedException {
		counter++;
		System.out.println("doWork : counter = " + counter);
		Thread.sleep(500);
	}
	private void doShutdown(){
		System.out.println("doShutdown: counter = " + counter);
	}
}

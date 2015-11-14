package WorkerThread;

public class WorkerThread extends Thread{
	private final Channel channel;
	private volatile boolean terminated = false;
	public WorkerThread(String name,Channel channel){
		super(name);
		this.channel = channel;
	}
	public void run(){
		try{
			while(!terminated){
				try{
					Request request = channel.takeRequest();//本来はexecutable インタフェイスでうける
					request.execute();
				}catch (InterruptedException e){
					terminated = true;
				}
			}
		 }finally{
			 System.out.println(Thread.currentThread().getName() + " is terminated.");
		 }
	}
	public void stopThread(){
		terminated = true;
		interrupt();
	}
}

package ThreadPerMessage;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadPerMessageSmp {
	public static void main(String[] args) {
		System.out.println("main BEGIN");

		Host host = new Host();

		ExecutorHost Ehost = new ExecutorHost(
				new Executor(){
					public void execute(Runnable r){
						new Thread(r).start();
					}
				}
		);

		/*E*/host.request(10,'A');
		/*E*/host.request(20,'B');
		/*E*/host.request(30,'C');
		System.out.println("main END");

		System.out.println("main ExecutorService BEGIN");

		ExecutorService executorService = Executors.newCachedThreadPool();
		ExecutorHost esh = new ExecutorHost(executorService);
		try{
			esh.request(10,'D');
			esh.request(20,'E');
			esh.request(30,'F');
		}finally{
			executorService.shutdown();
			System.out.println("main ExecutorService END");
		}

		System.out.println("main: scheduledExecutorService BEGIN");
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
		ScheduledExecutorServiceHost shost = new ScheduledExecutorServiceHost(ses);
		try{
			shost.request(20, 'G');
			shost.request(15, 'H');
			shost.request(20, 'I');
		}finally{
			ses.shutdown();
			System.out.println("main: scheduledExecutorService END");
		}
	}
}

class Host{
	protected final Helper helper = new Helper();
	public void request(final int count,final char c){
		System.out.println("    request(" + count + ", " + c + ") BEGIN");
		new Thread(){
			public void run(){
				helper.handle(count,c);
			}
		}.start();
		System.out.println("    request(" + count + ", " + c + ") END");
	}
}

class ThreadFactoryHost extends Host{
	private final ThreadFactory threadFactory;
	public ThreadFactoryHost(ThreadFactory threadFactory){
		this.threadFactory = threadFactory;
	}
	public void request(final int count,final char c){
		System.out.println("    request(" + count + ", " + c + ") BEGIN");

		threadFactory.newThread(new Runnable(){
			public void run(){
				helper.handle(count,c);
			}
		}).start();

		System.out.println("    request(" + count + ", " + c + ") END");
	}
}

class ExecutorHost extends Host{
	private final Executor executor;
	public ExecutorHost(Executor executor){
		this.executor = executor;
	}
	public void request(final int count,final char c){
		System.out.println("    request(" + count + ", " + c + ") BEGIN");

		executor.execute(
				new Runnable(){
					public void run(){
						helper.handle(count,c);
					}
				});

		System.out.println("    request(" + count + ", " + c + ") END");
	}
}

class ScheduledExecutorServiceHost extends Host{
	private final ScheduledExecutorService scheduledExecutorService;
	public ScheduledExecutorServiceHost(ScheduledExecutorService ses){
		this.scheduledExecutorService = ses;
	}
	public void request(final int count,final char c){
		System.out.println("    request(" + count + ", " + c + ") BEGIN");
		scheduledExecutorService.schedule(
				new Runnable(){
					public void run(){
						helper.handle(count,c);
					}
				},
				3L,
				TimeUnit.SECONDS
		);
		System.out.println("    request(" + count + ", " + c + ") END");
	}
}

class Helper{
	public void handle(int count, char c){
		System.out.println("    handle(" + count +", " + c + ") BEGIN");
		for(int i = 0;i < count; i++){
			slowly();
			System.out.print(c);
		}
		System.out.println("");
		System.out.println("    handle(" + count + ", " + c + ") END");
	}
	private void slowly(){
		try{
			Thread.sleep(100);
		} catch(InterruptedException e){
		}
	}
}

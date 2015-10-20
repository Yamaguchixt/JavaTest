package ThreadSpecificStorage;


public class Log {
	private static final ThreadLocal<ThreadSpecificLog> TSLogCollection = new ThreadLocal<ThreadSpecificLog>();
	public static void println(String s){
		getTSLog().println(s);
	}
	public static void close(){
		getTSLog().close();
	}
	private static ThreadSpecificLog getTSLog(){
		ThreadSpecificLog TSLog = TSLogCollection.get();
		if(TSLog == null){
			TSLog = new ThreadSpecificLog(Thread.currentThread().getName() + "-log.txt");
			TSLogCollection.set(TSLog);
			startWatcher(TSLog);
		}
		return TSLog;
	}
	private static void startWatcher(final ThreadSpecificLog TSLog){
		//終了を監視されるスレッド
		final Thread target = Thread.currentThread();
		//targetを監視するスレッド
		final Thread watcher = new Thread(){
			public void run(){
				System.out.println("startWatcher for " + target.getName() + " BEGIN");
				try{
					target.join();
				}catch(InterruptedException e){
				}
				TSLog.close();
				System.out.println("startWatcher for " + target.getName() + " END");
			}
		};
		//監視の開始
		watcher.start();
	}
}

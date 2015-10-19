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
		}
		return TSLog;
	}
}

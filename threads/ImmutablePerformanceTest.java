package threads;

public class ImmutablePerformanceTest {
	private static final long CALL_COUNT = 1000000000L;
	public static void main(String[] args) {
		trial("notSynch",CALL_COUNT, new NotSynch());
		trial("Synch",CALL_COUNT,new Synch());
	}
	private static void trial(String msg,long count,Object obj){
		System.out.println(msg + ": BEGIN");
		long startTime = System.currentTimeMillis();
		for(int i =0; i < count; i++){
			obj.toString();
		}
		System.out.println(msg + ": END");
		System.out.println("Elapsed time = " + (System.currentTimeMillis() - startTime) + "msec.");
	}
}
	class NotSynch{
		private final String name = "NotSynch";
		public String toString(){
			return "[ " + name + " ]";
		}
	}
	class Synch{
		private final String name = "Synch";
		public synchronized String toString(){
			return "[ " + name + " ]";
		}
	}


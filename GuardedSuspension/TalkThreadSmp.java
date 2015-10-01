package GuardedSuspension;

public class TalkThreadSmp {
	public static void main(String[] args) {
		RequestQueue requestQueue1 = new RequestQueue();
		RequestQueue requestQueue2 = new RequestQueue();
		requestQueue1.putRequest(new Request("Hello"));
		new TalkThread(requestQueue1,requestQueue2,"Alice").start();
		new TalkThread(requestQueue2,requestQueue1,"Bob").start();
	}

}
class TalkThread extends Thread{
	private final RequestQueue input;
	private final RequestQueue output;
	public TalkThread(RequestQueue input,RequestQueue output,String name){
		super(name);
		this.input = input;
		this.output = output;
	}
	public void run(){
		System.out.println(Thread.currentThread().getName() + ":BEGIN ");
		for(int i=0; i < 20; i++){
			Request request1 = input.getRequest();
			System.out.println(Thread.currentThread().getName() + " gets  " + request1);

			Request request2 = new Request(request1.getName() + "!");
			System.out.println(Thread.currentThread().getName() + " puts  " + request2);
			output.putRequest(request2);
		}
		System.out.println(Thread.currentThread().getName() + ":END");
	}
}


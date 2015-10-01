package GuardedSuspension;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ThreadFactory;


public class GuardedSuspensionSmp {
	public static void main(String[] args) {
		RequestQueue requestQueue = new RequestQueue();
		ThreadFactory threadFactory = java.util.concurrent.Executors.defaultThreadFactory();
		threadFactory.newThread(new ClientThread(requestQueue,"Alice",3141592L)).start();
		threadFactory.newThread(new ServerThread(requestQueue,"Bob",6535897L)).start();
	}
}

class Request{
	private final String name;
	public Request(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public String toString(){
		return "[ Request " + name + " ]";
	}
}

class RequestQueue{
	private final Queue<Request> queue = new LinkedList<Request>();
	public synchronized Request getRequest(){
		while(queue.peek() == null){
			try{
				wait();
			}catch(InterruptedException e){
			}
		}
		return queue.remove();
	}
	public synchronized void putRequest(Request request){
		queue.offer(request);
		notifyAll();
	}
}

class ClientThread implements Runnable{
	private final String name;
	private final Random random;
	private final RequestQueue requestQueue;
	public ClientThread(RequestQueue requestQueue,String name,long seed){
		this.name = name;
		this.requestQueue = requestQueue;
		this.random = new Random(seed);
	}
	public void run(){
		for(int i =0; i < 1000; i++){
			Request request = new Request("No." + i);
			System.out.println(Thread.currentThread().getName() + " requests " + request);
			requestQueue.putRequest(request);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
			}
		}
	}
}

class ServerThread implements Runnable{
	private final String name;
	private final Random random;
	private final RequestQueue requestQueue;
	public ServerThread(RequestQueue requestQueue,String name,long seed){
		this.name = name;
		this.requestQueue = requestQueue;
		this.random = new Random(seed);
	}
	public void run(){
		for(int i = 0;i < 1000; i++){
			Request request = requestQueue.getRequest();
			System.out.println(Thread.currentThread().getName() + " handles  "+ request);
			try{
				Thread.sleep(random.nextInt(1000));
			}catch(InterruptedException e){
			}
		}
	}
}

package Future;
import util.U;


public class FutureSmp {
	public static void main(String[] args) {
		System.out.println("main BEGIN");
		Host host = new Host();
		Data data1 = host.request(10,'A');
		Data data2 = host.request(20,'B');
		Data data3 = host.request(30,'C');

		System.out.println("main otherJob BEGIN");
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
		}
		System.out.println("main otherJob END");

		System.out.println("data1 = " + data1.getContents());
		System.out.println("data2 = " + data2.getContents());
		System.out.println("data3 = " + data3.getContents());
		System.out.println("main END");
	}
}

class Host{
	public Data request(final int count,final char c){
		System.out.println(U.n()+ "	request(" + count + ", " + c + ") BEGIN");
		final FutureData future = new FutureData();
		new Thread(){
			public void run(){
				RealData realdata = new RealData(count,c);
				future.setRealData(realdata);
			}
		}.start();
		System.out.println(U.n()+"    request(" + count + ", " + c +") END");
		return future;
	}
}

interface Data{
	public abstract String getContents();
}

class FutureData implements Data{
	private RealData realData = null;
	private boolean ready = false;
	public synchronized void setRealData(RealData realdata){
		if(ready){
			return; //balk
		}
		this.realData = realdata;
		this.ready = true;
		notifyAll();
	}
	public synchronized String  getContents(){
		while(!ready){
			try{
				wait();
			}catch(InterruptedException e){
			}
		}
		return realData.getContents();
	}
}

class RealData implements Data{
	private final String content;
	public RealData(int count,char c){
		System.out.println(U.n()+"    making RealData(" + count + ", " + c + ") BEGIN");
		char[] buffer = new char[count];
		for(int i = 0;i < count; i++){
			buffer[i] = c;
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
			}
		}
		System.out.println(U.n()+"    making RealData(" + count + ", " + c + ") END");
		this.content = new String(buffer);
	}
	public String getContents(){
		return this.content;
	}
}

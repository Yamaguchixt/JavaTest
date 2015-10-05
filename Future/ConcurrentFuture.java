package Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import util.U;

public class ConcurrentFuture {
	public static void main(String[] args) {
		System.out.println("main BEGIN");
		CHost host = new CHost();
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

class CHost{
	public Data request(final int count,final char c){
		System.out.println(U.n()+ "	request(" + count + ", " + c + ") BEGIN");

		CFutureData future = new CFutureData(
			new Callable<RealData>(){
				public RealData call(){
					return new RealData(count,c);
				}
			}
		);

		new Thread(future).start();

		System.out.println(U.n()+"    request(" + count + ", " + c +") END");
		return future;
	}
}

class CFutureData extends FutureTask<RealData> implements Data{
	public CFutureData(Callable<RealData> callable){
		super(callable);
	}
	public String getContents(){
		String string = null;
		try{
			string = get().getContents();
		}catch(InterruptedException e){

		}catch(ExecutionException e){

		}
		return string;
	}
}
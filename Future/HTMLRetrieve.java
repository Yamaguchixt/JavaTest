package Future;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import util.U;

public class HTMLRetrieve {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		Content content1 = Retriever.retrieve("http://www.yahoo.com/");
		Content content2 = Retriever.retrieve("http://www.google.com/");
		Content content3 = Retriever.retrieve("http://www.hyuki.com/");

		saveToFile("yahoo.html",content1);
		saveToFile("google.html",content2);
		saveToFile("hyuki.html",content3);

		long end = System.currentTimeMillis();
		System.out.println(" Elapsed time = " + (end - start) + "msec.");
	}

	private static void saveToFile(String fileName,Content content){
		byte[] bytes = content.getBytes();
		try(FileOutputStream out = new FileOutputStream(fileName)){
			System.out.println(U.n() + ":Saving to " + fileName);
			for(int i=0; i < bytes.length; i++){
				out.write(bytes[i]);
			}
		}catch(IOException e){

		}
	}
}

class Retriever{//シングルスレッドバージョン
	public static Content retrieve(String urlstr){
		return new SynContentImpl(urlstr);
	}
}

class Retriever2{//マルチスレッド化
	public static Content retrieve(final String urlstr){
		final AsynContentImpl future = new AsynContentImpl();
		new Thread(){
			public void run(){
				future.setContent(new SynContentImpl(urlstr));
			}
		}.start();
		return future;
	}
}

class Retriever3{//concurrent.Callable
	public static Content retrieve(final String urlstr){
		AsynContentImpl2 future = new AsynContentImpl2(
				new Callable<SynContentImpl>(){
					public SynContentImpl call(){
						return new SynContentImpl(urlstr);
					}
				}
		);
		new Thread(future).start();
		return future;
	}
}

interface Content{
	public abstract byte[] getBytes();
}

class AsynContentImpl implements Content{
	private SynContentImpl syncontent;
	private boolean ready = false;
	public synchronized void setContent(SynContentImpl syncontent){
		this.syncontent = syncontent;
		this.ready = true;
		notifyAll();
	}
	public synchronized byte[] getBytes(){
		while(!ready){
			try{
				wait();
			}catch(InterruptedException e){

			}
		}
		return syncontent.getBytes();
	}
}

class AsynContentImpl2 extends FutureTask<SynContentImpl> implements Content{
	public AsynContentImpl2(Callable<SynContentImpl> callable){
		super(callable);
	}
	public byte[] getBytes(){
		byte[] bytes = null;
		try{
			bytes = get().getBytes();
		}catch(InterruptedException e){
		}catch(ExecutionException e){
		}
		return bytes;
	}
}


class SynContentImpl implements Content{
	private byte[] contentbytes;
	public SynContentImpl(String urlstr){
		System.out.println(U.n() + ": Getting " + urlstr);
		try{
			URL url = new URL(urlstr);
			DataInputStream in = new DataInputStream(url.openStream());
			byte[] buffer = new byte[1];
			int index = 0;
			try{
				while(true){//ここどうやって抜けるの？
					int c = in.readUnsignedByte();
					if(buffer.length <= index){
						byte[] largerbuffer = new byte[buffer.length * 2];
						System.arraycopy(buffer,0,largerbuffer,0,index);
						buffer = largerbuffer;
					}
					buffer[index++] = (byte)c;
				}
			}catch(EOFException e){

			}finally{
				in.close();
			}
			contentbytes = new byte[index];
			System.arraycopy(buffer,0,contentbytes,0,index);
		}catch(Exception e){

		}
	}
	public byte[] getBytes(){
		return contentbytes;
	}
}


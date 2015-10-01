package ReadWriteLock;

import java.util.Random;

public class ReadWriteLockSmp {
	public static void main(String[] args) {
		Data data = new Data(10);
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new WriterThread(data,"ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
		new WriterThread(data,"abcdefghijklmnopqrstuvwxyz").start();
	}
}

class Data{
	private final char[] buffer;
	private final ReadWriteLock lock = new ReadWriteLock();
	public Data(int size){
		this.buffer = new char[size];
		for(int i=0;i < buffer.length;i++){
			buffer[i] = '*';
		}
	}
	public char[] read() throws InterruptedException {
		lock.readLock();
		try{
			return doRead();
		}finally{
			lock.readUnlock();
		}
	}
	public void write(char c) throws InterruptedException{
		lock.writeLock();
		try{
			doWrite(c);
		}finally{
			lock.writeUnlock();
		}
	}
	private char[] doRead(){
		char[] newbuf = new char[buffer.length];
		for(int i=0;i < buffer.length;i++){
			newbuf[i] = buffer[i];
		}
		slowly();
		return newbuf;
	}
	private void doWrite(char c){
		for(int i=0;i < buffer.length; i++){
			buffer[i] = c;
			slowly();
		}
	}
	private void slowly(){
		try{
			Thread.sleep(50);
		}catch(InterruptedException e){
		}

	}
}

class ReadWriteLock{
	private int readingReaders = 0; //実際に読んでいる最中のスレッド数
	private int waitingWriters = 0; //書くのをまっているスレッドの数
	private int writingWriters = 0; //実際に書いているスレッドの数、必ず0か１．一瞬でも2以上にならない。
	private boolean preferWriter = true;

	public synchronized void readLock() throws InterruptedException{
		while(writingWriters > 0 || (preferWriter && waitingWriters > 0)){
			wait();
		}
		readingReaders++;
	}

	public synchronized void readUnlock(){
		readingReaders--;
		preferWriter = true;
		notifyAll();
	}

	public synchronized void writeLock() throws InterruptedException{
		waitingWriters++;
		try{
			while(readingReaders > 0 || writingWriters > 0){
				wait();
			}
		}finally{
			waitingWriters--;
		}
		writingWriters++;
	}

	public synchronized void writeUnlock(){
		writingWriters--;
		preferWriter = false;
		notifyAll();
	}

}

class WriterThread extends Thread{
	private static final Random random = new Random();
	private final Data data;
	private final String filler;
	private int index = 0;
	public WriterThread(Data data,String filler){
		this.data = data;
		this.filler = filler;
	}
	public void run(){
		try{
			while(true){
				char c = nextChar();
				data.write(c);
				Thread.sleep(random.nextInt(2000));
			}
		}catch(InterruptedException e){
		}
	}
	private char nextChar(){
		char c = filler.charAt(index);
		index++;
		if(index >= filler.length()){
			index = 0;
		}
		return c;
	}
}

class ReaderThread extends Thread{
	private final Data data;
	public ReaderThread(Data data){
		this.data = data;
	}
	public void run(){
		try{
			while(true){
				char[] readbuf = data.read();
				System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readbuf));
			}
		}catch(InterruptedException e){
		}
	}
}

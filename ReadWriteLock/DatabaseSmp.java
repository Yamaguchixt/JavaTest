package ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DatabaseSmp{
	public static void main(String[] args) {
		Database<String,String> database = new Database<String,String>();

		new AssignThread(database,"Alice","Alaska").start();
		new AssignThread(database,"Alice","Australia").start();
		new AssignThread(database,"Bob","Brazil").start();
		new AssignThread(database,"Bob","Bulgaria").start();

		for(int i=0;i<100;i++){
			new RetrieveThread(database,"Alice").start();
			new RetrieveThread(database,"Bob").start();
		}

		try{
			Thread.sleep(10000);
		}catch(InterruptedException e){
		}
		System.exit(0);
	}
}



class Database<K,V> {
	private final Map<K,V> map = new HashMap<K,V>();

	private final ReadWriteLock lock = new ReentrantReadWriteLock(true /* fair */);
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();

	public /*synchronized*/ void clear(){
		writeLock.lock();
		try{
			verySlowly();
			map.clear();
		}finally{
			writeLock.unlock();
		}
	}

	public /*synchronized*/ void assign(K key,V value){
		writeLock.lock();
		try{
			verySlowly();
			map.put(key, value);
		}finally{
			writeLock.unlock();
		}
	}

	public synchronized V retrieve(K key){
		readLock.lock();
		try{
			slowly();
			return map.get(key);
		}finally{
			readLock.unlock();
		}
	}

	private void slowly(){
		try{
			Thread.sleep(50);
		}catch(InterruptedException e){
		}
	}
	private void verySlowly(){
		try{
			Thread.sleep(500);
		}catch(InterruptedException e){
		}
	}
}

class RetrieveThread extends Thread{
	private final Database<String,String> database;
	private final String key;
	private static final AtomicInteger atomicCounter = new AtomicInteger(0);
	public RetrieveThread(Database<String,String> database,String key){
		this.database = database;
		this. key = key;
	}
	public void run(){
		while(true){
			int counter = atomicCounter.incrementAndGet();
			String value = database.retrieve(key);
			System.out.println(counter + ":" + key + " => " + value);
		}
	}
}

class AssignThread extends Thread{
	private static Random random = new Random(314159);
	private final Database<String,String> database;
	private final String key;
	private final String value;
	public AssignThread(Database<String,String> database,String key,String value){
		this.database = database;
		this.key = key;
		this.value = value;
	}
	public void run(){
		while(true){
			System.out.println(Thread.currentThread().getName() + ":assign(" + key + "," + value + ")");
			database.assign(key, value);
			try{
				Thread.sleep(random.nextInt(1000));
			}catch(InterruptedException e){
			}
		}
	}
}

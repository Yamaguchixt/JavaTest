package Balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class BalkingSmp {
	public static void main(String[] args) {
		Data data = new Data("data.txt","(empty)");
		new ChangerThread("ChangerThread",data).start();
		new SaverThread("SaverThread",data).start();
	}
}

class Data{
	private final String filename;
	private  String content;
	private boolean isChanged;
	public Data(String filename,String content){
		this.filename = filename;
		this.content = content;
		this.isChanged = true;
	}
	public synchronized void change(String newContent){
		this.content = newContent;
		isChanged = true;
	}
	public synchronized void save() throws IOException{
		if(!isChanged){
			return;
		}
		doSave();
		isChanged = false;
	}
	private void doSave() throws IOException {
		System.out.println(Thread.currentThread().getName() + " calls doSave,content = " + content);
		Writer writer = new FileWriter(filename);
		writer.write(content);
		writer.close();
	}
}

class SaverThread extends Thread{
	private final Data data;
	public SaverThread(String name,Data data){
		super(name);
		this.data = data;
	}
	public void run(){
		try{
			while(true){
				data.save();
				Thread.sleep(1000);
			}
		}catch(IOException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

class ChangerThread extends Thread{
	private final Data data;
	private final Random random = new Random();
	public ChangerThread(String name,Data data){
		super(name);
		this.data = data;
	}
	public void run(){
		try{
			for(int i = 0;true; i++){
					data.change("No."+i);
					Thread.sleep(random.nextInt(1000));
					data.save();
			}
		}catch(IOException e){
		}catch(InterruptedException e){
		}
	}
}
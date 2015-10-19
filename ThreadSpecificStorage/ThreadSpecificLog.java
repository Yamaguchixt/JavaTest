package ThreadSpecificStorage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ThreadSpecificLog {
	private PrintWriter writer = null;
	public ThreadSpecificLog(String filename){
		try{
			writer = new PrintWriter(new FileWriter(filename));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void println(String s){
		writer.println(s);
	}
	public void close(){
		writer.println("==== End of log ====");
		writer.close();
	}
}

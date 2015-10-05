package util;

public class U {
	public static String n(){
		return Thread.currentThread().getName();
	}
	public static void slowly(int count){
		try{
			Thread.sleep(count);
		}catch(InterruptedException e){

		}
	}
}

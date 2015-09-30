package threads;

public class Thread1 {
	public static void main(String[] args) {
		MyThread t = new MyThread();
		t.start();
		for(int i = 0; i < 1000;i++){
			System.out.print("Good");
		}
	}
}

 class MyThread extends Thread{
	public void run(){
		for(int i = 0; i < 1000; i++){
			System.out.print("Nice");
		}
	}
}

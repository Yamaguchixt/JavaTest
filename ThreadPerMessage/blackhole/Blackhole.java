package ThreadPerMessage.blackhole;

public class Blackhole {
	public static void enter(Object obj){
		System.out.println("step 1");
		magic(obj);
		System.out.println("step 2");
		synchronized(obj){
			System.out.println("step 3(never reached here");
		}
	}
	public static void magic(final Object obj){
		Thread thread = new Thread(){
			public void run(){
				synchronized (obj){
					synchronized(this){
						this.setName("Loced");
						this.notifyAll();
					}
					while(true){

					}
				}
			}
		};

		synchronized(thread){
			thread.setName("");
			thread.start();
			while(thread.getName().equals("")){
				try{
					thread.wait();
				}catch(InterruptedException e){

				}
			}
		}
	}
}

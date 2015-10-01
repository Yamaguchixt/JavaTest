package SingleThreadedExecution;

public class DeadLockSmp {
	public static void main(String[] args) {
		System.out.println("Testing EatherThread");
		Tool spoon = new Tool("Spoon");
		Tool fork = new Tool("Fork");
		new EaterThread("Alice",spoon,fork).start();
		new EaterThread("BoB",fork,spoon).start();
	}
}

class Tool{
	private final String name;
	Tool(String name){
		this.name = name;
	}
	public String toString(){
		return "[ " + name + " ]";
	}
}

class EaterThread extends Thread{
	private String name;
	private final Tool lefthand;
	private final Tool righthand;
	public EaterThread(String name,Tool lefthand,Tool righthand){
		this.name = name;
		this.lefthand = lefthand;
		this.righthand = righthand;
	}
	public void run(){
		while(true){
			eat();
		}
	}
	public void eat(){
		synchronized(lefthand){
			System.out.println(name + " takes up " + lefthand + " (left)." );
			synchronized(righthand){
				System.out.println(name + "takes up " + righthand + " (right).");
				System.out.println(name + "is eating now, yum yum !");
				System.out.println(name + "puts down " + righthand + " (right)");
			}
			System.out.println(name + "puts down " + lefthand + " (left).");
		}
	}
}


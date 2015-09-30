package SingleThreadedExecution;

public class SingleThreadedExecutionSmp {
	public static void main(String[] args) {
		System.out.println("Testing Gate, hit CTRL+C to exit");
		Gate gate = new Gate();
		new GateUserThread(gate,"Alice","Alaska").start();
		new GateUserThread(gate,"Bob","Brazil").start();
		new GateUserThread(gate,"Chris","Canada").start();
	}
}

class Gate{
	private int counter = 0;
	private String name ="";
	private String address =  "";
	public  void pass(String name,String address){
		this.counter++;
		this.name = name;
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){

		}
		this.address = address;
		check();
	}
	public  String toString(){
		return "No." + counter + ": " + name + ", " + address;
	}
	private void check(){
		if(name.charAt(0) != address.charAt(0)){
			System.out.println("***** BROKEN *****" + toString());
		}
	}
}

class GateUserThread extends Thread{
	private final Gate gate;
	private final String myname;
	private final String myaddress;
	public GateUserThread(Gate gate,String myname,String myaddress){
		this.gate = gate;
		this.myname = myname;
		this.myaddress = myaddress;
	}
	public void run(){
		System.out.println(myname + " BEGIN");
		while(true){
			gate.pass(myname, myaddress);
		}
	}
}



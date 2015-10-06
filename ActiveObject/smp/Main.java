package ActiveObject.smp;

public class Main {
	public static void main(String[] args) {
		ActiveObject activeObject = ActiveObjectFactory.createActiveObject();//Proxyが返ってきている
		new MakerClientThread("Alice",activeObject).start();
		new MakerClientThread("Bob",activeObject).start();
		new DisplayClientThread("Chris",activeObject).start();
	}
}

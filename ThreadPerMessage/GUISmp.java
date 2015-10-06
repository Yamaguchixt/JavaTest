package ThreadPerMessage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUISmp {
	public static void main(String[] args) {
		new Myframe();
	}
}

class Myframe extends JFrame implements ActionListener{
	public Myframe(){
		super("MyFrame");
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(new JLabel("Thread-Per-Message Sample"));
		JButton button = new JButton("Execute");
		getContentPane().add(button);
		button.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		Service.service();
	}
}

class Service{
	public static boolean working = false;
	private static Thread worker = null;
	/* ボタンおされたらその処理を並列実行する。出力はまざる
	public static void service(){
		ThreadFactory tf = Executors.defaultThreadFactory();
		tf.newThread(new Runnable(){public void run(){
			doService();
		}}).start();
	}
	*/
	/*処理実行中にボタンおされても無視する。
	 public static synchronized void service(){
		 if(working){
			 System.out.println("is baked.");
			 return;
		 }
		working = true;
		ThreadFactory tf = Executors.defaultThreadFactory();
		tf.newThread(new Runnable(){public void run(){
			doService();
		}}).start();
	 }
	 */
	//ボタン押されたら実行中の処理中止して新意処理開始。
	public static synchronized void service(){
		if(worker != null && worker.isAlive()){
			worker.interrupt();
			try{
				worker.join();
			}catch(InterruptedException e){

			}worker = null;
		}
		ThreadFactory tf = Executors.defaultThreadFactory();
		worker = tf.newThread(new Runnable(){public void run(){
			doService();
		}});
		worker.start();
	}




	public static /*synchronized*/ void doService(){
		try{
			System.out.println("service");
			for(int i =0;i <50;i++){
				System.out.print(".");
					Thread.sleep(50);
			}
			System.out.println("done");
		}catch(InterruptedException e){
			System.out.println("cancelled");
		}finally{
			working = false;
		}
	}
}

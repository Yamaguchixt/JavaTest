package WorkerThread.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame implements ActionListener {
	private final JLabel label = new JLabel("Event Dispatching Thread Sample");
	JButton button = new JButton("countUp");
	public MyFrame(){
		super("MyFrame");
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(label);
		getContentPane().add(button);
		button.addActionListener(this);//これどういう意味？
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == button){
			countUp();
		}
	}
	private void countUp(){
		for(int i=0;i <10;i++){
			System.out.println(Thread.currentThread().getName() + ":countUp:setText(" + i + ")");
			label.setText(""+ i);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){

			}
		}
	}
}

package ThreadPerMessage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public static void service(){
		System.out.println("service");
		for(int i =0;i <50;i++){
			System.out.print(".");
			try{
				Thread.sleep(100);
			}catch (InterruptedException e){

			}
		}
		System.out.println("done");
	}
}

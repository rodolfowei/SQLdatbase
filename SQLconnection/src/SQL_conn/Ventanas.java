package SQL_conn;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Ventanas {

	private JFrame frame;

	
	// The aplication dosnt need to be launched here 
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventanas window = new Ventanas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public Ventanas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

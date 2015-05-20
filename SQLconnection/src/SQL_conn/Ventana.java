package SQL_conn;

import java.awt.Image;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Ventana extends javax.swing.JFrame {

	
	ArrayList<Imagen> imagenes;
	String ruta,nombre;
	ProductsDB bd;
	int contador = 0;
	
	private JFrame frame;
	private JTextField textFieldruta;

	/**
	 * Launch the application. (This needs to be modified cause we don't want a main on this class
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldruta = new JTextField();
		textFieldruta.setBounds(29, 34, 208, 20);
		frame.getContentPane().add(textFieldruta);
		textFieldruta.setColumns(10);
		
		JButton BotonAbrirDB = new JButton("Abrir");
		
		//Think about putting the Action performed out of the initialize 
		
		BotonAbrirDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser elegirImagen = new JFileChooser();
				elegirImagen.setMultiSelectionEnabled(false);
				int o = elegirImagen.showOpenDialog(elegirImagen); 
				//Maybe in showOpenDialog the argument is not BotonAbrirDB
				if (o == JFileChooser.APPROVE_OPTION ) {
					ruta = elegirImagen.getSelectedFile().getAbsolutePath();
					nombre = elegirImagen.getSelectedFile().getName();
					textFieldruta.setText(ruta);
					Image preview = Toolkit.getDefaultToolkit().getImage(ruta);
					if (preview != null) {
						
					}
				}
				
			}
		});
		BotonAbrirDB.setBounds(312, 33, 89, 23);
		frame.getContentPane().add(BotonAbrirDB);
	}
}

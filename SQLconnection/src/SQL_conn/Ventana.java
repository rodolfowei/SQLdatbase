package SQL_conn;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ventana extends javax.swing.JFrame {

	
	ArrayList<Imagen> imagenes;
	String ruta,nombre;
	ProductsDB bd;
	int contador = 0;
	
	//Window objects
	
	private JFrame frame;
	private JTextField textFieldruta;
	private JButton BotonAbrirDB;
	private JLabel jLabelpreview;


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
		
		BotonAbrirDB = new JButton("Abrir");
		
		
		BotonAbrirDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonAbrirDBActionPerformed(evt);
			}
		});
	
		BotonAbrirDB.setBounds(312, 33, 89, 23);
		frame.getContentPane().add(BotonAbrirDB);
		
		jLabelpreview = new JLabel("Preview");
		jLabelpreview.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelpreview.setBounds(29, 84, 123, 129);
		frame.getContentPane().add(jLabelpreview);
	}
	
	private void BotonAbrirDBActionPerformed(ActionEvent evt){
		final JFileChooser elegirImagen = new JFileChooser();
		elegirImagen.setMultiSelectionEnabled(false);
		int o = elegirImagen.showOpenDialog(elegirImagen); 
		
		if (o == JFileChooser.APPROVE_OPTION ) {
			ruta = elegirImagen.getSelectedFile().getAbsolutePath();
			nombre = elegirImagen.getSelectedFile().getName();
			textFieldruta.setText(ruta);
			Image preview = Toolkit.getDefaultToolkit().getImage(ruta);
			if (preview != null) {
				jLabelpreview.setText("");
				ImageIcon icon = new ImageIcon(preview.getScaledInstance(jLabelpreview.getWidth(),jLabelpreview.getHeight(), Image.SCALE_DEFAULT));
				jLabelpreview.setIcon(icon);
				
				
			}
		}
		
	}
	
}

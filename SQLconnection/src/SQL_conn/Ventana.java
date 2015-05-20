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
import javax.swing.JPanel;

public class Ventana extends javax.swing.JFrame {

	
	ArrayList<Imagen> imagenes;
	String ruta,nombre;
	ProductsDB bd;
	int contador = 0;
	
	//Window objects
	
	private JFrame frame;
	private JTextField textFieldruta;
	private JButton BotonAbrirDB;
	private JLabel jLabelpreview1;


	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 571, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 535, 200);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		jLabelpreview1 = new JLabel("Preview1");
		jLabelpreview1.setBounds(10, 74, 96, 100);
		panel.add(jLabelpreview1);
		jLabelpreview1.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldruta = new JTextField();
		textFieldruta.setBounds(31, 30, 355, 20);
		panel.add(textFieldruta);
		textFieldruta.setColumns(10);
		
		BotonAbrirDB = new JButton("Abrir");
		BotonAbrirDB.setBounds(422, 29, 89, 23);
		panel.add(BotonAbrirDB);
		
		
		BotonAbrirDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonAbrirDBActionPerformed(evt);
			}
		});
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
				jLabelpreview1.setText("");
				ImageIcon icon = new ImageIcon(preview.getScaledInstance(jLabelpreview1.getWidth(),jLabelpreview1.getHeight(), Image.SCALE_DEFAULT));
				jLabelpreview1.setIcon(icon);
				
				
			}
		}
		
	}
}

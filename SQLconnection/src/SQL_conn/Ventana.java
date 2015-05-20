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
	
	private JPanel panel1;
	private JLabel jLabelpreview1;
	private JPanel subpanel1;
	private JPanel subpanel2;
	private JButton btnprevious;
	private JButton btnNext;
	private JLabel jlabelpreview2;


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
		
		panel1 = new JPanel();
		panel1.setBounds(10, 11, 535, 335);
		frame.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		subpanel1 = new JPanel();
		subpanel1.setBounds(0, 0, 535, 200);
		panel1.add(subpanel1);
		subpanel1.setLayout(null);
		
		jLabelpreview1 = new JLabel("Preview1");
		jLabelpreview1.setBounds(10, 74, 96, 100);
		subpanel1.add(jLabelpreview1);
		jLabelpreview1.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldruta = new JTextField();
		textFieldruta.setBounds(31, 30, 355, 20);
		subpanel1.add(textFieldruta);
		textFieldruta.setColumns(10);
		
		BotonAbrirDB = new JButton("Abrir");
		BotonAbrirDB.setBounds(422, 29, 89, 23);
		subpanel1.add(BotonAbrirDB);
		
		subpanel2 = new JPanel();
		subpanel2.setBounds(0, 211, 535, 124);
		panel1.add(subpanel2);
		subpanel2.setLayout(null);
		
		btnprevious = new JButton("Previous");
		btnprevious.setBounds(29, 90, 89, 23);
		subpanel2.add(btnprevious);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(411, 90, 89, 23);
		subpanel2.add(btnNext);
		
		jlabelpreview2 = new JLabel("Preview2");
		jlabelpreview2.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelpreview2.setBounds(192, 11, 146, 102);
		subpanel2.add(jlabelpreview2);
		
		
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

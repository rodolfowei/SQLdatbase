package SQL_conn;

import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

import java.awt.Color;

public class Ventana extends javax.swing.JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Imagen> imagenes;
	String ruta,nombre;
	ProductsDB bd;
	int contador = 0;
	
	//Window objects
	
	private JFrame frame;
	private JTextField textFieldruta;
	
	
	private JLabel jLabelpreview1;
	private JLabel jlabelpreview2;
	
	private JButton BotonAbrirDB;   //boton1
	private JButton btnprevious;    //boton4
	private JButton btnNext;        //boton3
	private JButton btnGuardar;     //boton2
	private JButton btnDescargar;   //boton5
	private JPanel panel1;
	private JPanel panel2;


	public Ventana() {
		initialize();
		bd = new ProductsDB("localhost:8889", "root", "root"); //Check if the parameters are correct
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Object creation
		setFrame(new JFrame());
		getFrame().setBounds(600, 600, 600, 600);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBounds(23, 11, 524, 266);
		panel1.setBorder(BorderFactory.createTitledBorder("Guardar imagen"));
		frame.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		//text fields
		textFieldruta = new JTextField();
		textFieldruta.setBounds(23, 24, 355, 20);
		panel1.add(textFieldruta);
		textFieldruta.setColumns(10);
		
		//buttons
		BotonAbrirDB = new JButton("Abrir");
		BotonAbrirDB.setBounds(408, 23, 89, 23);
		panel1.add(BotonAbrirDB);
		
		//labels for images
		jLabelpreview1 = new JLabel("Preview1");
		jLabelpreview1.setBackground(Color.BLACK);
		jLabelpreview1.setBounds(99, 65, 160, 179);
		panel1.add(jLabelpreview1);
		jLabelpreview1.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(408, 118, 89, 23);
		panel1.add(btnGuardar);
		
		panel2 = new JPanel();
		panel2.setBounds(23, 288, 524, 247);
		panel2.setBorder(BorderFactory.createTitledBorder("Leer Imagen"));
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(296, 194, 89, 23);
		panel2.add(btnNext);
		
		btnprevious = new JButton("Previous");
		btnprevious.setBounds(138, 194, 89, 23);
		panel2.add(btnprevious);
		
		jlabelpreview2 = new JLabel("Preview2");
		jlabelpreview2.setBounds(184, 23, 158, 160);
		panel2.add(jlabelpreview2);
		
		
		jlabelpreview2.setHorizontalAlignment(SwingConstants.CENTER);
		btnDescargar = new JButton("Descargar");
		btnDescargar.setBounds(410, 64, 89, 23);
		panel2.add(btnDescargar);
		
		
		btnDescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonDescargarDBActionPerformed(evt);
			}
		});
		
		
		btnprevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonPreviousDBActionPerformed(evt);
			}
		});
		
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonNextDBActionPerformed(evt);
			}
		});
		
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonGuardarDBActionPerformed(evt);
			}
		});
		
		
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
	
	private void BotonGuardarDBActionPerformed(ActionEvent evt){
		if(!ruta.equals("")){
			if(bd.guardarImagen(ruta, nombre)){
				JOptionPane.showMessageDialog(null, "Imagen guardada Exitosamente en la base de datos", "Hecho", JOptionPane.INFORMATION_MESSAGE);
				textFieldruta.setText("");
				jLabelpreview1.setIcon(null);
				refrescarCarrusel(0);
			}else{
				JOptionPane.showMessageDialog(null, "La imagen no ha podido ser Guardada en la base de datos","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private void BotonDescargarDBActionPerformed(ActionEvent evt){
		
		System.out.println(imagenes.get(contador).getNombre());
		try{
			final JFileChooser elegirCarpeta = new JFileChooser();
			elegirCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int o = elegirCarpeta.showSaveDialog(elegirCarpeta); //Don't know yet if here goes elegirCarpeta or this 
			if(o == JFileChooser.APPROVE_OPTION){
				String path = elegirCarpeta.getSelectedFile().getAbsolutePath();
				System.out.println(path.length()+" : "+path);
				String aux = imagenes.get(contador).getNombre();
				StringTokenizer token = new StringTokenizer(aux,".");
				token.nextToken();
				String formato = token.nextToken();
				ImageIO.write((RenderedImage) imagenes.get(contador).getImagen(), formato, new File(path+"\\"+imagenes.get(contador).getNombre()));
				}
		}catch(Exception ex){
			Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
		}
	}	
	
	private void BotonNextDBActionPerformed(ActionEvent evt){
		contador++;
		refrescarCarrusel(contador);
		
	}
	
	private void BotonPreviousDBActionPerformed(ActionEvent evt){
		if((contador-1)>=0){
			contador--;
			refrescarCarrusel(contador);
		}
	}
	
	private void refrescarCarrusel(int p){
		imagenes = bd.getImages();
		if(p < imagenes.size()){
			if(imagenes.size()>0){
				System.out.println("leyendo Imagen");
				ImageIcon icon = new ImageIcon(imagenes.get(p).getImagen().getScaledInstance(jlabelpreview2.getWidth(), jlabelpreview2.getHeight(), Image.SCALE_DEFAULT));
				jlabelpreview2.setText("");
				jlabelpreview2.setIcon(icon);
				jlabelpreview2.updateUI();
			}
		}else{
			contador--;
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

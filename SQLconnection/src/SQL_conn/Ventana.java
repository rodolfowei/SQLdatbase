package SQL_conn;

import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
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
		
	private JPanel panel1;
	private JPanel subpanel1;
	private JPanel subpanel2;
	
	private JLabel jLabelpreview1;
	private JLabel jlabelpreview2;
	
	private JButton BotonAbrirDB;   //boton1
	private JButton btnprevious;    //boton4
	private JButton btnNext;        //boton3
	private JButton btnGuardar;     //boton2
	private JButton btnDescargar;   //boton5


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
		BotonAbrirDB.setBounds(436, 29, 89, 23);
		subpanel1.add(BotonAbrirDB);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonGuardarDBActionPerformed(evt);
			}
		});
		btnGuardar.setBounds(436, 113, 89, 23);
		subpanel1.add(btnGuardar);
		
		subpanel2 = new JPanel();
		subpanel2.setBounds(0, 211, 535, 124);
		panel1.add(subpanel2);
		subpanel2.setLayout(null);
		
		btnprevious = new JButton("Previous");
		btnprevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonPreviousDBActionPerformed(evt);
			}
		});
		btnprevious.setBounds(29, 90, 89, 23);
		subpanel2.add(btnprevious);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonNextDBActionPerformed(evt);
			}
		});
		btnNext.setBounds(411, 90, 89, 23);
		subpanel2.add(btnNext);
		
		jlabelpreview2 = new JLabel("Preview2");
		jlabelpreview2.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelpreview2.setBounds(192, 11, 146, 102);
		subpanel2.add(jlabelpreview2);
		
		btnDescargar = new JButton("Descargar");
		btnDescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BotonDescargarDBActionPerformed(evt);
			}
		});
		btnDescargar.setBounds(436, 11, 89, 23);
		subpanel2.add(btnDescargar);
		
		
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
		}
	}
}

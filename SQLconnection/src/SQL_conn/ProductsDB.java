package SQL_conn;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;




public class ProductsDB {
	
	Connection conexion;
	Statement st;
	
	public ProductsDB(String host, String user, String pass) {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://"+host+"/pruebas", user, pass);
			st = conexion.createStatement();
			
		}catch (Exception ex) {
			Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public boolean guardarImagen(String ruta, String nombre)
	{
		String insert = "insert into Imagenes (imagen, nombre) values(?,?)";
		FileInputStream finStream = null;
		PreparedStatement prepst = null;
		
		try {
			conexion.setAutoCommit(false);
			File file = new File(ruta);
			finStream = new FileInputStream(file);
			prepst = conexion.prepareStatement(insert);
			prepst.setBinaryStream(1, finStream, (int)file.length());
			prepst.setString(2, nombre);
			prepst.executeUpdate();
			conexion.commit();
			return true;
		}catch (Exception ex){
			Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
			try {
				prepst.close();
				finStream.close();
			} catch (Exception ex) {
				Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return false;
		
	}
	
	ArrayList<Imagen> getImages(){
		ArrayList<Imagen> lista = new ArrayList<Imagen>();
		try {
			ResultSet rs = st.executeQuery("SELECT imagen, nombre From imagenes");
			
			while(rs.next())
			{
				Imagen imagen = new Imagen();
				java.sql.Blob blob = rs.getBlob("imagen");
				String nombre = rs.getObject("nombre").toString();
				byte[] data = blob.getBytes(1, (int)blob.length());
				BufferedImage img = null;
				
				try{
					img = ImageIO.read(new ByteArrayInputStream(data));
				}catch (IOException ex){
					Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				imagen.setImagen(img);
				imagen.setNombre(nombre);
				lista.add(imagen);
			}
			rs.close();
		} catch (SQLException ex){
			Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
		}
		return lista;
	}
		
		
		
	

}

package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.plaf.SliderUI;

import Ventanas.BusquedaVuelos;
/**
 * Clase: Base de Datos
 * @author Joseba Villanueva y Leyre Ibañez
 *
 */
public class BD {
		
	private static Connection con;
	private static Statement stmt;
	private static String precio;
	
	/**
	 * Metodo que crea una sentencia para acceder a la base de datos 
	 */
	public static void crearSentencia()
	{
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que permite conectarse a la base de datos
	 */
		public static void conectar()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			con= DriverManager.getConnection("jdbc:sqlite:baseDeDatos.db");
			crearSentencia();
		}catch(Exception e)
		{
			System.out.println("No se ha podido conectar a la base de datos");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que cierra una sentencia 
	 */
	
	public static void cerrarSentencia()
	{
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		/**
	 * Metodo que permite desconectarse de la base de datos
	 */
	public static void desconectar()
	{
		try {
			cerrarSentencia();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*A partir de aquí hacemos las consultas específicas de cada proyecto*/
	
	/**
	 * 
	 * @param nom: Nombre introducido por el usuario
	 * @param con: Contraseña introducida por el usuario
	 * @return : 
	 * 			0 - Si no existe el usuario
	 * 			1 - Si sí existe el usuario pero la contraseña no es correcta
	 * 			2 - Si el nombre de usuario es correcto y la contraseña también
	 */
	public static int existeUsuario(String n, String c){
		
		String query = "SELECT * FROM usuario WHERE nombre='"+n+"'";
		ResultSet rs = null;
		int resul=0;
		try {
			rs = stmt.executeQuery(query);
			if(rs.next()){ //Aquí estamos comprobando si la SELECT ha devuelto alguna fila
				String nom = rs.getString("nombre");
				String con = rs.getString("contrasenia");
				if(!n.equals(n))
					resul=0;
				else if(!c.equals(c))
					resul=1;
				else
					resul=2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resul;
		}
		/**
		 * 
		 * @param n: String que guarda el nombre del usuario
		 * @param c: String que guarda la cobtraseña del usuario
		 */
		public static void registrarUsuario(String n, String c){
			String query= "INSERT INTO Usuario(nombre,contrasenia) VALUES('"+n+"','"+c+"')";
			try {
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		/**
		 * 
		 * @param origen: Origen del vuelo
		 * @param destino: Destino del vuelo
		 * @param dia: Dia en el que se desea realizar el vuelo
		 * @param mes: Mes en el que se desea realizar el vuelo
		 * @param anio: Año en el que se desea realizar el vuelo
		 * @param precioMin: Precio minimo por el que se desea buscar el vuelo
		 * @param precioMax: Precio maximo por el que se desea buscar el vuelo
		 * @return: Devuelve el numero de vuelos que cumplen los requisitos de busqueda
		 */
		public static int contarVueltos(String origen, String destino, int dia, String mes, int anio, int precioMin , int precioMax){
			String query = "SELECT COUNT(*) FROM vuelos WHERE origen='"+origen+"' AND destino='"+destino+"' AND dia="+dia+" AND mes='"+mes+"' AND año="+anio+" AND precio>="+precioMin +" AND precio<="+precioMax;
            int cont=0;
			try {
				ResultSet rs = stmt.executeQuery(query);
				cont = rs.getInt(1);
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return cont;
		}
		
		/**
		 * 
		 * @param origen: Origen del vuelo
		 * @param destino: Destino del vuelo
		 * @param dia: Dia del vuelo
		 * @param mes: Mes del vuelo
		 * @param anio: Año del vuelo
		 * @param precioMin: Precio minimo del vuelo
		 * @param precioMax: Precio maximo del vuelo 
		 * @return devuelve los datos del vuelo
		 */
		public static Object[][] volcarDatosVuelos(String origen, String destino, int dia, String mes, int anio, int precioMin , int precioMax) {
            //insertamos tantas filas como vuelos de la base de datos haya para volcar
			int cont = contarVueltos(origen, destino, dia, mes, anio, precioMin, precioMax);
			Object [][] datos =new Object[cont][8];
            int i=0;
            //cogemos mediante un select los vuelos disponibles respecto a los datos que hayamos elegido
            String query = "SELECT * FROM vuelos WHERE origen='"+origen+"' AND destino='"+destino+"' AND dia="+dia+" AND mes='"+mes+"' AND año="+anio+" AND precio>="+precioMin +" AND precio<="+precioMax;
                       
            try {
                            ResultSet rs = stmt.executeQuery(query);
                                i=0;
	                            while(rs.next()){
	                                            datos[i][0] = rs.getString("origen");
	                                            datos[i][1] = rs.getString("destino");
	                                            datos[i][2] = new Integer(rs.getInt("duracion"));
	                                            datos[i][3] = new Integer(rs.getInt("precio"));
	                                            datos[i][4] = new Integer(rs.getInt("hora"));
	                                            datos[i][5] = new Integer(rs.getInt("dia"));
	                                            datos[i][6] = rs.getString("mes");
	                                            datos[i][7] = new Integer(rs.getInt("año"));
	                                            i++;
	                            }
                            
	                           rs.close();
                            
            } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
            }
            if(i>0)
            	return datos;
            else
            	return null;

		}
		
		/**
		 * Creamos este método para seleccionar de la base de datos los origenes y destinos que sean diferentes entre si
		 * y no se repitan, ya que solo queremos que cada uno se nos muestre una vez(DISTINCT).
		 * @param opcion: Si la opción que le pasamos es "o", se nos mostraran los origenes, si es "d" los destinos. (A este método le llamamos desde los ComBox de la ventana principal).
		 * @return 
		 */
		public static String[] obtenerOrigenDestino(char opcion){
			String query; 
			ArrayList<String> datos = new ArrayList<String>(); 
			String opciones[]=null;
			if(opcion=='o')
				query = "SELECT DISTINCT(origen) FROM vuelos";
			else
				query = "SELECT DISTINCT(destino) FROM vuelos";
			try {
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){ //mientras haya más origenes o destinos...
					//En el arrayList datos vamos a meter todos los origenes y destinos
					String s = rs.getString(1);
					datos.add(s);
				}
				rs.close();
				//le damos al array opciones una longitud de datos.size, es decir, el numero de origenes y destinos que haya
				opciones = new String[datos.size()];
				//recorremos el array opciones y vamos guardando los datos del array datos en el array opciones
				for(int i=0;i<datos.size();i++){
					String s = datos.get(i);
					opciones[i] = s;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return opciones;
		}
		
		/**
		 * 
		 * @param precioMin: Precio minimo del hotel
		 * @param precioMax: Precio maximo hotel
		 * @param lugar: Localizacion del hotel
		 * @return: Numero de hoteles
		 */
		public static int contarHoteles(int precioMin , int precioMax, String lugar){
			String query = "SELECT COUNT(*) FROM hoteles WHERE precio>='"+precioMin +"' AND precio<='"+precioMax+"' AND Lugar='"+lugar+"'";
            int cont=0;
			try {
				ResultSet rs = stmt.executeQuery(query);
				cont = rs.getInt(1);
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return cont;
		}
		
		/**
		 * 
		 * @param precioMin: Precio minimo seleccionado
		 * @param precioMax: Precio maximo seleccionado
		 * @param lugar: Lugar seleccionado
		 * @return
		 */
		public static Object[][] volcarDatosHoteles(int precioMin , int precioMax, String lugar) {
            int cont = contarHoteles(precioMin, precioMax, lugar);
			Object [][] datos =new Object[cont][5];
            int i=0;
            //cogemos mediante un select los vuelos disponibles respecto a los datos que hayamos elegido
             String query = "SELECT * FROM hoteles WHERE precio>='"+precioMin+"' AND precio<='"+precioMax+"' AND lugar='"+lugar+"'";
            try {
                            ResultSet rs = stmt.executeQuery(query);
                            int j=0;
                            while(rs.next()){
                            				
                            				datos[j][0] = new Integer(rs.getInt("precio"));
                                            datos[j][1] = rs.getString("lugar");                                            
                                            datos[j][2] = rs.getString("nombre");
                                            j++;
                            }
                            rs.close();
            } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
            }
            return datos;

		}
}




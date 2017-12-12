package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.plaf.SliderUI;

import Ventanas.BusquedaVuelos;

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
	/*
	public BD(){
		conectar();
	}*/
	
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
		
		public static void registrarUsuario(String n, String c){
			String query= "INSERT INTO Usuario(nombre,contrasenia) VALUES('"+n+"','"+c+"')";
			//No podemos REsultSet pq una INSERT no devuelve filas, solo inserta en la tabla
			try {
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		
		
		
		public static Object[][] volcarDatosVuelos(String origen, String destino, int dia, String mes, int anio, int precio) {
            Object [][] datos = new Object[1000][8];
            //cogemos mediante un select los vuelos disponibles respecto a los datos que hayamos elegido
            //SELECT * FROM vuelos WHERE VentanaPrincipal.origen=opciones1.getOrigenSeleccionado AND VentanaPrincipal.destino=opciones2.getDestinoSeleccionado AND precio=precio.getValor AND dia,mes,año...
            String query = "SELECT * FROM vuelos WHERE origen='"+origen+"' AND destino='"+destino+"' AND dia="+dia+" AND mes='"+mes+"' AND año="+anio+" AND precio='"+precio+"'";
            //System.out.println("dia origen: "+dia+"mes origen: "+mes+"año origen: "+anio);

           
            
            try {
                            ResultSet rs = stmt.executeQuery(query);
                            int i=0;
                            while(rs.next()){
                                            datos[i][0] = rs.getString("origen");
                                            datos[i][1] = rs.getString("destino");
                                            datos[i][2] = new Double(rs.getDouble("duracion"));
                                            datos[i][3] = new Double(rs.getDouble("precio"));
                                            datos[i][4] = new Double(rs.getDouble("hora"));
                                            datos[i][5] = new Double(rs.getDouble("dia"));
                                            datos[i][6] = rs.getString("mes");
                                            datos[i][7] = new Double(rs.getDouble("año"));
                                            i++;
                            }
                            rs.close();
            } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
            }
            return datos;


		}
		
		
		public static int obtenerPrecio(int precio){
			/*metodo que recoge los precios minimo y maximo en la selección de un vuelo y busca los vuelos con un precio
			entre esos dos valores*/
			//el precio que devolveremos será el precio que volcaremos en la select de volcarDatosVuelos

			if(BusquedaVuelos.valorSliderMin()<=precio && BusquedaVuelos.valorSliderMax()>=precio)
			return precio;
			else{
				System.out.println("no hay ningún precio entre esos valores");
			}
			return precio;
			
			
		}
		
		
		/**
		 * Creamos este método para seleccionar de la base de datos los origenes y destinos que sean diferentes entre si
		 * y no se repitan, ya que solo queremos que cada uno se nos muestre una vez(DISTINCT). Si la opción que le pasamos es "o",
		 * se nos mostraran los origenes, si es "d" los destinos. (A este método le llamamos desde los ComBox de la ventana principal).
		 * */
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
		
		
		
		
		
		public static Object[][] volcarDatosHoteles(int precio, String lugar) {
            Object [][] datos = new Object[1000][3];
            //cogemos mediante un select los vuelos disponibles respecto a los datos que hayamos elegido
            //SELECT * FROM vuelos WHERE VentanaPrincipal.origen=opciones1.getOrigenSeleccionado AND VentanaPrincipal.destino=opciones2.getDestinoSeleccionado AND precio=precio.getValor AND dia,mes,año...
            String query = "SELECT * FROM hoteles WHERE precio='"+precio+"' AND lugar='"+lugar+"'";
            //System.out.println("dia origen: "+dia+"mes origen: "+mes+"año origen: "+anio);

           
            
            try {
                            ResultSet rs = stmt.executeQuery(query);
                            int i=0;
                            while(rs.next()){
                            				
                            				datos[i][0] = new Double(rs.getDouble("precio"));
                                            datos[i][1] = rs.getString("lugar");                                            
                                            datos[i][2] = rs.getString("nombre");
                                            i++;
                            }
                            rs.close();
            } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
            }
            return datos;


		}
		
	
		
		
}




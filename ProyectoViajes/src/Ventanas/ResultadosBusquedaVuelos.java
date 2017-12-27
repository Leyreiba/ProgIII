package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import BaseDeDatos.BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class ResultadosBusquedaVuelos extends JFrame {

	private JPanel contentPane;
	private JTable tablaSurIda; 
	private JTable tablaSurVuelta;

	
	/**
	 * Create the frame.
	 */
	public ResultadosBusquedaVuelos(String origen, String destino, int dia, String mes, int anio, int precioMin, int precioMax, int diaVuelta, String mesVuelta, int anioVuelta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnBuscarOtro = new JButton("Buscar otro");
		panelSur.add(btnBuscarOtro);
		
		JButton btnComprar = new JButton("Comprar");
		panelSur.add(btnComprar);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numFilaIda, numFilaVuelta;
				//si no hay nada seleccionado aparece un mensaje de error para volver a seleccionar, ya que no puede seguir sin vuelo de ida 
				if(tablaSurIda.getSelectedRow()==-1){ //si no hay nada seleccionado
					JOptionPane.showMessageDialog(null, "Necesita seleccionar una opción para su vuelo de ida");
				}
				//si en la tabla de vuelta no hay nada seleccionado, le da la opcion de seleccionar algo o ir a por el hotel
				else if(tablaSurVuelta.getSelectedRow()==-1){
					String[] opcion={"SI","NO"};
					JOptionPane.showConfirmDialog(null, "¿Seguir sin seleccionar vuelo de vuelta?", "ALERTA", JOptionPane.YES_NO_OPTION);
						if(opcion.equals("SI")){
							//le pregunto a ver si quiere comprar hotel
							JOptionPane.showConfirmDialog(null, "¿Desea reservar un hotel?","HOTEL", JOptionPane.YES_NO_OPTION);
							if(opcion.equals("SI")){
								BusquedaHoteles bh = new BusquedaHoteles();
								bh.setVisible(true);
								dispose();
							}
							else if(opcion.equals("NO")){
								GestionCompra gc= new GestionCompra();
								gc.setVisible(true);
								dispose();
							}
							
						}
						else{
							
							ResultadosBusquedaVuelos rv= new ResultadosBusquedaVuelos(origen, destino, dia, mes, anio, precioMin, precioMax, diaVuelta, mesVuelta, anioVuelta);
							rv.setVisible(true);
							dispose();
						}
				}else{
					//{"ORIGEN","DESTINO","DURACION","PRECIO","HORA","DIA","MES","AÑO"};
					numFilaIda = tablaSurIda.getSelectedRow();
					numFilaVuelta = tablaSurVuelta.getSelectedRow();
					TableModel modelo = tablaSurIda.getModel();
					String origen = (String) modelo.getValueAt(numFilaIda, 0);
					String destino = (String) modelo.getValueAt(numFilaIda, 1);
					int duracion = (int) modelo.getValueAt(numFilaIda, 2);
					int precio = (int) modelo.getValueAt(numFilaIda, 3);
					int hora = (int) modelo.getValueAt(numFilaIda, 4);
					int dia = (int) modelo.getValueAt(numFilaIda, 5);
					int mes = (int) modelo.getValueAt(numFilaIda, 6);
					int anio = (int) modelo.getValueAt(numFilaIda, 7);
				}
//				GestionCompra vp= new GestionCompra();
//				vp.setVisible(true);
//				dispose();
			}
		});
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BusquedaVuelos vp= new BusquedaVuelos();
				vp.setVisible(true);
				dispose();
			}
		});
		
		JPanel panelDcha = new JPanel();
		contentPane.add(panelDcha, BorderLayout.EAST);
		
		JPanel panelIzda = new JPanel();
		contentPane.add(panelIzda, BorderLayout.WEST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelDatos = new JPanel();
		panelCentro.add(panelDatos);
		panelDatos.setLayout(null);
		
		
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblOrigen.setBounds(18, 5, 61, 14);
		panelDatos.add(lblOrigen);
		
		//volcar el valor elegido para el origen
		JLabel lblOrig = new JLabel(origen);
		lblOrig.setBounds(87, 5, 55, 14);
		panelDatos.add(lblOrig);       
		
		//volcar el valor elegido para la fecha origen
		JLabel lblFechaOrig = new JLabel(String.valueOf(dia)+"-"+mes+"-"+String.valueOf(anio));
		lblFechaOrig.setBounds(147, 5, 103, 14);
		panelDatos.add(lblFechaOrig);
		
		
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblDestino.setBounds(18, 31, 55, 14);
		panelDatos.add(lblDestino);
		
		//volcar el valor elegido para el destino
		JLabel lblDest = new JLabel(destino);
		lblDest.setBounds(87, 31, 55, 14);
		panelDatos.add(lblDest);		
		
		//volcar el valor elegido para la fecha destino (respecto a los datos que hayamos introducido en el calendario de vuelta)
		//quiero crear una opción que cuando le digamos que solo queremos vuelo de ida ponga a no visible estos datos de vuelta y el calendario de vuelta en la ventana anterior
		JLabel lblFechaDest = new JLabel(String.valueOf(diaVuelta)+"-"+mesVuelta+"-"+String.valueOf(anioVuelta));
		lblFechaDest.setBounds(147, 31, 75, 14);
		panelDatos.add(lblFechaDest);
		

		
		JPanel panelTabla = new JPanel();
		panelCentro.add(panelTabla);
		panelTabla.setLayout(new GridLayout(0, 1));
		
		//creo 2 paneles nuevos dentro del anterior para meter cada jtable en cada uno
		
		JPanel panelTablaArriba = new JPanel();
		panelTabla.add(panelTablaArriba);
		
		JPanel panelTablaAbajo = new JPanel();
		panelTabla.add(panelTablaAbajo);
		
		/**
		 * Creamos una JTable con un array que mostraremos en la primera fila como muestra de los titulos
		 * de cada uno de los atributoso de la base de datos. Para mostrar la información que hay de cada uno de ellos
		 * en función de la select que hayamos hecho anteriormente introducidos unos datos en concreto
		 * */
		
		String nombresColumnasIda[] = {"ORIGEN","DESTINO","DURACION","PRECIO","HORA","DIA","MES","AÑO"}; //array con los titulos de cada columna
		Object datosIda[][] = BD.volcarDatosVuelos(origen, destino, dia, mes, anio,precioMin, precioMax); //array donde voy a volcar los datos de la bd que se correspondan con los datos seleccionados anteriormente
		tablaSurIda = new JTable(datosIda,nombresColumnasIda); //metemos en una jtable estos arrays
		panelTablaArriba.setLayout(new BorderLayout());
		panelTablaArriba.add(tablaSurIda.getTableHeader(), BorderLayout.NORTH); 
		panelTablaArriba.add(tablaSurIda, BorderLayout.CENTER);
		JScrollBar sbIda= new JScrollBar();
		tablaSurIda.add(sbIda);		
		
		/*si no hay vuelos en la base de datos que coincidan con nuestros datos seleccionados y por lo tanto nada que volcar a la jtable,
		bloqueo las casillas de esta ya q no hay nada para seleccionar*/
		
		Object obj[][]=null;		
		if(datosIda.equals(obj)){
			//System.out.println("no hay datos para seleccionar");
			tablaSurIda.setRowSelectionAllowed(false);
		}

		//el origen en este caso va a ser el destino y viceversa, porque es la jtable de vuelta
       String nombrescolumnasVuelta[]= {"ORIGEN", "DESTINO", "DURACION", "PRECIO", "HORA", "DIA", "MES", "AÑO"};
       Object datosVuelta[][] = BD.volcarDatosVuelos(destino, origen, dia, mes, anio, precioMin, precioMax);
       tablaSurVuelta = new JTable(datosVuelta, nombrescolumnasVuelta);
       panelTablaAbajo.setLayout(new BorderLayout());
       panelTablaAbajo.add(tablaSurVuelta.getTableHeader(), BorderLayout.NORTH);
       panelTablaAbajo.add(tablaSurVuelta, BorderLayout.CENTER);
       JScrollBar sbVuelta= new JScrollBar();
       tablaSurVuelta.add(sbVuelta);
       
       if(datosVuelta.equals(null)){
    	   tablaSurVuelta.setRowSelectionAllowed(false);
       }
       
   	
	}
}

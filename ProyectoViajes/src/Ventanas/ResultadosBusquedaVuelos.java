package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

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
				//si no hay nada seleccionado aparece un mensaje de error //CORREGIR LO DE SELECCIONAR DE LA JTABLE!!!!!!!!!!!!
				if(!tablaSurIda.isCellSelected(1, 1)){
					JOptionPane.showMessageDialog(null, "Necesita seleccionar una opción para su vuelo de ida");
				}
				//si en la tabla de vuelta no hay nada seleccionado, le da la opcion de seleccionar algo o ir a por el hotel
				else if(!tablaSurVuelta.isCellSelected(1, 1)){
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
		
		JLabel lblOrigen = new JLabel("Origen:");
		panelDatos.add(lblOrigen);
		
		//volcar el valor elegido para el origen
		JLabel lblOrig = new JLabel(origen);
		panelDatos.add(lblOrig);
		
       
		
		//volcar el valor elegido para la fecha origen
		JLabel lblFechaOrig = new JLabel(String.valueOf(dia)+ "-" + mes + " - " +String.valueOf(anio));
		panelDatos.add(lblFechaOrig);
		
		JLabel lblDestino = new JLabel("Destino:");
		panelDatos.add(lblDestino);
		
		//volcar el valor elegido para el destino
		JLabel lblDest = new JLabel(destino);
		panelDatos.add(lblDest);
		
		
		//volcar el valor elegido para la fecha destino (respecto a los datos que hayamos introducido en el calendario de vuelta)
		//quiero crear una opción que cuando le digamos que solo queremos vuelo de ida pponga a no visible estos datos de vuelta y el calendario de vuelta en la ventana anterior
		JLabel lblFechaDest = new JLabel(String.valueOf(diaVuelta)+"-"+mesVuelta+"-"+String.valueOf(anioVuelta));
		panelDatos.add(lblFechaDest);

		
		JPanel panelTabla = new JPanel();
		panelCentro.add(panelTabla);
		panelTabla.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//creo 2 paneles nuevos dentro del anterior para meter cada jtable en cada uno
		
		JPanel panelTablaEn2Izq = new JPanel();
		panelTabla.add(panelTablaEn2Izq);
		panelTablaEn2Izq.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		
		JPanel panelTablaEn2Der = new JPanel();
		panelTabla.add(panelTablaEn2Der);
		panelTablaEn2Der.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 2));
		
		/**
		 * Creamos una JTable con un array que mostraremos en la primera fila como muestra de los titulos
		 * de cada uno de los atributoso de la base de datos. Para mostrar la información que hay de cada uno de ellos
		 * en función de la select que hayamos hecho anteriormente introducidos unos datos en concreto
		 * */
		
		
		
		String nombresColumnasIda[] = {"ORIGEN","DESINO","DURACION","PRECIO","HORA","DIA","MES","AÑO"}; //array con los titulos de cada columna
		Object datosIda[][] = BD.volcarDatosVuelos(origen, destino, dia, mes, anio,precioMin, precioMax); //array donde voy a volcar los datos de la bd que se correspondan con los datos seleccionados anteriormente
		tablaSurIda = new JTable(datosIda,nombresColumnasIda); //metemos en una jtable estos arrays
		panelTablaEn2Izq.setLayout(new BorderLayout());
		panelTablaEn2Izq.add(tablaSurIda.getTableHeader(), BorderLayout.NORTH); 
		panelTablaEn2Izq.add(tablaSurIda, BorderLayout.NORTH);
		JScrollBar sbIda= new JScrollBar();
		tablaSurIda.add(sbIda);

		
       String nombrescolumnasVuelta[]= {"ORIGEN", "DESTINO", "DURACION", "PRECIO", "HORA", "DIA", "MES", "AÑO"};
       Object datosVuelta[][] = BD.volcarDatosVuelos(origen, destino, dia, mes, anio, precioMin, precioMax);
       tablaSurVuelta = new JTable(datosVuelta, nombrescolumnasVuelta);
       panelTablaEn2Der.setLayout(new BorderLayout());
       panelTablaEn2Der.add(tablaSurVuelta.getTableHeader(), BorderLayout.SOUTH);
       panelTablaEn2Der.add(tablaSurVuelta, BorderLayout.SOUTH);
       JScrollBar sbVuelta= new JScrollBar();
       tablaSurVuelta.add(sbVuelta);
       
	}
}

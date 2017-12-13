package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
	private JTable tablaSur; 

	
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
				//si no hay nada seleccionado aparece un mensaje de error
				if(!tablaSur.isCellSelected(1, 1)){
					JOptionPane.showMessageDialog(null, "Necesita seleccionar una opción para poder realizar con la compra");
				}
				GestionCompra vp= new GestionCompra();
				vp.setVisible(true);
				dispose();
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
		
		/**
		 * Creamos una JTable con un array que mostraremos en la primera fila como muestra de los titulos
		 * de cada uno de los atributoso de la base de datos. Para mostrar la información que hay de cada uno de ellos
		 * en función de la select que hayamos hecho anteriormente introducidos unos datos en concreto
		 * */
		String nombresColumnas[] = {"ORIGEN","DESTINO","DURACION","PRECIO","HORA","DIA","MES","AÑO"}; //array con los titulos de cada columna
		Object datos[][] = BD.volcarDatosVuelos(origen, destino, dia, mes, anio,precioMin, precioMax); //array donde voy a volcar los datos de la bd que se correspondan con los datos seleccionados anteriormente
		tablaSur = new JTable(datos,nombresColumnas); //metemos en una jtable estos arrays
		panelTabla.setLayout(new BorderLayout());
		panelTabla.add(tablaSur.getTableHeader(), BorderLayout.NORTH); 
		panelTabla.add(tablaSur, BorderLayout.CENTER);

        	
	}
}

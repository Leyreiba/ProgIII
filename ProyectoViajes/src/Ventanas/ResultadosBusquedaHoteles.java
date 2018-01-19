package Ventanas;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BaseDeDatos.BD;
import javax.swing.JTextField;
import java.awt.Font;

public class ResultadosBusquedaHoteles extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentro;
	//private int precio;
	//private String lugar;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ResultadosBusquedaHoteles frame = new ResultadosBusquedaHoteles();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ResultadosBusquedaHoteles(int precioMin, int precioMax, int diaVuelta, String mesVuelta, int anioVuelta, int diaIda, String mesIda, int anioIda, int numHuespedes, String destino) {
		this.setTitle("Resultado búsqueda hoteles");
		
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

		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BusquedaHoteles vp= new BusquedaHoteles();
				vp.setVisible(true);
				dispose();
			}
		});
		
		JPanel panelDcha = new JPanel();
		contentPane.add(panelDcha, BorderLayout.EAST);
		
		JPanel panelIzda = new JPanel();
		contentPane.add(panelIzda, BorderLayout.WEST);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
				
		
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBounds(0, 0, 404, 105);
		panelCentro.add(panelDatos);
		panelDatos.setLayout(null);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(0, 103, 404, 105);
		panelCentro.add(panelTabla);
		

		String nombresColumnas[] = {"PRECIO","LUGAR","NOMBRE"}; //array con los titulos de cada columna
		Object datos[][] = BD.volcarDatosHoteles(precioMin, precioMax, destino);
		//cancelo la edición de las celdas mediante este método
		DefaultTableModel modelo = new DefaultTableModel(datos,nombresColumnas){
				    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
		
		JTable tablaSur = new JTable(modelo); //metemos en una jtable el modelo con los arrays (titulo y celdas)
		panelTabla.setLayout(new BorderLayout());
		panelTabla.add(tablaSur.getTableHeader(), BorderLayout.NORTH); 
		panelTabla.add(tablaSur, BorderLayout.CENTER);
		JScrollBar sb= new JScrollBar();
		tablaSur.add(sb);		
		
		
		
		JButton btnComprar = new JButton("Comprar");
		panelSur.add(btnComprar);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//quiero pasarle el nombre de usuario con el que se ha registrado, los datos del vuelos, el nombre del hotel, el precio del vuelo y el precio del hotel
				FinCompra vp= new FinCompra();
				vp.setVisible(true);
				dispose();
			}
		});
		
		
		
		//NUMERO DE HUESPEDES
		JLabel lblNumHuespedes = new JLabel("N\u00FAmero de Huespedes:");
		lblNumHuespedes.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblNumHuespedes.setBounds(11, 5, 140, 14);
		panelDatos.add(lblNumHuespedes);
				
		//INTRODUCIMOS EL NUMERO DE HUESPEDES QUE EL USUARIO HAYA ELEGIDO
		JLabel lblHuespedes = new JLabel(String.valueOf(numHuespedes));
		lblHuespedes.setBounds(147, 6, 17, 14);
		panelDatos.add(lblHuespedes);
		
		
		JLabel lblFechaEntrada = new JLabel("Fecha Entrada:");
		lblFechaEntrada.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblFechaEntrada.setBounds(198, 5, 88, 14);
		panelDatos.add(lblFechaEntrada);
		
		JLabel lblFechaSalida = new JLabel("Fecha Salida:");
		lblFechaSalida.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblFechaSalida.setBounds(198, 30, 88, 14);
		panelDatos.add(lblFechaSalida);
		
		//LA FECHA DE ENTRADA ES LA MISMA QUE LA FECHA DEL VUELO DE IDA
		JLabel lblEntrada = new JLabel(String.valueOf(diaIda)+"-"+mesIda+"-"+String.valueOf(anioIda));
		lblEntrada.setBounds(296, 30, 75, 14);
		panelDatos.add(lblEntrada);
		
		//LA FECHA DE SALIDA ES LA MISMA QUE LA FECHA DEL VUELO DE VUELTA
		JLabel lblSalida = new JLabel(String.valueOf(diaVuelta)+"-"+mesVuelta+"-"+String.valueOf(anioVuelta));
		lblSalida.setBounds(296, 6, 75, 14);
		panelDatos.add(lblSalida);
		
		
		
		
	}
}

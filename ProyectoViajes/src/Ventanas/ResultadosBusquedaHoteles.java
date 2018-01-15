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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BaseDeDatos.BD;
import javax.swing.JTextField;

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
		
		JPanel panelDcha = new JPanel();
		contentPane.add(panelDcha, BorderLayout.EAST);
		
		JPanel panelIzda = new JPanel();
		contentPane.add(panelIzda, BorderLayout.WEST);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinCompra vp= new FinCompra();
				vp.setVisible(true);
				dispose();
			}
		});
		btnComprar.setBounds(228, 197, 108, 23);
		panelCentro.add(btnComprar);
		
		JButton btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BusquedaHoteles vp= new BusquedaHoteles();
				vp.setVisible(true);
				dispose();
			}
		});
		btnBuscarOtro.setBounds(64, 197, 124, 23);
		panelCentro.add(btnBuscarOtro);
		
		//INTENTANDO INTRODUCIR EL NUM DE HUESPEDES
		JLabel lblHuespedes = new JLabel(String.valueOf(numHuespedes));
		lblHuespedes.setBounds(37, 11, 99, 14);
		panelCentro.add(lblHuespedes);
		
		//EL NUMERO QUE HAYAMOS SELECCIONADO EN LA VENTANA ANTERIOR
		JLabel lblNumHuespedes = new JLabel("New label");
		lblNumHuespedes.setBounds(142, 11, 46, 14);
		panelCentro.add(lblNumHuespedes);
		
		JLabel lblFechaEntrada = new JLabel("Fecha Entrada:");
		lblFechaEntrada.setBounds(228, 11, 75, 14);
		panelCentro.add(lblFechaEntrada);
		
		JLabel lblFechaSalida = new JLabel("Fecha Salida:");
		lblFechaSalida.setBounds(228, 35, 75, 14);
		panelCentro.add(lblFechaSalida);
		
		//LA FECHA DE ENTRADA ES LA MISMA QUE LA FECHA DEL VUELO DE IDA
		JLabel lblEntrada = new JLabel(String.valueOf(diaIda)+"-"+mesIda+"-"+String.valueOf(anioIda));
		lblEntrada.setBounds(318, 11, 46, 14);
		panelCentro.add(lblEntrada);
		
		//LA FECHA DE SALIDA ES LA MISMA QUE LA FECHA DEL VUELO DE VUELTA
		JLabel lblSalida = new JLabel(String.valueOf(diaVuelta)+"-"+mesVuelta+"-"+String.valueOf(anioVuelta));
		lblSalida.setBounds(318, 35, 46, 14);
		panelCentro.add(lblSalida);
				
		
		
		
		JPanel panelTabla = new JPanel();
		panelCentro.add(panelTabla);
		panelTabla.setLayout(new GridLayout(0, 1));


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

		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

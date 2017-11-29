package Ventanas;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import javax.swing.JTextField;

public class ResultadosBusquedaHoteles extends JFrame {

	private JPanel contentPane;

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
	public ResultadosBusquedaHoteles(int precio, String lugar) {
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
		
		JPanel panelCentro = new JPanel();
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
		
		JLabel lblHuespedes = new JLabel("Total Huespedes:");
		lblHuespedes.setBounds(37, 11, 99, 14);
		panelCentro.add(lblHuespedes);
		
		JLabel lblNumHuespedes = new JLabel("New label");
		lblNumHuespedes.setBounds(142, 11, 46, 14);
		panelCentro.add(lblNumHuespedes);
		
		JLabel lblFechaEntrada = new JLabel("Fecha Entrada:");
		lblFechaEntrada.setBounds(228, 11, 75, 14);
		panelCentro.add(lblFechaEntrada);
		
		JLabel lblFechaSalida = new JLabel("Fecha Salida:");
		lblFechaSalida.setBounds(228, 35, 75, 14);
		panelCentro.add(lblFechaSalida);
		
		JLabel lblEntrada = new JLabel("New label");
		lblEntrada.setBounds(318, 11, 46, 14);
		panelCentro.add(lblEntrada);
		
		JLabel lblSalida = new JLabel("New label");
		lblSalida.setBounds(318, 35, 46, 14);
		panelCentro.add(lblSalida);
	}
	


	String nombresColumnas[] = {"PRECIO","LUGAR","NOMBRE"}; //array con los titulos de cada columna
	Object datos[][] = BD.volcarDatosHoteles(precio, lugar); //array donde voy a volcar los datos de la bd que se correspondan con los datos seleccionados anteriormente
	JTable tablaSur = new JTable(datos,nombresColumnas); //metemos en una jtable estos arrays
//	panelTabla.setLayout(new BorderLayout());
//	panelTabla.add(tablaSur.getTableHeader(), BorderLayout.NORTH); 
//	panelTabla.add(tablaSur, BorderLayout.CENTER);
}

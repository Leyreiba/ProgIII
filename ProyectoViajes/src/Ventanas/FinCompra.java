package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import Login.Login;

public class FinCompra extends JFrame {
	private JPanel contentPane;
	
	/**
	 * Creamos la ventana
	 */
	public FinCompra() {
		this.setTitle("Fin compra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(15, 29, 69, 20);
		panel_1.add(lblNombre);
		
		JLabel lblVuelo = new JLabel("Vuelo:");
		lblVuelo.setBounds(15, 65, 69, 20);
		panel_1.add(lblVuelo);
		
		JLabel lblDatosVuelo = new JLabel(BusquedaVuelos.Fecha);
		lblDatosVuelo.setBounds(81, 65, 319, 20);
		panel_1.add(lblDatosVuelo);
		
		if(ResultadosBusquedaVuelos.hotel){
			JLabel lblHotel = new JLabel("Hotel:");
			lblHotel.setBounds(15, 101, 69, 20);
			panel_1.add(lblHotel);
		}
		/**
		 * Cogemos el nombre del Hotel
		 */
		JLabel lblDatosHotel = new JLabel(ResultadosBusquedaHoteles.nom);
		lblDatosHotel.setBounds(81, 101, 177, 20);
		panel_1.add(lblDatosHotel);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(15, 139, 69, 20);
		panel_1.add(lblTotal);
		
		/*
		 * Sumamos el precio del hotel + el precio del vuelo de ida + el precio del vuelo de vuelta
		 */
		JLabel lblPreciototal = new JLabel(String.valueOf(ResultadosBusquedaHoteles.precio+ResultadosBusquedaVuelos.preciovueloida+ResultadosBusquedaVuelos.preciovuelovuelta));
		lblPreciototal.setBounds(81, 139, 102, 20);
		panel_1.add(lblPreciototal);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 202, 424, 32);
		panel_1.add(panel);
		panel.setLayout(null);
		
		/**
		 * Boton que crea un documento de texto con algunos de los datos que hemos ido seleccionando
		 */
		JButton btnPagar = new JButton("Pagar");
		btnPagar.setBounds(165, 0, 73, 29);
		panel.add(btnPagar);
		btnPagar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date d = new Date(System.currentTimeMillis());
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(d);
				//Creamos el documento
				PrintWriter pw;
				try {
					pw = new PrintWriter("Compra "+Login.n+" "+gc.get(Calendar.YEAR)+(gc.get(Calendar.MONTH)+1)+gc.get(Calendar.DAY_OF_MONTH)+".txt");
					pw.println("Compra con fecha: "+ d.toString());
					pw.println("Hotel: "+ResultadosBusquedaHoteles.nom);
					pw.println("Fecha de la reserva: "+BusquedaVuelos.Fecha);
					pw.println("Precio total: "+String.valueOf(ResultadosBusquedaHoteles.precio+ResultadosBusquedaVuelos.preciovueloida+ResultadosBusquedaVuelos.preciovuelovuelta));
					pw.flush();
					pw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNombreUsuario = new JLabel(Login.n);
		lblNombreUsuario.setBounds(80, 29, 103, 20);
		panel_1.add(lblNombreUsuario);
	}
}

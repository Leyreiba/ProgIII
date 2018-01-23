package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Login.Login;

public class FinCompra extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinCompra frame = new FinCompra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
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
		
		JLabel lblDatosHotel = new JLabel(ResultadosBusquedaHoteles.nom);
		lblDatosHotel.setBounds(81, 101, 177, 20);
		panel_1.add(lblDatosHotel);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(15, 139, 69, 20);
		panel_1.add(lblTotal);
		
		//sumamos el precio del hotel + el precio del vuelo de ida + el precio del vuelo de vuelta
		JLabel lblPreciototal = new JLabel(ResultadosBusquedaHoteles.precio+ResultadosBusquedaVuelos.preciovueloida+ResultadosBusquedaVuelos.preciovuelovuelta);
		lblPreciototal.setBounds(59, 139, 102, 20);
		panel_1.add(lblPreciototal);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 202, 424, 32);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.setBounds(165, 0, 73, 29);
		panel.add(btnPagar);
		
		JLabel lblNombreUsuario = new JLabel(Login.n);
		lblNombreUsuario.setBounds(80, 29, 103, 20);
		panel_1.add(lblNombreUsuario);
	}
}

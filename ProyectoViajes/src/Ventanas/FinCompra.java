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
		
		JLabel lblDatosVuelo = new JLabel("Datos Vuelo");
		lblDatosVuelo.setBounds(99, 65, 102, 20);
		panel_1.add(lblDatosVuelo);
		
		JLabel lblHotel = new JLabel("Hotel:");
		lblHotel.setBounds(15, 101, 69, 20);
		panel_1.add(lblHotel);
		
		JLabel lblDatosHotel = new JLabel(ResultadosBusquedaHoteles.nom);
		lblDatosHotel.setBounds(99, 101, 102, 20);
		panel_1.add(lblDatosHotel);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(99, 137, 69, 20);
		panel_1.add(lblTotal);
		
		JLabel lblPreciototal = new JLabel("PrecioTotal");
		lblPreciototal.setBounds(183, 137, 102, 20);
		panel_1.add(lblPreciototal);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 202, 418, 32);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.setBounds(278, 0, 73, 29);
		panel.add(btnPagar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionCompra vp= new GestionCompra();
				vp.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(15, 0, 115, 29);
		panel.add(btnVolver);
		JLabel lblNombreUsuario = new JLabel(Login.n);
		lblNombreUsuario.setBounds(98, 29, 103, 20);
		panel_1.add(lblNombreUsuario);
	}
}

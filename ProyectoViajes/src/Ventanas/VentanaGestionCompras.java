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

public class VentanaGestionCompras extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestionCompras frame = new VentanaGestionCompras();
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
	public VentanaGestionCompras() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setBounds(15, 16, 69, 20);
		panelCentro.add(lblOrigen);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setBounds(15, 52, 69, 20);
		panelCentro.add(lblDestino);
		
		JLabel lblOrig = new JLabel("New label");
		lblOrig.setBounds(80, 16, 69, 20);
		panelCentro.add(lblOrig);
		
		JLabel lblDest = new JLabel("New label");
		lblDest.setBounds(80, 52, 69, 20);
		panelCentro.add(lblDest);
		
		JLabel lblFechaOrig = new JLabel("New label");
		lblFechaOrig.setBounds(164, 16, 69, 20);
		panelCentro.add(lblFechaOrig);
		
		JLabel lblFechaDest = new JLabel("New label");
		lblFechaDest.setBounds(164, 52, 69, 20);
		panelCentro.add(lblFechaDest);
		
		JLabel lblHotel = new JLabel("\u00BFDesea buscar algun hotel?");
		lblHotel.setBounds(15, 111, 193, 20);
		panelCentro.add(lblHotel);
		
		JButton btnBuscar = new JButton("Buscar hotel");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBusquedaH vp= new VentanaBusquedaH();
				vp.setVisible(true);
				dispose();
			}
		});
		btnBuscar.setBounds(15, 147, 134, 29);
		panelCentro.add(btnBuscar);
		
		JButton btnFinalizar = new JButton("Finalizar Compra");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaFinCompra vp= new VentanaFinCompra();
				vp.setVisible(true);
				dispose();
			}
		});
		btnFinalizar.setBounds(164, 147, 193, 29);
		panelCentro.add(btnFinalizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaResultadosBusqueda vp= new VentanaResultadosBusqueda();
				vp.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(15, 189, 115, 29);
		panelCentro.add(btnVolver);
	}
}

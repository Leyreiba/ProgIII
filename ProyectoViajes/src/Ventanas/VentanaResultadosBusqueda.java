package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class VentanaResultadosBusqueda extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaResultadosBusqueda frame = new VentanaResultadosBusqueda();
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
	public VentanaResultadosBusqueda() {
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
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setBounds(96, 24, 46, 14);
		panelCentro.add(lblOrigen);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setBounds(93, 49, 46, 14);
		panelCentro.add(lblDestino);
		
		//volcar el valor elegido para el origen
		JLabel lblOrig = new JLabel("New label");
		lblOrig.setBounds(162, 24, 46, 14);
		panelCentro.add(lblOrig);
		
		//volcar el valor elegido para el destino
		JLabel lblDest = new JLabel("New label");
		lblDest.setBounds(162, 49, 46, 14);
		panelCentro.add(lblDest);
		
		//volcar el valor elegido para la fecha origen
		JLabel lblFechaOrig = new JLabel("New label");
		lblFechaOrig.setBounds(255, 24, 46, 14);
		panelCentro.add(lblFechaOrig);
		
		JLabel lblVuelo = new JLabel("Vuelo");
		lblVuelo.setBounds(64, 78, 46, 14);
		panelCentro.add(lblVuelo);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(131, 78, 46, 14);
		panelCentro.add(lblHora);
		
		JLabel lblDur = new JLabel("Duraci\u00F3n");
		lblDur.setBounds(200, 78, 46, 14);
		panelCentro.add(lblDur);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(290, 78, 46, 14);
		panelCentro.add(lblPrecio);
		
		//volcar el valor elegido para la fecha destino
		JLabel lblFechaDest = new JLabel("New label");
		lblFechaDest.setBounds(255, 49, 46, 14);
		panelCentro.add(lblFechaDest);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestionCompras vp= new VentanaGestionCompras();
				vp.setVisible(true);
				dispose();
			}
		});
		btnComprar.setBounds(228, 197, 108, 23);
		panelCentro.add(btnComprar);
		
		JButton btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal vp= new VentanaPrincipal();
				vp.setVisible(true);
				dispose();
			}
		});
		btnBuscarOtro.setBounds(64, 197, 124, 23);
		panelCentro.add(btnBuscarOtro);
		
		
		ControladorResultadosBusqueda crb= new ControladorResultadosBusqueda();
		crb.CrearTabla();
		
        	
	}
}

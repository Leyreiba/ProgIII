
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import Ventanas.BusquedaVuelos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Array;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPasswordField textContrasenia;
	//public static BD bd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void vaciar(){
		textNombre.setText("");
		textContrasenia.setText("");
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		this.setTitle("Login");
		/**
		 * Creamos un objeto BD. Mediante el Handler sabemos a qué fichero se mandarán los logs.
		 * Al log le ponemos un formato normal y se lo actualizamos en el Handler. 
		 * Por ultimo creamos el log y le añadimos el Handler
		 */
		//bd= new BD();
		BD.conectar();
		Handler fileHandler = null;
		try {
			fileHandler= new FileHandler("./prueba.log", true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleFormatter simpleformatter= new SimpleFormatter();
		fileHandler.setFormatter(simpleformatter);
		Logger logger= Logger.getLogger("Logger");
		logger.addHandler(fileHandler);
		
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
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblNombreDeUsuario.setBounds(39, 68, 159, 27);
		panelCentro.add(lblNombreDeUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblContrasenia.setBounds(39, 115, 159, 27);
		panelCentro.add(lblContrasenia);
		
		textNombre = new JTextField();
		textNombre.setBounds(208, 68, 129, 25);
		panelCentro.add(textNombre);
		textNombre.setColumns(10);
		
		textContrasenia = new JPasswordField();
		textContrasenia.setBounds(208, 115, 129, 25);
		panelCentro.add(textContrasenia);
		
		
		
		JLabel lblIdentifqueseParaEmpezar = new JLabel("Identifíquese para empezar...");
		lblIdentifqueseParaEmpezar.setForeground(new Color(255, 127, 80));
		lblIdentifqueseParaEmpezar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 23));
		lblIdentifqueseParaEmpezar.setBounds(39, 11, 327, 27);
		panelCentro.add(lblIdentifqueseParaEmpezar);
		
		JCheckBox chckbxCondiciones = new JCheckBox("Al iniciar sesión acepta nuestros términos y condiciones");
		chckbxCondiciones.setFont(new Font("Sitka Text", Font.PLAIN, 10));
		chckbxCondiciones.setBounds(39, 156, 298, 23);
		panelCentro.add(chckbxCondiciones);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(248, 197, 89, 23);
		panelCentro.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String n= textNombre.getText();
				String c= textContrasenia.getText();
				if(n.equals("")){
					JOptionPane.showMessageDialog(null, "Introduzca un nombre");
					logger.log(Level.WARNING, "Debe introducir un nombre");
				}
				else if(c.equals("")){
					JOptionPane.showMessageDialog(null, "Introduzca una contraseña");
					logger.log(Level.WARNING, "Debe introducir una contraseña");

				}else if(!chckbxCondiciones.isSelected()){
					JOptionPane.showMessageDialog(null, "Debe aceptar los términos y condiciones");
					logger.log(Level.WARNING,  "Debe aceptar los términos y condiciones");
				}
				else{
										
					/**
					 * llamamos al método existeusuario() de la clase BD y a registrar usuario
					 * */
					
					int existe= BD.existeUsuario(n, c);
					if(existe==0){
						String e= JOptionPane.showInputDialog("¿Quiere registrarse? (S/N) ");
						if(e.equalsIgnoreCase("S")){
							BD.registrarUsuario(n, c);
							JOptionPane.showMessageDialog(null, "REGISTRO COMPLETADO","CORRECTO", JOptionPane.INFORMATION_MESSAGE);
							vaciar();
							
						}
						else{
							JOptionPane.showMessageDialog(null, "Vaya...Vuelve pronto");
						}
					}
					else if(existe==1){
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
						logger.log(Level.SEVERE, "Esta contraseña no es correcta");
					}
					else{
						JOptionPane.showMessageDialog(null, "BIENVENIDO");
						vaciar();
						BusquedaVuelos vp= new BusquedaVuelos();
						vp.setVisible(true);
						dispose();
					}
					
					//INTRODUCIR UN FONDO DE VIAJES EN LA VENTANA DE LOGIN
					
				}
				
			}
		});
	}
}

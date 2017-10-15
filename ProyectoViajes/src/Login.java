import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPasswordField textContrasenia;

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

	/**
	 * Create the frame.
	 */
	public Login() {
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
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
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
		
		JLabel lblIdentifqueseParaEmpezar = new JLabel("Identif\u00EDquese para empezar...");
		lblIdentifqueseParaEmpezar.setForeground(Color.RED);
		lblIdentifqueseParaEmpezar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblIdentifqueseParaEmpezar.setBounds(39, 11, 298, 27);
		panelCentro.add(lblIdentifqueseParaEmpezar);
		
		JCheckBox chckbxAlIniciarSesin = new JCheckBox("Al iniciar sesi\u00F3n acepta nuestros t\u00E9rminos y condiciones");
		chckbxAlIniciarSesin.setFont(new Font("Sitka Text", Font.PLAIN, 10));
		chckbxAlIniciarSesin.setBounds(39, 156, 298, 23);
		panelCentro.add(chckbxAlIniciarSesin);
		
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
				}
				else if(c.equals("")){
					JOptionPane.showMessageDialog(null, "Introduzca una contraseña");
				}
				else{
					
				}
				
			}
		});
	}
}

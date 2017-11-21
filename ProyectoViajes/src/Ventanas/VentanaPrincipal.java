package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import com.toedter.calendar.JDayChooser;

import BaseDeDatos.BD;

import com.toedter.calendar.JCalendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JCalendar calendarIda, calendarVuelta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setBounds(22, 11, 43, 24);
		panel.add(lblOrigen);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setBounds(223, 11, 51, 24);
		panel.add(lblDestino);
		
		JLabel lblFechaIda = new JLabel("Fecha ida");
		lblFechaIda.setBounds(22, 46, 87, 24);
		panel.add(lblFechaIda);
		
		JLabel lblFechaVuelta = new JLabel("Fecha vuelta");
		lblFechaVuelta.setBounds(223, 46, 87, 24);
		panel.add(lblFechaVuelta);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(27, 178, 48, 24);
		panel.add(lblPrecio);
		
		JLabel lblPrecioScroll = new JLabel(" ");
		lblPrecioScroll.setBounds(78, 180, 69, 20);
		panel.add(lblPrecioScroll);
		
		JSlider sliderPrecio = new JSlider();
		sliderPrecio.setValue(0);
		sliderPrecio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int v= sliderPrecio.getValue();
				//hay que convertir el entero en un string para poder utilizar el settext()
				String s= String.valueOf(v);
				lblPrecioScroll.setText(s);
			}
		});
		sliderPrecio.setPaintLabels(true);
		sliderPrecio.setMaximum(200);
		sliderPrecio.setMajorTickSpacing(200);
		sliderPrecio.setPaintTicks(true);
		sliderPrecio.setBounds(127, 178, 226, 44);
		panel.add(sliderPrecio);
		
		//String[] opciones1= {"origen1","origen2","origen3","origen4"};
		//volcar de la base de datos todos los origenes que sean distintos. Para ello llamamos al metodo ya implementado en la clase BD
		String[] opciones1 = BD.obtenerOrigenDestino('o');
		JComboBox<String> comboBoxOrigen = new JComboBox<String>(opciones1);
		comboBoxOrigen.setBounds(75, 13, 87, 20);
		panel.add(comboBoxOrigen);
		
		//String[] opciones2= {"destino1","destino2","destino3","destino4"};
		//Llamamos al metodo ya implementado en la clase BD para volcar los destinos que podemos seleccionar
		String[] opciones2 = BD.obtenerOrigenDestino('d');
		JComboBox<String> comboBoxDestino = new JComboBox<String>(opciones2);
		comboBoxDestino.setBounds(286, 13, 87, 20);
		panel.add(comboBoxDestino);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//muestro en la ventanaResultadosBusqueda los datos elegidos en la ventana anterior
				VentanaResultadosBusqueda vp= new VentanaResultadosBusqueda(comboBoxOrigen.getSelectedItem().toString(),comboBoxDestino.getSelectedItem().toString(), calendarIda.getDayChooser().getDay(), String.valueOf(calendarIda.getMonthChooser().getMonth()), calendarIda.getYearChooser().getYear(),sliderPrecio.getValue());
				vp.setVisible(true);
				dispose();
			}
		});
		btnBuscar.setBounds(180, 225, 89, 23);
		panel.add(btnBuscar);
		
		calendarIda = new JCalendar();
		calendarIda.setBounds(23, 69, 190, 106);
		panel.add(calendarIda);
		
		calendarVuelta = new JCalendar();
		calendarVuelta.setBounds(223, 69, 191, 106);
		panel.add(calendarVuelta);
		
	}
}

package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JCalendar;

public class BusquedaHoteles extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusquedaHoteles frame = new BusquedaHoteles();
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
	public BusquedaHoteles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblFechaIda = new JLabel("Fecha entrada");
		lblFechaIda.setBounds(22, 46, 106, 24);
		panel.add(lblFechaIda);
		
		JLabel lblFechaVuelta = new JLabel("Fecha Salida");
		lblFechaVuelta.setBounds(223, 46, 87, 24);
		panel.add(lblFechaVuelta);
		
		JLabel lblPrecioMin = new JLabel("Precio:");
		lblPrecioMin.setBounds(27, 178, 48, 24);
		panel.add(lblPrecioMin);
		
		JLabel precioMin = new JLabel(" ");
		precioMin.setBounds(78, 180, 69, 20);
		panel.add(precioMin);
		
		JSlider sliderPrecioMin = new JSlider();
		sliderPrecioMin.setValue(0);
		sliderPrecioMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int v= sliderPrecioMin.getValue();
				//hay que convertir el entero en un string para poder utilizar el settext()
				String s= String.valueOf(v);
				precioMin.setText(s);
			}
		});
		sliderPrecioMin.setPaintLabels(true);
		sliderPrecioMin.setMaximum(200);
		sliderPrecioMin.setMajorTickSpacing(200);
		sliderPrecioMin.setPaintTicks(true);
		sliderPrecioMin.setBounds(127, 178, 226, 36);
		panel.add(sliderPrecioMin);
		

		JLabel labelPrecioMax = new JLabel("Precio:");
		labelPrecioMax.setBounds(27, 213, 48, 24);
		panel.add(labelPrecioMax);
		
		JLabel precioMax = new JLabel(" ");
		precioMax.setBounds(78, 215, 69, 20);
		panel.add(precioMax);
		
		JSlider sliderPrecioMax = new JSlider();
		sliderPrecioMax.setValue(0);
		sliderPrecioMax.setPaintTicks(true);
		sliderPrecioMax.setPaintLabels(true);
		sliderPrecioMax.setMaximum(200);
		sliderPrecioMax.setMajorTickSpacing(200);
		sliderPrecioMax.setBounds(127, 215, 226, 36);
		panel.add(sliderPrecioMax);
		
		
		String[] NHuespedes = {"1","2","3","4","5"};
		JComboBox comboBoxNumHuespedes = new JComboBox(NHuespedes);
		comboBoxNumHuespedes.setBounds(143, 13, 48, 26);
		panel.add(comboBoxNumHuespedes);
		

		JCalendar calendarEntrada = new JCalendar();
		calendarEntrada.setBounds(23, 69, 190, 106);
		panel.add(calendarEntrada);
		
		JCalendar calendarSalida = new JCalendar();
		calendarSalida.setBounds(223, 69, 191, 106);
		panel.add(calendarSalida);
		
		/**
		 * Mediante este botón pasamos a la siguiente ventana donde se van a mostrar los hoteles accesibles en la ciudad que hayamos seleccionado.
		 * La fecha de salida y entrada serán cuando el usuario quiera, por lo tanto en la base de datos se introducirán estas fechas cuando seleccionemos el hotel que queremos.
		 * Así como los huespedes. (estos también serán tantos como el usuario quiera).
		 * El precio indicado es por noche, por lo tanto se multiplicará dependiendo de las noches que pasemos.
		 * */
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//precio, lugar, nombre
				//aquí guaurdo los datos que he seleccionado en la ventana anterior(busquedaHoteles)
				//quiero coger el destino obtenido en la ventana vuelos como lugar donde va a estar mi hotel
				//¿Cómo cojo el valor del comboBox destino?
				ResultadosBusquedaHoteles vp= new ResultadosBusquedaHoteles(sliderPrecioMin.getValue(), comboBoxDestino.getSelectedItem().toString());
				vp.setVisible(true);
				dispose();
			}
		});
		btnBuscar.setBounds(179, 249, 89, 23);
		panel.add(btnBuscar);
		
		
		JLabel lblNHuespedes = new JLabel("N\u00BA Huespedes:");
		lblNHuespedes.setBounds(22, 16, 106, 20);
		panel.add(lblNHuespedes);
		
		
	}
}

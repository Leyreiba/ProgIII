package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JCalendar;

public class BusquedaHoteles extends JFrame {

	private JPanel contentPane;
	private static String destino;


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
		this.setTitle("Búsqueda hoteles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 381);
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
		
		JLabel lblPrecioMin = new JLabel("Precio min:");
		lblPrecioMin.setBounds(23, 181, 68, 39);
		panel.add(lblPrecioMin);
		
		JLabel precioMin = new JLabel(" ");
		precioMin.setBounds(98, 190, 31, 20);
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
		sliderPrecioMin.setBounds(127, 185, 226, 43);
		panel.add(sliderPrecioMin);
		

		JLabel labelPrecioMax = new JLabel("Precio max:");
		labelPrecioMax.setBounds(23, 226, 68, 39);
		panel.add(labelPrecioMax);
		
		JLabel precioMax = new JLabel(" ");
		precioMax.setBounds(98, 235, 31, 20);
		panel.add(precioMax);
		
		JSlider sliderPrecioMax = new JSlider();
		sliderPrecioMax.setValue(0);
		sliderPrecioMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int v = sliderPrecioMax.getValue();
				String s = String.valueOf(v);
				precioMax.setText(s);
				
			}
		});
		sliderPrecioMax.setPaintLabels(true);
		sliderPrecioMax.setMaximum(200);
		sliderPrecioMax.setMajorTickSpacing(200);
		sliderPrecioMax.setPaintTicks(true);
		sliderPrecioMax.setBounds(127, 231, 226, 43);
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
		
		JLabel lblNHuespedes = new JLabel("N\u00BA Huespedes:");
		lblNHuespedes.setBounds(22, 16, 106, 20);
		panel.add(lblNHuespedes);
		
		/**
		 * Mediante este botón pasamos a la siguiente ventana donde se van a mostrar los hoteles accesibles en la ciudad que hayamos seleccionado.
		 * La fecha de salida y entrada serán cuando el usuario quiera, por lo tanto en la base de datos se introducirán estas fechas cuando seleccionemos el hotel que queremos.
		 * Así como los huespedes. (estos también serán tantos como el usuario quiera).
		 * El precio indicado es por noche, por lo tanto se multiplicará dependiendo de las noches que pasemos.
		 * */
		
		
		JButton btnBuscar = new JButton("Aceptar");
		btnBuscar.setBounds(162, 283, 89, 23);
		panel.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//resumen que sale en la siguiente  ventana en función de lo que hayamos seleccionado en la anterior
				//aquí guaurdo los datos que he seleccionado en la ventana anterior(busquedaHoteles)
				//quiero coger el destino obtenido en la ventana vuelos como lugar donde va a estar mi hotel
				//hacer lo mismo q con el combobox de vuelos
				if(sliderPrecioMin.getValue() > sliderPrecioMax.getValue()) {
					JOptionPane.showMessageDialog(null,"El precio mínimo no puede ser mayor que el precio máximo");
				}
				else{
					//El error que da es por que necesitamos pasarl el valor del destino al metodo para que en resultados busqueda hoteles no nos de error
					ResultadosBusquedaHoteles vp= new ResultadosBusquedaHoteles(sliderPrecioMin.getValue(), sliderPrecioMax.getValue(), calendarEntrada.getDayChooser().getDay(), String.valueOf(calendarEntrada.getMonthChooser().getMonth()+1), calendarEntrada.getYearChooser().getYear(), calendarSalida.getDayChooser().getDay(), String.valueOf(calendarSalida.getMonthChooser().getMonth()+1), calendarSalida.getYearChooser().getYear(), Integer.parseUnsignedInt((String) comboBoxNumHuespedes.getSelectedItem()));
					vp.setVisible(true);
					dispose();
				}
			}
		});
		
		
		
		
		
	}
	
	//creamos un destino y le asginamos el valor del destino del combobox de vuelos
	
	public static void setDestino(String dest){
		destino=dest;
	}
}

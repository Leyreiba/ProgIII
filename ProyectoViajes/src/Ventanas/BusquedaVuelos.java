package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.JOptionPane;
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

public class BusquedaVuelos extends JFrame {

	private JPanel contentPane;
	private JCalendar calendarIda, calendarVuelta;
	private static String destino;
	private static JSlider sliderPrecioMax;
	private static JSlider sliderPrecioMin;
	public static int diaIda, mesIda, anioIda, diaVuelta, mesVuelta, anioVuelta;
	public static String Fecha;

	/**
	 * Creamos la ventana
	 */
	public BusquedaVuelos() {
		this.setTitle("B�squeda vuelos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 425);
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
		
		JLabel lblPrecioMin = new JLabel("Precio min:");
		lblPrecioMin.setBounds(16, 178, 68, 39);
		panel.add(lblPrecioMin);
		
		JLabel lblPrecioScrollMin = new JLabel(" ");
		lblPrecioScrollMin.setBounds(92, 187, 37, 20);
		panel.add(lblPrecioScrollMin);

		JLabel lblPrecioMax = new JLabel("Precio max:");
		lblPrecioMax.setBounds(16, 233, 87, 24);
		panel.add(lblPrecioMax);
		
		JLabel lblPrecioScrollMax = new JLabel(" ");
		lblPrecioScrollMax.setBounds(92, 235, 37, 20);
		panel.add(lblPrecioScrollMax);
		
		sliderPrecioMin = new JSlider();
		sliderPrecioMin.setValue(0);
		sliderPrecioMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int v= sliderPrecioMin.getValue();
				//hay que convertir el entero en un string para poder utilizar el settext()
				String s= String.valueOf(v);
				lblPrecioScrollMin.setText(s);
			}
		});
		sliderPrecioMin.setPaintLabels(true);
		sliderPrecioMin.setMaximum(200);
		sliderPrecioMin.setMajorTickSpacing(200);
		sliderPrecioMin.setPaintTicks(true);
		sliderPrecioMin.setBounds(127, 178, 226, 53);
		panel.add(sliderPrecioMin);
		

		sliderPrecioMax = new JSlider();
		sliderPrecioMax.setValue(0);
		sliderPrecioMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int v = sliderPrecioMax.getValue();
				String s = String.valueOf(v);
				lblPrecioScrollMax.setText(s);
			}
		});
		sliderPrecioMax.setPaintLabels(true);
		sliderPrecioMax.setMaximum(200);
		sliderPrecioMax.setMajorTickSpacing(200);
		sliderPrecioMax.setPaintTicks(true);
		sliderPrecioMax.setBounds(127, 229, 226, 53);
		panel.add(sliderPrecioMax);
		
		//Volcar de la base de datos todos los origenes que sean distintos. Para ello llamamos al metodo ya implementado en la clase BD
		String[] opciones1 = BD.obtenerOrigenDestino('o');
		JComboBox<String> comboBoxOrigen = new JComboBox<String>(opciones1);
		comboBoxOrigen.setBounds(75, 13, 87, 20);
		panel.add(comboBoxOrigen);
		
		//Llamamos al metodo ya implementado en la clase BD para volcar los destinos que podemos seleccionar
		String[] opciones2 = BD.obtenerOrigenDestino('d');
		JComboBox<String> comboBoxDestino = new JComboBox<String>(opciones2);
		comboBoxDestino.setBounds(286, 13, 87, 20);
		panel.add(comboBoxDestino);
		
		comboBoxDestino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BusquedaHoteles.setDestino(comboBoxDestino.getSelectedItem().toString());
				
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sliderPrecioMin.getValue() > sliderPrecioMax.getValue()) {
					JOptionPane.showMessageDialog(null,"El precio m�nimo no puede ser mayor que el precio m�ximo");
				}
				else {
					//muestro en la ventanaResultadosBusqueda los datos elegidos en la ventana anterior. Origen, destino, dia, mes, anio, precio(queremos sacar en la jtable aquellos vuelos con un precio entre el minimo y el maximo que hayamos introducido)
					ResultadosBusquedaVuelos vp= new ResultadosBusquedaVuelos(comboBoxOrigen.getSelectedItem().toString(),comboBoxDestino.getSelectedItem().toString(), calendarIda.getDayChooser().getDay(), String.valueOf(calendarIda.getMonthChooser().getMonth()+1), calendarIda.getYearChooser().getYear(),sliderPrecioMin.getValue(),sliderPrecioMax.getValue(),calendarVuelta.getDayChooser().getDay(), String.valueOf(calendarVuelta.getMonthChooser().getMonth()+1), calendarVuelta.getYearChooser().getYear());
					Fecha = (calendarIda.getDayChooser().getDay())+"-"+String.valueOf(calendarIda.getMonthChooser().getMonth()+1)+"-"+calendarIda.getYearChooser().getYear()+" a "+calendarVuelta.getDayChooser().getDay()+"-"+String.valueOf(calendarVuelta.getMonthChooser().getMonth()+1)+"-"+calendarVuelta.getYearChooser().getYear();
					BusquedaHoteles.setDestino(String.valueOf(comboBoxDestino.getSelectedItem()));
					diaIda = calendarIda.getDayChooser().getDay();
					mesIda = calendarIda.getMonthChooser().getMonth();
					anioIda = calendarIda.getYearChooser().getYear();
					diaVuelta = calendarVuelta.getDayChooser().getDay();
					mesVuelta = calendarVuelta.getMonthChooser().getMonth();
					anioVuelta = calendarVuelta.getYearChooser().getYear();
					vp.setVisible(true);
					dispose();
				}
			}
		});
		btnBuscar.setBounds(127, 320, 89, 23);
		panel.add(btnBuscar);
		
		calendarIda = new JCalendar();
		calendarIda.setBounds(23, 69, 190, 106);
		panel.add(calendarIda);
		
		//hacer q el calendario de vuelta aparezca por defecto con un d�a m�s que el de vuelta siempre,
		//o el mismo d�a ya que puedes ir y volver en el mismo d�a
		calendarVuelta = new JCalendar();
		calendarVuelta.setBounds(223, 69, 191, 106);
		panel.add(calendarVuelta);
	
	}
	
}

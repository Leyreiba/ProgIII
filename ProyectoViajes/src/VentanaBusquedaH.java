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

public class VentanaBusquedaH extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBusquedaH frame = new VentanaBusquedaH();
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
	public VentanaBusquedaH() {
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
		
		String[] opciones1= {"origen1","origen2","origen3","origen4"};
		
		String[] opciones2= {"destino1","destino2","destino3","destino4"};
		
		/**
		 * Mediante este botón buscamos en la base de datos si tenemos viajes que coincidan
		 * con todos los datos que el usuario haya introducido.
		 * 1-Si exite un viaje con esos datos en la bd, en la siguiente ventana se visualizarán los vuelos disponibles referentes a esa info.
		 * 2-Si no existe, en la siguiente ventana se mostrará un mensaje diciendo que no existe información con relación a los datos seleccionados.
		 * */
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaResultadosH vp= new VentanaResultadosH();
				vp.setVisible(true);
				dispose();
			}
		});
		btnBuscar.setBounds(180, 225, 89, 23);
		panel.add(btnBuscar);
		
		JCalendar calendarEntrada = new JCalendar();
		calendarEntrada.setBounds(23, 69, 190, 106);
		panel.add(calendarEntrada);
		
		JCalendar calendarSalida = new JCalendar();
		calendarSalida.setBounds(223, 69, 191, 106);
		panel.add(calendarSalida);
		
		JLabel lblNHuespedes = new JLabel("N\u00BA Huespedes:");
		lblNHuespedes.setBounds(22, 16, 106, 20);
		panel.add(lblNHuespedes);
		
		String[] NHuespedes = {"1","2","3","4","5"};
		JComboBox comboBox = new JComboBox(NHuespedes);
		comboBox.setBounds(143, 13, 48, 26);
		panel.add(comboBox);
		
	}
}

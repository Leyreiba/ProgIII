import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class ControladorResultadosBusqueda extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControladorResultadosBusqueda frame = new ControladorResultadosBusqueda();
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
	public ControladorResultadosBusqueda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		JPanel panelSur = new JPanel();
//		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		
		
		
	}
	public void CrearTabla() {
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
	    String nombresColumnas[] = {"ORIGEN","DESTINO","DURACION","PRECIO","HORA","DIA","MES","AÑO"}; //array con los titulos de cada columna
	    Object datos[][] = Login.bd.volcarDatosTabla(); //array donde voy a volcar los datos de la bd que se correspondan con los datos seleccionados anteriormente
	    JTable tablaSur = new JTable(datos,nombresColumnas); //metemos en una jtable estos arrays
	    panelSur.setLayout(new BorderLayout());
	    panelSur.add(tablaSur.getTableHeader(), BorderLayout.NORTH); 
	    panelSur.add(tablaSur, BorderLayout.CENTER);
	    tablaSur.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				 /**
	                * Al seleccionar una opción de las que nos aparezcan, nos lleva a la siguiente ventana(Gestión compras)
	                * */
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
		}
	
	

}

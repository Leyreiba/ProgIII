package Login;
import static org.junit.Assert.*;


import BaseDeDatos.BD;
import junit.framework.TestCase;

public class Test extends TestCase{

	public void setUp() throws Exception {
	}

	
	public void test() {
		int result= BD.existeUsuario("leyre", "leyre");
		assertEquals(2, result);

	}

}

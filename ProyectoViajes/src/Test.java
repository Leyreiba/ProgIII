import static org.junit.Assert.*;

import org.junit.Before;

import BaseDeDatos.BD;
import junit.framework.TestCase;

public class Test extends TestCase{

	@Before
	public void setUp() throws Exception {
	}

	@org.junit.Test
	public void test() {
		int result= BD.existeUsuario("leyre", "leyre");
		assertEquals(2, result);
	}

}

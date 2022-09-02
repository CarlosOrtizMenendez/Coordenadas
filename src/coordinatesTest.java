import static org.junit.Assert.*;

import org.junit.Test;

public class coordinatesTest {

	@Test
	public void coordTest() {
		double valor = 22.4335;
		double[] resultado = coordinates.coord(valor);
		double[] esperado = {22, 26, 0.6};
		assertArrayEquals(resultado, esperado, 0.01);
	}

}

package plugins;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class FilterTest {

	PluginFilter f;
	
	@Before
	public void setUp() throws Exception {
		f = new PluginFilter();
	}

	@Test
	public void notExistsFileTest() {
		assertFalse(f.accept(null, "jenexistepas.class"));
	}
	
	@Test
	public void dirTest() {
		File dir = new File("./bin");
		assertTrue(f.accept(dir, "PluginFilter.class"));
	}

}

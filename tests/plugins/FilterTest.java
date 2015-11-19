package plugins;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class FilterTest {

	PluginFilter f;
	File dir;
	
	@Before
	public void setUp() throws Exception {
		dir = new File("./dropins");
		f = new PluginFilter();
	}

	@Test
	public void notExistsFileTest() {
		assertFalse(f.accept(dir, "jenexistepas.class"));
	}
	
	@Test
	public void existsFileTest() {
		assertTrue(f.accept(dir, "ToUppercasePlugin.class"));
	}

}

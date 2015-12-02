package plugins;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class FilterTest {

	PluginFilter f;
	File dir;
	
	@Before
	public void setUp() throws Exception {
		dir = new File("./dropins/plugins");
		f = new PluginFilter();
	}

	@Test
	public void notExistsDirectoryTest() {
		f.accept(new File("./jaiessayedexister"), "jenexistepas.class");
	}
	
	@Test
	public void notExistsFileTest() {
		assertFalse(f.accept(dir, "jenexistepas.class"));
	}
	
	@Test
	public void wrongExtensionTest() {
		assertFalse(f.accept(dir, "ToLowercasePlugin.ext"));
	}
	
	@Test
	public void pluginWithConstructorTest() {
		assertFalse(f.accept(dir, "PluginWithConstructor.class"));
	}
	
	@Test
	public void pluginNotImplementsPluginTest() {
		assertFalse(f.accept(dir, "FalsePlugin.class"));
	}
	
	@Test
	public void pluginTest() {
		assertTrue(f.accept(dir, "ToLowercasePlugin.class"));
	}
}

package plugins;

import java.io.File;
import java.io.FilenameFilter;

public class PluginFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		if(name.endsWith(".class")){
			
			return true;
		}
		return false;
	}

}

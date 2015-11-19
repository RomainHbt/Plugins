package plugins;

import java.io.File;
import java.io.FilenameFilter;

public class PluginFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name){
		if(!dir.exists()) return false;
		if(!(new File(dir.getName()+name)).exists()) return false;
		if(!name.endsWith(".class")) return false;
		
		return true;
	}

}

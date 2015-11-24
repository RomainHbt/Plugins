package plugins;

import java.io.File;
import java.io.FilenameFilter;

public class PluginFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name){
		if(!dir.exists()) return false;
		
		File fichier = new File(dir.getName()+name);
		
		if(!fichier.exists()) return false;
		if(!name.endsWith(".class")) return false;
		
		return true;
	}

}

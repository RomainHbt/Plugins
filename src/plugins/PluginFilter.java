package plugins;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;

public class PluginFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name){
		Class<?> c = null;
		Object instance = null;
		
		if(!dir.exists()) 
			return false;
		
		File fichier = new File(dir.getPath()+File.separator+name);
		
		if(!fichier.exists()) 
			return false;
		if(!name.endsWith(".class")) 
			return false;
		
		try {
			c = Class.forName("plugins."+name.substring(0, name.length()-6));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		try {
			// Test if there is an empty constructor
			if(c.getConstructor() == null){
				System.err.println("Pas de constructeur vide");
				return false;
			}
			// Instance new object with its constructor
			instance = c.getConstructor().newInstance();
			
			// Test if the new instance implements Plugin
			if(!(instance instanceof plugins.Plugin)){
				return false;
			}
			
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}

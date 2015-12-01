package plugins;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name){
		Class<?> c = null;
		Object instance = null;
		URL fileUrl = null;
		
		// Test if dir exists
		if(!dir.exists()) 
			return false;
		// Then load the file
		File fichier = new File(dir.getPath()+File.separator+name);
		
		// Test if exits and if its name finished with ".class"
		if(!fichier.exists())
			return false;
		if(!name.endsWith(".class")) 
			return false;
		
		try {
			// Convert File to a URL
			fileUrl = fichier.toURL();
		    URL[] urls = new URL[]{fileUrl};
		    
		    // Create a new class loader with the directory
		    ClassLoader cl = new URLClassLoader(urls);
		    
			// c = Class.forName("plugins."+name.substring(0, name.length()-6));
		    c = cl.loadClass("plugins."+name.substring(0, name.length()-6));
		    
		} catch (ClassNotFoundException | MalformedURLException e) {
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
		
		System.out.println(instance);

		return true;
	}

}

package plugins;

import java.io.File;
import java.io.FilenameFilter;

public class PluginFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name){
		Class<?> c = null;
		Object instance = null;
		
		// Test if dir exists
		if(!dir.exists()) {
			System.err.println(dir.getName()+" : Dossier innexistant");
			return false;
		}
		// Then load the file
		File fichier = new File(dir.getPath()+File.separator+name);
		
		// Test if exits and if its name finished with ".class"
		if(!fichier.exists()){
			System.err.println(fichier.getName()+" : Fichier introuvable");
			return false;
		}
		if(!name.endsWith(".class")) {
			System.err.println(fichier.getName()+" : Le fichier specifie n'est pas un .class");
			return false;
		}
		
		try {
			c = Class.forName("plugins."+name.substring(0, name.length()-6));
		    
		} catch (ClassNotFoundException e) {
			System.err.println(fichier.getName()+" : La classe n'a pas été trouvée (est-elle dans le package plugins ?)");
			return false;
		}

		// Test if there is an empty constructor
		try {
			if(c.getConstructor() == null){
				System.err.println(fichier.getName()+" : Pas de constructeur vide");
				return false;
			}
			// Instance new object with its constructor
			instance = c.getConstructor().newInstance();
			
			// Test if the new instance implements Plugin
			if(!(instance instanceof plugins.Plugin)){
				System.err.println(fichier.getName()+" : La classe n'implemente pas Plugin");
				return false;
			}
		} catch (NoSuchMethodException e) {
			System.err.println(fichier.getName()+" : Pas de constructeur vide");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}

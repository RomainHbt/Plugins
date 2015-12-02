package plugins;

import ihm.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

public class PluginFinder {
	private static int DELAY = 2000; 
	private PluginFilter filter;
	private Timer timer;
	private File dir;
	
	private MainFrame frame;
	
	private List<Plugin> importedPlugins;

	public PluginFinder(String dir, MainFrame frame) {
		this.frame = frame;
		this.filter = new PluginFilter();
		this.dir = new File(dir);
		this.timer = new Timer(DELAY, new CheckFilesListener());
		this.timer.start();
		this.importedPlugins = getPluginListByNames(PluginFinder.this.dir.list(PluginFinder.this.filter));
	}
	
	private class CheckFilesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			List<Plugin> newPlugin = null;
			
			String[] t = PluginFinder.this.dir.list(PluginFinder.this.filter);
			
			newPlugin = getPluginListByNames(t);
			
			for (Plugin p : getRemovedPlugin(importedPlugins, newPlugin)) {
				PluginFinder.this.frame.removePlugin(p);
			}
			
			for (Plugin plugin : newPlugin) {
				// Vérification si existant
				if(!(PluginFinder.this.frame.existsPlugin(plugin.getLabel()))){
					// Si non : ajout au menu
					PluginFinder.this.frame.addPlugin(plugin);
				}
			}
			
			importedPlugins = newPlugin;
		}
		
	}
	
	private List<Plugin> getRemovedPlugin(List<Plugin> alreadyImported, List<Plugin> newPluginList){
		alreadyImported.removeAll(newPluginList);
		return alreadyImported;
		
	}
	
	private List<Plugin> getPluginListByNames(String [] nameList){
		Class<?> c = null;
		Plugin instance = null;
		List<Plugin> newPlugin = new LinkedList<>();
		
		for (String nom : nameList) {
			// Création objet plugin
			try {
				c = Class.forName("plugins."+nom.substring(0, nom.length()-6));
				instance = (Plugin) c.getConstructor().newInstance();
				newPlugin.add(instance);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return newPlugin;
	}
}

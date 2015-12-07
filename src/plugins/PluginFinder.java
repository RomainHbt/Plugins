package plugins;

import ihm.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

/**
 * The plugin finder finds the plugins into a directory envery DELAY seconds and adds plugins into the frame's menu
 * @author hembert bellamy
 *
 */
public class PluginFinder {
	private static int DELAY = 2000; 
	private PluginFilter filter;
	private Timer timer;
	private File dir;
	
	private MainFrame frame;
	
	private List<Plugin> importedPlugins;

	/**
	 * Creation of the plugin finder
	 * @param dir Path to the plugins folder
	 * @param frame Frame of the editor
	 */
	public PluginFinder(String dir, MainFrame frame) {
		this.frame = frame;
		this.filter = new PluginFilter();
		this.dir = new File(dir);
		this.timer = new Timer(DELAY, new CheckFilesListener());
		this.timer.start();
		this.importedPlugins = getPluginListByNames(PluginFinder.this.dir.list(PluginFinder.this.filter));
	}
	
	/**
	 * The listener which check new files into the directory. It's called every DELAY seconds
	 * @author hembert bellamy
	 *
	 */
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
				// if the plugin exist in the menu
				if(!(PluginFinder.this.frame.existsPlugin(plugin.getLabel()))){
					// if not : we add the plugin into the menu
					PluginFinder.this.frame.addPlugin(plugin);
				}
			}
			
			importedPlugins = newPlugin;
		}
		
	}
	
	/**
	 * Return a list of removed plugins
	 * @param alreadyImported plugins alereay imported
	 * @param newPluginList new plugins added
	 * @return a list of plugins
	 */
	private List<Plugin> getRemovedPlugin(List<Plugin> alreadyImported, List<Plugin> newPluginList){
		alreadyImported.removeAll(newPluginList);
		return alreadyImported;
		
	}
	
	/**
	 * Return a list of plugins finds by their names
	 * @param nameList name's list
	 * @return a list of plugins
	 */
	private List<Plugin> getPluginListByNames(String [] nameList){
		Class<?> c = null;
		Plugin instance = null;
		List<Plugin> newPlugin = new LinkedList<>();
		
		for (String nom : nameList) {
			// plugin creation
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

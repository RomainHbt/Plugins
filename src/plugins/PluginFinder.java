package plugins;

import ihm.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Timer;

public class PluginFinder {

	private static int DELAY = 2000; 
	private PluginFilter filter;
	private Timer timer;
	private File dir;
	
	private MainFrame frame;

	public PluginFinder(String dir, MainFrame frame) {
		this.frame = frame;
		this.filter = new PluginFilter();
		this.dir = new File(dir);
		this.timer = new Timer(DELAY, new CheckFilesListener());
		this.timer.start();
	}
	
	private class CheckFilesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] t = PluginFinder.this.dir.list(PluginFinder.this.filter);
			Class<?> c = null;
			Plugin instance = null;
			for (String nom : t) {
				// Création objet plugin
				try {
					c = Class.forName("plugins."+nom.substring(0, nom.length()-6));
					instance = (Plugin) c.getConstructor().newInstance();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Vérification si existant
				
				if(!(PluginFinder.this.frame.existsPlugin(instance.getLabel()))){
					// Si non : ajout au menu
					PluginFinder.this.frame.addPlugin(instance);
				}
			}
		}
		
	}
}

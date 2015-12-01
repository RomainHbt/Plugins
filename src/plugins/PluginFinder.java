package plugins;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.Timer;

public class PluginFinder {

	private static int DELAY = 2000;
	private PluginFilter filter;
	private Timer timer;
	private File dir;
	
	private JFrame frame;

	public PluginFinder(String dir, JFrame frame) {
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
			for (String nom : t) {
				System.out.println(nom);
				
				// Création objet plugin
				// Vérification si existant
					// Si non : ajout au menu
			}
		}
		
	}
}

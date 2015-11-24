package plugins;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class PluginFinder {

	private static int DELAY = 2000;
	private PluginFilter filter;
	private Timer timer;
	private File dir;
	private List<File> files;

	public PluginFinder(String dir) {
		this.files = new ArrayList<File>();
		this.filter = new PluginFilter();
		this.dir = new File(dir);
		this.timer = new Timer(DELAY, new CheckFilesListener());
		this.timer.start();
	}
	
	private class CheckFilesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] t = PluginFinder.this.dir.list(PluginFinder.this.filter);
			PluginFinder.this.files.clear();
			for (String nom : t) {
				PluginFinder.this.files.add(new File(nom));
			}
		}
		
	}
}

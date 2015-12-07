package main;

import ihm.MainFrame;
import plugins.PluginFinder;

/**
 * Main class
 * @author hembert bellamy
 *
 */
public class Main {

	/**
	 * Main of the program
	 * @param args Args in the command line
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		final String pluginFolder = "./dropins/plugins";
		
		MainFrame f = new MainFrame("Un super cool editeur.", pluginFolder);
		PluginFinder finder = new PluginFinder(pluginFolder, f);
	}
}

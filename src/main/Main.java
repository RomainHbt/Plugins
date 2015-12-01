package main;

import ihm.MainFrame;
import plugins.PluginFinder;

public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		final String pluginFolder = "./dropins/plugins";
		
		MainFrame f = new MainFrame("Un super cool éditeur.", pluginFolder);
		PluginFinder finder = new PluginFinder(pluginFolder, f);
	}
}

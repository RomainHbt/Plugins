package main;

import ihm.MainFrame;
import plugins.PluginFinder;

public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MainFrame f = new MainFrame("Un super cool éditeur.");
		PluginFinder finder = new PluginFinder("./dropins/plugins", f);
	}
}

package main;

import ihm.MainFrame;

import javax.swing.JFrame;

import plugins.PluginFinder;

public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		JFrame f = new MainFrame("Un super cool éditeur.");
		PluginFinder finder = new PluginFinder("./dropins", f);
	}
}

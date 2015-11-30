package main;

import ihm.MainFrame;

import java.awt.Frame;

import plugins.PluginFinder;

public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		PluginFinder finder = new PluginFinder("./dropins");
		Frame f = new MainFrame("Un super cool éditeur.");
	}
}

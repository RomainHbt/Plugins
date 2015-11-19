package main;

import java.io.File;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File dir = new File("./bin/plugins");
		String[] t = dir.list();
		for (String string : t) {
			System.out.println(string);
		}
	}
}

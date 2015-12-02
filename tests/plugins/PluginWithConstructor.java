package plugins;

import plugins.Plugin;

public class PluginWithConstructor implements Plugin{

	@SuppressWarnings("unused")
	private String attribut;
	
	public PluginWithConstructor(String txt){
		this.attribut = txt;
	}
	
	@Override
	public String transform(String s) {
		return null;
	}

	@Override
	public String getLabel() {
		return null;
	}

}

package plugins;

/**
 * Interface for a plugin. A plugin have to implements this interface to be added in the program !
 * @author hembert bellamy
 *
 */
public interface Plugin {
	/**
	 * The method to transform the text in the text aera
	 * @param s Text to be transofrmed
	 * @return the text transformed
	 */
	public String transform(String s);
	
	/**
	 * Name of the plugin
	 * @return the plugin's name
	 */
	public String getLabel();
}

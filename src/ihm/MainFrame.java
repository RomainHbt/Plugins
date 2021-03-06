package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import plugins.Plugin;

/**
 * Class to create the main editor
 * @author hembert bellamy
 *
 */
public class MainFrame extends JFrame{
	private static final long serialVersionUID = -5459108069903947815L;
	// The area where you can write your text
	private JTextArea textArea;
	// Menu bar on the top of the window
	private JMenuBar menu;
	// The different sub-menu
	private JMenu files;
	private JMenu tools;
	private JMenu help;
	// The item of each sub-menu
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem exit;
	private JMenuItem helpMenuItem;
	// Use to open the fileChooser
	private String pluginFolder;
	// Map of plugins
	private Map<String, Plugin> plugins;
	private Map<String, JMenuItem> pluginsItem;
	
	private File file;
	
	/**
	 * Constructor for the editor
	 * @param title Frame's name
	 * @param pluginFolder Path to the plugins folder
	 */
	public MainFrame(String title, String pluginFolder) {
		super(title);
		this.pluginFolder = pluginFolder;

		textArea = new JTextArea(5,30);
		
		// Customization of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		// Setup the menu
		this.addMenu();
		this.add(textArea);
		// Display the window
		this.setVisible(true);
		
		this.plugins = new HashMap<>();
		this.pluginsItem = new HashMap<>();
	}
	
	/**
	 * Add a top menu on the frame
	 */
	public void addMenu() {
		menu = new JMenuBar();
		files = new JMenu("Fichier");
		tools = new JMenu("Outils");
		help = new JMenu("Aide");
		
		open = new JMenuItem("Ouvrir");
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fileChooser = new JFileChooser(pluginFolder);
				fileChooser.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// Load File
						if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
							file = fileChooser.getSelectedFile();
							try {
								FileInputStream in = new FileInputStream(file);
								byte[] buffer = new byte[512];
								StringBuilder sb = new StringBuilder();
								while(in.read(buffer) > 0){
									sb.append(new String(buffer));
								}
								in.close();
								MainFrame.this.setText(sb.toString());
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				});
				
				fileChooser.showDialog(MainFrame.this, "Retour");
				
			}
		});
		
		exit = new JMenuItem("Quitter");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		save = new JMenuItem("Enregistrer");
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(file != null){
					try {
						FileOutputStream out = new FileOutputStream(file);
						out.write(textArea.getText().getBytes());
						out.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else{
					final JFileChooser fileChooser = new JFileChooser(pluginFolder);
					fileChooser.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
								file = fileChooser.getSelectedFile();
								try {
									FileOutputStream out = new FileOutputStream(file);
									out.write(textArea.getText().getBytes());
									out.close();
								}catch(IOException ex){
									ex.printStackTrace();
								}
							}
						}
					});
					
					fileChooser.showDialog(MainFrame.this, "Enregistrer");
				}
			}
		});
		
		saveAs = new JMenuItem("Enregistrer sous");
		saveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fileChooser = new JFileChooser(pluginFolder);
				fileChooser.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
							file = fileChooser.getSelectedFile();
							try {
								FileOutputStream out = new FileOutputStream(file);
								out.write(textArea.getText().getBytes());
								out.close();
							}catch(IOException ex){
								ex.printStackTrace();
							}
						}
					}
				});
				
				fileChooser.showDialog(MainFrame.this, "Enregistrer sous");
				
			}
		});
		
		helpMenuItem = new JMenuItem("Comment faire...");
		helpMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpFrame();
			}
		});
		
		files.add(open);
		files.addSeparator();
		files.add(exit);
		files.addSeparator();
		files.add(save);
		files.add(saveAs);
		
		menu.add(files);
		menu.add(tools);
		menu.add(help);
		
		help.add(helpMenuItem);
		
		setJMenuBar(menu);
	}
	
	/**
	 * Set a text in the teaxt area
	 * @param s The text to set
	 */
	public void setText(String s){
		if(this.textArea.getSelectedText() == null){
			this.textArea.setText(s);
		}else{
			int startIdx = this.textArea.getSelectionStart();
			int endIdx = this.textArea.getSelectionEnd();
			char[] baseText = this.textArea.getText().toCharArray();
			char[] changeSequence = s.toCharArray();
			
			for (int i = startIdx; i < endIdx; i++) {
				baseText[i] = changeSequence[i-startIdx];
			}
			this.textArea.setText(new String(baseText));
		}
	}
	
	/**
	 * Return the text in the text area
	 * @return
	 */
	public String getText(){
		return (this.textArea.getSelectedText() == null ? 
				this.textArea.getText() : 
				this.textArea.getSelectedText());
	}
	
	/**
	 * Test if the plugin exists in the menu
	 * @param nom Plugin's name
	 * @return true if the plugin is already in the menu, false if not
	 */
	public boolean existsPlugin(String nom){
		return this.plugins.containsKey(nom);
	}
	
	/**
	 * Add a plugin in the menu
	 * @param plugin Plugin to be added
	 */
	public void addPlugin(Plugin plugin){
		String name = plugin.getLabel();
		this.plugins.put(name, plugin);
		
		JMenuItem pluginMenu = new JMenuItem(name);
		
		pluginMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				// Transform the text
				String textTransformed = MainFrame.this.plugins.get(a.getActionCommand()).transform(MainFrame.this.getText());
				MainFrame.this.setText(textTransformed);
			}
		});
		
		this.pluginsItem.put(name, pluginMenu);
		this.tools.add(pluginMenu);
	}
	
	/**
	 * Remove a from in the menu
	 * @param plugin Plugin to be removed
	 */
	public void removePlugin(Plugin plugin){
		this.plugins.remove(plugin.getLabel());
		this.tools.remove(pluginsItem.get(plugin.getLabel()));
		this.pluginsItem.remove(plugin.getLabel());
	}

}

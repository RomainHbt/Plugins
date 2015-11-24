package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

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
	private JMenuItem exit;
	
	public MainFrame(String title) {
		super(title);
		
		textArea = new JTextArea(5,30);
		
		// Customization of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		// Setup the menu
		this.addMenu();
		this.add(textArea);
		// Display the window
		this.setVisible(true);
	}
	
	public void addMenu() {
		menu = new JMenuBar();
		files = new JMenu("Fichier");
		tools = new JMenu("Outils");
		help = new JMenu("Aide");
		
		open = new JMenuItem("Ouvrir");
		//open.addActionListener(new OpenListener(this));
		exit = new JMenuItem("Quitter");
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		files.add(open);
		files.addSeparator();
		files.add(exit);
		
		menu.add(files);
		menu.add(tools);
		menu.add(help);
		
		setJMenuBar(menu);
	}

}

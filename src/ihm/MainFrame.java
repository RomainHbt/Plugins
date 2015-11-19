package ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = -5459108069903947815L;
	private JTextArea textArea;
	
	public MainFrame(String title) {
		super(title);
		
		textArea = new JTextArea(5,30);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.addMenu();
		this.add(textArea);
		this.setVisible(true);
	}
	
	public void addMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu files = new JMenu("Fichier");
		JMenu tools = new JMenu("Outils");
		JMenu help = new JMenu("Aide");
		
		JMenuItem open = new JMenuItem("Ouvrir");
		//open.addActionListener(new OpenListener(this));
		JMenuItem exit = new JMenuItem("Quitter");
		
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

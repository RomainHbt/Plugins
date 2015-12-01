package ihm;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class HelpFrame extends JFrame{
	private static final long serialVersionUID = -649485865120128049L;

	public HelpFrame() {
		super("Aide");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450, 300);
		this.setResizable(false);
		
		JTextArea text = new JTextArea(	" Bienvenu dans notre nouvel editeur de texte !\n\n" +
										" Il est bien sûr possible d'écrire du texte dans la fenêtre mais \n" +
										" pas seulement.\n\n" +
										" Vous avez également la possibilité de personnaliser votre texte en \n" +
										" ajoutant des plugins à l'éditeur. \n" +
										" Pour cela il faut simplement ajouter les classes correspondantes dans\n" +
										" le dossier prévu à cet\n" +
										" effet. Pour ouvrir le dossier, cliquez sur Fichier > Ouvrir.\n\n" +
										" Une fois les plugins souhaités importés, vous pouvez :\n" +
										"     - Cliquer sur Outils > \"nom du plugin\" pour transformer le texte.\n" +
										"     - Selectionner une partie du texte que vous voulez transformer,\n" +
										" puis selectionner le plugin.\n\n" +
										" En vous souhaitant une bonne utilisation.");
		
		text.setEditable(false);
		
		this.add(text);
		this.setVisible(true);
	}
}

package ihm;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Class to create the help frame, when the user click on "Aide"
 * @author hembert bellamy
 *
 */
public class HelpFrame extends JFrame{
	private static final long serialVersionUID = -649485865120128049L;

	/**
	 * Constructor for the help frame
	 */
	public HelpFrame() {
		super("Aide");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450, 300);
		this.setResizable(false);
		
		JTextArea text = new JTextArea(	" Bienvenu dans notre nouvel editeur de texte !\n\n" +
										" Il est bien sur possible d'ecrire du texte dans la fenetre mais \n" +
										" pas seulement.\n\n" +
										" Vous avez egalement la possibilite de personnaliser votre texte en \n" +
										" ajoutant des plugins a l'editeur. \n" +
										" Pour cela il faut simplement ajouter les classes correspondantes dans\n" +
										" le dossier prevu a cet\n" +
										" effet. Pour ouvrir le dossier, cliquez sur Fichier > Ouvrir.\n\n" +
										" Une fois les plugins souhaites importes, vous pouvez :\n" +
										"     - Cliquer sur Outils > \"nom du plugin\" pour transformer le texte.\n" +
										"     - Selectionner une partie du texte que vous voulez transformer,\n" +
										" puis selectionner le plugin.\n\n" +
										" En vous souhaitant une bonne utilisation.");
		
		text.setEditable(false);
		
		this.add(text);
		this.setVisible(true);
	}
}

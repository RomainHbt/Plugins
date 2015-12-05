Editeur de texte avec Plugins
BELLAMY Matthieu - HEMBERT Romain
Groupe 1
08/12/2015


### 1/ Introduction

Cette application est un éditeur de texte fonctionnant à base de Plugins. Entrez un texte à la main dans la zone de texte, ou chargez un fichier texte depuis le menu Fichier.
Pour modifier le texte, sélectionnez la partie à modifier, puis cliquez sur le plugin souhaité dans le menu Plugins. Par défaut, sans sélection, tout le texte est transformé lorsqu'on utilise un plugin.


### 2/ Usage

// A faire

### 3/ Architecture

Les sources sont décomposées en 3 packages dont 2 importantes (la troisième étant le Main) :
- ihm : ce package contient toutes les interfaces de l'application. Tout le côté graphique est géré dedans, y compris la gestion du menu Plugins lorsqu'un plugin est ajouté ou supprimé.
- plugins : ce dossier contient de base toutes les classes gérant l'ajout et la suppression de plugins. Elle contient aussi l'interface Plugin, qui sert de base à tous les plugins de l'application.

Il y a aussi le dossier dropins, à la racine du projet. Ce dossier ne contient pas de sources, il contient les fichiers .class des plugins dans le dossier plugins du dossier dropins. C'est dans ce dossier qu'il faut déposer les fichier pour ajouter un plugin. Le logiciel s'occupe d'ajouter le plugin à l'application automatiquement, même si elle est en marche (actualisation du menu Plugin toutes les 2 secondes).

### 4/ Code sample

La fonction actionPerformed de la classe privée CheckFilesListener (dans PluginFinder) est appelée toutes les 2 secondes. Elle se charge de lire le contenu du dossier passé à la création du PluginFinder (ici ./dropins/plugins), et d'ajouter ou de supprimer les plugins à l'application.

	public void actionPerformed(ActionEvent e) {
		List<Plugin> newPlugin = null;

		String[] t = PluginFinder.this.dir.list(PluginFinder.this.filter);
				
		newPlugin = getPluginListByNames(t);
				
		for (Plugin p : getRemovedPlugin(importedPlugins, newPlugin)) {
			PluginFinder.this.frame.removePlugin(p);
		}
				
		for (Plugin plugin : newPlugin) {
			// Vérification si existant
			if(!(PluginFinder.this.frame.existsPlugin(plugin.getLabel()))){
				// Si non : ajout au menu
				PluginFinder.this.frame.addPlugin(plugin);
			}
		}
				
		importedPlugins = newPlugin;
	}

La fonction getPluginListByNames crée à partie d'une liste de noms de plugins (en String) une liste de Plugins créés, et prêts à être utilisés.

	private List<Plugin> getPluginListByNames(String [] nameList){
		Class<?> c = null;
		Plugin instance = null;
		List<Plugin> newPlugin = new LinkedList<>();
			
		for (String nom : nameList) {
			// Création objet plugin
			try {
				c = Class.forName("plugins."+nom.substring(0, nom.length()-6));
				instance = (Plugin) c.getConstructor().newInstance();
				newPlugin.add(instance);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return newPlugin;
	}

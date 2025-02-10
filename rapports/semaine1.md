# Rapport semaine1

## Organisation

Lors de la 1ʳᵉ séance, nous avons commencé par lire attentivement le sujet afin de bien cerner les attentes pour la conception du jeu.

Par la suite, nous avons synthétisé nos idées sur papier et commencé à élaborer notre premier diagramme de classes, décrivant les différentes entités que nous avions envisagées.

## Différents choix de conception

### Classe pokemon

Concernant la classe Pokémon, nous avons initialement envisagé de créer une classe avec des attributs tels que la vie, le nom, les points de force et un élément. Nous avons pensé à l'élément comme une interface, avec des classes filles Air, Eau, Terre et Feu. Cependant, nous avons opté pour une approche différente par la suite.

Nous avons décidé de faire de la classe Pokémon une classe abstraite, avec une méthode d'attaque abstraite qui varierait en fonction des sous-classes Air, Terre, Eau et Feu.

Cependant, cette approche a engendré des désaccords au sein du binôme. D'un côté, Amine Benomar souhaitait intégrer le maximum de fonctionnalités et d'attributs dans la classe Pokémon, tandis que de l'autre côté, Amine Belhaj préférait séparer les fonctionnalités autant que possible pour rendre la conception plus modulaire. 

Cela aurait conduit à une classe Pokémon sans prise en charge des affinités (donc un Pokémon neutre), avec des sous-classes gérant les affinités. Cependant, cela aurait nécessité de réécrire la méthode "attaque" dans chaque sous-classe, bien que seuls quelques paramètres mineurs changent.

Nous avons donc décidé de combiner nos idées pour avoir une classe Pokémon neutre, héritant d'une classe abstraite PokémonAffinité qui gère la partie attaque, elle-même héritant de sous-classes pour les différents types.

### Classe joueur

Nous avons ensuite conçu la classe joueur qui possede comme attributs différentes listes:
1) Pokemons dédiés à la main du joueur.
2) Pokemons qui sont placés sur le terrain du joueur.
3) Pokemons que le joueur peut piocher lorsque l'un de ses Pokémon sur le terrain meurt ou lorsque sa main contient moins de 5 Pokémon.
4) Pokémons du joueur qui ont été éliminés.

Cette classe Joueur comprend un constructeur avec un booléen pour déterminer s'il s'agit du premier joueur ou non, afin d'ajuster le nombre de Pokémon dans la pioche du joueur.

Elle contient également plusieurs méthodes pour faciliter le jeu du joueur, telles que piocher, placer, attaquer, ainsi que deux autres méthodes pour gérer les Pokémon générés aléatoirement et la pioche pour la liste des pioches.

### Classe ordinateur

La classe Ordinateur est une sous-classe de Joueur, car un ordinateur doit obligatoirement placer ses Pokémon et attaquer différemment d'un joueur humain. C'est pourquoi cette classe doit redéfinir les méthodes placer et attaquer.

### Classe jeu

La classe Jeu comprend deux joueurs : un joueur humain et un joueur ordinateur, ainsi qu'une liste des noms des différents Pokémons.

### Classe affichage

La classe Affichage sera chargée de gérer les différents affichages du jeu.
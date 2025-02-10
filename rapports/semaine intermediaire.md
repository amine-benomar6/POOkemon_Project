# Rapport intermédiaire

## Organisation

Après le rendu de la première semaine, nous avons continué à avancer sur le projet en implémentant et en complétant les classes et les fonctions telles qu'elles étaient définies dans l'UML.

Au fur et à mesure de notre progression dans le projet, nous avons réalisé que notre UML initial n'était pas assez détaillé. Nous avons identifié de nombreuses fonctions qui étaient trop "lourdes", ce qui nous a incités à décomposer certaines d'entre elles en ajoutant des fonctions intermédiaires. Par conséquent, nous avons mis à jour l'UML pour refléter ces changements.

## Modifications

Nous avons également apporté quelques modifications au code, même s'il était déjà fonctionnel. Ces modifications avaient pour objectif d'améliorer la lisibilité du code, son optimisation ou encore l'expérience utilisateur.

### Classe Joueur

La principale modification apportée à la classe Joueur concerne la fonction "attaquer".

À l'origine, lorsque c'était au tour du joueur d'attaquer, il devait saisir le nom du Pokémon avec lequel il voulait attaquer, ainsi que le nom du Pokémon qu'il souhaitait attaquer.

Non seulement cette méthode était fastidieuse, mais elle n'était pas optimisée car nous devions ensuite vérifier si le Pokémon était présent sur le terrain du joueur et ensuite sur celui de l'ennemi. Initialement, nous avons réalisé cette vérification avec deux boucles (ce qui n'était pas optimal), puis avec la méthode ".contains" (ce qui était mieux).

Cependant, cela exigeait toujours que le joueur saisisse le nom complet des Pokémon, sans erreur. Nous avons donc décidé d'adopter une approche différente. Désormais, le joueur sélectionnera le numéro du Pokémon avec lequel il souhaite attaquer et celui qu'il souhaite attaquer. Avec ce système de numérotation, cela permet une plus grande fluidité en jeu et nous pouvons utiliser directement ce numéro comme indice du Pokémon dans le code.

### Classe Ordinateur

Nous avons également résolu quelques problèmes rencontrés dans la fonction "attaquer" (décidément).

Nous avions des problèmes avec le choix du Pokémon qui devait attaquer, car il ne choisissait pas celui qui avait une faiblesse par rapport à l'affinité.


## Futures modifications

Avec le temps, on s'est rendu compte qu'il y avait encore d'autres modifications interessantes qu'on réalisera lors de la deuxième partie.

Par exemple, pour les classes correspondantes aux attributs, on a mit en place 4 classes mais qui sont en réalité quasiment identiques. C'est pour cela qu'on souhaite changer cela. Nous avons pensé à tout gérer directement dans "PokemonAffinite", comme ca on aura la classe "Pokemon" pour faire des pokemons neutres et qui contient la base de la classe, et une classe fille "PokemonAffinite" qui va gérer les affinités.

Nous avons également pensé à changer le systeme de tour. Pour le moment, nous avons fait en sorte qu'un tour se déroule en deux actions: l'attaque du joueur 1 puis l'attaque du joueur 2.
Nous souhaiterons changer cette disposition afin que chaque joueur doit attaquer 3 fois, sans rejouer les memes pokemons.

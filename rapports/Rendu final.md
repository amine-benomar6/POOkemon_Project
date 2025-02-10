# Rapport Final

## Organisation

Comme durant les autres semaines, nous avons continué à avancer dans le projet en nous répartissant les charges de travail. Étant donné qu'il ne nous restait que des finitions et des réarrangements, depuis une semaine, nous avons procédé différemment. Amine Belhaj s'est chargé de tester les possibilités et de réfléchir à des modifications pour améliorer la conception ainsi que l'expérience utilisateur, tandis qu'Amine Benomar s'est chargé de l'implémentation de ces modifications et également des tests.

## Modifications

Nous n'avons pas ajouté de nouvelle classe mais nous en avons amélioré certaines.

### Classe Pouvoir

Nous avons bien avancé sur les pouvoirs et avons réussi à implémenter les 8 pouvoirs obligatoires.

Pour éviter des problèmes d'interdépendance entre les différentes classes, nous avons décidé de modifier la classe, notamment ses attributs. Par exemple, nous avions initialement un attribut "m_joueur" qui nous permettait d'avoir accès au terrain et à la défausse du joueur (pour soins de zone ou nécromancie). Nous avons enlevé cet attribut pour passer le joueur en paramètre d'une fonction à la place. Au départ, nous voulions passer en paramètre uniquement ce dont nous avions besoin, par exemple la défausse pour la nécromancie ou le terrain pour les soins de zone. Cependant, comme nous travaillions avec une classe abstraite, nous aurions dû passer tous les paramètres à chaque fois, même pour des pouvoirs simples comme les soins simples, ou les mettre en NULL, ce qui n'est pas une bonne pratique (nous ne sommes pas en Python). Nous avons donc décidé de passer le joueur en paramètre d'une fonction pour avoir quand même accès à son terrain et à sa défausse si besoin.

### Classe Affichage

Nous avons également amélioré l'affichage. Afin d'améliorer l'expérience utilisateur, nous avons ajouté des éléments rendant le jeu plus esthétique, comme l'ajout de couleurs. De plus, la possibilité de consulter les regles au debut du jeu à été ajouté.

### Ajout des Tests Unitaires

Les tests unitaires ont été revus complètement. À la base, dans nos tests, nous créions un joueur et nous faisions les tests sur lui, mais comme le joueur se crée aléatoirement avec des pokemons aléatoires, les résultats changeaient tout le temps. Nous avions pensé que c'était une bonne idée pour montrer que le pouvoir fonctionne tout le temps avec tout type de pokemon, mais après réflexion, nous nous sommes dit qu'il serait préférable que les tests restent constants. C'est pourquoi nous avons créé un pokemon "en dur" directement dans les classes de test.
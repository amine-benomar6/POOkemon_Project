# Rapport semaine 3

## Organisation

Comme durant les autres semaines, nous avons continué à avancer dans le projet en nous répartissant les charges de travail.

## Modifications

Avant de commencer à travailler sur l'implémentation des pouvoirs, nous voulions nous assurer que toutes nos implémentations étaient bonnes et fonctionnelles. Cependant, ce n'était pas le cas. C'est pour cela que nous avons totalement revu notre façon de gérer les affinités.

### Classe Pokémon et Element

Initialement, les affinités étaient des sous-classes de `PokemonAffinite`, mais en réalité, ce n'était pas la meilleure façon de procéder, car nous ne pouvions pas faire en sorte qu'un Pokémon possède plusieurs affinités, par exemple. C'est pour cela que nous avons maintenant une seule classe `Pokemon` qui contient comme attribut une liste d'affinités. Cela permet, premièrement, de gérer les affinités en dehors des Pokémon dans 'Element' et, deuxièmement, qu'un Pokémon puisse avoir plusieurs affinités.

### Classe Jeu

Nous avons également changé notre vision des tours. Comme énoncé dans le rapport intermédiaire, nous avons modifié notre système de tour afin qu'un tour corresponde à l'attaque de tous les Pokémon présents sur le terrain.

## Autres modifications

Nous avons commencé à implémenter le système de pouvoirs, même s'il n'est pas encore fonctionnel.
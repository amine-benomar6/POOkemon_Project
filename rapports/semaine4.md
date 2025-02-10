# Rapport semaine 4

## Organisation

Comme durant les autres semaines, nous avons continué à avancer dans le projet en nous répartissant les charges de travail.

## Modifications

Nous avons modifié la gestion des différentes cartes main, terrain, pioche, défausse que nous avons séparé dans des classes distinctes.
On a décidé de créer ces classes-là, car la classe joueur avait beaucoup de responsabilité ce qui est contraire au principe de la programmation objet qui est qu'une classe à qu'une fonctionnalité et pas plusieurs. 

### Classe Pouvoir

Nous avons bien avancé sur les pouvoirs, on a réussi à implementer les 8 pouvoirs obligatoires. 

Nous avons décidé de faire une classe pouvoir abstract avec comme attributs 2 boolean pour savoir si le pouvoir est réutilisable ou pas et s'il est déjà utilisé ou pas, le nom du pouvoir, le joueur qui détient le pouvoir pour qu'il puisse choisir par la suite le pokemon de son terrain qui aura la capacite du pouvoir ainsi que le pokemon choisi par le joueur.

Les méthodes qui sont abstract sont `afficherDescripition()` `utilisationPouvoir()` `choisirPokemon(ennemi : Joueur, pokemonDetenteur : Pokemon)` et elles devront être réécrite dans les classes fille de celle-ci.
La méthode `choisirPokemon` a toujours en paramètre le joueur ennemi, car on ne peut pas prévoir à l'avance quel pouvoir on va être utilisé et quel pouvoir aura besoin du joueur ennemi pour cibler l'un de ses Pokémon.

### Classe Ordinateur

On a décidé de choisir les pouvoir de l'ordinateur dans l'ordre et pour choisir à quel pokemon il attribut cette capacité on a decidé de prendre le pokemon qui a le plus de vie et le plus fort. 
Pour choisir le pokemon ennemi ont choisi le pokemon ennemi qui a le moins de vie.

## Ajout des tests unitaires

Nous avons commencé à faire les tests unitaires pour chaque pouvoir, on a créé une partie de jeu, mais dans cette partie-là les 2 joueurs sont des ordinateurs pour ne pas avoir de problème avec les Scanners puisque dans les test la console d'entrée n'est pas accessible.  
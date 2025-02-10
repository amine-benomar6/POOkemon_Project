package pouvoir;

import jeu.Affichage;
import pokemon.Pokemon;
import jeu.Joueur;

public class Peur extends Pouvoir
{
    public Peur()
    {
        super("Peur");
        this.m_estReutilisable = false;
    }
    /**
     * Affiche la description du pouvoir Peur
     */
    @Override
    public void afficherDescripition()
    {
        Affichage.affichagePhrase("Peur: Pouvoir à utilisation unique. Le Pokémon choisit un Pokémon du camp adverse. Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, les attaques de celui-ci infligent 10 dégâts de moins.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Utilise le pouvoir Peur.
     * Cette méthode réduit définitivement l'attaque du Pokémon cible de 10 points.
     * @param pokemonCible Le Pokémon adverse dont l'attaque sera réduite.
     * @param detenteur Le Pokémon qui détient le pouvoir.
     * @param joueur Le joueur utilisant le pouvoir.
     */
    @Override
    public void utilisationPouvoir(Pokemon pokemonCible, Pokemon detenteur, Joueur joueur)
    {
        pokemonCible.setAttaque(pokemonCible.getAttaque()-10);
        Affichage.affichagePhrase(pokemonCible.getNom()+" a ses dégâts infligés réduits de 10, passant à "+ pokemonCible.getAttaque());
        m_estDejaUtilise = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return Le type de cible du pouvoir (ENNEMI).
     */
    @Override
    public TypePouvoir getCible()
    {
        return TypePouvoir.ENNEMI;
    }
}
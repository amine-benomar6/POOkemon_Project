package pouvoir;

import jeu.Affichage;
import jeu.Joueur;
import pokemon.Pokemon;


public class Resistance extends Pouvoir
{
    public Resistance()
    {
        super("Résistance");
        this.m_estReutilisable = false;
    }
    /**
     * Affiche la description du pouvoir Résistance
     */
    @Override
    public void afficherDescripition()
    {
        Affichage.affichagePhrase(m_nomPouvoir+" : Le Pokémon qui détient ce pouvoir peu choisir un Pokémon de son camp et celui-ci recevras 10 dégâts de moins jusqu'à ça mort.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Utilise le pouvoir Résistance.
     * Cette méthode augmente la défense du Pokémon cible de 10 points, ce qui réduit tous les dégâts subis de 10 points de manière permanente.
     * @param pokemonCible Le Pokémon dont la défense sera augmentée.
     * @param detenteur Le Pokémon qui détient le pouvoir.
     * @param joueur Le joueur utilisant le pouvoir.
     */
    @Override
    public void utilisationPouvoir(Pokemon pokemonCible, Pokemon detenteur, Joueur joueur)
    {
        pokemonCible.setDefense(10);
        Affichage.affichagePhrase(pokemonCible.getNom()+" recoit un boost de 10pt de défense.\n");
        m_estDejaUtilise = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return Le type de cible du pouvoir (NOUS).
     */
    @Override
    public TypePouvoir getCible()
    {
        return TypePouvoir.NOUS;
    }
}
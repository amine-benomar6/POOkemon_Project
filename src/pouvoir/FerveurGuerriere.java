package pouvoir;

import jeu.Affichage;
import jeu.Joueur;
import pokemon.Pokemon;

public class FerveurGuerriere extends Pouvoir
{
    public FerveurGuerriere()
    {
        super("Ferveur guerrière");
        this.m_estReutilisable = false;
    }
    /**
     * Affiche la description du pouvoir Ferveur guerrière
     */
    @Override
    public void afficherDescripition()
    {
        Affichage.affichagePhrase(m_nomPouvoir+" : Le Pokémon qui détient ce pouvoir peu choisir un Pokémon de son camp et celui-ci rajoutera 10 dégâts de plus jusqu'à ça mort.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Utilise le pouvoir Ferveur Guerrière.
     * Ce pouvoir augmente définitivement l'attaque du Pokémon cible de 10 points.
     * @param pokemonCible Le Pokémon dont l'attaque sera augmentée.
     * @param detenteur Le Pokémon qui détient le pouvoir.
     * @param joueur Le joueur utilisant le pouvoir.
     */
    @Override
    public void utilisationPouvoir(Pokemon pokemonCible, Pokemon detenteur, Joueur joueur)
    {
        pokemonCible.setAttaque(pokemonCible.getAttaque()+10);
        Affichage.affichagePhrase(pokemonCible.getNom()+" recoit un boost de 10pt d'attaque.\n");
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
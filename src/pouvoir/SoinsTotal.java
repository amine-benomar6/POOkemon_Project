package pouvoir;

import jeu.Affichage;
import jeu.Joueur;
import pokemon.Pokemon;

public class SoinsTotal extends Pouvoir
{
    public SoinsTotal()
    {
        super("Soins total");
        m_estReutilisable = false;
    }
    /**
     * Affiche la description du pouvoir Soins total
     */
    @Override
    public void afficherDescripition()
    {
        Affichage.affichagePhrase(m_nomPouvoir+ " : Le Pokémon qui détient ce pouvoir peu choisir un Pokémon de son camp et celui-ci regagne toute sa vie");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Utilise le pouvoir Soins Total.
     * Cette méthode restaure tous les points de vie du Pokémon cible, le ramenant à son maximum de points de vie.
     * @param pokemonCible Le Pokémon dont la vie sera entièrement restaurée.
     * @param detenteur Le Pokémon qui détient le pouvoir.
     * @param joueur Le joueur utilisant le pouvoir.
     */
    @Override
    public void utilisationPouvoir(Pokemon pokemonCible, Pokemon detenteur, Joueur joueur)
    {
        pokemonCible.setVie(pokemonCible.getVieMax());
        Affichage.affichagePhrase(pokemonCible.getNom()+" a récupéré toute sa vie.\n");
        m_estDejaUtilise = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return Le type de cible du pouvoir Soins Total (NOUS).
     */
    @Override
    public TypePouvoir getCible()
    {
        return TypePouvoir.NOUS;
    }
}
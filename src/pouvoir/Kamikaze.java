package pouvoir;

import jeu.Affichage;
import jeu.Joueur;
import pokemon.Pokemon;

public class Kamikaze extends Pouvoir
{
    public Kamikaze()
    {
        super("Kamikaze");
        this.m_estReutilisable = false;
    }
    /**
     * Affiche la description du pouvoir Kamikaze
     */
    @Override
    public void afficherDescripition()
    {
        Affichage.affichagePhrase(m_nomPouvoir+" : Le Pokémon qui détient ce pouvoir peu choisir un Pokémon du camp adverse et celui-ci meurt ainsi que le détenteur du pouvoir.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Utilise le pouvoir Kamikaze.
     * Cette méthode inflige 500 points de dégâts à la fois au Pokémon cible et au Pokémon détenteur du pouvoir, les tuant tous les deux.
     * @param pokemonCible Le Pokémon adverse qui sera tué.
     * @param detenteur Le Pokémon qui détient le pouvoir et qui mourra aussi.
     * @param joueur Le joueur utilisant le pouvoir.
     */
    @Override
    public void utilisationPouvoir(Pokemon pokemonCible, Pokemon detenteur, Joueur joueur)
    {
        pokemonCible.seFaitAttaquer(500);
        detenteur.seFaitAttaquer(500);
        Affichage.affichagePhrase("BOOOOM");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Affichage.affichagePhrase(detenteur.getNom() +" meurt, emportant " + pokemonCible.getNom() +" avec lui.");
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
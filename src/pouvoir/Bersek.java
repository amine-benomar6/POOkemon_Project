package pouvoir;

import jeu.Affichage;
import jeu.Joueur;
import pokemon.Pokemon;

public class Bersek extends Pouvoir
{
    public Bersek()
    {
        super("Bersek");
        this.m_estReutilisable = false;
    }
    /**
     * Affiche la description du pouvoir Bersek
     */
    @Override
    public void afficherDescripition()
    {
        Affichage.affichagePhrase(m_nomPouvoir+" : Le Pokémon qui détient ce pouvoir peu choisir un Pokémon de son camp et celui-ci aura son attaque doublé pour le tour courant.");
        try {
        Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Utilise le pouvoir Bersek.
     * Ce pouvoir va doubler l'attaque du Pokémon cible pour sa prochaine attaque.
     * @param pokemonCible Le Pokémon dont l'attaque sera doublée.
     * @param detenteur Le Pokémon qui détient le pouvoir.
     * @param joueur Le joueur utilisant le pouvoir.
     */
    @Override
    public void utilisationPouvoir(Pokemon pokemonCible, Pokemon detenteur, Joueur joueur)
    {
        int attaqueDouble = pokemonCible.getAttaque()*2;
        pokemonCible.setAttaqueTemporaire(attaqueDouble);
        Affichage.affichagePhrase(pokemonCible.getNom()+" recoit un boost d'attaque x2, uniquement pour sa prochaine attaque.\n");
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
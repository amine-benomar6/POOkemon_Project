package pouvoir;

import jeu.Affichage;
import jeu.Joueur;
import pokemon.Pokemon;

public class Intimidation extends Pouvoir
{
    public Intimidation()
    {
        super("Intimidation");
        this.m_estReutilisable = false;
    }
    /**
     * Affiche la description du pouvoir Intimidation
     */
    @Override
    public void afficherDescripition()
    {
        Affichage.affichagePhrase(m_nomPouvoir+" : Le Pokémon qui détient ce pouvoir peu choisir un Pokémon du camp adverse\n" +
                "Celui-ci verra ses attaques réduites de moitié pour sa prochaine attaque.\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Utilise le pouvoir Intimidation.
     * Cette méthode réduit temporairement l'attaque du Pokémon cible ennemi de moitié pour sa prochaine attaque.
     * @param pokemonCible Le Pokémon ennemi dont l'attaque sera réduite.
     * @param detenteur Le Pokémon qui détient le pouvoir.
     * @param joueur Le joueur utilisant le pouvoir.
     */
    @Override
    public void utilisationPouvoir(Pokemon pokemonCible, Pokemon detenteur, Joueur joueur)
    {
        int attaqueReduit = pokemonCible.getAttaque() / 2;
        pokemonCible.setAttaqueTemporaire(attaqueReduit);
        Affichage.affichagePhrase(pokemonCible.getNom()+" a ses dégâts infligés réduits de moitié, uniquement pour sa prochaine attaque qui sera de "+ attaqueReduit +"pt.\n");
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
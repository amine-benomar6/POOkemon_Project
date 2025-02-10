package pouvoir;

import jeu.Affichage;
import jeu.Joueur;
import pokemon.Pokemon;

public class SoinSimple extends Pouvoir
{
    public SoinSimple()
    {
        super("Soins simple");
        m_estReutilisable = true;
    }

    @Override
    public boolean getEstDejaUtilise()
    {
        if(m_estReutilisable)
        {
            return false;
        }
        return super.getEstDejaUtilise();
    }
    /**
     * Affiche la description du pouvoir Soins simple
     */
    @Override
    public void afficherDescripition() {
        Affichage.affichagePhrase(m_nomPouvoir+ " : Le Pokémon qui détient ce pouvoir peu choisir un Pokémon de son camp et celui-ci regagne 30 point de vie");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Utilise le pouvoir Soin Simple.
     * Cette méthode restaure 30 points de vie au Pokémon cible, ou le ramène à son maximum de points de vie s'il lui restait moins de 30 points à récupérer.
     * @param pokemonCible Le Pokémon dont la vie sera restaurée de 30 ou moins.
     * @param detenteur Le Pokémon qui détient le pouvoir.
     * @param joueur Le joueur utilisant le pouvoir.
     */
    @Override
    public void utilisationPouvoir(Pokemon pokemonCible, Pokemon detenteur, Joueur joueur)
    {
        int nombreSoins;
        if(pokemonCible.getVie()+30 < pokemonCible.getVieMax())
        {
            nombreSoins = 30;
            Affichage.affichagePhrase(pokemonCible.getNom() + " à récupéré 30 points de sa vie\n");
        }
        else
        {
            nombreSoins = pokemonCible.getVieMax()-pokemonCible.getVie();
            Affichage.affichagePhrase(pokemonCible.getNom() + " à récupéré toute sa vie\n");
        }
        pokemonCible.setVie(pokemonCible.getVie() + nombreSoins);
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
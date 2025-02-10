package pouvoir;

import jeu.Joueur;
import pokemon.Pokemon;

public abstract class Pouvoir
{
    protected String m_nomPouvoir;
    protected boolean m_estReutilisable;
    protected boolean m_estDejaUtilise;
    public Pouvoir(String nomPouvoir)
    {
        this.m_nomPouvoir = nomPouvoir;
        this.m_estDejaUtilise = false;
    }

    public String getNomPouvoir()
    {
        return m_nomPouvoir;
    }

    public boolean getEstDejaUtilise()
    {
        return m_estDejaUtilise;
    }

    public abstract TypePouvoir getCible();
    public abstract void afficherDescripition();
    public abstract void utilisationPouvoir(Pokemon pokemonCible, Pokemon detenteur, Joueur joueur);
}
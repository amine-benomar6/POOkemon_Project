package pokemon;

//import jeu.Affichage;

/**
 * Classe abstraite représentant un Pokémon avec des affinités.
 * Cette classe hérite de la classe Pokémon.
 */
public abstract class Element
{
    /**
     * Méthode abstraite qui renvoie l'affinité du Pokémon.
     *
     * @return L'affinité du Pokémon.
     */
    public abstract Affinite getAffinite();

    /**
     * Renvoie le désavantage du Pokémon.
     *
     * @return Le désavantage du Pokémon.
     */
    public abstract Affinite getDesavantage();

    /**
     * Renvoie l'avantage du Pokémon.
     *
     * @return L'avantage du Pokémon.
     */
    public abstract Affinite getAvantage();

    /**
     * Méthode qui calcule les dégâts à infliger à l'ennemi en fonction de son type et de l'attaque du Pokémon.
     * @param ennemi Le type de l'ennemi à attaquer.
     * @param attaque La valeur de l'attaque du Pokémon.
     * @return Les dégâts à infliger à l'ennemi.
     */
    public int degatAInfliger(Element ennemi, int attaque)
    {
        int degatInflige = attaque;
        if(ennemi.getAvantage().equals(getAffinite()))
        {
            degatInflige -= 10;
        }
        return degatInflige;
    }
}

package pokemon;

/**
 * Classe représentant un Pokémon de type Terre.
 * Cette classe hérite de la classe Element.
 */
public class Terre extends Element
{
    /**
     * Méthode qui renvoie l'affinité du Pokémon.
     * Dans le cas de cette classe, l'affinité est Affinite.TERRE.
     *
     * @return L'affinité du Pokémon.
     */
    @Override
    public Affinite getAffinite()
    {
        return Affinite.TERRE;
    }
    /**
     * Renvoie l'avantage du Pokémon.
     *
     * @return L'avantage du Pokémon.
     */
    @Override
    public Affinite getAvantage()
    {
        return Affinite.EAU;
    }
    /**
     * Renvoie le désavantage du Pokémon.
     *
     * @return Le désavantage du Pokémon.
     */
    @Override
    public Affinite getDesavantage()
    {
        return Affinite.AIR;
    }
    /**
     * Méthode qui calcule les dégâts à infliger à l'ennemi en fonction de son type et de l'attaque du Pokémon.<p>
     * Si l'ennemi a un désavantage par rapport au type de ce Pokémon, les dégâts infligés seront augmentés de 10.<p>
     * Si l'ennemi n'a pas de désavantage, les dégâts infligés seront calculé par la méthode de la classe mère.<p>
     *
     * @param ennemi Le type de l'ennemi à attaquer.
     * @param attaque La valeur de l'attaque du Pokémon.
     * @return Les dégâts à infliger à l'ennemi.
     */
    @Override
    public int degatAInfliger(Element ennemi, int attaque)
    {
        if(Affinite.TERRE.equals(ennemi.getDesavantage()))
        {
            return attaque + 10;
        }
        else
        {
            return super.degatAInfliger(ennemi, attaque);
        }
    }
}
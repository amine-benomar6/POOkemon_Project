package pokemon;

import jeu.Affichage;
import pouvoir.Pouvoir;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un Pokémon.
 */
public class Pokemon
{
    /**
     * Le nom du Pokémon.
     */
    private final String m_nom;

    /**
     * La vie actuelle du Pokémon.
     */
    private int m_vie;

    /**
     * La vie maximale du Pokémon.
     */
    private final int m_vieMax;

    /**
     * L'attaque du Pokémon.
     */
    private int m_attaque;

    private int m_defense;

    private boolean m_estDejaUtilise;

    private Pouvoir m_pouvoir;

    private List<Element> m_type;

    private int m_attaqueTemporaire = 0;
    /**
     * Constructeur de la classe Pokémon.
     * Initialise le Pokémon avec un nom, une vie maximale et une attaque.
     * La vie actuelle est initialisée à la vie maximale.
     *
     * @param nom Le nom du Pokémon.
     * @param vieMax La vie maximale du Pokémon.
     * @param attaque L'attaque du Pokémon.
     */
    public Pokemon(String nom, int vieMax, int attaque, Element type, Pouvoir pouvoir)
    {
        this.m_nom = nom;
        this.m_vieMax = vieMax;
        this.m_vie = m_vieMax;
        this.m_attaque = attaque;
        this.m_type = new ArrayList<>();
        this.m_type.add(type);
        this.m_estDejaUtilise = false;
        this.m_defense = 0;
        this.m_pouvoir = pouvoir;
    }

    public void ajouterType(Element type)
    {
        this.m_type.add(type);
    }
    /**
     * Méthode qui renvoie vrai si le Pokémon est en vie, faux sinon.
     *
     * @return Vrai si le Pokémon est en vie, faux sinon.
     */
    public Boolean estEnVie()
    {
        return this.m_vie > 0;
    }
    /**
     * Méthode qui renvoie le nom du Pokémon.
     *
     * @return Le nom du Pokémon.
     */
    public String getNom()
    {
        return m_nom;
    }
    /**
     * Méthode qui renvoie la vie actuelle du Pokémon.
     *
     * @return La vie actuelle du Pokémon.
     */
    public int getVie()
    {
        return m_vie;
    }
    /**
     * Méthode qui renvoie la vie maximale du Pokémon.
     *
     * @return La vie maximale du Pokémon.
     */
    public int getVieMax()
    {
        return m_vieMax;
    }
    /**
     * Méthode qui renvoie l'attaque du Pokémon.
     *
     * @return L'attaque du Pokémon.
     */
    public int getAttaque()
    {
        return m_attaque;
    }
    /**
     * Méthode qui renvoie l'attaque temporaire du Pokémon.
     *
     * @return L'attaque temporaire du Pokémon.
     */
    public int getAttaqueTemporaire()
    {
        return m_attaqueTemporaire;
    }
    /**
     * Méthode permettant à ce Pokémon de subir une attaque d'un ennemi.<p>
     * La vie actuelle du Pokémon est réduite en fonction de la valeur de l'attaque subie.<p>
     * Si les dégâts infligés par l'attaque sont supérieurs à la défense du Pokémon, la vie est réduite de la différence.<p>
     * Si la vie atteint ou passe en dessous de zéro, elle est ajustée à zéro pour indiquer que le Pokémon est K.O.
     *
     * @param attaque La valeur de l'attaque subie.
     */
    public void seFaitAttaquer(int attaque)
    {
        if(this.m_vie-(attaque - m_defense) >= 0)
        {
            this.m_vie -= (attaque - m_defense);
        }
        else
        {
            this.m_vie = 0;
        }
    }
    /**
     * Méthode qui renvoie la défense du Pokémon.
     *
     * @return La défense du Pokémon.
     */
    public int getDefense() {
        return m_defense;
    }
    /**
     * Méthode permettant à ce Pokémon d'attaquer un autre Pokémon ennemi.<p>
     * Les dégâts infligés dépendent de l'attaque du Pokémon attaquant, ainsi que des types de ce Pokémon et de son ennemi.<p>
     * Si ce Pokémon possède une attaque temporaire, les dégâts sont calculés en utilisant cette attaque temporaire.<p>
     * Ensuite, l'ennemi se fait attaquer avec les dégâts calculés, et l'attaque est affichée dans la console.
     *
     * @param ennemi Le Pokémon ennemi à attaquer.
     */
    public void attaque(Pokemon ennemi)
    {
        int degatInflige;
        Element type = m_type.get(0);
        Element typeEnnemi = ennemi.m_type.get(0);
        if (m_attaqueTemporaire > 0)
        {
            degatInflige = type.degatAInfliger(typeEnnemi, m_attaqueTemporaire);
        }
        else
        {
            degatInflige = type.degatAInfliger(typeEnnemi, m_attaque);
        }
        ennemi.seFaitAttaquer(degatInflige);
        Affichage.finAttaquePokemon(ennemi, degatInflige-ennemi.m_defense);
    }
    /**
     * Méthode qui renvoie le pouvoir du Pokémon.
     *
     * @return Le pouvoir du Pokémon.
     */
    public Pouvoir getPouvoir()
    {
        return m_pouvoir;
    }
    /**
     * Renvoie une copie de la liste des types du Pokémon.
     *
     * @return Une liste des types du Pokémon.
     */
    public List<Element> getType()
    {
        return new ArrayList<>(m_type);
    }
    /**
     * Méthode qui renvoie si le Pokémon est déjà utilisé.
     *
     * @return True si le Pokémon est utilisé, False sinon.
     */
    public boolean getEstDejaUtilise()
    {
        return m_estDejaUtilise;
    }
    /**
     * Modifie l'état du Pokémon indiquant s'il a déjà été utilisé.
     *
     * @param estDejaUtilise La valeur a attribué à l'état du Pokémon.
     */
    public void setEstDejaUtilise(boolean estDejaUtilise)
    {
        this.m_estDejaUtilise = estDejaUtilise;
    }
    /**
     * Modifie l'attaque du Pokémon.
     *
     * @param attaque La nouvelle valeur de l'attaque.
     */
    public void setAttaque(int attaque)
    {
        this.m_attaque = attaque;
    }
    /**
     * Modifie l'attaque temporaire du Pokémon.
     *
     * @param attaqueTemporaire La nouvelle valeur de l'attaque temporaire.
     */
    public void setAttaqueTemporaire(int attaqueTemporaire)
    {
        this.m_attaqueTemporaire = attaqueTemporaire;
    }
    /**
     * Modifie la défense du Pokémon.
     *
     * @param defense La nouvelle valeur de la défense.
     */
    public void setDefense(int defense)
    {
        this.m_defense = defense;
    }
    /**
     * Modifie la vie du Pokémon.
     *
     * @param vie La nouvelle valeur de la vie.
     */
    public void setVie(int vie)
    {
        this.m_vie = vie;
    }

    @Override
    public String toString()
    {
        return getNom();
    }
}

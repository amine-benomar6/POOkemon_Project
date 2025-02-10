package jeu;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Defausse
{
    private List<Pokemon> m_defausse;
    /**
     * Constructeur de la classe Defausse.
     * Initialise une liste vide pour les Pokémon défaussés.
     */
    public Defausse()
    {
        m_defausse = new ArrayList<>();
    }
    /**
     * Ajoute un Pokémon à la défausse.
     * @param pokemon Pokémon à ajouter à la défausse.
     */
    public void ajouterPokemonDefausse(Pokemon pokemon)
    {
        m_defausse.add(pokemon);
    }
    /**
     * Renvoie la taille de la défausse.
     * @return Taille de la défausse.
     */
    public int tailleDefausse()
    {
        return m_defausse.size();
    }
}
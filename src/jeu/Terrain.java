package jeu;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Terrain
{
    private List<Pokemon> m_terrain;
    /**
     * Constructeur de la classe Terrain.
     * Initialise une liste vide pour les Pokémon sur le terrain.
     */
    public Terrain()
    {
        m_terrain = new ArrayList<>();
    }
    /**
     * Renvoie une copie de la liste des Pokémon sur le terrain.
     * @return Copie de la liste des Pokémon sur le terrain.
     */
    public List<Pokemon> getTerrain()
    {
        return new ArrayList<>(m_terrain);
    }
    /**
     * Ajoute un Pokémon sur le terrain.
     * @param pokemon Pokémon à ajouter sur le terrain.
     */
    public void ajouterPokemonTerrain(Pokemon pokemon)
    {
        m_terrain.add(pokemon);
    }
    /**
     * Supprime un Pokémon du terrain.
     * @param pokemon Pokémon à supprimer du terrain.
     */
    public void supprimePokemonTerrain(Pokemon pokemon)
    {
        m_terrain.remove(pokemon);
    }
    /**
     * Renvoie le Pokémon situé à un indice donné sur le terrain.
     * @param indice Indice du Pokémon sur le terrain.
     * @return Le Pokémon situé à l'indice spécifié.
     */
    public Pokemon getPokemon(int indice)
    {
        return m_terrain.get(indice);
    }
    /**
     * Renvoie la taille du terrain.
     * @return Taille du terrain (nombre de Pokémon présents).
     */
    public int tailleTerrain()
    {
        return m_terrain.size();
    }
}
package jeu;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    private List<Pokemon> m_main;
    /**
     * Constructeur de la classe Main.
     * Initialise une liste vide pour les Pokémon dans la main.
     */
    public Main()
    {
        m_main = new ArrayList<>();
    }
    /**
     * Renvoie une copie de la liste des Pokémon dans la main.
     * @return Copie de la liste des Pokémon dans la main.
     */
    public List<Pokemon> getMain()
    {
        return new ArrayList<>(m_main);
    }
    /**
     * Ajoute un Pokémon dans la main.
     * @param pokemon Pokémon à ajouter dans la main.
     */
    public void ajouterPokemonMain(Pokemon pokemon)
    {
        m_main.add(pokemon);
    }
    /**
     * Renvoie la taille de la main.
     * @return Taille de la main.
     */
    public int tailleMain()
    {
        return m_main.size();
    }
    /**
     * Méthode renvoyant le Pokémon situé à un indice donné dans la main.
     * @param indice Indice du Pokémon dans la main.
     * @return Le Pokémon situé à l'indice spécifié.
     */
    public Pokemon getPokemon(int indice)
    {
        return m_main.get(indice);
    }
    /**
     * Supprime un Pokémon de la main.
     * @param pokemon Pokémon à supprimer de la main.
     */
    public void supprimePokemonMain(Pokemon pokemon)
    {
        m_main.remove(pokemon);
    }
}
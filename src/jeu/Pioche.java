package jeu;

import pokemon.*;

import java.util.ArrayList;
import java.util.List;

public class Pioche
{
    private List<Pokemon> m_pioche;
    private int m_taillePioche;
    /**
     * Constructeur de la classe Pioche.
     * @param pioche Liste de Pokémon constituant la pioche initiale.
     */
    public Pioche(ArrayList<Pokemon> pioche)
    {
        m_pioche = pioche;
        m_taillePioche = pioche.size();
        Affichage.affichagePhrase("Generation terminée, voici le nombre de pokemon dans la pioche :" + m_pioche.size());
    }
    /**
     * Méthode renvoyant une copie de la liste des Pokémon dans la pioche.
     * @return Copie de la liste des Pokémon dans la pioche.
     */
    public List<Pokemon> getPioche()
    {
        return new ArrayList<>(m_pioche);
    }
    /**
     * Méthode pour supprimer un Pokémon de la pioche.
     * @param pokemon Pokémon à supprimer de la pioche.
     */
    public void supprimePokemonPioche(Pokemon pokemon)
    {
        m_pioche.remove(pokemon);
    }
    /**
     * Méthode pour piocher un Pokémon dans la pioche.
     * @return Le Pokémon pioché.
     */
    public Pokemon piocher()
    {
        int indiceDernierPokemonPioche = m_pioche.size() - 1;
        return m_pioche.get(indiceDernierPokemonPioche);
    }
    /**
     * Méthode renvoyant la taille maximale de la pioche.
     * @return Taille maximale de la pioche.
     */
    public int getTailleMaxPioche()
    {
        return m_taillePioche;
    }
    /**
     * Méthode renvoyant la taille actuelle de la pioche.
     * @return Taille actuelle de la pioche.
     */
    public int taillePioche()
    {
        return m_pioche.size();
    }
}
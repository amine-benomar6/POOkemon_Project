package testUnitaire;

import org.junit.jupiter.api.Test;
import jeu.*;
import pokemon.*;
import pouvoir.*;

import static org.junit.jupiter.api.Assertions.*;

public class PeurTest
{
    private final Peur peur = new Peur();

    @Test
    public void test_peurAdversaire()
    {
        Pokemon pokemon = new Pokemon("ZimZim",140, 40, new Air(), peur);
        int ancienneAttaquePokemon = pokemon.getAttaque();
        peur.utilisationPouvoir(pokemon, pokemon, null);
        assertEquals(ancienneAttaquePokemon-10, pokemon.getAttaque());
        pokemon.attaque(pokemon);
    }

    @Test
    public void test_estUtilisee()
    {
        Pokemon pokemon = new Pokemon("ZimZim",140,40, new Air(), peur);
        assertFalse(peur.getEstDejaUtilise());
        peur.utilisationPouvoir(pokemon, pokemon, null);
        assertTrue(peur.getEstDejaUtilise());
    }
}
package testUnitaire;

import jeu.*;
import org.junit.jupiter.api.Test;
import pokemon.*;
import pouvoir.*;

import static org.junit.jupiter.api.Assertions.*;

public class FerveurGuerriereTest
{
    private final FerveurGuerriere ferveur = new FerveurGuerriere();

    @Test
    public void test_attaqueAjout()
    {
        Pokemon pokemon = new Pokemon("ZimZim",140,40, new Air(), ferveur);
        int ancienneAttaquePokemon = pokemon.getAttaque();
        ferveur.utilisationPouvoir(pokemon, pokemon, null);
        assertEquals(ancienneAttaquePokemon+10, pokemon.getAttaque());
        pokemon.attaque(pokemon);
    }

    @Test
    public void test_estUtilisee()
    {
        Pokemon pokemon = new Pokemon("ZimZim",140,40, new Air(), ferveur);
        assertFalse(ferveur.getEstDejaUtilise());
        ferveur.utilisationPouvoir(pokemon, pokemon, null);
        assertTrue(ferveur.getEstDejaUtilise());
    }
}
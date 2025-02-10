package testUnitaire;

import org.junit.jupiter.api.Test;
import jeu.*;
import pokemon.*;
import pouvoir.*;

import static org.junit.jupiter.api.Assertions.*;

public class IntimidationTest
{
    private final Intimidation intimidation = new Intimidation();

    @Test
    public void test_intimidationAdversaire()
    {
        Pokemon pokemon = new Pokemon("ZimZim",140,40, new Air(), intimidation);
        int ancienneAttaquePokemon = pokemon.getAttaque();
        intimidation.utilisationPouvoir(pokemon, pokemon, null);
        assertEquals(ancienneAttaquePokemon/2, pokemon.getAttaqueTemporaire());
        pokemon.attaque(pokemon);
    }

    @Test
    public void test_estUtilisee()
    {
        Pokemon pokemon = new Pokemon("ZimZim",140,40, new Air(), intimidation);
        assertFalse(intimidation.getEstDejaUtilise());
        intimidation.utilisationPouvoir(pokemon, pokemon, null);
        assertTrue(intimidation.getEstDejaUtilise());
    }
}
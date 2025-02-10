package testUnitaire;

import org.junit.jupiter.api.Test;
import jeu.*;
import pokemon.*;
import pouvoir.*;

import static org.junit.jupiter.api.Assertions.*;

public class ResistanceTest
{
    private final Resistance resistance = new Resistance();

    @Test
    public void test_defense()
    {
        Pokemon pokemon = new Pokemon("ZimZim", 140, 40, new Air(), resistance);
        int ancienneDefense = pokemon.getDefense();
        resistance.utilisationPouvoir(pokemon, pokemon, null);
        assertEquals(ancienneDefense+10, pokemon.getDefense());
        pokemon.attaque(pokemon);
    }

    @Test
    public void test_estUtilisee()
    {
        Pokemon pokemon = new Pokemon("ZimZim", 140, 40, new Air(), resistance);
        assertFalse(resistance.getEstDejaUtilise());
        resistance.utilisationPouvoir(pokemon, pokemon, null);
        assertTrue(resistance.getEstDejaUtilise());
    }
}
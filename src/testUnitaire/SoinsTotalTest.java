package testUnitaire;

import org.junit.jupiter.api.Test;
import jeu.*;
import pokemon.*;
import pouvoir.*;

import static org.junit.jupiter.api.Assertions.*;

public class SoinsTotalTest
{
    private final SoinsTotal soinsTotal = new SoinsTotal();

    @Test
    public void test_attaqueAdversaire()
    {
        Pokemon pokemon = new Pokemon("ZimZim", 140, 40, new Air(), soinsTotal);
        pokemon.attaque(pokemon);
        soinsTotal.utilisationPouvoir(pokemon, pokemon, null);
        assertEquals(pokemon.getVieMax(), pokemon.getVie());
    }

    @Test
    public void test_estUtilisee()
    {
        Pokemon pokemon = new Pokemon("ZimZim", 140, 40, new Air(), soinsTotal);
        assertFalse(soinsTotal.getEstDejaUtilise());
        soinsTotal.utilisationPouvoir(pokemon, pokemon, null);
        assertTrue(soinsTotal.getEstDejaUtilise());
    }

}
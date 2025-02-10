package testUnitaire;

import org.junit.jupiter.api.Test;
import jeu.*;
import pokemon.*;
import pouvoir.*;

import static org.junit.jupiter.api.Assertions.*;

public class KamikazeTest
{
    private final Kamikaze kamikaze = new Kamikaze();

    @Test
    public void test_pokemonSontMort()
    {
        Pokemon pokemon = new Pokemon("ZimZim",140, 40, new Air(), kamikaze);
        Pokemon pokemonCible = new Pokemon("WemWem", 100, 30, new Air(), null);
        kamikaze.utilisationPouvoir(pokemonCible, pokemon, null);
        assertFalse(pokemonCible.estEnVie());
        assertFalse(pokemon.estEnVie());
    }

    @Test
    public void test_estUtilisee()
    {
        Pokemon pokemon = new Pokemon("ZimZim",140,40, new Air(), kamikaze);
        assertFalse(kamikaze.getEstDejaUtilise());
        kamikaze.utilisationPouvoir(pokemon, pokemon, null);
        assertTrue(kamikaze.getEstDejaUtilise());
    }
}
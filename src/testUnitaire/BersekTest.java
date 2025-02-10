package testUnitaire;

import jeu.*;
import pokemon.Air;
import pokemon.Pokemon;
import jeu.Joueur;
import org.junit.jupiter.api.Test;
import pouvoir.Bersek;


import static org.junit.jupiter.api.Assertions.*;

public class BersekTest
{
    private final Bersek bersek = new Bersek();

    @Test
    public void test_attaqueDoubler()
    {
        Pokemon pokemon = new Pokemon("ZimZim",140,40, new Air(), bersek);
        int ancienneAttaquePokemon = pokemon.getAttaque();
        bersek.utilisationPouvoir(pokemon, pokemon, null);
        assertEquals(ancienneAttaquePokemon*2, pokemon.getAttaqueTemporaire());
        pokemon.attaque(pokemon);
    }

    @Test
    public void test_estUtilisee()
    {
        Pokemon pokemon = new Pokemon("ZimZim", 140, 40, new Air(), bersek);
        assertFalse(bersek.getEstDejaUtilise());
        bersek.utilisationPouvoir(pokemon, pokemon, null);
        assertTrue(bersek.getEstDejaUtilise());
    }
}
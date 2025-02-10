package testUnitaire;

import org.junit.jupiter.api.Test;
import jeu.*;
import pokemon.*;
import pouvoir.*;

import static org.junit.jupiter.api.Assertions.*;

public class SoinSimpleTest
{
    private final SoinSimple soinsSimple = new SoinSimple();
    private final Jeu jeu = new Jeu();
    private Joueur joueur;

    @Test
    public void test_soinSimple()
    {
        Pokemon pokemon = new Pokemon("ZimZim", 140, 40, new Air(), soinsSimple);
        pokemon.attaque(pokemon);
        int vieApreAttaque = pokemon.getVie();
        soinsSimple.utilisationPouvoir(pokemon, pokemon, null);
        assertEquals(vieApreAttaque+30, pokemon.getVie());
        soinsSimple.utilisationPouvoir(pokemon, pokemon, null);
        assertEquals(pokemon.getVieMax(), pokemon.getVie());
    }

    @Test
    public void test_estUtilisee()
    {
        Pokemon pokemon = new Pokemon("ZimZim", 140, 40, new Air(), soinsSimple);
        assertFalse(soinsSimple.getEstDejaUtilise());
        soinsSimple.utilisationPouvoir(pokemon, pokemon, null);
        assertFalse(soinsSimple.getEstDejaUtilise());
    }
}
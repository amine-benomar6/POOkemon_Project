import jeu.Jeu;

public class JouerPokemon
{
    public static void main(String[] args)
    {
        Jeu jeu = new Jeu();
        jeu.initialisation();

        jeu.start();
        System.out.println("fini");
    }
}
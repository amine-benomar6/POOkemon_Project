package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pokemon.*;
import pouvoir.Pouvoir;

/**
 * Classe représentant un joueur ordinateur dans le jeu de Pokémon.
 * Cette classe hérite de la classe Joueur.
 */
public class Ordinateur extends Joueur
{
    private int m_indiceProchainPokemonAttaque;
    private int m_indiceProchainPokemonPouvoir;
    /**
     * Constructeur de la classe Ordinateur.
     * Initialise le joueur ordinateur avec les informations fournies.
     *
     * @param premierJoueur Boolean indiquant si le joueur ordinateur est le premier à jouer.
     */
    public Ordinateur(Boolean premierJoueur, ArrayList<Pokemon> pioche)
    {
        super(premierJoueur, pioche);
        m_indiceProchainPokemonAttaque = 0;
        m_indiceProchainPokemonPouvoir = -1;
    }

    /**
     * Méthode permettant au joueur ordinateur d'attaquer un Pokémon de l'ennemi.<p>
     * Il sélectionne le Pokémon ennemi le plus vulnérable en fonction des affinités de type,
     * et, à défaut, attaque simplement le Pokémon ennemi le plus faible.<p>
     * L'indice du prochain Pokémon à attaquer est mis à jour à la fin de l'attaque.
     * @param ennemi Joueur représentant l'ennemi du joueur ordinateur.
     *
     */
    @Override
    public void attaquer(Joueur ennemi)
    {
        if(m_indiceProchainPokemonAttaque == m_terrain.tailleTerrain())
        {
            m_indiceProchainPokemonAttaque--;
        }
        Pokemon pokemonFaible;
        List<Pokemon> pokemonFaibleListe = new ArrayList<>();
        String messageAttaque = "COUP CRITIQUE SUR ";
        Pokemon pokemonQuiAttaque = m_terrain.getPokemon(m_indiceProchainPokemonAttaque);
        Element typePokemon = pokemonQuiAttaque.getType().get(0);
        Affinite avantage = typePokemon.getAvantage();

        Affichage.affichagePhrase("\n" + this + " utilise "+ pokemonQuiAttaque.getNom());
        pokemonVulnerable(ennemi.getTerrainPokemon(), avantage, pokemonFaibleListe);

        // Si aucun Pokémon vulnérable n'est trouvé, cibler tous les Pokémon ennemis pour ensuite trouver le faible
        if(pokemonFaibleListe.isEmpty())
        {
            pokemonFaibleListe = new ArrayList<>(ennemi.getTerrainPokemon());
            messageAttaque = "Attaque simple sur ";
        }
        pokemonFaible = trouverLePlusFaible(pokemonFaibleListe);
        Affichage.affichagePhrase(messageAttaque + pokemonFaible.getNom());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pokemonQuiAttaque.attaque(pokemonFaible);
        ennemi.pokemonAPerdu(pokemonFaible);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // on met à jour l'indice du prochain pokemon qui va attaquer chez l'ordinateur
        m_indiceProchainPokemonAttaque = (m_indiceProchainPokemonAttaque +1 ) % m_terrain.tailleTerrain();
    }

    /**
     * Ajoute les Pokémon ennemis vulnérables à la liste de pokemon faible si le pokemon a pour affinité l'avantage du pokemon qui veut l'attaquer.
     *
     * @param ennemiTerrain La liste des Pokémon présents sur le terrain de l'ennemi.
     * @param avantage L'affinité d'avantage contre laquelle on vérifie si l'affinité des Pokémon ennemi correspond
     * @param pokemonFaibleListe La liste des Pokémon vulnérables à l'avantage, à laquelle seront ajoutés les Pokémon ennemis vulnérables.
     */
    private void pokemonVulnerable(List<Pokemon> ennemiTerrain, Affinite avantage, List<Pokemon> pokemonFaibleListe)
    {
        for(Pokemon pokemonEnnemi : ennemiTerrain)
        {
            Element typePokemon = pokemonEnnemi.getType().get(0);
            if(typePokemon.getAffinite().equals(avantage))
            {
                pokemonFaibleListe.add(pokemonEnnemi);
            }
        }
    }

    /**
     * Méthode permettant de trouver le Pokémon le plus faible dans une liste de Pokémon.<p>
     * Si plusieurs Pokémon ont la même vie minimale, un Pokémon est sélectionné aléatoirement.
     *
     * @param listePokemon List<Pokemon> contenant les Pokémon à comparer.
     * @return Pokemon représentant le Pokémon le plus faible, ou null si la liste est vide.
     */
    private Pokemon trouverLePlusFaible(List<Pokemon> listePokemon)
    {
        // c'est pas sensé arriver mais on sait jamais
        if (listePokemon.isEmpty())
        {
            return null;
        }

        int vieMinimale = Integer.MAX_VALUE;
        List<Pokemon> pokemonsFaibles = new ArrayList<>();

        for (Pokemon pokemon : listePokemon)
        {
            int vie = pokemon.getVie();
            if (vie <= vieMinimale)
            {
                if(vie < vieMinimale)
                {
                    pokemonsFaibles.clear();
                }
                vieMinimale = vie;
                pokemonsFaibles.add(pokemon);
            }
        }

        // on retourne le pokemon le plus faible en gérant le cas où il peut en avoir plusieurs
        Random random = new Random();
        return pokemonsFaibles.get(random.nextInt(pokemonsFaibles.size()));
    }

    /**
     * Méthode permettant au joueur ordinateur de placer ses Pokémon sur le terrain.
     * Les Pokémon sont placés dans l'ordre de la main du joueur ordinateur.
     */
    @Override
    public void placer()
    {
        int i = 0;
        while (m_main.tailleMain() > 0 && m_terrain.tailleTerrain() < 3)
        {
            Pokemon pokemonPlacer = m_main.getPokemon(i);
            m_terrain.ajouterPokemonTerrain(pokemonPlacer);
            Affichage.affichagePhrase(this+" a placé "+pokemonPlacer.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m_main.supprimePokemonMain(pokemonPlacer);
            if(m_main.tailleMain() > 1)
            {
                i++;
            }
            else
            {
                i = 0;
            }
        }
    }

    /**
     * Renvoie une liste des indices des Pokémon qui ont un pouvoir non utilisé.
     *
     * @return Une liste d'indices de Pokémon avec un pouvoir non utilisé.
     */
    private List<Integer> indicePouvoir()
    {
        List<Integer> indice = new ArrayList<>();
        for (int i = 0; i < m_terrain.tailleTerrain(); i++)
        {
            if(m_terrain.getPokemon(i).getPouvoir() != null && !m_terrain.getPokemon(i).getPouvoir().getEstDejaUtilise())
            {
                indice.add(i);
            }
        }
        return indice;
    }

    /**
     * Utilise tous les pouvoirs qui sont sur le terrain du joueur qui peut affecter soit un ennemi, soit avantagé un de ces pokémon.
     * Si aucun pouvoir n'est disponible, affiche un message indiquant l'absence de pouvoir.
     *
     * @param ennemi Le joueur ennemi contre lequel on peut utiliser certains pouvoirs sur les pokémons ennemis.
     */
    @Override
    public void utiliserPouvoirs(Joueur ennemi)
    {
        if(aUnPouvoir())
        {
            for (int i = 0; i < nombrePouvoir(); i++)
            {
                utilisationPouvoir(ennemi);
            }
        }
        else
        {
            Affichage.affichagePhrase("Dommage,"+ this +" ne possède pas de pouvoir!");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Utilise le pouvoir d'un Pokémon de l'Ordinateur.<p>
     * La méthode sélectionne le prochain pouvoir non utilisé.<p>
     * Si l'indice du Pokémon qui a été choisi a été tué au dernier tour, il sélectionne le premier Pokémon disponible avec un pouvoir.<p>
     * Ensuite, l'ordi choisit un Pokémon cible ennemi ou un Pokémon allié en fonction du pouvoir utilisé.<p>
     * Le pouvoir est utilisé contre le Pokémon cible, et les états des Pokémon sont mis à jour pour voir s'ils sont éliminé ou pas.
     *
     * @param ennemi L'ennemi est utilisé seulement en fonction de certain pouvoir utilisé ou la cible est un ennemi.
     */
    @Override
    public void utilisationPouvoir(Joueur ennemi)
    {
        List<Integer> indice = indicePouvoir();
        if (!indice.contains(m_indiceProchainPokemonPouvoir))
        {
            m_indiceProchainPokemonPouvoir = indice.get(0);
        }
        Pouvoir pouvoir = m_terrain.getPokemon(m_indiceProchainPokemonPouvoir).getPouvoir();
        Pokemon pokemonCible = getPokemonCible(ennemi, pouvoir);
        Affichage.affichagePhrase(this +" utilise le pouvoir de " + m_terrain.getPokemon(m_indiceProchainPokemonPouvoir).getNom().toUpperCase() + " qui est : " + pouvoir.getNomPouvoir().toUpperCase());
        pouvoir.utilisationPouvoir(pokemonCible, m_terrain.getPokemon(m_indiceProchainPokemonPouvoir), this);
        pokemonAPerdu(m_terrain.getPokemon(m_indiceProchainPokemonPouvoir));
        ennemi.pokemonAPerdu(pokemonCible);
        if (indice.size() > 1 && indice.indexOf(m_indiceProchainPokemonPouvoir) + 1 < indice.size())
        {
            m_indiceProchainPokemonPouvoir = indice.get(indice.indexOf(m_indiceProchainPokemonPouvoir) + 1);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Choisit le Pokémon de l'ordi ayant les meilleures caractéristiques (attaque et vie) pour attribuer un pouvoir.
     *
     * @return L'indice du meilleur Pokémon en termes d'attaque et de vie.
     */
    @Override
    public int choisirPokemonAttributPouvoir()
    {
        int maxAttaque = 0;
        int maxVie = 0;
        int indiceMeilleurPokemon = -1;
        for (int i = 0; i < m_terrain.tailleTerrain(); i++)
        {
            if(m_terrain.getPokemon(i).getAttaque() > maxAttaque && m_terrain.getPokemon(i).getVie() > maxVie)
            {
                maxAttaque = m_terrain.getPokemon(i).getAttaque();
                maxVie = m_terrain.getPokemon(i).getVie();
                indiceMeilleurPokemon = i;
            }
        }
        return indiceMeilleurPokemon;
    }

    /**
     * Choisit le Pokémon ennemi ayant la plus faible vie pour attribuer un pouvoir.
     *
     * @param ennemi Le joueur ennemi dont le Pokémon le plus faible sera choisi.
     * @return L'indice du Pokémon ennemi avec la plus faible vie.
     */
    @Override
    public int choisirPokemonAttributPouvoir(Joueur ennemi)
    {
        int minVie = Integer.MAX_VALUE;
        int indiceFaiblePokemon = -1;
        for (int i = 0; i < ennemi.m_terrain.tailleTerrain(); i++)
        {
            if(ennemi.m_terrain.getPokemon(i).getVie() < minVie)
            {
                minVie = ennemi.m_terrain.getPokemon(i).getVie();
                indiceFaiblePokemon = i;
            }
        }
        return indiceFaiblePokemon;
    }

    /**
     * Méthode renvoyant une représentation sous forme de chaîne de l'objet Ordinateur.
     *
     * @return String représentant l'objet Ordinateur.
     */
    @Override
    public String toString()
    {
        return " Ordinateur";
    }
}

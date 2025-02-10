package jeu;

import pokemon.*;
import pouvoir.*;

import java.util.*;

/**
 * Cette classe représente un jeu de cartes à collectionner Pokémon.
 * Elle gère l'initialisation, le déroulement des tours et la détermination du gagnant.
 */
public class Jeu {
    private String[] pokemonPremiereGenerationArray = {
            "Bulbizarre", "Herbizarre", "Florizarre", "Salamèche", "Reptincel",
            "Dracaufeu", "Carapuce", "Carabaffe", "Tortank", "Chenipan",
            "Chrysacier", "Papilusion", "Aspicot", "Coconfort", "Dardargnan",
            "Roucool", "Roucoups", "Roucarnage", "Rattata", "Rattatac",
            "Piafabec", "Rapasdepic", "Abo", "Arbok", "Pikachu", "Raichu",
            "Sabelette", "Sablaireau", "Nidoran", "Nidorina", "Nidoqueen",
            "Nidorino", "Nidoking", "Mélofée", "Mélodelfe", "Onix",
            "Goupix", "Feunard", "Rondoudou", "Grodoudou", "Nosferapti",
            "Nosferalto", "Mystherbe", "Ortide", "Rafflesia", "Paras",
            "Parasect", "Mimitoss", "Aéromite", "Taupiqueur", "Triopikeur",
            "Miaouss", "Persian", "Psykokwak", "Akwakwak", "Férosinge",
            "Colossinge", "Caninos", "Arcanin", "Ptitard", "Têtarte", "Tartard",
            "Abra", "Kadabra", "Alakazam", "Machoc", "Machopeur", "Mackogneur",
            "Chétiflor", "Boustiflor", "Empiflor", "Tentacool", "Tentacruel",
            "Racaillou", "Gravalanch", "Grolem", "Ponyta", "Galopa", "Ramoloss",
            "Flagadoss", "Magnéti", "Magnéton", "Canarticho", "Doduo", "Dodrio",
            "Otaria", "Lamantine", "Tadmorv", "Grotadmorv", "Kokiyas", "Crustabri",
            "Fantominus", "Spectrum", "Ectoplasma", "Soporifik", "Hypnomade",
            "Krabby", "Krabboss", "Voltorbe", "Électrode", "Nœunœuf", "Noadkoko",
            "Osselait", "Ossatueur", "Kicklee", "Tygnon", "Excelangue", "Smogo",
            "Smogogo", "Rhinocorne", "Rhinoféros", "Leveinard", "Saquedeneu",
            "Kangourex", "Hypotrempe", "Hypocean", "Poissirène", "Poissoroy",
            "Stari", "Staross", "M. Mime", "Insécateur", "Lippoutou", "Élektek",
            "Magmar", "Scarabrute", "Tauros"
    };

    private Pouvoir[] m_pouvoir = {
         new Resistance()
        , new Intimidation()
        , new FerveurGuerriere()
        , new Peur()
        , new Bersek()
        , new SoinsTotal()
        , new SoinSimple()
        , new Kamikaze()
    };

    private List<Pokemon> m_pokemonList;

    private ArrayList<Pouvoir> m_listePouvoir = new ArrayList<>();
    /**
     * Liste contenant les noms des Pokémon disponibles pour les joueurs.
     */
    private ArrayList<String> m_listeNomPokemon = new ArrayList<>();

    /**
     * Premier joueur participant à la partie.
     */
    private Joueur m_joueur1;

    /**
     * Deuxième joueur participant à la partie.
     */
    private Joueur m_joueur2;

    /**
     * Constructeur par défaut de la classe Jeu.
     * Initialise la liste des noms de Pokémon avec ceux de la première génération.
     */
    public Jeu()
    {
        m_pokemonList = new ArrayList<>();
        m_listeNomPokemon.addAll(Arrays.asList(pokemonPremiereGenerationArray));
        m_listePouvoir.addAll(List.of(m_pouvoir));
        genererListePokemonAleatoire();
    }
    /**
     * Méthode d'initialisation de la partie.
     * Affiche une barre de chargement, le message de bienvenue et initialise les joueurs.
     */
    public void initialisation() {
        Affichage.barreDeChargement(4);
        Affichage.affichageDebutJeu();
        demandeAfficherRegle();
        // en temps normal joueur 1 sera l'humain, là j'ai mis ordi parce que flemme de placer tout le temps les pokemons
        // faut aussi ajouter un truc pour rendre aléatoire les booleens
        initialisationJoueur();
    }
    /**
     * Méthode d'initialisation des joueurs.
     * Crée un joueur humain et un joueur ordinateur, crée les 2 liste de pioche pour les joueurs à partir de la liste
     * de pokemon du jeu que l'on va mélanger avec la méthode Collections.shuffle(m_pokemonList) et détermine
     * aléatoirement lequel des deux jouera en premier.
     */
    private void initialisationJoueur()
    {
        Random random = new Random();
        int premierJoueur = random.nextInt(2);

        Collections.shuffle(m_pokemonList);
        ArrayList<Pokemon> piochePremier = new ArrayList<>(m_pokemonList.subList(0, 20));
        m_pokemonList.subList(0, 20).clear();

        Collections.shuffle(m_pokemonList);
        ArrayList<Pokemon> piocheDeuxieme = new ArrayList<>(m_pokemonList);
        switch (premierJoueur)
        {
            case 0:
                m_joueur1 = new Joueur(true, piochePremier);
                m_joueur2 = new Ordinateur(false, piocheDeuxieme);
                break;
            case 1:
                m_joueur2 = new Ordinateur(true, piochePremier);
                m_joueur1 = new Joueur(false, piocheDeuxieme);
                break;
        }
    }
    /**
     * Méthode de génération aléatoire de la liste de Pokémon pour la partie.
     */
    private void genererListePokemonAleatoire()
    {
        for (int i = 0; i < 41; i++)
        {
            m_pokemonList.add(genererPokemonAleatoire());
        }
    }
    /**
     * Crée un Pokémon aléatoire à partir d'une liste de noms de Pokémon et de pouvoirs.
     * @return le Pokémon créé
     */
    private Pokemon genererPokemonAleatoire()
    {
        Pouvoir pouvoir = null;
        if(m_listePouvoir.size() > 0)
        {
            Collections.shuffle(m_listePouvoir);
            pouvoir = m_listePouvoir.get(0);
            m_listePouvoir.remove(pouvoir);
        }
        Random random = new Random();
        String nom = m_listeNomPokemon.get(random.nextInt(m_listeNomPokemon.size()));
        int vie = 100 + (random.nextInt(11) * 10);
        int pntAttaque = 10 + (random.nextInt(4) * 10);
        int affinite = random.nextInt(4) + 1;
        m_listeNomPokemon.remove(nom);
        switch (affinite) {
            case 1:
                return new Pokemon(nom, vie, pntAttaque, new Air(), pouvoir);
            case 2:
                return new Pokemon(nom, vie, pntAttaque, new Feu(), pouvoir);
            case 3:
                return new Pokemon(nom, vie, pntAttaque, new Terre(), pouvoir);
            case 4:
                return new Pokemon(nom, vie, pntAttaque, new Eau(), pouvoir);
            default:
                return null;
        }
    }
    /**
     * Méthode représentant un tour de jeu.
     * Gère le déroulement du tour en fonction du numéro de celui-ci et des actions des joueurs.
     * @param nbTour le numéro du tour actuel
     */
    private void tour(int nbTour)
    {
        Affichage.afficherTour(nbTour, "Joueur 1 :" + m_joueur1.toString());
        m_joueur1.piocher();
        m_joueur2.piocher();

        // on place des Pokémon sur le terrain pour le premier joueur
        placer(nbTour);
        m_joueur1.reutiliserPokemon();
        m_joueur1.enleverAttaqueTemporaire();
        m_joueur2.enleverAttaqueTemporaire();
        Affichage.afficherTerrain(m_joueur2, m_joueur1);
        pouvoir();
        attaquerTour();
        // on vérifie si un joueur a perdu ou gagné
        gagnantPerdant();
    }
    /**
     * Demande à l'utilisateur s'il souhaite afficher les règles du jeu.
     * Si l'utilisateur répond "o", les règles sont affichées et une boucle
     * attend que l'utilisateur confirme avoir fini de les lire.
     */
    private void demandeAfficherRegle()
    {
        Affichage.affichagePhrase("Voulez-vous lire les règles du jeu ? o/n");
        String reponse = scanString();
        if (reponse.equalsIgnoreCase("o"))
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Affichage.afficherRegles();
            attendreFinDeLecture();
        }
        else
        {
            Affichage.affichagePhrase("Vous avez décidé de ne pas lire les règles du jeu");
        }
    }
    /**
     * Scanne une entrée utilisateur pour obtenir une réponse valide "o" ou "n".<p>
     * @return une chaîne de caractères contenant "o" ou "n".
     */
    private static String scanString()
    {
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);
        String reponse = "";
        while (!validInput)
        {
            try
            {
                reponse = scanner.next();
                if (reponse.equalsIgnoreCase("o") || reponse.equalsIgnoreCase("n"))
                {
                    validInput = true;
                }
                else
                {
                    Affichage.affichagePhrase("Entrée invalide. Veuillez répondre par 'o' ou 'n'.");
                }
            }
            catch (InputMismatchException e)
            {
                Affichage.affichagePhrase("Entrée invalide. Veuillez réessayer.");
                scanner.next(); // Consommer l'entrée non valide pour éviter une boucle infinie
            }
        }
        return reponse;
    }
    /**
     * Attend que l'utilisateur confirme avoir fini de lire les règles du jeu.<p>
     * Une boucle continue de demander à l'utilisateur s'il a fini de lire
     * les règles et attend une seconde avant de redemander si l'utilisateur
     * répond "n".
     */
    private static void attendreFinDeLecture()
    {
        boolean finiDeLire = false;
        String reponse;

        while (!finiDeLire)
        {
            Affichage.affichagePhrase("Avez-vous fini de lire ? (o/n)");
            reponse = scanString();
            if (reponse.equalsIgnoreCase("n"))
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                finiDeLire = true;
            }
        }
    }
    /**
     * Permet au joueur de placer en fonction du 1er tour et du premierJoueur mais pour les autres tours ça va
     * être le joueur humain qui place, ensuite l'ordi
     * Gère le déroulement du tour en fonction du numéro de celui-ci et des actions des joueurs.
     * @param nbTour le numéro du tour actuel
     */
    private void placer(int nbTour)
    {
        if (nbTour == 1 && m_joueur2.getPremierJoueur())
        {
            m_joueur2.placer();
            m_joueur1.placer();
        }
        else
        {
            m_joueur1.placer();
            m_joueur2.placer();
        }
    }
    /**
     * Gère les attaques des Pokémon pendant un tour de jeu.
     */
    private void attaquerTour()
    {
        Terrain terrainJoueur1 = m_joueur1.getTerrain();
        Terrain terrainJoueur2 = m_joueur2.getTerrain();

        int nbAttaque = Math.min(terrainJoueur1.tailleTerrain(), terrainJoueur2.tailleTerrain());
        for(int i = 0; i < nbAttaque; i++)
        {
            if(m_joueur1.getPremierJoueur())
            {
                attaquer(m_joueur1, m_joueur2);
            }
            else
            {
                attaquer(m_joueur2, m_joueur1);
            }
            nbAttaque = Math.min(terrainJoueur1.tailleTerrain(), terrainJoueur2.tailleTerrain());
        }
    }
    /**
     * Gère l'utilisation des pouvoirs pour les 2 joueurs du jeu, c'est le 1er joueur de la partie qui va utiliser ou pas
     * un de ces pouvoirs puis le 2ᵉ
    **/
     private void pouvoir()
    {
        if(m_joueur1.getPremierJoueur())
        {
            m_joueur1.utiliserPouvoirs(m_joueur2);
            m_joueur2.utiliserPouvoirs(m_joueur1);
        }
        else
        {
            m_joueur2.utiliserPouvoirs(m_joueur1);
            m_joueur1.utiliserPouvoirs(m_joueur2);
        }
    }
    /**
     * Gère les attaques des 2 joueurs du jeu, c'est le 1er joueur de la partie qui attaque puis le 2ᵉ
     * Si le joueur humain à malheureusement tous les Pokémon dans son terrain déjà utilisé pour éviter qu'il soit bloqué,
     * on lui permet de remettre tous les Pokémon dans son terrain a non utilisé avant qu'il n'attaque
     * @param premierJoueur le joueur qui attaque en premier
     * @param deuxiemeJoueur le joueur qui attaque en second
     */
    private void attaquer(Joueur premierJoueur, Joueur deuxiemeJoueur)
    {
        if(!premierJoueur.aPerdu() && !deuxiemeJoueur.aPerdu())
        {
            rendrePokemonsDisponibles(premierJoueur);
            Affichage.affichagePhrase("\n\t\t\t\t\t\t\t\t\t\t" + " C'est à"+ premierJoueur +" d'attaquer \n");
            premierJoueur.attaquer(deuxiemeJoueur);
            if(!deuxiemeJoueur.aPerdu())
            {
                rendrePokemonsDisponibles(deuxiemeJoueur);
                Affichage.affichagePhrase("\n\t\t\t\t\t\t\t\t\t\t" + " C'est à"+ deuxiemeJoueur + " d'attaquer \n");
                deuxiemeJoueur.attaquer(premierJoueur);
            }
        }
    }
    /**
     * Méthode de lancement de la partie.
     * Fait se succéder les tours de jeu jusqu'à ce qu'un joueur ait perdu.
     */
    public void start()
    {
        int nbTour = 1;
        while(!m_joueur1.aPerdu() && !m_joueur2.aPerdu())
        {
            tour(nbTour);
            nbTour ++;
        }
    }
    /**
     * Vérifie si un joueur ne peut pas jouer de Pokémon pendant un tour.<p>
     * Il ne peut pas jouer si :
     * - Tous les Pokémons du terrain ont déjà joué
     * @param joueur le joueur à vérifier
     * @return true si le joueur ne peut pas jouer de Pokémon, sinon false
     */
    private boolean impossibleJouer(Joueur joueur)
    {
        boolean pasJouer = true;
        int i = 0;
        Terrain terrain = joueur.getTerrain();
        while (i < terrain.tailleTerrain() && pasJouer)
        {
            if(!terrain.getPokemon(i).getEstDejaUtilise())
            {
                pasJouer = false;
            }
            i++;
        }
        return pasJouer;
    }
    /**
     * Rend les Pokémon disponibles à jouer pour un joueur qui ne peut pas jouer à cause de ces Pokémon déjà utilisé.
     * @param joueur le joueur dont les Pokémon doivent être rendus disponibles
     */
    private void rendrePokemonsDisponibles(Joueur joueur)
    {
        if(impossibleJouer(joueur))
        {
            joueur.reutiliserPokemon();
        }
    }
    /**
     * Méthode affichant le résultat de la partie.
     * Indique quel joueur a gagné et quel joueur a perdu.
     */
    private void gagnantPerdant()
    {
        Affichage.affichagePhrase("");
        if (m_joueur1.aPerdu())
        {
            Affichage.affichagePhrase("Le joueur 1 a perdu !");
            Affichage.affichagePhrase("Le joueur 2 a gagné !");
        }
        else if (m_joueur2.aPerdu())
        {
            Affichage.affichagePhrase("Le joueur 2 a perdu !");
            Affichage.affichagePhrase("Le joueur 1 a gagné !");
        }
    }
}
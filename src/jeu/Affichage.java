package jeu;

import pokemon.*;

import java.util.List;

/**
 * Classe contenant des méthodes statiques pour afficher différentes informations de jeu.
 */
public class Affichage {
    private static final String GRIS_CLAIR = "\u001B[37m";
    private static final String BLEU_CLAIR = "\u001B[36m";
    private static final String ROUGE = "\u001B[31m";
    private static final String ROUGE_FONCE = "\u001B[31;1m";
    private static final String VERT = "\u001B[32m";
    private static final String BLEU = "\u001B[34m";
    private static final String BLEU_TRES_CLAIR = "\u001B[96m";
    private static final String VIOLET_CLAIR = "\u001B[95m";
    private static final String ORANGE_CLAIR = "\u001B[93m";
    private static final String RESET = "\u001B[0m";
    /**
     * Affiche le numéro du tour actuel et le nom du joueur.
     *
     * @param tour le numéro du tour actuel
     * @param nomJoueur le nom du joueur
     */
    public static void afficherTour(int tour, String nomJoueur) {
        String borderLine = "********************************************************************************************************************";
        String emptyLine = "*                                                                                                                  *";
        String tourText = String.format("Tour %d", tour);
        int totalLength = borderLine.length() - 2;
        int textLength = tourText.length();
        int padding = (totalLength - textLength) / 2;

        String tourLine;
        if ((totalLength - textLength) % 2 == 0) {
            tourLine = String.format("*%" + padding + "s" + ORANGE_CLAIR + "%s" + RESET + "%" + padding + "s*", "", tourText, "");
        } else {
            tourLine = String.format("*%" + padding + "s" + ORANGE_CLAIR + "%s" + RESET + "%" + (padding + 1) + "s*", "", tourText, "");
        }

        System.out.println(BLEU_CLAIR + borderLine + RESET);
        System.out.println(BLEU_CLAIR + emptyLine + RESET);
        System.out.println(BLEU_CLAIR + tourLine + RESET);
        System.out.println(BLEU_CLAIR + emptyLine + RESET);
        System.out.println(BLEU_CLAIR + borderLine + RESET);

        try {
            Thread.sleep(2000); // 2000 millisecondes = 2 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche l'état actuel du terrain, y compris les Pokémon actifs de chaque joueur,
     * ainsi que la taille de leur pioche et de leur défausse.
     *
     * @param ordinateur le joueur ordinateur
     * @param joueur le joueur humain
     */
    public static void afficherTerrain(Joueur ordinateur, Joueur joueur) {
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\tJoueur 2 :" + ROUGE_FONCE + ordinateur.toString() + RESET + "\n");

        System.out.println(" Pioche: " + ordinateur.getPioche().taillePioche() + " pokemons");
        System.out.println(" Defausse: " + ordinateur.getDefausse().tailleDefausse()+ " pokemons");

        afficherPokemonCarteEnnemi(ordinateur.getTerrainPokemon());
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        afficherPokemonCarte(joueur.getTerrainPokemon());
        System.out.println(" Pioche: " + joueur.getPioche().taillePioche() + " pokemons");
        System.out.println(" Defausse: " + joueur.getDefausse().tailleDefausse()+ " pokemons");

        afficherMain(joueur);
        try {
            Thread.sleep(4000); // 2000 millisecondes = 2 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Affiche les règles du jeu Pokémon.<p>
     * Cette méthode affiche les règles et les instructions du jeu Pokémon
     * pour aider les joueurs à comprendre les objectifs, la mise en place,
     * et le déroulement des tours.
     */
    public static void afficherRegles() {
        System.out.println(VIOLET_CLAIR + "############################################################" + RESET);
        System.out.println(VIOLET_CLAIR + "#                       " + ORANGE_CLAIR + "RÈGLES DU JEU" + VIOLET_CLAIR + "                      #" + RESET);
        System.out.println(VIOLET_CLAIR + "#                         " + ORANGE_CLAIR + "POKEMON" + VIOLET_CLAIR + "                          #" + RESET);
        System.out.println(VIOLET_CLAIR + "############################################################" + RESET + "\n");

        System.out.println("Bienvenue dans ce jeu de cartes " + ORANGE_CLAIR + "Pokémon" + RESET + " où vous affronterez l'ordinateur dans des batailles stratégiques et palpitantes.\n");

        System.out.println(ORANGE_CLAIR + "OBJECTIF DU JEU" + RESET + "\n---------------");
        System.out.println("Le but du jeu est d'éliminer tous les Pokémon de votre adversaire. Le premier joueur à réussir cet exploit remporte la partie.\n");

        System.out.println(ORANGE_CLAIR + "MISE EN PLACE" + RESET + "\n-------------");
        System.out.println("1. " + VIOLET_CLAIR + "Distribution des Cartes" + RESET + " :\n"
                + "   - Les cartes Pokémon sont distribuées aléatoirement aux deux joueurs au début de la partie.\n"
                + "   - Le joueur 1 reçoit 20 cartes dans sa pioche.\n"
                + "   - Le joueur 2 reçoit 21 cartes dans sa pioche.\n"
                + "   - L'attribution des rôles de joueur 1 et joueur 2 est faite aléatoirement.\n");

        System.out.println("2. " + VIOLET_CLAIR + "Génération des Pokémon" + RESET + " :\n"
                + "   - Chaque Pokémon est généré aléatoirement, incluant leur type, leurs points de vie, leur attaque, et leur pouvoir.\n"
                + "   - Notez qu'un Pokémon peut avoir un type différent de celui qu'il possède habituellement (par exemple, un Dracaufeu de type Terre).\n");

        System.out.println(ORANGE_CLAIR + "DÉROULEMENT DES TOURS" + RESET + "\n---------------------");
        System.out.println("Le jeu se joue au tour par tour, et chaque tour suit les étapes suivantes :\n");

        System.out.println("1. " + VIOLET_CLAIR + "Utilisation des Pouvoirs" + RESET + " :\n"
                + "   - Au début de chaque tour, les deux joueurs décident s'ils souhaitent utiliser leurs pouvoirs s'ils en ont.\n");

        System.out.println("2. " + VIOLET_CLAIR + "Pioche des Pokémon" + RESET + " :\n"
                + "   - Les joueurs piochent des cartes Pokémon jusqu'à en avoir 5 en main ou jusqu'à ce que leur pioche soit vide.\n");

        System.out.println("3. " + VIOLET_CLAIR + "Placement des Pokémon" + RESET + " :\n"
                + "   - Chaque joueur place un Pokémon de sa main face visible sur chaque emplacement vide de son terrain.\n");

        System.out.println("4. " + VIOLET_CLAIR + "Attaques" + RESET + " :\n"
                + "   - Les joueurs peuvent attaquer une fois avec chacun des Pokémon présents sur leur terrain, dans l'ordre de leur choix.\n"
                + "   - Un Pokémon ne peut attaquer qu'une fois par tour et doit attendre le prochain tour pour être réutilisé.\n");

        System.out.println(ORANGE_CLAIR + "AFFINITÉS ET AVANTAGES" + RESET + "\n----------------------");
        System.out.println("Il y a quatre éléments dans le jeu : " + VERT + "Terre" + RESET + ", " + BLEU + "Eau" + RESET + ", " + ROUGE + "Feu" + RESET + ", et " + BLEU_TRES_CLAIR + "Air" + RESET + ". Chaque élément a un avantage et un désavantage par rapport à un autre élément :\n"
                + "- La Terre a l'avantage sur l'Eau.\n"
                + "- L'Eau a l'avantage sur le Feu.\n"
                + "- Le Feu a l'avantage sur l'Air.\n"
                + "- L'Air a l'avantage sur la Terre.\n\n"
                + "Impact des Avantages :\n"
                + "- Avantage d'affinité : Augmente l'attaque de 10 points.\n"
                + "- Désavantage d'affinité : Diminue l'attaque de 10 points.\n");

        System.out.println(ORANGE_CLAIR + "COMBAT ET POINTS DE VIE" + RESET + "\n------------------------");
        System.out.println("- Lorsqu'un Pokémon attaque, il réduit les points de vie du Pokémon adverse de la valeur de sa force d'attaque.\n"
                + "- Si un Pokémon n'a plus de points de vie après une attaque, il est placé dans la défausse de l'adversaire.\n");

        System.out.println(ORANGE_CLAIR + "NOTES SUPPLÉMENTAIRES" + RESET + "\n----------------------");
        System.out.println("- Esthétique et Indicateurs : Les couleurs utilisées dans le jeu sont principalement esthétiques, mais elles fournissent des informations utiles.\n"
                + "  - Les pouvoirs apparaissent en " + ORANGE_CLAIR + "orange" + RESET + " sur la carte si vous pouvez les utiliser ou les réutiliser.\n"
                + "  - Les pouvoirs apparaissent en " + GRIS_CLAIR + "gris" + RESET + " s'ils ne peuvent plus être utilisés.\n");

        System.out.println("Nous espérons que ces règles vous aideront à profiter pleinement de votre expérience de jeu. Préparez vos stratégies, utilisez vos Pokémon à bon escient, et que le meilleur dresseur gagne !");
    }
    /**
     * Méthode d'assistance qui affiche un Pokémon sous forme de carte.
     *
     * @param listePokemon la liste de Pokémon à afficher
     */
    private static void afficherPokemonCarte(List<Pokemon> listePokemon) {
        StringBuilder hautCarte = new StringBuilder();
        StringBuilder attaqueCarte = new StringBuilder();
        StringBuilder vieCarte = new StringBuilder();
        StringBuilder pouvoirCarte = new StringBuilder();
        StringBuilder affiniteCarte = new StringBuilder();
        StringBuilder nomCarte = new StringBuilder();
        StringBuilder basCarte = new StringBuilder();

        for (Pokemon pokemon : listePokemon) {
            hautCarte.append(BLEU + "  *-----------------------------*" + RESET + "\t\t");

            attaqueCarte.append(String.format(BLEU + "  |" + RESET + " Attaque: " + GRIS_CLAIR + "%-15s" + RESET + BLEU + "    |" + RESET + "\t\t", pokemon.getAttaque()));
            vieCarte.append(String.format(BLEU + "  |" + RESET + " Vie: " + VIOLET_CLAIR + "%-19s" + RESET + BLEU + "    |" + RESET + "\t\t", pokemon.getVie() + "/" + pokemon.getVieMax()));

            Affinite affinite = pokemon.getType().get(0).getAffinite();
            String affiniteColor = getAffiniteColor(affinite);
            affiniteCarte.append(String.format(BLEU + "  |" + RESET + " Affinite : " + affiniteColor + "%-13s" + RESET + BLEU + "    |" + RESET + "\t\t", affinite));

            if (pokemon.getPouvoir() != null) {
                if (pokemon.getPouvoir().getEstDejaUtilise()) {
                    pouvoirCarte.append(String.format(BLEU + "  |" + RESET + " Pouvoir : " + GRIS_CLAIR + "%-18s" + RESET + BLEU + "|" + RESET + "\t\t", pokemon.getPouvoir().getNomPouvoir()));
                } else {
                    pouvoirCarte.append(String.format(BLEU + "  |" + RESET + " Pouvoir : " + ORANGE_CLAIR + "%-18s" + RESET + BLEU + "|" + RESET + "\t\t", pokemon.getPouvoir().getNomPouvoir()));
                }
            } else {
                pouvoirCarte.append(BLEU + "  |" + RESET + " Pouvoir : /                 " + BLEU + "|" + RESET + "\t\t");
            }

            int longueurNom = pokemon.toString().length();
            int espaceAvant = (24 - longueurNom) / 2;
            int espaceApres = 24 - longueurNom - espaceAvant;
            nomCarte.append(String.format(BLEU + "  |%" + espaceAvant + "s" + BLEU_CLAIR + "%s" + BLEU + "%" + espaceApres + "s     |" + RESET + "\t\t", "", pokemon, ""));

            basCarte.append(BLEU + "  *-----------------------------*" + RESET + "\t\t");
        }

        System.out.println(hautCarte);
        System.out.println(nomCarte);
        System.out.println(attaqueCarte);
        System.out.println(vieCarte);
        System.out.println(affiniteCarte);
        System.out.println(pouvoirCarte);
        System.out.println(basCarte);
    }

    private static void afficherPokemonCarteEnnemi(List<Pokemon> listePokemon) {
        StringBuilder hautCarte = new StringBuilder();
        StringBuilder attaqueCarte = new StringBuilder();
        StringBuilder vieCarte = new StringBuilder();
        StringBuilder pouvoirCarte = new StringBuilder();
        StringBuilder affiniteCarte = new StringBuilder();
        StringBuilder nomCarte = new StringBuilder();
        StringBuilder basCarte = new StringBuilder();

        for (Pokemon pokemon : listePokemon) {
            hautCarte.append(ROUGE_FONCE + "  *-----------------------------*" + RESET + "\t\t");

            attaqueCarte.append(String.format(ROUGE_FONCE + "  |" + RESET + " Attaque: " + GRIS_CLAIR + "%-15s" + RESET + ROUGE_FONCE + "    |" + RESET + "\t\t", pokemon.getAttaque()));
            vieCarte.append(String.format(ROUGE_FONCE + "  |" + RESET + " Vie: " + VIOLET_CLAIR + "%-19s" + RESET + ROUGE_FONCE + "    |" + RESET + "\t\t", pokemon.getVie() + "/" + pokemon.getVieMax()));

            Affinite affinite = pokemon.getType().get(0).getAffinite();
            String affiniteColor = getAffiniteColor(affinite);
            affiniteCarte.append(String.format(ROUGE_FONCE + "  |" + RESET + " Affinite : " + affiniteColor + "%-13s" + RESET + ROUGE_FONCE + "    |" + RESET + "\t\t", affinite));

            if (pokemon.getPouvoir() != null) {
                if (pokemon.getPouvoir().getEstDejaUtilise()) {
                    pouvoirCarte.append(String.format(ROUGE_FONCE + "  |" + RESET + " Pouvoir : " + GRIS_CLAIR + "%-18s" + RESET + ROUGE_FONCE + "|" + RESET + "\t\t", pokemon.getPouvoir().getNomPouvoir()));
                } else {
                    pouvoirCarte.append(String.format(ROUGE_FONCE + "  |" + RESET + " Pouvoir : " + ORANGE_CLAIR + "%-18s" + RESET + ROUGE_FONCE + "|" + RESET + "\t\t", pokemon.getPouvoir().getNomPouvoir()));
                }
            } else {
                pouvoirCarte.append(ROUGE_FONCE + "  |" + RESET + " Pouvoir : /                 " + ROUGE_FONCE + "|" + RESET + "\t\t");
            }

            int longueurNom = pokemon.toString().length();
            int espaceAvant = (24 - longueurNom) / 2;
            int espaceApres = 24 - longueurNom - espaceAvant;
            nomCarte.append(String.format(ROUGE_FONCE + "  |%" + espaceAvant + "s" + ROUGE + "%s" + ROUGE_FONCE + "%" + espaceApres + "s     |" + RESET + "\t\t", "", pokemon, ""));

            basCarte.append(ROUGE_FONCE + "  *-----------------------------*" + RESET + "\t\t");
        }

        System.out.println(hautCarte);
        System.out.println(nomCarte);
        System.out.println(attaqueCarte);
        System.out.println(vieCarte);
        System.out.println(affiniteCarte);
        System.out.println(pouvoirCarte);
        System.out.println(basCarte);
    }


    /**
     * Affiche les Pokémon dans la main du joueur.
     *
     * @param joueur le joueur dont la main doit être affichée
     */
    public static void afficherMain(Joueur joueur) {
        StringBuilder main = new StringBuilder("\nEn main:\n");
        if (joueur.getMain().tailleMain() > 0)
        {
            for (Pokemon pokemon : joueur.getMainPokemon())
            {
                Element typePokemon = pokemon.getType().get(0);
                String affiniteColor = getAffiniteColor(typePokemon.getAffinite());

                main.append("- ")
                        .append(BLEU_CLAIR).append(pokemon).append(RESET)
                        .append(", Affinite: ").append(affiniteColor).append(typePokemon.getAffinite()).append(RESET)
                        .append(", Vie: ").append(VIOLET_CLAIR).append(pokemon.getVie()).append(RESET)
                        .append(", Attaque: ").append(GRIS_CLAIR).append(pokemon.getAttaque()).append(RESET)
                        .append(", Pouvoir: ");

                if (pokemon.getPouvoir() != null)
                {
                    main.append(ORANGE_CLAIR).append(pokemon.getPouvoir().getNomPouvoir()).append(RESET);
                }
                else
                {
                    main.append("aucun pouvoir");
                }
                main.append("\n");
            }
            System.out.println(main);
        }
        else
        {
            System.out.println("\nVous n'avez plus de pokémons en main car votre pioche est vide et le(s) pokémon(s) restant(s) est/sont sur le terrain");
        }
    }
    /**
     * Demande à l'utilisateur de choisir un Pokémon à placer.
     *
     * Affiche les Pokémon disponibles dans la main du joueur avec leur numéro.
     * Demande ensuite à l'utilisateur d'entrer l'indice du Pokémon à placer.
     *
     * @param main La liste des Pokémon disponibles dans la main du joueur.
     */

    public static void demanderPokemonPlacer(List<Pokemon> main) {
        StringBuilder mainAffichage = new StringBuilder("\n * Pokémons dans votre main :\n");
        for (int i = 0; i < main.size(); i++) {
            Pokemon pokemon = main.get(i);
            Element typePokemon = pokemon.getType().get(0);
            String affiniteColor = getAffiniteColor(typePokemon.getAffinite());

            mainAffichage.append(i + 1).append(". ")
                    .append(BLEU_CLAIR).append(pokemon).append(RESET)
                    .append(", Affinite: ").append(affiniteColor).append(typePokemon.getAffinite()).append(RESET)
                    .append(", Vie: ").append(VIOLET_CLAIR).append(pokemon.getVie()).append(RESET)
                    .append(", Attaque: ").append(GRIS_CLAIR).append(pokemon.getAttaque()).append(RESET)
                    .append(", Pouvoir: ");

            if (pokemon.getPouvoir() != null) {
                mainAffichage.append(ORANGE_CLAIR).append(pokemon.getPouvoir().getNomPouvoir()).append(RESET);
            } else {
                mainAffichage.append(ORANGE_CLAIR).append("aucun pouvoir").append(RESET);
            }
            mainAffichage.append("\n");
        }
        System.out.println(mainAffichage);
        System.out.println("Entrez l'indice du Pokémon à placer :");
    }
    /**
     * Demande au joueur de sélectionner un Pokémon à jouer.
     *
     * @param joueur le joueur qui doit sélectionner un Pokémon
     */
    public static void demanderPokemonAJouer(Joueur joueur)
    {
        System.out.println("\n * Quel pokemon souhaitez-vous jouer? : ");
        pokemonAfficherPourJouer(joueur.getTerrainPokemon());
    }
    /**
     * Demande à l'utilisateur de choisir un pouvoir parmi les Pokémon donnés.<p>
     * Affiche une liste de Pokémon avec leur numéro et leur pouvoir s'il existe.<p>
     * L'utilisateur peut choisir d'utiliser ou d'afficher un pouvoir.
     *
     * @param pokemons La liste des Pokémon parmi lesquels choisir un pouvoir.
     * @param utiliser Indique si l'utilisateur souhaite utiliser ou afficher un pouvoir.
     */
    public static void demanderAfficherUtiliserPouvoir(List<Pokemon> pokemons, boolean utiliser)
    {
        StringBuilder pokemonTerrain = new StringBuilder("\n * Quel pouvoir souhaitez-vous ");
        if (utiliser)
        {
            pokemonTerrain.append("utiliser");
        }
        else
        {
            pokemonTerrain.append("afficher");
        }
        pokemonTerrain.append(" ?  \n( 0 choisir aucun pouvoir / ");
        int i;
        for (i = 0; i < pokemons.size(); i++)
        {
            Pokemon pokemon = pokemons.get(i);
            pokemonTerrain.append(i + 1).append(" ").append(pokemon.getNom()).append(" ");
            if(pokemon.getPouvoir() != null && !pokemons.get(i).getPouvoir().getEstDejaUtilise())
            {
                pokemonTerrain.append("pouvoir : ").append(ORANGE_CLAIR).append(pokemon.getPouvoir().getNomPouvoir()).append(RESET);
            }
            else if (pokemon.getPouvoir() != null)
            {
                pokemonTerrain.append("pouvoir est déja utilisé ");
            }
            else
            {
                pokemonTerrain.append("ne possède pas de pouvoir ");
            }
            if (i < pokemons.size() - 1)
            {
                pokemonTerrain.append(" / ");
            }
        }
        pokemonTerrain.append(")");
        System.out.println(pokemonTerrain);
    }
    /**
     * Demande à l'utilisateur de choisir un Pokémon auquel attribuer un pouvoir parmi les Pokémon du joueur.<p>
     *
     * Affiche une liste des Pokémon du joueur avec leur numéro à choisir.
     *
     * @param joueur Le joueur dont les Pokémon sont utilisés pour la sélection.
     **/
    public static void demanderPokemonPouvoir(Joueur joueur)
    {
        System.out.println("\n * Quel pokemon souhaitez-vous attribuer la capacité du pouvoir ? : ");
        pokemonAfficher(joueur.getTerrainPokemon());
    }
    /**
     * Affiche une liste de Pokémon avec leur numéro à choisir.
     *
     * @param pokemons La liste des Pokémon à afficher.
     */
    public static void pokemonAfficher(List<Pokemon> pokemons)
    {
        int i;
        StringBuilder pokemonTerrain = new StringBuilder("(");
        for (i = 0; i < pokemons.size(); i++)
        {
            pokemonTerrain.append(i + 1).append(" ").append(pokemons.get(i).getNom());
            if (i < pokemons.size() - 1)
            {
                pokemonTerrain.append(" / ");
            }
        }
        pokemonTerrain.append(")");
        System.out.println(pokemonTerrain);
    }

    /**
     * Méthode d'assistance qui affiche une liste de Pokémon.
     *
     * @param pokemons la liste de Pokémon à afficher
     */
    public static void pokemonAfficherEnnemie(List<Pokemon> pokemons) {
        StringBuilder pokemonTerrain = new StringBuilder("(");
        for (int i = 0; i < pokemons.size(); i++) {
            Element typePokemon = pokemons.get(i).getType().get(0);
            String desavantageColor = getAffiniteColor(typePokemon.getDesavantage());

            pokemonTerrain.append(i + 1).append(" ").append(pokemons.get(i).getNom())
                    .append(" désavantage : ").append(desavantageColor).append(typePokemon.getDesavantage()).append(RESET);
            if (i < pokemons.size() - 1) {
                pokemonTerrain.append(" / ");
            }
        }
        pokemonTerrain.append(")");
        System.out.println(" * Quel pokemon souhaitez-vous attaquer ?");
        System.out.println(pokemonTerrain);
    }
    /**
     * Affiche une liste de Pokémon avec leur numéro à choisir et leur affinité pour le joueur.<p>
     * Pour chaque Pokémon, affiche son numéro, son nom et son affinité.<p>
     * Si un Pokémon est déjà utilisé, affiche un message indiquant qu'il ne peut être choisi qu'au prochain tour.
     *
     * @param pokemons La liste des Pokémon à afficher.
     */
    public static void pokemonAfficherPourJouer(List<Pokemon> pokemons) {
        StringBuilder pokemonTerrain = new StringBuilder("(");
        for (int i = 0; i < pokemons.size(); i++) {
            if (!pokemons.get(i).getEstDejaUtilise()) {
                Element typePokemon = pokemons.get(i).getType().get(0);
                String affiniteColor = getAffiniteColor(typePokemon.getAffinite());

                pokemonTerrain.append(i + 1).append(" ").append(pokemons.get(i).getNom())
                        .append(" affinité : ").append(affiniteColor).append(typePokemon.getAffinite()).append(RESET);
            } else {
                pokemonTerrain.append(pokemons.get(i).getNom()).append(" est déjà utilisé");
            }
            if (i < pokemons.size() - 1) {
                pokemonTerrain.append(" / ");
            }
        }
        pokemonTerrain.append(")");
        System.out.println(pokemonTerrain);
    }
    /**
     * Indique la fin d'une attaque sur un Pokémon ennemi.<p>
     * Affiche le nom du Pokémon ennemi et les dégâts infligés. Si le Pokémon est mort, affiche un message approprié.
     *
     * @param ennemi Le Pokémon ennemi sur lequel l'attaque a été effectuée.
     * @param degatInflige Les dégâts infligés au Pokémon ennemi.
     */
    public static void finAttaquePokemon(Pokemon ennemi, int degatInflige)
    {
        System.out.println("\nLe pokemon " + ennemi.getNom() +" a perdu "+ degatInflige + " de vie.");
        if(!ennemi.estEnVie())
        {
            System.out.println("Il est mort");
        }
        else
        {
            System.out.println("Il lui reste " +ennemi.getVie() + " de vie.");
        }
    }
    /**
     * Affiche un message d'introduction au jeu.
     */
    public static void affichageDebutJeu(){

        System.out.println("\n" +
                " ___       __   _______   ___       ________  ________  _____ ______   _______           _________  ________     \n" +
                "|\\  \\     |\\  \\|\\  ___ \\ |\\  \\     |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\         |\\___   ___\\\\   __  \\    \n" +
                "\\ \\  \\    \\ \\  \\ \\   __/|\\ \\  \\    \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|        \\|___ \\  \\_\\ \\  \\|\\  \\   \n" +
                " \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\  \\_|/__           \\ \\  \\ \\ \\  \\\\\\  \\  \n" +
                "  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\           \\ \\  \\ \\ \\  \\\\\\  \\ \n" +
                "   \\ \\____________\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\           \\ \\__\\ \\ \\_______\\\n" +
                "    \\|____________|\\|_______|\\|_______|\\|_______|\\|_______|\\|__|     \\|__|\\|_______|            \\|__|  \\|_______|\n" +
                "                                                                                                                 \n" +
                "                                                                                                                 \n" +
                "                                                                                                                 \n");
        try {
            Thread.sleep(2000); // 3000 millisecondes = 3 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n" +
                "\t\t ________  ________  ___  __    _______   _____ ______   ________  ________   ___       \n" +
                "\t\t|\\   __  \\|\\   __  \\|\\  \\|\\  \\ |\\  ___ \\ |\\   _ \\  _   \\|\\   __  \\|\\   ___  \\|\\  \\      \n" +
                "\t\t\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\/  /|\\ \\   __/|\\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\     \n" +
                "\t\t \\ \\   ____\\ \\  \\\\\\  \\ \\   ___  \\ \\  \\_|/_\\ \\  \\\\|__| \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\    \n" +
                "\t\t  \\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\    \\ \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\__\\   \n" +
                "\t\t   \\ \\__\\    \\ \\_______\\ \\__\\\\ \\__\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\ \\__\\\\ \\__\\|__|   \n" +
                "\t\t    \\|__|     \\|_______|\\|__| \\|__|\\|_______|\\|__|     \\|__|\\|_______|\\|__| \\|__|   ___ \n" +
                "\t\t                                                                                   |\\__\\\n" +
                "\t\t                                                                                   \\|__|\n" +
                "\t\t                                                                                        \n");
        try {
            Thread.sleep(3000); // 3000 millisecondes = 3 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche une barre de chargement animée pendant une certaine durée.
     *
     * @param duree la durée en secondes pendant laquelle la barre de chargement doit être affichée
     */
    public static void barreDeChargement(int duree) {
        int largeurBarre = 40;
        int pourcentage = 0;

        System.out.print("Chargement en cours... ");

        for (int i = 0; i <= duree * 10; i++) {
            // ici on calcul le pourcentage en fonction de la durée qu'on a mis en parametre
            pourcentage = (int) ((i * 100.0) / (duree * 10));

            // affichage de la barre avec stringBuilder. Remplie va nous permettre de savoir combien on va ajouté de "="
            int remplie = (pourcentage * largeurBarre) / 100;
            StringBuilder barre = new StringBuilder();
            for (int j = 0; j < largeurBarre; j++) {
                if (j < remplie) {
                    barre.append("=");
                } else {
                    barre.append(" ");
                }
            }

            // on affiche la barre de chargement avec \r pour supprimer la precedente
            System.out.print("\r" + barre + " | " + pourcentage + "%");

            // on attend 1 seconde avant la prochaine itération comme ca ca reste cohérent avec le nombre de seconde qu'on a mis en parametre
            try {
                Thread.sleep(100); // 100 millisecondes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\rChargement terminé !            ");
    }

    public static void affichagePhrase(String texte)
    {
        System.out.println(texte);
    }

    private static String getAffiniteColor(Affinite affinite) {
        switch (affinite) {
            case EAU:
                return BLEU;
            case FEU:
                return ROUGE;
            case TERRE:
                return VERT;
            case AIR:
                return BLEU_TRES_CLAIR;
            default:
                return RESET;
        }
    }
}
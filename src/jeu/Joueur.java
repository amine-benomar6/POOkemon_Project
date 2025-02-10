package jeu;

import java.util.*;
import pokemon.*;
import pouvoir.Pouvoir;
import pouvoir.TypePouvoir;

/**
 * Cette classe représente un joueur dans un jeu de cartes à collectionner Pokémon.
 * Elle gère la pioche, la main, le terrain et la défausse du joueur, ainsi que ses actions de jeu.
 **/
public class Joueur
{
    protected Pioche m_pioche;
    protected Main m_main;
    protected Terrain m_terrain;
    protected Defausse m_defausse;
    protected Boolean m_premierJoueur;
    /**
     * Constructeur de la classe Joueur.
     *
     * @param premierJoueur Détermine si le joueur est le premier à jouer.
     * @param pioche        La liste des Pokémon disponibles dans la pioche du joueur.
     */
    public Joueur(Boolean premierJoueur, ArrayList<Pokemon> pioche)
    {
        m_pioche = new Pioche(pioche);
        m_main = new Main();
        m_terrain = new Terrain();
        m_defausse = new Defausse();
        m_premierJoueur = premierJoueur;
    }
    /**
     * Renvoie le terrain du joueur.
     *
     * @return Le terrain du joueur.
     */
    public Terrain getTerrain()
    {
        return m_terrain;
    }
    /**
     * Renvoie la main du joueur.
     *
     * @return La main du joueur.
     */
    public Main getMain()
    {
        return m_main;
    }
    /**
     * Renvoie la pioche du joueur.
     *
     * @return La pioche du joueur.
     */
    public Pioche getPioche()
    {
        return m_pioche;
    }
    /**
     * Renvoie la défausse du joueur.
     *
     * @return La défausse du joueur.
     */
    public Defausse getDefausse()
    {
        return m_defausse;
    }
    /**
     * Renvoie une copie de la liste des Pokémon présents sur le terrain du joueur.
     *
     * @return Une liste des Pokémon sur le terrain.
     */
    public List<Pokemon> getTerrainPokemon()
    {
        return new ArrayList<>(m_terrain.getTerrain());
    }
    /**
     * Renvoie une copie de la liste des Pokémon dans la main du joueur.
     *
     * @return Une liste des Pokémon dans la main du joueur.
     */
    public List<Pokemon> getMainPokemon()
    {
        return new ArrayList<>(m_main.getMain());
    }
    /**
     * Renvoie une copie de la liste des Pokémon dans la pioche du joueur.
     *
     * @return Une liste des Pokémon dans la pioche du joueur.
     */
    public List<Pokemon> getPiochePokemon()
    {
        return new ArrayList<>(m_pioche.getPioche());
    }
    /**
     * Renvoie la taille initiale de la pioche du joueur.
     *
     * @return La taille de la pioche.
     */
    public int getTailleMaxPioche(){
        return m_pioche.getTailleMaxPioche();
    }
    /**
     * Indique si le joueur est le premier à jouer.
     *
     * @return True si le joueur est le premier, False sinon.
     */
    public Boolean getPremierJoueur() {
        return m_premierJoueur;
    }
    /**
     * Permet au joueur de piocher des cartes dans sa pioche jusqu'à ce qu'il ait 5 cartes dans sa main uniquement si
     * la pioche n'est pas vide.
     */
    public void piocher()
    {
        Pokemon pokemon;
        while (m_pioche.getPioche().size() > 0 && m_main.tailleMain() < 5)
        {
            pokemon = m_pioche.piocher();
            m_main.ajouterPokemonMain(pokemon);
            m_pioche.supprimePokemonPioche(pokemon);
        }
        Affichage.affichagePhrase(this+" a pioché");
    }
    /**
     * Permet au joueur de placer des Pokémon de sa main sur son terrain, jusqu'à ce qu'il y ait 3 Pokémon sur le
     * terrain uniquement si la main du joueur n'est pas vide.
     */
    public void placer()
    {
        int indicePokemon;
        Pokemon pokemonAPlacer;
        while (m_main.tailleMain() > 0 && m_terrain.tailleTerrain() < 3)
        {
            indicePokemon = choisirPokemonPlacer();
            pokemonAPlacer = m_main.getPokemon(indicePokemon);
            m_terrain.ajouterPokemonTerrain(pokemonAPlacer);
            m_main.supprimePokemonMain(pokemonAPlacer);
            Affichage.affichagePhrase(this+" : Pokemon placé avec succès !");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Permet au joueur de choisir un Pokémon de sa main pour le placer sur le terrain.<p>
     * Affiche un message d'erreur si l'indice entré est invalide.
     *
     * @return L'indice du Pokémon choisi dans la main du joueur.
     */
    private int choisirPokemonPlacer()
    {
        String messageErreur = "L'indice que vous avez rentré est soit inférieur à 1 ou soit supérieur à " + m_main.tailleMain() + " de votre main";
        return choisirIndice(messageErreur, false, false, false, false, false, null);
    }
    /**
     * Scanne et récupère un entier à partir de l'entrée utilisateur.<p>
     * Répète le processus jusqu'à ce qu'un entier valide soit saisi.
     *
     * @return L'entier saisi par l'utilisateur.
     */
    private static int scanInt()
    {
        Scanner scanner = new Scanner(System.in);
        int indiceRecu = 0;
        boolean validInput = false;

        while (!validInput)
        {
            try
            {
                indiceRecu = scanner.nextInt();
                validInput = true; // Sortir de la boucle si l'entrée est un entier valide
            }
            catch (InputMismatchException e)
            {
                Affichage.affichagePhrase("Ce n'est pas un entier. Veuillez réessayer.");
                scanner.next(); // Consommer l'entrée non valide pour éviter une boucle infinie
            }
        }
        return indiceRecu;
    }
    /**
     * Permet au joueur d'attaquer un Pokémon adverse avec l'un de ses propres Pokémon.
     * @param ennemi le joueur adverse
     */
    public void attaquer(Joueur ennemi)
    {
        int indicePokemonAllie = choisirPokemonAllie();
        int indicePokemonEnnemi = choisirPokemonEnnemi(ennemi);
        Pokemon pokemonAllie = m_terrain.getPokemon(indicePokemonAllie);
        Pokemon pokemonEnnemi = ennemi.m_terrain.getPokemon(indicePokemonEnnemi);
        pokemonAllie.setEstDejaUtilise(true);
        pokemonAllie.attaque(pokemonEnnemi);
        ennemi.pokemonAPerdu(pokemonEnnemi);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Vérifie si un Pokémon possède un pouvoir non utilisé.
     *
     * @return Vrai si au moins un Pokémon possède un pouvoir non utilisé, sinon faux.
     */
    public boolean aUnPouvoir()
    {
        int i = 0;
        boolean aPouvoir = false;
        while (i < m_terrain.tailleTerrain() && !aPouvoir)
        {
            if(m_terrain.getPokemon(i).getPouvoir() != null && !m_terrain.getPokemon(i).getPouvoir().getEstDejaUtilise())
            {
                aPouvoir = true;
            }
            i++;
        }
        return aPouvoir;
    }
    /**
     * Renvoie le nombre de pouvoirs non utilisés parmi les Pokémon.
     *
     * @return Le nombre de pouvoirs non utilisés.
     */
    public int nombrePouvoir()
    {
        int nb = 0;
        for (int i = 0; i < m_terrain.tailleTerrain(); i++)
        {
            if(m_terrain.getPokemon(i).getPouvoir() != null && !m_terrain.getPokemon(i).getPouvoir().getEstDejaUtilise())
            {
                nb++;
            }
        }
        return nb;
    }
    /**
     * Utilise certains pouvoirs disponibles du joueur.<p>
     * Si le joueur a des pouvoirs disponibles, il choisit de les utilisé ou pas tous l'un après l'autre.<p>
     * Si le joueur n'a pas de pouvoir, un message est affiché.
     *
     * @param ennemi Le joueur ennemi utilisé pour certains pouvoirs lorsque la cible est un Pokémon ennemi.
     */
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
            Affichage.affichagePhrase("Dommage"+ this +" n'avez aucun pouvoir");
        }
    }
    /**
     * Le joueur peut choisir d'utiliser le pouvoir d'un Pokémon ou il peut choisir de ne pas l'utiliser<p>
     * Ensuite, le joueur choisit un Pokémon cible ennemi ou un Pokémon allié en fonction du pouvoir utilisé.<p>
     * Le pouvoir est appliqué contre le Pokémon cible, et les états des Pokémon sont mis à jour pour voir s'ils sont éliminé ou pas.
     * @param ennemi Le joueur ennemi utilisé pour certains pouvoirs lorsque la cible est un Pokémon ennemi.
     */
    public void utilisationPouvoir(Joueur ennemi)
    {
        afficherPouvoir();
        Affichage.affichagePhrase("\n\t\t\t\t\t\t\t\t\t\t" + " C'est à" + this + " de choisir son pouvoir\n");
        int indicePouvoir = choisirPouvoirUtiliserAfficher(true);

        if (indicePouvoir != -1)
        {
            Pouvoir pouvoir = m_terrain.getPokemon(indicePouvoir).getPouvoir();
            Pokemon pokemonCible = getPokemonCible(ennemi, pouvoir);
            Affichage.affichagePhrase("\nVous avez choisi " + pouvoir.getNomPouvoir());
            pouvoir.utilisationPouvoir(pokemonCible, m_terrain.getPokemon(indicePouvoir), this);
            pokemonAPerdu(m_terrain.getPokemon(indicePouvoir));
            ennemi.pokemonAPerdu(pokemonCible);
        }
        else
        {
            Affichage.affichagePhrase("Vous avez choisi de ne pas utiliser de pouvoir.");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Détermine le Pokémon cible pour un pouvoir donné.<p>
     * Si le pouvoir cible un Pokémon allié, demande au joueur de choisir un Pokémon sur son propre terrain.<p>
     * Si le pouvoir cible un Pokémon ennemi, demande au joueur de choisir un Pokémon sur le terrain de l'ennemi.
     *
     * @param ennemi Le joueur ennemi.
     * @param pouvoir Le pouvoir qui doit être utilisé.
     * @return Le Pokémon cible pour le pouvoir.
     */
    public Pokemon getPokemonCible(Joueur ennemi, Pouvoir pouvoir)
    {
        int indicePokemonCible;
        if(pouvoir.getCible().equals(TypePouvoir.NOUS))
        {
            indicePokemonCible = this.choisirPokemonAttributPouvoir();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return m_terrain.getPokemon(indicePokemonCible);
        }
        else
        {
            indicePokemonCible = this.choisirPokemonAttributPouvoir(ennemi);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ennemi.m_terrain.getPokemon(indicePokemonCible);
        }
    }
    /**
     * Affiche les pouvoirs disponibles pour le joueur.<p>
     * Demande au joueur de choisir un pouvoir à afficher et affiche sa description.<p>
     * Si le joueur choisit de ne pas afficher de pouvoir, un message est affiché.
     */
    public void afficherPouvoir()
    {
        int indiceAfficherPouvoir = choisirPouvoirUtiliserAfficher(false);
        if(indiceAfficherPouvoir != -1)
        {
            Pouvoir pouvoir = m_terrain.getPokemon(indiceAfficherPouvoir).getPouvoir();
            pouvoir.afficherDescripition();
        }
        else
        {
            System.out.println("Vous avez choisi de ne pas afficher de pouvoir.");
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Permet au joueur de choisir un indice pour utiliser ou afficher un pouvoir.<p>
     * Affiche un message d'erreur si l'indice n'est pas valide.
     *
     * @param utiliser Boolean indiquant si le pouvoir doit être utilisé (true) ou simplement affiché (false).
     * @return L'indice du pokemon qui va utiliser ou afficher son pouvoir.
     */
    private int choisirPouvoirUtiliserAfficher(boolean utiliser)
    {
        String messageErreur = "L'indice que vous avez rentré est soit inférieur à 0 ou soit supérieur à " + (m_terrain.tailleTerrain()) + " de votre terrain";
        return choisirIndice(messageErreur, true, utiliser, false, false, false, null);
    }
    /**
     * Permet au joueur de choisir un Pokémon sur son terrain pour attribuer un pouvoir.<p>
     * Affiche un message d'erreur si l'indice n'est pas valide.
     *
     * @return L'indice du pokemon cible.
     */
    public int choisirPokemonAttributPouvoir()
    {
        String messageErreur = "L'indice que vous avez rentré est soit inférieur à 1 ou soit supérieur à " + (m_terrain.tailleTerrain()) + " de votre terrain";
        return choisirIndice(messageErreur, false, false, true, false, false, null);
    }
    /**
     * Permet au joueur de choisir un Pokémon sur le terrain ennemi pour attribuer un pouvoir.<p>
     * Affiche un message d'erreur si l'indice n'est pas valide.
     *
     * @param ennemi L'ennemi dont le terrain sera utilisé pour choisir un Pokémon.
     * @return L'indice du pokemon cible.
     */
    public int choisirPokemonAttributPouvoir(Joueur ennemi)
    {
        String messageErreur = "L'indice que vous avez rentré est soit inférieur à 1 ou soit supérieur à " + (ennemi.m_terrain.tailleTerrain()) + " du terrain ennemie";
        return choisirIndice(messageErreur, false, false, true, true, false, ennemi);
    }
    /**
     * Permet au joueur de choisir un Pokémon allié sur son terrain.
     * Affiche un message d'erreur si l'indice n'est pas valide.
     *
     * @return L'indice choisi, ajusté pour correspondre au bon indice de la liste de mon terrain.
     */
    private int choisirPokemonAllie()
    {
        String messageErreur = "L'indice que vous avez rentré est soit inférieur à 1 ou soit supérieur à " + (m_terrain.tailleTerrain()) + " de votre terrain";
        return choisirIndice(messageErreur, false, false, false, false, true, null);
    }
    /**
     * Permet au joueur de choisir un Pokémon ennemi sur le terrain de l'ennemi.
     * Affiche un message d'erreur si l'indice n'est pas valide.
     *
     * @param ennemi L'ennemi dont le terrain sera utilisé pour choisir un Pokémon.
     * @return L'indice choisi, ajusté pour correspondre au bon indice de la liste du terrain ennemi.
     */
    private int choisirPokemonEnnemi(Joueur ennemi)
    {
        String messageErreur = "L'indice que vous avez rentré est soit inférieur à 1 ou soit supérieur à " + (ennemi.m_terrain.tailleTerrain()) + " du terrain ennemie";
        return choisirIndice(messageErreur, false, false, false, true, true, ennemi);
    }
    /**
     * Permet d'afficher au joueur l'action et choisir un indice avec une vérification de validité en fonction du contexte.<br><br>
     * Affiche un message d'erreur et demande de resaisir l'indice si celui-ci n'est pas valide.
     *
     * @param messageErreur Le message d'erreur à afficher si l'indice n'est pas valide.
     * @param pouvoir Boolean indiquant si on doit utiliser le boolean utiliserAfficher .
     * @param utiliserAfficher Boolean indiquant si le pouvoir doit être utilisé ou affiché.
     * @param attributPouvoir Boolean indiquant si un pouvoir doit être attribué.
     * @param pourEnnemi Boolean indiquant si l'action concerne un ennemi.
     * @param attaque Boolean indiquant si l'action est un choix de pokemon pour une attaque.
     * @param ennemi Le joueur ennemi, si applicable.
     * @return L'indice choisi, ajusté pour correspondre à la bonne indice de la liste.
     */
    private int choisirIndice(String messageErreur, boolean pouvoir, boolean utiliserAfficher, boolean attributPouvoir, boolean pourEnnemi, boolean attaque, Joueur ennemi)
    {
        int indice = -1;
        boolean indiceValide = false;

        while (!indiceValide)
        {
            if(pouvoir)
            {
                Affichage.demanderAfficherUtiliserPouvoir(this.getTerrainPokemon(), utiliserAfficher);
                indice = scanInt();
                indiceValide = indiceValidePouvoirUtilise(indice, this.m_terrain.tailleTerrain(), messageErreur);
            }
            else if(attributPouvoir && !pourEnnemi)
            {
                Affichage.demanderPokemonPouvoir(this);
                indice = scanInt();
                indiceValide = indiceValidePlacerEtPokemonAttributPouvoir(indice, this.m_terrain.tailleTerrain(), messageErreur);
            }
            else if(attributPouvoir)
            {
                Affichage.demanderPokemonPouvoir(ennemi);
                indice = scanInt();
                indiceValide = indiceValidePlacerEtPokemonAttributPouvoir(indice, ennemi.m_terrain.tailleTerrain(), messageErreur);

            }
            else if(attaque && !pourEnnemi)
            {
                Affichage.demanderPokemonAJouer(this);
                indice = scanInt();
                indiceValide = indiceValide(indice, this.m_terrain.tailleTerrain(), messageErreur, this);
            }
            else if(attaque)
            {
                Affichage.pokemonAfficherEnnemie(ennemi.getTerrainPokemon());
                indice = scanInt();
                indiceValide = indiceValide(indice, ennemi.m_terrain.tailleTerrain(), messageErreur, ennemi);
            }
            else
            {
                Affichage.demanderPokemonPlacer(m_main.getMain());
                indice = scanInt();
                indiceValide = indiceValidePlacerEtPokemonAttributPouvoir(indice, m_main.tailleMain(), messageErreur);
            }

        }
        return indice - 1;
    }
    /**
     * Vérifie si l'indice est valide pour une action d'attaque ou de sélection de Pokémon.
     * L'indice est valide si :<br><br>
     * - L'indice est compris entre 0 et tailleTerrain - 1.<br>
     * - Le Pokémon sélectionné n'a pas encore été joué.<br><br>
     * Affiche un message d'erreur et demande de resaisir l'indice si celui-ci n'est pas valide.
     *
     * @param indice L'indice choisi par le joueur.
     * @param tailleTerrain La taille du terrain du joueur.
     * @param messageErreur Le message d'erreur à afficher si l'indice n'est pas valide.
     * @param joueur Le joueur pour lequel l'indice est vérifié.
     * @return True si l'indice est valide, false sinon.
     */
    private boolean indiceValide(int indice, int tailleTerrain, String messageErreur, Joueur joueur)
    {
        boolean indiceValide = (indice-1) >= 0 && (indice-1) < tailleTerrain;

        if(indiceValide && !joueur.m_terrain.getPokemon(indice-1).getEstDejaUtilise())
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        else if(indiceValide && joueur.m_terrain.getPokemon(indice-1).getEstDejaUtilise())
        {
            messageErreur = "Ce pokemon ne peut plus être jouer";
        }
        Affichage.affichagePhrase(messageErreur);
        Affichage.affichagePhrase("Remettre un autre indice conforme s'il vous plaît");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Vérifie si l'indice est valide pour utiliser un pouvoir.
     * L'indice est valide si :<br><br>
     * - L'indice est 0 (cas particulier où aucun Pokémon n'est choisi).<br>
     * - L'indice est compris entre 0 et tailleTerrain - 1.<br>
     * - Le Pokémon sélectionné a un pouvoir et qui n'est pas encore utilisé.<br><br>
     * Affiche un message d'erreur si l'indice n'est pas valide.
     *
     * @param indice L'indice choisi par le joueur.
     * @param tailleTerrain La taille du terrain du joueur.
     * @param messageErreur Le message d'erreur à afficher si l'indice n'est pas valide.
     * @return True si l'indice est valide, false sinon.
     */
    private boolean indiceValidePouvoirUtilise(int indice, int tailleTerrain, String messageErreur)
    {
        boolean indiceValide = (indice-1) >= 0 && (indice-1) < tailleTerrain;

        if(indice == 0 || (indiceValide && m_terrain.getPokemon(indice-1).getPouvoir()!=null &&!m_terrain.getPokemon(indice-1).getPouvoir().getEstDejaUtilise()))
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        else if(indiceValide && m_terrain.getPokemon(indice-1).getPouvoir()!=null && m_terrain.getPokemon(indice-1).getPouvoir().getEstDejaUtilise())
        {
            messageErreur = "Le pouvoir est déjà utilisé";
        }
        else if(indiceValide && m_terrain.getPokemon(indice-1).getPouvoir()==null)
        {
            messageErreur = m_terrain.getPokemon(indice-1)+ " n'a pas de pouvoir";
        }
        Affichage.affichagePhrase(messageErreur);
        Affichage.affichagePhrase("Remettre un autre indice conforme s'il vous plaît");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Vérifie si l'indice est valide pour placer un Pokémon ou attribuer un pouvoir.
     * L'indice est valide si :<br><br>
     * - L'indice est compris entre 0 et tailleTerrain - 1.<br><br>
     * Affiche un message d'erreur si l'indice n'est pas valide.
     *
     * @param indice L'indice choisi par le joueur.
     * @param taille La taille de la main ou du terrain du joueur.
     * @param messageErreur Le message d'erreur à afficher si l'indice n'est pas valide.
     * @return True si l'indice est valide, false sinon.
     */
    private boolean indiceValidePlacerEtPokemonAttributPouvoir(int indice, int taille, String messageErreur)
    {
        boolean indiceValide = (indice-1) >= 0 && (indice-1) < taille;

        if(indiceValide)
        {
            return true;
        }
        Affichage.affichagePhrase(messageErreur);
        Affichage.affichagePhrase("Indice incorrect");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Réinitialise l'état d'utilisation de tous les Pokémon sur le terrain du joueur.<p>
     * Parcourt tous les Pokémon du terrain et définit leur état d'utilisation à false.
     */
    public void reutiliserPokemon()
    {
        for (Pokemon pokemon : this.getTerrainPokemon())
        {
            pokemon.setEstDejaUtilise(false);
        }
    }
    /**
     * Réinitialise l'attaque temporaire de tous les Pokémon sur le terrain du joueur.<p>
     * Parcourt tous les Pokémon du terrain et définit leur attaque temporaire à 0 si elle est supérieure à 0.
     */
    public void enleverAttaqueTemporaire()
    {
        for (Pokemon pokemon : m_terrain.getTerrain())
        {
            if(pokemon.getAttaqueTemporaire() > 0)
            {
                pokemon.setAttaqueTemporaire(0);
            }
        }
    }
    /**
     * On enlève le pokemon qui vient d'être attaqué du terrain pour le rajouter dans la défausse lorsque celui-ci est mort.
     * @param pokemonQuiAEteAttaque le Pokémon qui a été attaqué
     */
    public void pokemonAPerdu(Pokemon pokemonQuiAEteAttaque)
    {
        if(!pokemonQuiAEteAttaque.estEnVie())
        {
            m_terrain.supprimePokemonTerrain(pokemonQuiAEteAttaque);
            m_defausse.ajouterPokemonDefausse(pokemonQuiAEteAttaque);
            Affichage.affichagePhrase("\n" + this + ": Le pokemon " + pokemonQuiAEteAttaque.getNom() + " est mort.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Vérifie si le joueur a perdu la partie.<p>
     * Un joueur est considéré comme ayant perdu si la taille de sa défausse est égale à la taille maximale de sa pioche.
     *
     * @return True si le joueur a perdu, false sinon.
     */
    public boolean aPerdu()
    {
        return m_defausse.tailleDefausse() == getTailleMaxPioche();
    }
    /**
     * Vérifie si le joueur à gagner la partie en vérifiant l'état de l'ennemi.<p>
     * Un joueur est considéré comme ayant gagné si la taille de la défausse de l'ennemi est égale à la taille maximale de la pioche de l'ennemi.
     *
     * @param ennemi Le joueur ennemi.
     * @return True si le joueur a gagné, false sinon.
     */
    public boolean aGagne(Joueur ennemi)
    {
        return ennemi.m_defausse.tailleDefausse() == ennemi.getTailleMaxPioche();
    }

    @Override
    public String toString()
    {
        return " Vous";
    }
}
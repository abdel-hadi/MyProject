package employe;
//La classe Employe
abstract class Employe {
    private String matricule;
    private String nom;
    private String prenom;
    private int age;
    private String date;
    
    public Employe(String matricule, String prenom, String nom, int age, String date) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.date = date;
    }

    public abstract double calculerSalaire();

    public String getTitre()
        {
            return ("L'employé");
        }
    
    public String getNom() {
        return getTitre() + prenom + " " + nom;
    }
}

//La classe Commercial (regroupant Vendeur et Représentant)
abstract class Commercial extends Employe {
    private double chiffreAffaire;

    public Commercial(String matricule, String prenom, String nom, int age, String date,
               double chiffreAffaire) {
        super(matricule, prenom, nom, age, date);
        this.chiffreAffaire = chiffreAffaire;
    }

    public double getChiffreAffaire()
        {
            return chiffreAffaire;
        }
    
}

//La classe Vendeur
class Vendeur extends Commercial {
    private final static double POURCENT_VENDEUR = 0.2;
    private final static int BONUS_VENDEUR = 100;
    
    public Vendeur(String matricule, String prenom, String nom, int age, String date,
            double chiffreAffaire) {
        super(matricule, prenom, nom, age, date, chiffreAffaire);
    }

    public double calculerSalaire() {
        return (POURCENT_VENDEUR * getChiffreAffaire()) + BONUS_VENDEUR;
    }

    public String getTitre()
        {
            return "Le vendeur ";
        }

}

//La classe Représentant
class Representant extends Commercial {
    private final static double POURCENT_REPRESENTANT = 0.2;
    private final static int BONUS_REPRESENTANT = 200;
    
    public Representant(String matricule, String prenom, String nom, int age, String date, double chiffreAffaire) {
        super(matricule, prenom, nom, age, date, chiffreAffaire);
    }

    public double calculerSalaire() {
        return (POURCENT_REPRESENTANT * getChiffreAffaire()) + BONUS_REPRESENTANT;
    }

    public String getTitre()
        {
            return ("Le représentant ");
        }
}

//La classe Technicien (Production)
class Technicien extends Employe {
    private final static double FACTEUR_UNITE = 5.0;
    private int unites;

    public Technicien(String matricule, String prenom, String nom, int age, String date, int unites) {
        super(matricule, prenom, nom, age, date);
        this.unites = unites;
    }

    public double calculerSalaire() {
        return FACTEUR_UNITE * unites;
    }

    public String getTitre()
        {
            return ("Le technicien ");
        }
}

//La classe Manutentionnaire
class Manutentionnaire extends Employe {
    private final static double SALAIRE_HORAIRE = 65.0;
    private int heures;

    public Manutentionnaire(String matricule, String prenom, String nom, int age, String date,
                     int heures) 
    {
        super(matricule, prenom, nom, age, date);
        this.heures = heures;
    }

    public double calculerSalaire() {
        return SALAIRE_HORAIRE * heures;
    }

    public String getTitre()
        {
            return ("Le manut. ") ;
        }
}

//L'interface d'employés à risque
interface ARisque {
    int PRIME = 25000;
}

//Une première sous-classe d'employé à risque
class TechnARisque extends Technicien implements ARisque {

    public TechnARisque(String matricule,String prenom, String nom, int age, String date, int unites) {
        super(matricule, prenom, nom, age, date, unites);
    }

    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}

//Une autre sous-classe d'employé à risque
class ManutARisque extends Manutentionnaire implements ARisque {
    
    public ManutARisque(String matricule, String prenom, String nom, int age, String date, int heures) {
        super(matricule, prenom, nom, age, date, heures);
    }

    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}

//La classe Personnel
class Personnel {
    private Employe[] staff;
    private int nbreEmploye;
    private final static int MAXEMPLOYE = 300;

    public Personnel() {
        staff = new Employe[MAXEMPLOYE];
        nbreEmploye = 0;
    }

    public void ajouterEmploye(Employe e) {
        ++nbreEmploye;
        if (nbreEmploye <= MAXEMPLOYE) {
            staff[nbreEmploye - 1] = e;
        } 
        else 
        {
            System.out.println("Pas plus de " + MAXEMPLOYE + " employés");
        }
    }

    public double salaireMoyen() {
        double somme = 0.0;
        for (int i = 0; i < nbreEmploye; i++) 
        {
            somme += staff[i].calculerSalaire();
        }
        return (somme / nbreEmploye);
    }

    public void afficherSalaires() {
        for (int i = 0; i < nbreEmploye; i++) {
            System.out.println(staff[i].getNom() + " gagne "
                    + staff[i].calculerSalaire() + " francs.");
        }
    }
}

//Main

class Salaires {
    public static void main(String[] args) {
        Personnel p = new Personnel();
        p.ajouterEmploye(new Vendeur("01A001EN", "Abdel", "you", 50, "01/02/2001", 50000));
        p.ajouterEmploye(new Representant("03A006EN", "Adam", "Pierre", 35, "08/05/2003", 40000));
        p.ajouterEmploye(new Technicien("05A023EN", "Dr-In", "Tchafros", 43, "09/01/2005", 1000));
        p.ajouterEmploye(new Manutentionnaire("06A036EN", "Jean", "Noel", 42, "01/09/2006", 450));
        p.ajouterEmploye(new TechnARisque("06A037EN", "Karl", "Du Chappel", 27, "18/07/2006", 1000));
        p.ajouterEmploye(new ManutARisque("10A038EN", "Ali", "Kouz", 30, "211/10/010", 456));

        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de "
                + p.salaireMoyen() + " francs.");

    }

}
 

 

    



 
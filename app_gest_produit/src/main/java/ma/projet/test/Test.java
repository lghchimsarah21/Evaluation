package ma.projet.test;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommande;
import ma.projet.classes.Produit;
import ma.projet.services.CategorieService;
import ma.projet.services.CommandeService;
import ma.projet.services.LigneCommandeService;
import ma.projet.services.ProduitService;
import ma.projet.util.HibernateUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ParseException {
        // Démarrage du contexte Spring
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateUtil.class);

        CategorieService categorieService = context.getBean(CategorieService.class);
        ProduitService produitService = context.getBean(ProduitService.class);
        CommandeService commandeService = context.getBean(CommandeService.class);
        LigneCommandeService ligneCommandeService = context.getBean(LigneCommandeService.class);
        // --- Création d’une commande ---
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCmd = sdf.parse("2025-04-14");
        Commande cmd = new Commande(dateCmd);
        commandeService.create(cmd);


        // --- Création de catégories ---
        Categorie cat2 = new Categorie("C03", "Accessoirs");
        Categorie cat3 = new Categorie("C04", "Telephones");
        categorieService.create(cat2);
        categorieService.create(cat3);

        // --- Création de produits ---
        Produit p5 = new Produit("FS12", 120, cat2);
        Produit p4 = new Produit("YR85", 100, cat3);
        Produit p6 = new Produit("ZE85", 200, cat2);
        produitService.create(p5);
        produitService.create(p4);
        produitService.create(p6);




        // --- Création des lignes de commande ---
        LigneCommande L5 = new LigneCommande(7, cmd,p5);
        LigneCommande L4 = new LigneCommande(14,cmd,p4);
        LigneCommande L6 = new LigneCommande( 5,cmd,p6);

        ligneCommandeService.create(L5);
        ligneCommandeService.create(L4);
        ligneCommandeService.create(L5);


        // --- TEST 1 : Liste de tous les produits ---
        System.out.println("\n==== Liste de tous les produits ====");
        produitService.findAll().forEach(System.out::println);

        // --- TEST 2 : Produits par catégorie ---
        System.out.println("\n==== Produits de la commande  ====");
        List<Produit> produitsCat = produitService.getProduitsByCommande(cmd.getId());
        produitsCat.forEach(System.out::println);

        // --- TEST 3 : Produits commandés entre deux dates ---
        System.out.println("\n==== Produits commandés entre 2022-03-01 et 2022-03-30 ====");
        Date debut = sdf.parse("2022-03-01");
        Date fin = sdf.parse("2022-03-30");
        List<Produit> produitsDates = produitService.getProduitsCommandesBetweenDates(debut, fin);
        produitsDates.forEach(System.out::println);

        // --- TEST 4 : Produits d’une commande donnée ---
        System.out.println("\n==== Produits de la commande 2023-03-14 " + cmd.getId() + " ====");
        List<Produit> produitsCmd = produitService.getProduitsByCommande(cmd.getId());
        produitsCmd.forEach(System.out::println);

        // --- TEST 5 : Produits dont le prix > 100 DH ---
        System.out.println("\n==== Produits avec prix supérieur à 100 DH ====");
        List<Produit> produitsPrix = produitService.getProduitsPrixSuppA100();
        produitsPrix.forEach(System.out::println);

        // --- TEST 6 : Liste de toutes les catégories ---
        System.out.println("\n==== Liste de toutes les catégories ====");
        categorieService.findAll().forEach(System.out::println);

        // --- TEST 7 : Liste de toutes les commandes ---
        System.out.println("\n==== Liste de toutes les commandes ====");
        commandeService.findAll().forEach(System.out::println);

        // --- TEST 8 : Lignes de commande ---
        System.out.println("\n==== Liste de toutes les lignes de commande ====");
        ligneCommandeService.findAll().forEach(System.out::println);

        System.out.println("\n✅ Tests terminés avec succès !");
    }
}

package ma.projet.test;

import ma.projet.services.EmployeService;
import ma.projet.services.EmployeTacheService;
import ma.projet.services.ProjetService;
import ma.projet.services.TacheService;
import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.util.HibernateUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateUtil.class);

        EmployeService employeService = context.getBean(EmployeService.class);
        EmployeTacheService employeTacheService = context.getBean(EmployeTacheService.class);
        ProjetService projetService = context.getBean(ProjetService.class);
        TacheService tacheService = context.getBean(TacheService.class);

        // ===================== CRÉATION DES EMPLOYÉS =====================
        Employe emp1 = new Employe();
        emp1.setNom("El Amrani");
        emp1.setPrenom("Youssef");
        emp1.setTelephone("0612345678");
        employeService.create(emp1);

        Employe emp2 = new Employe();
        emp2.setNom("Benali");
        emp2.setPrenom("Khadija");
        emp2.setTelephone("0623456789");
        employeService.create(emp2);

        Employe emp3 = new Employe();
        emp3.setNom("Toumi");
        emp3.setPrenom("Imane");
        emp3.setTelephone("0678901234");
        employeService.create(emp3);

        // ===================== CRÉATION DES PROJETS =====================
        Projet proj1 = new Projet();
        proj1.setNom("Application E-commerce");
        proj1.setEmploye(emp1);
        proj1.setDateDebut(getDate(2025, Calendar.JANUARY, 10));
        proj1.setDateFin(getDate(2025, Calendar.MAY, 30));
        projetService.create(proj1);

        Projet proj2 = new Projet();
        proj2.setNom("Plateforme RH");
        proj2.setEmploye(emp2);
        proj2.setDateDebut(getDate(2025, Calendar.FEBRUARY, 1));
        proj2.setDateFin(getDate(2025, Calendar.JUNE, 15));
        projetService.create(proj2);

        Projet proj3 = new Projet();
        proj3.setNom("Système de réservation hôtelière");
        proj3.setEmploye(emp3);
        proj3.setDateDebut(getDate(2025, Calendar.MARCH, 5));
        proj3.setDateFin(getDate(2025, Calendar.JULY, 31));
        projetService.create(proj3);

        // ===================== CRÉATION DES TÂCHES =====================
        // Tâches planifiées (dateFin = null)
        Tache t1 = new Tache();
        t1.setNom("Conception de la base de données");
        t1.setProjet(proj1);
        t1.setPrix(1200f);
        t1.setDateDebut(getDate(2025, Calendar.JANUARY, 15));
        t1.setDateFin(null);
        tacheService.create(t1);

        Tache t2 = new Tache();
        t2.setNom("Création de l’interface utilisateur");
        t2.setProjet(proj2);
        t2.setPrix(2500f);
        t2.setDateDebut(getDate(2025, Calendar.FEBRUARY, 10));
        t2.setDateFin(null);
        tacheService.create(t2);

        // Tâches réalisées
        Tache t3 = new Tache();
        t3.setNom("Développement du backend");
        t3.setProjet(proj1);
        t3.setPrix(3500f);
        t3.setDateDebut(getDate(2025, Calendar.FEBRUARY, 1));
        t3.setDateFin(getDate(2025, Calendar.MARCH, 20));
        tacheService.create(t3);

        Tache t4 = new Tache();
        t4.setNom("Intégration du module paiement");
        t4.setProjet(proj1);
        t4.setPrix(1800f);
        t4.setDateDebut(getDate(2025, Calendar.MARCH, 25));
        t4.setDateFin(getDate(2025, Calendar.APRIL, 10));
        tacheService.create(t4);

        Tache t5 = new Tache();
        t5.setNom("Tests unitaires");
        t5.setProjet(proj2);
        t5.setPrix(950f);
        t5.setDateDebut(getDate(2025, Calendar.MAY, 1));
        t5.setDateFin(getDate(2025, Calendar.MAY, 15));
        tacheService.create(t5);

        // ===================== ASSOCIATION EMPLOYÉ–TÂCHE =====================
        EmployeTache et1 = new EmployeTache();
        et1.setEmploye(emp1);
        et1.setTache(t1);
        employeTacheService.create(et1);

        EmployeTache et2 = new EmployeTache();
        et2.setEmploye(emp1);
        et2.setTache(t3);
        employeTacheService.create(et2);

        EmployeTache et3 = new EmployeTache();
        et3.setEmploye(emp2);
        et3.setTache(t2);
        employeTacheService.create(et3);

        EmployeTache et4 = new EmployeTache();
        et4.setEmploye(emp3);
        et4.setTache(t5);
        employeTacheService.create(et4);

        EmployeTache et5 = new EmployeTache();
        et5.setEmploye(emp1);
        et5.setTache(t4);
        employeTacheService.create(et5);

        // ===================== AFFICHAGES =====================
        System.out.println("\n--- Tâches d'un employé ---");
        employeService.findTachesByEmploye(emp1.getId());

        System.out.println("\n--- Projets gérés par l'employé ---");
        employeService.findProjetsByEmploye(emp1.getId());

        System.out.println("\n--- Tâches planifiées pour le projet ---");
        projetService.findTachesByProjectId(proj1.getId());


        System.out.println("\n--- Tâches dont le prix est supérieur à 1000 DH ---");
        tacheService.findTacheByPrixSup1000();

        System.out.println("\n--- Tâches réalisées entre deux dates ---");
        Date debut = getDate(2025, Calendar.FEBRUARY, 1);
        Date fin = getDate(2025, Calendar.MAY, 31);
        tacheService.findTacheBetweenTwoDates(debut, fin);

        context.close();
    }

    // Méthode utilitaire pour simplifier la création de dates
    private static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}

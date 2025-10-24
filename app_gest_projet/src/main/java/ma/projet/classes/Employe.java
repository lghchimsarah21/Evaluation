package ma.projet.classes;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employe")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    @OneToMany(mappedBy = "employe",fetch = FetchType.LAZY)
    private List <Projet> projets;
    @OneToMany(mappedBy = "employe",fetch = FetchType.LAZY)
    private List<EmployeTache> employeTaches;

    public Employe() {
    }

    public Employe(String nom, String prenom, String telephone, List<Projet> projets) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.projets = projets;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<Projet> getProjet() {
        return projets;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setProjet(List<Projet> projets) {
        this.projets = projets;
    }

}

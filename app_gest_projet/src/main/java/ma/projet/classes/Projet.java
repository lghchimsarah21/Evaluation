package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({

            @NamedQuery(
                    name="Projet.findChefById",
                    query=" select p From Projet p where p.employe.id=:idEmploye"
            )
})
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private Date dateDebut ;
    private Date  dateFin ;
    @ManyToOne( fetch = FetchType.LAZY)
    private Employe employe;
    @OneToMany(mappedBy = "projet", fetch = FetchType.LAZY)
    private List<Tache> taches;

    public Projet() {
    }

    public Projet(String nom, Date dateDebut, Date dateFin, Employe employe, List<Tache> taches) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.employe = employe;
        this.taches = taches;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}

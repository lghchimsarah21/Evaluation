package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "EmployeTache.findTachesByEmployeId",
                query = "SELECT et.tache FROM EmployeTache et WHERE et.employe.id = :idEmploye"
        )
})
public class EmployeTache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateDebutRelle;
    private Date dateFinRelle;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tache tache;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employe employe; // ✅ renommé pour correspondre à mappedBy = "employe"

    public EmployeTache() {}

    public EmployeTache(Date dateDebutRelle, Date dateFinRelle, Tache tache, Employe employe) {
        this.dateDebutRelle = dateDebutRelle;
        this.dateFinRelle = dateFinRelle;
        this.tache = tache;
        this.employe = employe;
    }

    public Long getId() {
        return id;
    }

    public Date getDateDebutRelle() {
        return dateDebutRelle;
    }

    public Date getDateFinRelle() {
        return dateFinRelle;
    }

    public Tache getTache() {
        return tache;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateDebutRelle(Date dateDebutRelle) {
        this.dateDebutRelle = dateDebutRelle;
    }

    public void setDateFinRelle(Date dateFinRelle) {
        this.dateFinRelle = dateFinRelle;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}

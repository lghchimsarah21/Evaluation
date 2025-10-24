package ma.projet.classes;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;
    private Date date ;
    @OneToMany(mappedBy = "commande", fetch = FetchType.LAZY)
    private List<LigneCommande> lignes = new ArrayList<>();

    public Commande() {
    }

    public Commande(Date date, List<LigneCommande> lignes) {
        this.date = date;
        this.lignes = lignes;
    }

    public Commande(Date dateCmd) {
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLignes(List<LigneCommande> lignes) {
        this.lignes = lignes;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "date=" + date +
                ", id=" + id +
                '}';
    }
}

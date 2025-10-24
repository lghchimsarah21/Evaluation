package ma.projet.classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Produit {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String reference ;
    private float prix;
    @ManyToOne(fetch = FetchType.LAZY)
    private  Categorie categorie;

    @OneToMany(mappedBy = "produit",fetch = FetchType.LAZY)
    private List<LigneCommande> lignesCommande = new ArrayList<>();

    public Produit() {
    }

    public Produit(String reference, float prix) {
        this.reference = reference;
        this.prix = prix;
    }

    public Produit( String reference,float prix, Categorie categorie) {
        this.prix = prix;
        this.reference = reference;
        this.categorie = categorie;
    }

    public String getReference() {
        return reference;
    }

    public int getId() {
        return id;
    }

    public float getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", prix=" + prix +
                '}';
    }
}

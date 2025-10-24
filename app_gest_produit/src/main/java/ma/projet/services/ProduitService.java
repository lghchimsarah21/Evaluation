package ma.projet.services;

import ma.projet.classes.Commande;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class ProduitService implements IDao<Produit> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(Produit produit) {
        sessionFactory.getCurrentSession().save(produit);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Produit produit) {
        sessionFactory.getCurrentSession().delete(produit);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Produit produit) {
        sessionFactory.getCurrentSession().update(produit);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Produit findById(int id) {
        return sessionFactory.getCurrentSession().get(Produit.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produit> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Produit", Produit.class)
                .list();
    }

    @Transactional(readOnly = true)
    public List<Produit> getProduitByCategorie(int categorieId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Produit p WHERE p.categorie.id = :catId", Produit.class)
                .setParameter("catId", categorieId)
                .list();
    }

    @Transactional(readOnly = true)
    public List<Produit> getProduitsCommandesBetweenDates(Date debut, Date fin) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT DISTINCT l.produit FROM LigneCommande l WHERE l.commande.date BETWEEN :d1 AND :d2", Produit.class)
                .setParameter("d1", debut)
                .setParameter("d2", fin)
                .list();
    }

    @Transactional(readOnly = true)
    public List<Produit> getProduitsByCommande(int commandeId) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT DISTINCT l.produit FROM LigneCommande l WHERE l.commande.id = :cmdId", Produit.class)
                .setParameter("cmdId", commandeId)
                .list();
    }

    @Transactional(readOnly = true)
    public List<Produit> getProduitsPrixSuppA100() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Produit p WHERE p.prix > 100", Produit.class)
                .list();
    }

    public void afficherDetailsCommande(int commandeId) {
        CommandeService commandeService = new CommandeService();
        Commande commande = commandeService.findById(commandeId);
        System.out.println("Commande : " + commande.getId() + "\tDate : " + commande.getDate());
    }
}

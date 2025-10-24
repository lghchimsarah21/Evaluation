package ma.projet.services;


import ma.projet.dao.IDao;
import ma.projet.classes.LigneCommande;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LigneCommandeService  implements IDao<LigneCommande> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(LigneCommande LigneCommande) {
        Session session = sessionFactory.getCurrentSession();
        session.save(LigneCommande);
        return true;
    }

    // Méthodes restantes à implémenter :
    @Override
    @Transactional
    public boolean delete(LigneCommande LigneCommande) {
        sessionFactory.getCurrentSession().delete(LigneCommande);
        return true;
    }

    @Override
    @Transactional
    public boolean update(LigneCommande LigneCommande) {
        sessionFactory.getCurrentSession().update(LigneCommande);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public LigneCommande findById(int id) {
        return sessionFactory.getCurrentSession().get(LigneCommande.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LigneCommande> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from LigneCommande ", LigneCommande.class)
                .list();
    }
}
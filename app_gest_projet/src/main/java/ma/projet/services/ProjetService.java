package ma.projet.services;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.classes.Employe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProjetService implements IDao<Projet> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(Projet Projet) {
        Session session = sessionFactory.getCurrentSession();
        session.save(Projet);
        return true;
    }

    // Méthodes restantes à implémenter :
    @Override
    @Transactional
    public boolean delete(Projet Projet) {
        sessionFactory.getCurrentSession().delete(Projet);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Projet Projet) {
        sessionFactory.getCurrentSession().update(Projet);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Projet findById(int id) {
        return sessionFactory.getCurrentSession().get(Projet.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projet> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Projet ",Projet.class)
                .list();
    }

    @Transactional(readOnly = true)
    public List<Tache> findTachesByProjectId (int idProjet) {
        return  sessionFactory.getCurrentSession()
                .createQuery("Select  t  from Tache t where t.projet.id=:idPrjt",Tache.class)
                .setParameter("idPrjt",idProjet)
                .list();
    }
    @Transactional
    public List<Tache> findTachesByDateReelle(int idProjet) {
        return  sessionFactory.getCurrentSession()
                .createQuery("SELECT t FROM Tache t WHERE t.projet.id = :idProjet " +
                        "AND t.dateDebutReelle IS NOT NULL AND t.dateFinReelle IS NOT NULL" ,Tache.class)
                .setParameter("idProjet",idProjet)
                .list();
    }
}

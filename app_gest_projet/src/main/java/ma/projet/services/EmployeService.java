package ma.projet.services;

import ma.projet.classes.EmployeTache;
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
public class EmployeService implements IDao<Employe> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(Employe Employe) {
        Session session = sessionFactory.getCurrentSession();
        session.save(Employe);
        return true;
    }

    // Méthodes restantes à implémenter :
    @Override
    @Transactional
    public boolean delete(Employe Employe) {
        sessionFactory.getCurrentSession().delete(Employe);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Employe Employe) {
        sessionFactory.getCurrentSession().update(Employe);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Employe findById(int id) {
        return sessionFactory.getCurrentSession().get(Employe.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("From Employe ", Employe.class)
                .list();
    }

    @Transactional(readOnly = true)
    public List<Tache> findTachesByEmploye(int idEmploye) {
        return sessionFactory.getCurrentSession()
                .createNamedQuery("EmployeTache.findTachesByEmployeId", Tache.class)
                .setParameter("idEmploye", idEmploye)
                .getResultList();
    }
    @Transactional(readOnly = true)
    public List<Projet> findProjetsByEmploye(int idEmploye) {
        return sessionFactory.getCurrentSession()
                .createNamedQuery("Projet.findChefById", Projet.class)
                .setParameter("idEmploye", idEmploye)
                .getResultList();
    }
}

package ma.projet.services;

import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class TacheService implements IDao<Tache> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(Tache Tache) {
        Session session = sessionFactory.getCurrentSession();
        session.save(Tache);
        return true;
    }

    // Méthodes restantes à implémenter :
    @Override
    @Transactional
    public boolean delete(Tache Tache) {
        sessionFactory.getCurrentSession().delete(Tache);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Tache Tache) {
        sessionFactory.getCurrentSession().update(Tache);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Tache findById(int id) {
        return sessionFactory.getCurrentSession().get(Tache.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tache> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("From Tache ",Tache.class)
                .list();
    }
    @Transactional(readOnly = true)
    public List<Tache> findTacheByPrixSup1000() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT DISTINCT t from Tache t where t.prix >1000",Tache.class)
                .list();
    }
    @Transactional(readOnly = true)
    public List<Tache> findTacheBetweenTwoDates(Date d1, Date d2) {
        return sessionFactory.getCurrentSession()
                .createQuery("Select t From Tache t where t.dateDebut=:d1 and t.dateFin=:d2",Tache.class)
                .setParameter("d1",d1)
                .setParameter("d2",d2)
                .list();
    }
}

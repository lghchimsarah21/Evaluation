package ma.projet.services;

import ma.projet.dao.IDao;
import ma.projet.classes.EmployeTache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeTacheService implements IDao<EmployeTache> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(EmployeTache EmployeTache) {
        Session session = sessionFactory.getCurrentSession();
        session.save(EmployeTache);
        return true;
    }

    // Méthodes restantes à implémenter :
    @Override
    @Transactional
    public boolean delete(EmployeTache EmployeTache) {
        sessionFactory.getCurrentSession().delete(EmployeTache);
        return true;
    }

    @Override
    @Transactional
    public boolean update(EmployeTache EmployeTache) {
        sessionFactory.getCurrentSession().update(EmployeTache);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeTache findById(int id) {
        return sessionFactory.getCurrentSession().get(EmployeTache.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeTache> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("From EmployeTache ", EmployeTache.class)
                .list();
    }
}

package com.company;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;


public class UserService {

    private SessionFactory sessionFactory;

    public UserService() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            throw e;
        }
    }

    public Usertable getUser(String nickname, String password) {

        Session session = sessionFactory.openSession();

        Query<Usertable> query = session.createQuery("Select u from com.company.Usertable u where u.nickname =:nickname and u.password=:password", Usertable.class);
        query.setParameter("nickname", nickname);
        query.setParameter("password", password);
        if (query.getSingleResult() != null) {
            return query.getSingleResult();
        }
        return null;
    }

    public void addUser(Usertable usertable) {

        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(usertable);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.printf("Nickname \'%s\' is reserved\n", usertable.getNickname());
        }
    }


}

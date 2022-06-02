package com.company;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class ItemService {

    private static SessionFactory sessionFactory;

    public ItemService() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            throw e;
        }
    }

    public void addListing(Listings listing) {
        Transaction tx = null;

        try(Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(listing);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.printf("Id \'%s\' is reserved\n", listing.getId());
        }

    }

    public static List<Listings> getAllListingsAdmin(){
        Session session = sessionFactory.openSession();
        Query<Listings> query = session.createQuery("from com.company.Listings", Listings.class);
        return query.getResultList();
    }

    public static List<Listings> getAllNotDeletedItems() {
        Session session = sessionFactory.openSession();
        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where deleted = false", Listings.class);
        return query.getResultList();
    }
    public static List<Listings> getAllReported() {
        Session session = sessionFactory.openSession();
        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where reported = true", Listings.class);
        return query.getResultList();
    }

    public static List<Listings> getAllSoldItems() {
        Session session = sessionFactory.openSession();
        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where sold = true", Listings.class);
        return query.getResultList();
    }

    public static List<Listings> getSearchedListings(String item) {
        Session session = sessionFactory.openSession();
        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where title like :search or notes like :search", Listings.class);
        query.setParameter("search", "%" + item + "%");
        return query.getResultList();
    }

    public static List<Listings> getYourListings(String nickname) {
        Session session = sessionFactory.openSession();
        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where nickname = :nickname", Listings.class);
        query.setParameter("nickname", nickname);
        return query.getResultList();
    }


    public void editListingg(String itemId, String title, float price, String city, String contactName, String phoneNumber, String notes) {

        Session session = sessionFactory.openSession();

        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where id =:id", Listings.class);
        query.setParameter("id", itemId);

        Listings edit = null;
        try {
            edit = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("blogai");
        }
        edit.setTitle(title);
        edit.setPrice(price);
        edit.setCity(city);
        edit.setContactName(contactName);
        edit.setPhoneNumber(phoneNumber);
        edit.setNotes(notes);

        Transaction tx = session.beginTransaction();
        session.update(edit);
        tx.commit();

    }
    public void markAsDeleted(String itemId){
        Session session = sessionFactory.openSession();

        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where id =:id", Listings.class);
        query.setParameter("id", itemId);

        Listings edit = null;
        try {
            edit = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("blogai");
        }
        edit.setDeleted(true);

        Transaction tx = session.beginTransaction();
        session.update(edit);
        tx.commit();
    }

    public void markAsSold(String itemId){
        Session session = sessionFactory.openSession();

        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where id =:id", Listings.class);
        query.setParameter("id", itemId);

        Listings edit = null;
        try {
            edit = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("blogai");
        }
        edit.setSold(true);

        Transaction tx = session.beginTransaction();
        session.update(edit);
        tx.commit();
    }

    public void markAsReported(String itemId){
        Session session = sessionFactory.openSession();

        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where id =:id", Listings.class);
        query.setParameter("id", itemId);

        Listings edit = null;
        try {
            edit = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("blogai");
        }
        edit.setReport(true);

        Transaction tx = session.beginTransaction();
        session.update(edit);
        tx.commit();
    }

    public void markAsOK(String itemId){
        Session session = sessionFactory.openSession();

        Query<Listings> query = session.createQuery("Select l from com.company.Listings l where id =:id", Listings.class);
        query.setParameter("id", itemId);

        Listings edit = null;
        try {
            edit = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("blogai");
        }
        edit.setReport(false);

        Transaction tx = session.beginTransaction();
        session.update(edit);
        tx.commit();
    }

}

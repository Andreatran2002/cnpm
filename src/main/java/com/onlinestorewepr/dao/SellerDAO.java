package com.onlinestorewepr.dao;

import com.onlinestorewepr.entity.Cart;
import com.onlinestorewepr.entity.Product;
import com.onlinestorewepr.entity.Seller;
import com.onlinestorewepr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SellerDAO {
    public void insert(Seller seller) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(seller);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void update(Seller seller) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(seller);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    public Seller getBySellerName(String name){
        Seller seller = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "SELECT c FROM Seller c WHERE c.sellerName = :name";
            Query query = session.createQuery(HQL);
            query.setParameter("name", name);
            List<Seller> sellers = query.getResultList();
            if (!sellers.isEmpty()) {
                seller = sellers.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seller;
    }

    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Seller seller = session.get(Seller.class, id);
            if (seller != null) {
                session.delete(seller);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<Seller> getAll() {
        List<Seller> sellers = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Seller> criteriaQuery = builder.createQuery(Seller.class);
            criteriaQuery.from(Seller.class);
            sellers = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sellers;
    }

    public Seller get(int id) {
        Seller seller = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            seller = session.get(Seller.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return seller;
    }
}

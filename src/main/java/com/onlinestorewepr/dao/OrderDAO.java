package com.onlinestorewepr.dao;

import com.onlinestorewepr.entity.Order;
import com.onlinestorewepr.entity.Product;
import com.onlinestorewepr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrderDAO {
   public void insert(Order order) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         session.save(order);

         transaction.commit();
      } catch (Exception e) {
         e.printStackTrace();
         if (transaction != null) {
            transaction.rollback();
         }
      }
   }

   public void update(Order order) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         session.update(order);

         transaction.commit();
      } catch (Exception e) {
         e.printStackTrace();
         if (transaction != null) {
            transaction.rollback();
         }
      }
   }
   public List<Order> getByUser(String username){
      List<Order> orders = null;

      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         String HQL = "SELECT c FROM Order c WHERE c.user.id = :username";
         Query query = session.createQuery(HQL);
         query.setParameter("username", username);
         orders = query.getResultList();

      } catch (Exception e) {
         e.printStackTrace();
      }
      return orders;
   }
   public List<Order> getOrderFromSelelr(int sellerId){
      List<Order> orders = null;

      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         String HQL = "SELECT Order FROM OrderItem c inner join Order on Order.id = c.order.id inner join Product  p on p.id = c.product.id where p.seller.id = :sellerId  group by Order.id";
         Query query = session.createQuery(HQL);
         query.setParameter("sellerId", 1);
         orders = query.getResultList();

      } catch (Exception e) {
         e.printStackTrace();
      }
      return orders;
   }

   public void delete(int id) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         Order order = session.get(Order.class, id);
         if (order != null) {
            session.delete(order);
         }

         transaction.commit();
      } catch (Exception e) {
         e.printStackTrace();
         if (transaction != null) {
            transaction.rollback();
         }
      }
   }

   public List<Order> getAll() {
      List<Order> orders = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
         criteriaQuery.from(Order.class);
         orders = session.createQuery(criteriaQuery).getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return orders;
   }

   public Order get(int id) {
      Order order = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {

         order = session.get(Order.class, id);

      } catch (Exception e) {
         e.printStackTrace();
      }
      return order;
   }
}

package com.onlinestorewepr.dao;

import com.onlinestorewepr.entity.OrderItem;
import com.onlinestorewepr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrderItemDAO {
   public void insert(OrderItem orderItem) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         session.save(orderItem);

         transaction.commit();
      } catch (Exception e) {
         e.printStackTrace();
         if (transaction != null) {
            transaction.rollback();
         }
      }
   }

   public void update(OrderItem orderItem) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         session.update(orderItem);

         transaction.commit();
      } catch (Exception e) {
         e.printStackTrace();
         if (transaction != null) {
            transaction.rollback();
         }
      }
   }

   public void delete(int id) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         OrderItem orderItem = session.get(OrderItem.class, id);
         if (orderItem != null) {
            session.delete(orderItem);
         }

         transaction.commit();
      } catch (Exception e) {
         e.printStackTrace();
         if (transaction != null) {
            transaction.rollback();
         }
      }
   }

   public List<OrderItem> getAll() {
      List<OrderItem> orderItems = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<OrderItem> criteriaQuery = builder.createQuery(OrderItem.class);
         criteriaQuery.from(OrderItem.class);
         orderItems = session.createQuery(criteriaQuery).getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return orderItems;
   }

   public OrderItem get(int id) {
      OrderItem orderItem = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {

         orderItem = session.get(OrderItem.class, id);

      } catch (Exception e) {
         e.printStackTrace();
      }
      return orderItem;
   }
}

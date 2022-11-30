package com.onlinestorewepr.dao;

import com.onlinestorewepr.entity.Category;
import com.onlinestorewepr.entity.Product;
import com.onlinestorewepr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
   public void insert(Product product) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         session.save(product);

         transaction.commit();
      } catch (Exception e) {
         e.printStackTrace();
         if (transaction != null) {
            transaction.rollback();
         }
      }
   }
   public List<Product> getByCategory(int cateid){
      List<Product> products = null;

      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         String HQL = "SELECT c FROM Product c WHERE c.category.id = :cateid";
         Query query = session.createQuery(HQL);
         query.setParameter("cateid", cateid);
         products = query.getResultList();

      } catch (Exception e) {
         e.printStackTrace();
      }
      return products;
   }


   public void update(Product product) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         session.update(product);

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

         Product product = session.get(Product.class, id);
         if (product != null) {
            session.delete(product);
         }

         transaction.commit();
      } catch (Exception e) {
         e.printStackTrace();
         if (transaction != null) {
            transaction.rollback();
         }
      }
   }

   public List<Product> getAll() {
      List<Product> products = null;
      System.out.println("products");

      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
         criteriaQuery.from(Product.class);
         products = session.createQuery(criteriaQuery).getResultList();
         System.out.println(products);

      } catch (Exception e) {
         e.printStackTrace();
      }
      return products;
   }
   public List<Product> getProductPaging(int offset, int limit) {
      List<Product> products = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
         criteriaQuery.from(Product.class);
         products = session.createQuery(criteriaQuery).setMaxResults(limit).setFirstResult(offset).getResultList();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return products;
   }

   public Product get(int id) {
      Product product = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {

         product = session.get(Product.class, id);

      } catch (Exception e) {
         e.printStackTrace();
      }
      return product;
   }

   public Product findByName(String name) {
      Product product = null;

      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         String HQL = "SELECT c FROM Product c WHERE c.name = :name";
         Query query = session.createQuery(HQL);
         query.setParameter("name", name);
         List<Product> products = query.getResultList();
         if (!products.isEmpty()) {
            product = products.get(0);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }


      return product;
   }
   public List<Product> searchByName(String key){
      List<Product> products = null;

      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
         String HQL = "SELECT c FROM Product c WHERE c.name like :name";
         Query query = session.createQuery(HQL);
         query.setParameter("name", "%"+key+"%");
          products = query.getResultList();

      } catch (Exception e) {
         e.printStackTrace();
      }


      return products;
   }
}

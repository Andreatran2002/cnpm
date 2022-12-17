package com.onlinestorewepr.dao;

import com.onlinestorewepr.entity.Product;
import com.onlinestorewepr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProductDAO {
    public int count = 0;

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

    public List<Product> getByCategory(int cateid) {
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


    public List<Product> getBySeller(int selelrid) {
        List<Product> products = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "SELECT c FROM Product c WHERE c.seller.id = :selelrid";
            Query query = session.createQuery(HQL);
            query.setParameter("selelrid", selelrid);
            products = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getBestSeller() {
        List<Product> products = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "SELECT c.product FROM OrderItem c group by c.product order by count(c.id) desc ";
            Query query = session.createQuery(HQL);
            products = query.setMaxResults(8).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getNewArrival() {
        List<Product> products = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "SELECT c FROM Product c order by c.id desc ";
            Query query = session.createQuery(HQL);
            products = query.setMaxResults(4).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getHotSale() {
        List<Product> products = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "SELECT c FROM Product c where c.discount != 0  order by c.discount  ";
            Query query = session.createQuery(HQL);
            products = query.setFirstResult(4).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getTopbyCategory(int CategoryID) {
        List<Product> products = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "SELECT c FROM Product c WHERE category.id = :CategoryID";
            Query query = session.createQuery(HQL);
            query.setParameter("CategoryID", CategoryID);
            products = query.setMaxResults(4).getResultList();
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
        count = products.size();
        return products;
    }

    public List<Product> getProductPaging(int offset, int limit, String cateid, String size, String name, double from, double to, int orderType) {
        List<Product> products = null;
        Query query = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "";
            if (cateid != "") {
                switch (orderType) {
                    case 0:
                        HQL = "select c from Product c where c.category.id= :cateid and c.name like :name and  c.size like :size and c.price >= :price_from and c.price < :price_to ORDER BY c.id";

                        break;
                    case 1:
                        HQL = "select c from Product c where c.category.id= :cateid and c.name like :name and c.size like :size  and c.price >= :price_from and c.price < :price_to ORDER BY c.price ASC ";

                        break;
                    case 2:
                        HQL = "select c from Product c where c.category.id= :cateid and c.name like :name and c.size like :size  and c.price >= :price_from and c.price < :price_to ORDER BY c.price DESC ";
                        break;
                    default:
                        HQL = "select c from Product c where c.category.id= :cateid and c.name like :name and c.size like :size  and c.price >= :price_from and c.price < :price_to ORDER BY c.id";
                        break;
                }
                query = session.createQuery(HQL);
                query.setParameter("cateid", Integer.parseInt(cateid));

            } else {
                switch (orderType) {
                    case 0:
                        HQL = "select c from Product c where   c.name like :name and  c.size like :size and c.price >= :price_from and c.price < :price_to ORDER BY c.id";

                        break;
                    case 1:
                        HQL = "select c from Product c where c.name like :name and c.size like :size  and c.price >= :price_from and c.price < :price_to ORDER BY c.price ASC ";

                        break;
                    case 2:
                        HQL = "select c from Product c where c.name like :name and c.size like :size  and c.price >= :price_from and c.price < :price_to ORDER BY c.price DESC ";
                        break;
                    default:
                        HQL = "select c from Product c where c.name like :name and c.size like :size  and c.price >= :price_from and c.price < :price_to ORDER BY c.id";
                        break;
                }
                query = session.createQuery(HQL);

            }


            query.setParameter("size", "%" + size + "%");
            query.setParameter("price_from", from);
            query.setParameter("price_to", to);
            query.setParameter("name", "%" + name + "%");
            count = query.getResultList().size();


            products = query.setMaxResults(limit).setFirstResult(offset).getResultList();


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

}

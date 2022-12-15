package com.onlinestorewepr.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer sellerId;
    private String sellerName;
    private String image;
    private Integer status;

    @OneToOne(mappedBy = "seller")
    private User user;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product>  products;

    public Seller(Integer sellerId, String sellerName, String image, Integer status) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.image = image;
        this.status = status;
    }

    public Seller(Integer sellerId, String sellerName, String image, Integer status, User user) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.image = image;
        this.status = status;
        this.user = user;
    }

    public Seller(Integer sellerId, String sellerName, String image, Integer status, User user, List<Product> products) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.image = image;
        this.status = status;
        this.user = user;
        this.products = products;
    }

    public Seller() {
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

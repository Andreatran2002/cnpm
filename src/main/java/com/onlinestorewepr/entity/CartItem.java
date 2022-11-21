package com.onlinestorewepr.entity;

import javax.persistence.*;

@Entity
@Table(name = "cartItem")
public class CartItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column
   private int id;
   @Column
   private int quantity;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "productId", referencedColumnName = "id")
   private Product product;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "cartId", referencedColumnName = "id")
   private Cart cart;

   public CartItem(int quantity, Product product, Cart cart) {
      this.quantity = quantity;
      this.product = product;
      this.cart = cart;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public Cart getCart() {
      return cart;
   }

   public void setCart(Cart cart) {
      this.cart = cart;
   }
}

package com.onlinestorewepr.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column
   private int id;
   private Double total=0.0;

   @OneToOne(mappedBy = "cart")
   private User user;

   @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
   private List<CartItem> cartItems;

   public Cart(Double total, User user) {
      this.total = total;
      this.user = user;
   }

   public Cart() {

   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Double getTotal() {
      return total;
   }

   public void setTotal(Double total) {
      this.total = total;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public List<CartItem> getCartItems() {
      return cartItems;
   }

   public void setCartItems(List<CartItem> cartItems) {
      this.cartItems = cartItems;
   }
}

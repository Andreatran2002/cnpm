<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
   <meta charset="UTF-8">
   <meta name="description" content="Male_Fashion Template">
   <meta name="keywords" content="Male_Fashion, unica, creative, html">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <title>Male-Fashion | Template</title>

   <!-- Google Font -->
   <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
         rel="stylesheet">

   <!-- Css Styles -->
   <link rel="stylesheet" href="./assets/css/bootstrap.min.css" type="text/css">
   <link rel="stylesheet" href="./assets/css/font-awesome.min.css" type="text/css">
   <link rel="stylesheet" href="./assets/css/elegant-icons.css" type="text/css">
   <link rel="stylesheet" href="./assets/css/magnific-popup.css" type="text/css">
   <link rel="stylesheet" href="./assets/css/nice-select.css" type="text/css">
   <link rel="stylesheet" href="./assets/css/owl.carousel.min.css" type="text/css">
   <link rel="stylesheet" href="./assets/css/slicknav.min.css" type="text/css">
   <link rel="stylesheet" href="./assets/css/style.css" type="text/css">
</head>

<body>
<!-- Header Section Begin-->
<jsp:include page="header.jsp" />
<!-- Header Section End -->

<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
   <div class="container">
      <div class="row">
         <div class="col-lg-12">
            <div class="breadcrumb__text">
               <h4>Shopping Cart</h4>
               <div class="breadcrumb__links">
                  <a href="./index.html">Home</a>
                  <a href="shop.jsp">Shop</a>
                  <span>Shopping Cart</span>
               </div>
            </div>
         </div>
      </div>
   </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Shopping Cart Section Begin -->
<section class="shopping-cart spad">
   <div class="container">
      <div class="row">
         <div class="col-lg-8">
            <div class="shopping__cart__table">
               <table>
                  <thead>
                  <tr>
                     <th>Product</th>
                     <th>Quantity</th>
                     <th>Total</th>
                     <th></th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                     <td class="product__cart__item">
                        <div class="product__cart__item__pic">
                           <img src="./assets/img/shopping-cart/cart-1.jpg" alt="">
                        </div>
                        <div class="product__cart__item__text">
                           <h6>T-shirt Contrast Pocket</h6>
                           <h5>$98.49</h5>
                        </div>
                     </td>
                     <td class="quantity__item">
                        <div class="quantity">
                           <div class="pro-qty-2">
                              <input type="text" value="1">
                           </div>
                        </div>
                     </td>
                     <td class="cart__price">$ 30.00</td>
                     <td class="cart__close"><i class="fa fa-close"></i></td>
                  </tr>
                  <tr>
                     <td class="product__cart__item">
                        <div class="product__cart__item__pic">
                           <img src="./assets/img/shopping-cart/cart-2.jpg" alt="">
                        </div>
                        <div class="product__cart__item__text">
                           <h6>Diagonal Textured Cap</h6>
                           <h5>$98.49</h5>
                        </div>
                     </td>
                     <td class="quantity__item">
                        <div class="quantity">
                           <div class="pro-qty-2">
                              <input type="text" value="1">
                           </div>
                        </div>
                     </td>
                     <td class="cart__price">$ 32.50</td>
                     <td class="cart__close"><i class="fa fa-close"></i></td>
                  </tr>
                  <tr>
                     <td class="product__cart__item">
                        <div class="product__cart__item__pic">
                           <img src="./assets/img/shopping-cart/cart-3.jpg" alt="">
                        </div>
                        <div class="product__cart__item__text">
                           <h6>Basic Flowing Scarf</h6>
                           <h5>$98.49</h5>
                        </div>
                     </td>
                     <td class="quantity__item">
                        <div class="quantity">
                           <div class="pro-qty-2">
                              <input type="text" value="1">
                           </div>
                        </div>
                     </td>
                     <td class="cart__price">$ 47.00</td>
                     <td class="cart__close"><i class="fa fa-close"></i></td>
                  </tr>
                  <tr>
                     <td class="product__cart__item">
                        <div class="product__cart__item__pic">
                           <img src="./assets/img/shopping-cart/cart-4.jpg" alt="">
                        </div>
                        <div class="product__cart__item__text">
                           <h6>Basic Flowing Scarf</h6>
                           <h5>$98.49</h5>
                        </div>
                     </td>
                     <td class="quantity__item">
                        <div class="quantity">
                           <div class="pro-qty-2">
                              <input type="text" value="1">
                           </div>
                        </div>
                     </td>
                     <td class="cart__price">$ 30.00</td>
                     <td class="cart__close"><i class="fa fa-close"></i></td>
                  </tr>
                  </tbody>
               </table>
            </div>
            <div class="row">
               <div class="col-lg-6 col-md-6 col-sm-6">
                  <div class="continue__btn">
                     <a href="#">Continue Shopping</a>
                  </div>
               </div>
               <div class="col-lg-6 col-md-6 col-sm-6">
                  <div class="continue__btn update__btn">
                     <a href="#"><i class="fa fa-spinner"></i> Update cart</a>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-lg-4">
            <div class="cart__discount">
               <h6>Discount codes</h6>
               <form action="#">
                  <input type="text" placeholder="Coupon code">
                  <button type="submit">Apply</button>
               </form>
            </div>
            <div class="cart__total">
               <h6>Cart total</h6>
               <ul>
                  <li>Subtotal <span>$ 169.50</span></li>
                  <li>Total <span>$ 169.50</span></li>
               </ul>
               <a href="#" class="primary-btn">Proceed to checkout</a>
            </div>
         </div>
      </div>
   </div>
</section>
<!-- Shopping Cart Section End -->

<!-- Footer Section Begin -->
<jsp:include page="footer.jsp" />
<!-- Footer Section End -->

<!-- Search Begin -->
<div class="search-model">
   <div class="h-100 d-flex align-items-center justify-content-center">
      <div class="search-close-switch">+</div>
      <form class="search-model-form">
         <input type="text" id="search-input" placeholder="Search here.....">
      </form>
   </div>
</div>
<!-- Search End -->

<!-- Js Plugins -->
<script src="./assets/js/jquery-3.3.1.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
<script src="./assets/js/jquery.nice-select.min.js"></script>
<script src="./assets/js/jquery.nicescroll.min.js"></script>
<script src="./assets/js/jquery.magnific-popup.min.js"></script>
<script src="./assets/js/jquery.countdown.min.js"></script>
<script src="./assets/js/jquery.slicknav.js"></script>
<script src="./assets/js/mixitup.min.js"></script>
<script src="./assets/js/owl.carousel.min.js"></script>
<script src="./assets/js/main.js"></script>
</body>

</html>
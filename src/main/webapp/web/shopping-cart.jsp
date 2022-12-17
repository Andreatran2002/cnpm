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
  <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap" rel="stylesheet">

  <!-- Css Styles -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/bootstrap.min.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/elegant-icons.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/magnific-popup.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/nice-select.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/owl.carousel.min.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/slicknav.min.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/style.css" type="text/css">

  <style>
     input.line-quantity {
        font-size: 17px !important;
        font-weight: 700;
     }
     input[type=number] {
        height: 30px;
     }

     input[type=number]:hover::-webkit-inner-spin-button {
        width: 14px;
        height: 30px;
     }

     td input[type="checkbox"] {
        margin-right: 24px;
        transform: scale(1.5);
        accent-color: #333333;
     }
  </style>
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
            <a href="${pageContext.request.contextPath}/web/index.jsp">Home</a>
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
              <th></th>
              <th>Product</th>
              <th>Quantity</th>
              <th>Total</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cartItems}" var="cartItem">
              <tr data-cartitem-id="${cartItem.id}" data-product-id="${cartItem.product.id}" data-max-quantity="${cartItem.product.quantity}" data-price="${cartItem.product.price}" data-discount="${cartItem.product.discount}" data-quantity="${cartItem.quantity}">
                <td><input type="checkbox" class="line-choose" id=""></td>
                <td class="product__cart__item">
                  <div class="product__cart__item__pic">
                    <img width="105px" src="${cartItem.product.image}" alt="cartItemPicture">
                  </div>
                  <div class="product__cart__item__text">
                    <h6>${cartItem.product.name} </h6>
                    <h6>${cartItem.product.color} ${cartItem.product.size}</h6>
                    <c:if test="${cartItem.product.discount != 0}">
                      <span style="font-size: 18px; font-weight: 700;">$${cartItem.product.discount}</span>
                      <span style="text-decoration: line-through; color: gray;">$${cartItem.product.price}</span>
                    </c:if>
                    <c:if test="${cartItem.product.discount == 0}">
                      <h5>$${cartItem.product.price}</h5>
                    </c:if>
                  </div>
                </td>
                <td class="quantity__item">
                  <div class="quantity">
                    <div class="pro-qty-2">
                      <input class="line-quantity" onchange="(e => updateItem(${cartItem.id}, e.target.value))(event)" name="input_quantity" type="number" min="0" value="${cartItem.quantity}">
                    </div>
                  </div>
                </td>
                <td class="cart__price">$${cartItem.product.discount == 0 ? cartItem.product.price * cartItem.quantity : cartItem.product.discount * cartItem.quantity}</td>
                <td class="cart__close">
                  <form
                          onsubmit="return confirm('Are you sure to delete this item?');"
                          action="<%request.getContextPath();%>/cart-item/delete"
                          method="post"
                  >
                    <input type="hidden" name="id" value="${cartItem.id}">
                    <button>
                      <i class="fa fa-close" ></i>
                    </button>
                  </form></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        <div class="row">
          <div class="col-lg-6 col-md-6 col-sm-6">
            <div class="continue__btn">
              <a href="${pageContext.request.contextPath}/shop">Continue Shopping</a>
            </div>
          </div>
          <div class="col-lg-6 col-md-6 col-sm-6">
            <div class="continue__btn update__btn">
              <a href=""><i class="fa fa-spinner"></i> Update cart</a>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-4">
        <div class="cart__total h-100">
          <h6 class="font-weight-bold text-decoration-underline">Cart total</h6>
          <ul>
            <li id="provisional">Provisional <span style="font-size: 16px;">0</span></li>
            <li id="discount">Discount <span style="font-size: 14px; font-weight: 600; color: gray; text-decoration: line-through;">0</span></li>
            <li id="total">Total <span>0</span></li>
          </ul>
          <a id="checkout-button" href="javascript:" class="primary-btn">Proceed to checkout</a>
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
<script src="${pageContext.request.contextPath}/web/assets/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/web/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/web/assets/js/jquery.nice-select.min.js"></script>
<script src="${pageContext.request.contextPath}/web/assets/js/jquery.nicescroll.min.js"></script>
<script src="${pageContext.request.contextPath}/web/assets/js/jquery.magnific-popup.min.js"></script>
<script src="${pageContext.request.contextPath}/web/assets/js/jquery.countdown.min.js"></script>
<script src="${pageContext.request.contextPath}/web/assets/js/mixitup.min.js"></script>
<script src="${pageContext.request.contextPath}/web/assets/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/web/assets/js/main.js"></script>
<script src="${pageContext.request.contextPath}/web/assets/js/shopping-cart.js"></script>

<script>
  <%--$('input[name=inputquantity]').change(function(e) {--%>
  <%-- updateItem(${cartItem.id},e.target.value);--%>
  <%--});--%>
  // functino
  function updateItem( id, quantity){

    $.ajax({
      url : "/cart-item/update", //send to Controller
      type : "get", //send it through get method
      data : {
        id : id,
        quantity : quantity
      },
      success : function(data) {
      },
      error : function(xhr) {
//Do Something to handle error
        console.log(xhr)
      }
    });

  }
  function deleteCartItem( id){

    $.ajax({
      url : "/cart-item/delete", //send to Controller
      type : "get", //send it through get method
      data : {
        id : id,
      },
      success : function(data) {
      },
      error : function(xhr) {
//Do Something to handle error
        console.log(xhr)
      }
    });

  }
</script>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
   <meta charset="utf-8" />
   <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
   <title>Your Orders</title>
   <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/seller/assets/img/favicon/favicon.ico" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/assets/css/style.css" type="text/css">
</head>
<body>
<jsp:include page="./header.jsp" />
  <!-- Layout wrapper -->
  <div class="content-wrapper">
      <div class="container-xxl flex-grow-1 container-p-y container mb-4">
          <h2 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"> </span>My Orders</h2>
          <div class="card">
              <h5 class="card-header">Orders</h5>
              <div class="table-responsive text-nowrap">
                  <table class="table table-hover">
                      <thead>
                      <tr>
                          <th>ID</th>
                          <th>Date</th>
                          <th>Customer</th>
                          <th>Products </th>
                          <th>Total</th>

                          <th>Status</th>
                      </tr>
                      </thead>
                      <tbody class="table-border-bottom-0">
                      <c:forEach items="${orders}" var="order" varStatus="status">
                          <tr>
                              <td>${order.id}</td>
                              <td>${order.getCreatedDate()}</td>
                              <td>${order.name}</td>
                              <td><i class="fab fa-angular fa-lg text-danger"></i> <strong>
                                  <c:forEach items = "${order.orderItems}" var="orderItem">
                                      ${orderItem.product.name},
                                  </c:forEach>
                              </strong></td>
                              <td>${order.total}</td>
                              <td ><span class="badge bg-label-success me-1">${order.status}</span></td>
                          </tr>
                      </c:forEach>

                      </tbody>
                  </table>
              </div>
          </div>
      </div>

      <jsp:include page="./footer.jsp" />
  </div>
<script>
    document.getElementById('menu-order').classList.add('active')
</script>
</body>
</html>

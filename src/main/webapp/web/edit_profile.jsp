<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
  <meta charset="UTF-8">
  <meta name="description" content="Male_Fashion Template">
  <meta name="keywords" content="Male_Fashion, unica, creative, html">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Chỉnh sửa thông tin</title>

  <!-- Google Font -->
  <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
        rel="stylesheet">

  <!-- Css Styles -->
  <link rel="stylesheet" href="./web/assets/css/bootstrap.min.css" type="text/css">
  <link rel="stylesheet" href="./web/assets/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="./web/assets/css/elegant-icons.css" type="text/css">
  <link rel="stylesheet" href="./web/assets/css/magnific-popup.css" type="text/css">
  <link rel="stylesheet" href="./web/assets/css/nice-select.css" type="text/css">
  <link rel="stylesheet" href="./web/assets/css/owl.carousel.min.css" type="text/css">
  <link rel="stylesheet" href="./web/assets/css/slicknav.min.css" type="text/css">
  <link rel="stylesheet" href="./web/assets/css/style.css" type="text/css">
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
          <h4>Thông tin cá nhân, đơn hàng</h4>
          <div class="breadcrumb__links">
            <a href="index.jsp">Home</a>
            <span>Chỉnh sửa thông tin cá nhân</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Contact Section Begin -->
<div class="container">
    <div class="spad">
      <div class="row">
        <div class="col-lg-4">
          <div class="card">
            <div class="card-body">
              <div class="d-flex flex-column align-items-center text-center">
                <img src="${userLogged.image}" alt="Admin" class="rounded-circle" width="150">
                <div class="mt-3">
                  <h4>${userLogged.name}</h4>
                  <p class="text-muted font-size-sm">${userLogged.address}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-8">
          <div class="card">
            <form class="card-body" method="post" action="update-profile">
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">Username</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" id="user-name" name="username" disabled value="${userLogged.username}">
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">Họ & tên</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" id="customer-name" name="name" value="${userLogged.name}">
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">Phone</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" id="phone" name="phone"value="${userLogged.phone}">
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">Giới tính</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" id="customer-sex" name="sex" value="${userLogged.gender}">
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">Địa chỉ</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" id="customer-address" name="address" value="${userLogged.address}">
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-9 text-secondary">
                  <button type="submit" class="submit btn btn-info">Lưu thay đổi</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

<!-- Contact Section End -->

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

<!-- Active menu -->
<script>
  document.getElementById('menu-contact').classList.add('active')
</script>

<!-- Js Plugins -->
<script src="./web/assets/js/jquery-3.3.1.min.js"></script>
<script src="./web/assets/js/bootstrap.min.js"></script>
<script src="./web/assets/js/jquery.nice-select.min.js"></script>
<script src="./web/assets/js/jquery.nicescroll.min.js"></script>
<script src="./web/assets/js/jquery.magnific-popup.min.js"></script>
<script src="./web/assets/js/jquery.countdown.min.js"></script>
<script src="./web/assets/js/jquery.slicknav.js"></script>
<script src="./web/assets/js/mixitup.min.js"></script>
<script src="./web/assets/js/owl.carousel.min.js"></script>
<script src="./web/assets/js/main.js"></script>
</body>

</html>
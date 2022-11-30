<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Thông tin cá nhân</title>

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
                    <h4>Thông tin cá nhân, đơn hàng</h4>
                    <div class="breadcrumb__links">
                        <a href="index.jsp">Home</a>
                        <span>Thông tin cá nhân, đơn hàng</span>
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
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex flex-column align-items-center text-center" id="profile-head">
                                <img src="${userLogged.image}" alt="Admin" class="rounded-circle" width="150">
                                <div class="mt-3">
                                    <h4>${userLogged.name}</h4>
                                    <p class="text-muted font-size-sm">${userLogged.address}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card mb-3 user-profile">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Họ & tên</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">${userLogged.name}</div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Số điện thoại</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${userLogged.phone}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Giới tính</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${userLogged.gender}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Địa chỉ</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${userLogged.address}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-6">
                                    <a class="btn btn-info" target="" href="update-profile">Sửa thông tin</a>
                                </div>
                                <div class="col-sm-6">
                                    <a class="btn btn-info" target="" href="change-password">Đổi mật khẩu</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
<%--            Hiển thị đơn hàng đã mua--%>
<%--            <div class="row">--%>
<%--                <div class="container mt-5">--%>
<%--                    <div class="d-flex justify-content-center row">--%>
<%--                        <div class="col-md-10">--%>
<%--                            <div class="rounded">--%>
<%--                                <div class="table-responsive table-borderless">--%>
<%--                                    <table class="table">--%>
<%--                                        <thead>--%>
<%--                                        <tr>--%>
<%--                                            <th class="text-center">--%>
<%--                                                <div class="toggle-btn">--%>
<%--                                                    <div class="inner-circle"></div>--%>
<%--                                                </div>--%>
<%--                                            </th>--%>
<%--                                            <th>Mã đơn hàng</th>--%>
<%--                                            <th>Company name</th>--%>
<%--                                            <th>Trạng thái</th>--%>
<%--                                            <th>Tổng tiền</th>--%>
<%--                                            <th>Ngày đặt hàng</th>--%>
<%--                                            <th></th>--%>
<%--                                        </tr>--%>
<%--                                        </thead>--%>
<%--                                        <tbody class="table-body">--%>
<%--                                        <tr class="cell-1">--%>
<%--                                            <td class="text-center">--%>
<%--                                                <div class="toggle-btn">--%>
<%--                                                    <div class="inner-circle"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>#SO-13487</td>--%>
<%--                                            <td>Gasper Antunes</td>--%>
<%--                                            <td><span class="badge badge-success">Fullfilled</span></td>--%>
<%--                                            <td>$2674.00</td>--%>
<%--                                            <td>Today</td>--%>
<%--                                            <td><button>Hủy đơn hàng</button></td>--%>
<%--                                        </tr>--%>
<%--                                        <tr class="cell-1">--%>
<%--                                            <td class="text-center">--%>
<%--                                                <div class="toggle-btn">--%>
<%--                                                    <div class="inner-circle"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>#SO-13453</td>--%>
<%--                                            <td>Aartsen van</td>--%>
<%--                                            <td><span class="badge badge-info">Confirmed</span></td>--%>
<%--                                            <td>$3454.00</td>--%>
<%--                                            <td>Yesterday</td>--%>
<%--                                            <td><button>Hủy đơn hàng</button></td>--%>
<%--                                        </tr>--%>
<%--                                        <tr class="cell-1">--%>
<%--                                            <td class="text-center">--%>
<%--                                                <div class="toggle-btn">--%>
<%--                                                    <div class="inner-circle"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>#SO-13498</td>--%>
<%--                                            <td>Trashes Habard</td>--%>
<%--                                            <td><span class="badge badge-danger">Partially shipped</span></td>--%>
<%--                                            <td>$6274.00</td>--%>
<%--                                            <td>May 12,2020</td>--%>
<%--                                            <td><button>Hủy đơn hàng</button></td>--%>
<%--                                        </tr>--%>
<%--                                        <tr class="cell-1">--%>
<%--                                            <td class="text-center">--%>
<%--                                                <div class="toggle-btn">--%>
<%--                                                    <div class="inner-circle"></div>--%>
<%--                                                </div>--%>
<%--                                            </td>--%>
<%--                                            <td>#SO-16499</td>--%>
<%--                                            <td>Samban Hubart</td>--%>
<%--                                            <td><span class="badge badge-success">Fullfilled</span></td>--%>
<%--                                            <td>$6375.00</td>--%>
<%--                                            <td>May 11,2020</td>--%>
<%--                                            <td><button>Hủy đơn hàng</button></td>--%>
<%--                                        </tr>--%>
<%--                                        </tbody>--%>
<%--                                    </table>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
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
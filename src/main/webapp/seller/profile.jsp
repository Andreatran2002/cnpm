<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
    <title>Account Profile</title>
    <link rel="icon" type="image/x-icon" href="./assets/img/favicon/favicon.ico"/>
    <link href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet"/>
    <link rel="stylesheet" href="./assets/vendor/fonts/boxicons.css"/>
    <link rel="stylesheet" href="./assets/vendor/css/core.css" class="template-customizer-core-css"/>
    <link rel="stylesheet" href="./assets/vendor/css/theme-default.css" class="template-customizer-theme-css"/>
    <link rel="stylesheet" href="./assets/css/demo.css"/>
    <link rel="stylesheet" href="./assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css"/>
    <link rel="stylesheet" href="./assets/vendor/libs/apex-charts/apex-charts.css"/>
    <script src="./assets/vendor/js/helpers.js"></script>
    <script src="./assets/js/config.js"></script>
</head>
<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <jsp:include page="./menu.jsp"/>

        <!-- Layout container -->
        <div class="layout-page">
            <!-- Navbar -->
            <jsp:include page="./navbar.jsp"/>
            <!-- / Navbar -->
            <!-- Content wrapper -->
            <div class="content-wrapper">
                <!-- Content -->

                <div class="container-xxl flex-grow-1 container-p-y">
                    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Account /</span> Profile</h4>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="card mb-4">
                                <h5 class="card-header">Profile Details</h5>
                                <!-- Account -->
                                <div class="card-body">
                                    <div class="d-flex align-items-center align-items-sm-center gap-4">
                                        <img
                                                src="./assets/img/avatars/1.png"
                                                alt="user-avatar"
                                                class="d-block rounded"
                                                height="100"
                                                width="100"
                                                id="uploadedAvatar"
                                        />

                                        <div class="button-wrapper">
                                            <label for="upload" class="btn btn-primary me-2" tabindex="0">
                                                <span class="d-none d-sm-block">Upload new photo</span>
                                                <i class="bx bx-upload d-block d-sm-none"></i>
                                                <input
                                                        type="file"
                                                        id="upload"
                                                        class="account-file-input"
                                                        hidden
                                                        accept="image/png, image/jpeg"
                                                />
                                            </label>
                                            <button type="button" class="btn btn-outline-secondary account-image-reset">
                                                <i class="bx bx-reset d-block d-sm-none"></i>
                                                <span class="d-none d-sm-block">Reset</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <hr class="my-0"/>

                                <div class="card-body">
                                    <%--                  <form>--%>
                                    <%--                    <div class="row mb-3">--%>
                                    <%--                      <label class="col-sm-2 col-form-label" for="basic-default-name">Shop name</label>--%>
                                    <%--                      <div class="col-sm-10">--%>
                                    <%--                        <input type="text" class="form-control" id="" value="" placeholder="Display name" />--%>
                                    <%--                      </div>--%>
                                    <%--                    </div>--%>
                                    <%--                    <div class="row mb-3">--%>
                                    <%--                      <label class="col-sm-2 col-form-label" for="">Username</label>--%>
                                    <%--                      <div class="col-sm-10">--%>
                                    <%--                        <span class="form-control"></span>--%>
                                    <%--                      </div>--%>
                                    <%--                    </div>--%>
                                    <%--                    <div class="row mb-3">--%>
                                    <%--                      <label class="col-sm-2 col-form-label" for="basic-default-name">Password</label>--%>
                                    <%--                      <div class="col-sm-10">--%>
                                    <%--                        <input type="password" class="form-control" id="basic-default-name" value="12345" placeholder="Password" />--%>
                                    <%--                      </div>--%>
                                    <%--                    </div>--%>
                                    <%--                    <div class="row justify-content-end">--%>
                                    <%--                      <div class="col-sm-10" style="text-align: end;">--%>
                                    <%--                        <button type="submit" class="btn btn-primary me-3">SAVE</button>--%>
                                    <%--                      </div>--%>
                                    <%--                    </div>--%>
                                    <%--                  </form>--%>
                                    <div class="spad">
                                        <div class="row">
                                            <div class="col-md-4 mb-3">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <div class="d-flex flex-column align-items-center text-center"
                                                             id="profile-head">
<%--                                                            <c:choose>--%>
<%--                                                                <c:when test="${userLogged.image!=null}"><img--%>
<%--                                                                        alt="Image Profile"--%>
<%--                                                                        src="${pageContext.request.contextPath}/${userLogged.image}"--%>
<%--                                                                        class="rounded-circle" width="150"></c:when>--%>
<%--                                                                <c:otherwise><img--%>
<%--                                                                        src="assets/img/profile/no-avartar.png"--%>
<%--                                                                        alt="Admin" class="rounded-circle"--%>
<%--                                                                        width="150"></c:otherwise>--%>
<%--                                                            </c:choose>--%>
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
                                                                <h6 class="mb-0">Full name</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">${userLogged.name}</div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">Phone number</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                ${userLogged.phone}
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">Email</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                ${userLogged.email}
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">Gender</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                ${userLogged.gender}
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">Address</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                ${userLogged.address}
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <a class="btn btn-info" target="" href="update-profile">Edit
                                                                    Profile</a>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <a class="btn btn-info" target=""
                                                                   href="change-password">Change password</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Account -->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- / Content -->
                <jsp:include page="footer.jsp"/>
                <!-- Content wrapper -->
            </div>
            <!-- / Layout page -->
        </div>

        <!-- Overlay -->
        <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->
</div>

<!-- Core JS -->
<!-- build:js assets/vendor/js/core.js -->
<script src="./assets/vendor/libs/jquery/jquery.js"></script>
<script src="./assets/vendor/libs/popper/popper.js"></script>
<script src="./assets/vendor/js/bootstrap.js"></script>
<script src="./assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

<script src="./assets/vendor/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->

<!-- Main JS -->
<script src="./assets/js/main.js"></script>

<!-- Active Menu Item -->
<script>
    document.getElementById('menu-account').classList.add('active', 'open')
    document.getElementById('menu-account-profile').classList.add('active')
</script>
</body>
</html>

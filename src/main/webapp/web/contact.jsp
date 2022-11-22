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

<!-- Map Begin -->
<div class="map">
   <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d15673.937203378548!2d106.7719223!3d10.8507214!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x282f711441b6916f!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBTxrAgcGjhuqFtIEvhu7kgdGh14bqtdCBUcC4gSOG7kyBDaMOtIE1pbmg!5e0!3m2!1svi!2s!4v1669107638415!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
   </div>
<!-- Map End -->

<!-- Contact Section Begin -->
<section class="contact spad">
   <div class="container">
      <div class="row">
         <div class="col-lg-6 col-md-6">
            <div class="contact__text">
               <div class="section-title">
                  <span>Information</span>
                  <h2>Contact Us</h2>
                  <p>As you might expect of a company that began as a high-end interiors contractor, we pay
                     strict attention.</p>
               </div>
               <ul>
                  <li>
                     <h4>Viet Nam</h4>
                     <p>Số 1 Võ Văn Ngân<br />+89 888888888</p>
                  </li>

               </ul>
            </div>
         </div>
         <div class="col-lg-6 col-md-6">
            <div class="contact__form">
               <form action="#">
                  <div class="row">
                     <div class="col-lg-6">
                        <input type="text" placeholder="Name">
                     </div>
                     <div class="col-lg-6">
                        <input type="text" placeholder="Email">
                     </div>
                     <div class="col-lg-12">
                        <textarea placeholder="Message"></textarea>
                        <button type="submit" class="site-btn">Send Message</button>
                     </div>
                  </div>
               </form>
            </div>
         </div>
      </div>
   </div>
</section>
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
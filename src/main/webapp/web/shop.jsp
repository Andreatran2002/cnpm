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
   <link rel="stylesheet" href="<c:url value='./web/assets/css/bootstrap.min.css'/>" type="text/css">
   <link rel="stylesheet" href="<c:url value='./web/assets/css/font-awesome.min.css'/>" type="text/css">
   <link rel="stylesheet" href="<c:url value='./web/assets/css/elegant-icons.css'/>" type="text/css">
   <link rel="stylesheet" href="<c:url value='./web/assets/css/magnific-popup.css'/>" type="text/css">
   <link rel="stylesheet" href="<c:url value='./web/assets/css/nice-select.css'/>" type="text/css">
   <link rel="stylesheet" href="<c:url value='./web/assets/css/owl.carousel.min.css'/>" type="text/css">
   <link rel="stylesheet" href="<c:url value='./web/assets/css/slicknav.min.css'/>" type="text/css">
   <link rel="stylesheet" href="<c:url value='./web/assets/css/style.css'/>" type="text/css">
   <link rel="stylesheet" href="<c:url value='./web/assets/css/header.css'/>" type="text/css">
   <link rel="stylesheet" href="<c:url value='./web/assets/css/base.css'/>" type="text/css">
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
               <h4>Shop</h4>
               <div class="breadcrumb__links">
                  <a href="./index.html">Home</a>
                  <span>Shop</span>
               </div>
            </div>
         </div>
      </div>
   </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Shop Section Begin -->
<section class="shop spad">
   <div class="container">
      <div class="row">
         <div class="col-lg-3">
            <div class="shop__sidebar">
               <div class="shop__sidebar__search">
                  <form action="">
                     <input  type="text" placeholder="Search..." id="search_key">
                     <button type="submit"><span class="icon_search"></span></button>
                  </form>
               </div>
               <div class="shop__sidebar__accordion">
                  <div class="accordion" id="accordionExample">
                     <div class="card">
                        <div class="card-heading">
                           <a data-toggle="collapse" data-target="#collapseOne">Categories</a>
                        </div>
                        <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                           <div class="card-body">
                              <div class="shop__sidebar__categories">
                                 <ul class="nice-scroll">
                                    <c:forEach items="${categories}" var="cate" varStatus="status">
                                    <li><a href="#" onclick="triggerFilter(event ,'cateid', ${cate.id})">${cate.name}</a></li>
                                    </c:forEach>

                                 </ul>
                              </div>
                           </div>
                        </div>
                     </div>
<%--                     <div class="card">--%>
<%--                        <div class="card-heading">--%>
<%--                           <a data-toggle="collapse" data-target="#collapseTwo">Branding</a>--%>
<%--                        </div>--%>
<%--                        <div id="collapseTwo" class="collapse show" data-parent="#accordionExample">--%>
<%--                           <div class="card-body">--%>
<%--                              <div class="shop__sidebar__brand">--%>
<%--                                 <ul>--%>
<%--                                    <c:forEach items="${brands}" var="brand" varStatus="status">--%>
<%--                                       <li><a href="${pageContext.request.contextPath}/products?brand=${brand}">${brand}</a></li>--%>
<%--                                    </c:forEach>--%>
<%--                                 </ul>--%>
<%--                              </div>--%>
<%--                           </div>--%>
<%--                        </div>--%>
<%--                     </div>--%>
                     <div class="card">
                        <div class="card-heading">
                           <a data-toggle="collapse" data-target="#collapseThree">Filter Price</a>
                        </div>
                        <div id="collapseThree" class="collapse show" data-parent="#accordionExample">
                           <div class="card-body">
                              <div class="shop__sidebar__price">
                                 <ul>
                                    <li><a href="#" onclick="triggerFilter(event,'price', 1)">$0.00 - $50.00</a></li>
                                    <li><a href="#" onclick="triggerFilter(event,'price',2)">$50.00 - $100.00</a></li>
                                    <li><a href="#" onclick="triggerFilter(event,'price', 3)">$100.00 - $150.00</a></li>
                                    <li><a href="#" onclick="triggerFilter(event,'price', 4)">$150.00 - $200.00</a></li>
                                    <li><a href="#" onclick="triggerFilter(event,'price', 5)">$200.00 - $250.00</a></li>
                                    <li><a href="#" onclick="triggerFilter(event,'price', 6)">250.00+</a></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="card">
                        <div class="card-heading">
                           <a data-toggle="collapse" data-target="#collapseFour">Size</a>
                        </div>
                        <div id="collapseFour" class="collapse show" data-parent="#accordionExample">
                           <div class="card-body">
                              <div class="shop__sidebar__size">
                                 <label for="xs">xs
                                    <input type="radio" id="xs" onclick="triggerFilter(event, 'size', 'xs')">
                                 </label>
                                 <label for="sm">s
                                    <input type="radio" id="sm" onclick="triggerFilter(event, 'size', 'sm')">
                                 </label>
                                 <label for="m">m
                                    <input type="radio" id="m" onclick="triggerFilter(event, 'size', 'm')">
                                 </label>
                                 <label for="xl">xl
                                    <input type="radio" id="xl" onclick="triggerFilter(event, 'size', 'xl')">
                                 </label>
                                 <label for="2xl">2xl
                                    <input type="radio" id="2xl" onclick="triggerFilter(event, 'size', '2xl')">
                                 </label>

                                 <label for="3xl">3xl
                                    <input type="radio" id="3xl" onclick="triggerFilter(event, 'size', '3xl')">
                                 </label>
                                 <label for="4xl">4xl
                                    <input type="radio" id="4xl" onclick="triggerFilter(event, 'size', '4xl')">
                                 </label>
                              </div>
                           </div>
                        </div>
                     </div>
<%--                     <div class="card">--%>
<%--                        <div class="card-heading">--%>
<%--                           <a data-toggle="collapse" data-target="#collapseFive">Colors</a>--%>
<%--                        </div>--%>
<%--                        <div id="collapseFive" class="collapse show" data-parent="#accordionExample">--%>
<%--                           <div class="card-body">--%>
<%--                              <div class="shop__sidebar__color">--%>
<%--                                 <label class="c-1" for="sp-1">--%>
<%--                                    <input type="radio" id="sp-1">--%>
<%--                                 </label>--%>
<%--                                 <label class="c-2" for="sp-2">--%>
<%--                                    <input type="radio" id="sp-2">--%>
<%--                                 </label>--%>
<%--                                 <label class="c-3" for="sp-3">--%>
<%--                                    <input type="radio" id="sp-3">--%>
<%--                                 </label>--%>
<%--                                 <label class="c-4" for="sp-4">--%>
<%--                                    <input type="radio" id="sp-4">--%>
<%--                                 </label>--%>
<%--                                 <label class="c-5" for="sp-5">--%>
<%--                                    <input type="radio" id="sp-5">--%>
<%--                                 </label>--%>
<%--                                 <label class="c-6" for="sp-6">--%>
<%--                                    <input type="radio" id="sp-6">--%>
<%--                                 </label>--%>
<%--                                 <label class="c-7" for="sp-7">--%>
<%--                                    <input type="radio" id="sp-7">--%>
<%--                                 </label>--%>
<%--                                 <label class="c-8" for="sp-8">--%>
<%--                                    <input type="radio" id="sp-8">--%>
<%--                                 </label>--%>
<%--                                 <label class="c-9" for="sp-9">--%>
<%--                                    <input type="radio" id="sp-9">--%>
<%--                                 </label>--%>
<%--                              </div>--%>
<%--                           </div>--%>
<%--                        </div>--%>
<%--                     </div>--%>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-lg-9">

            <div class="shop__product__option">
               <div class="row">
                  <div class="col-lg-6 col-md-6 col-sm-6">
                     <div class="shop__product__option__left">
                        <p> ${typesearch}</p>
                     </div>
                  </div>
                  <div id='infoFilter'></div>
                  <div class="col-lg-6 col-md-6 col-sm-6">
                     <div class="shop__product__option__right">
                        <p>Sort by Price:</p>
                        <select id="sortOption" onchange="(e => triggerFilter(e, 'sort' ,e.target.value))(event)">
                           <option value="0">Default</option>
                           <option value="1">Low To High</option>
                           <option value="2">High To Low</option>
                        </select>
                     </div>
                  </div>
               </div>
            </div>
            <div class="row product-data-list" id="product-content">

               <c:forEach items="${products}" var="product" varStatus="status">
                  <div class="col-lg-4 col-md-6 col-sm-6">
                     <div class="product__item ${product.discount>0?"sale":""}">
                        <div class="product__item__pic set-bg">
                           <img class="" src="${product.image}" style="object-fit: contain"/>

                        </div>

                        <div class="product__item__text">
                           <h6>${product.name}</h6>
                           <a href="#" class="add-cart" onclick="addtocart(${product.id})">+ Add To Cart</a>
                           <div class="rating">
                              <i class="fa fa-star-o"></i>
                              <i class="fa fa-star-o"></i>
                              <i class="fa fa-star-o"></i>
                              <i class="fa fa-star-o"></i>
                              <i class="fa fa-star-o"></i>
                           </div>
                           <h5>${product.price}</h5>
                           <div class="product__color__select">

                              <label class="active ${product.color}" for="pc-5">
                                 <input type="radio" id="pc-5">
                              </label>

                           </div>
                        </div>
                     </div>
                  </div>
               </c:forEach>


            </div>
            <div class="row">
               <div class="col-lg-12">
                  <div class="product__pagination">
                        <c:forEach begin="0" end="${countP}"  varStatus="status">
                        <a class="pageindex" onclick="triggerFilter(event,'page', ${status.index + 1})" href="">${status.index + 1}</a>
                     </c:forEach>

                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</section>
<!-- Shop Section End -->

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
    document.getElementById('menu-shop').classList.add('active')
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

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
   const queryString = window.location.search;
   const urlParams = new URLSearchParams(queryString);
   const inputSearch = document.querySelector("#search_key")
   const infoFilter = document.querySelector("#infoFilter")
   const sortOption = document.querySelector("#sortOption")

   // $("#search_key").on('change keydown paste input', function(){
   //       console.log($("#search_key").val())
   //    removeElementsByClass("product__item")
   // });


   $(window).scroll(function() {
      if($(window).scrollTop() + $(window).height() >= $(document).height()){

         loadMore();
      }
   });
//    function searchItem( key){
//       removeElementsByClass("product__item");
//
//       $.ajax({
//          url : "/load-product-ajax", //send to Controller
//          type : "get", //send it through get method
//          data : {
//             exits : amount
//          },
//          success : function(data) {
//             $("#product-content").append(data);
//          },
//          error : function(xhr) {
// //Do Something to handle error
//             console.log(xhr)
//          }
//       });
//    }
   function addtocart( productId){

      $.ajax({
         url : "/cart-item/add", //send to Controller
         type : "get", //send it through get method
         data : {
            productid : productId ,
            quantity : 1
         },
         success : function(data) {
            $("#product-content").append(data);
         },
         error : function(xhr) {
//Do Something to handle error
            console.log(xhr)
         }
      });
   }


   function loadMore() {


      if (!urlParams.has('cateid') && !urlParams.has('key') && !urlParams.has('size') && !urlParams.has('color')){
         /* tạo viên amount để Gọi và đếm classname là product */
         var amount = document.getElementsByClassName("product__item").length;

         $.ajax({
            url : "/load-product-ajax", //send to Controller
            type : "get", //send it through get method
            data : {
               exist : amount
            },
            success : function(data) {
               $("#product-content").append(data);
            },
            error : function(xhr) {
//Do Something to handle error
               console.log(xhr)
            }
         });
      }

   };

   function removeElementsByClass(className){
      const elements = document.getElementsByClassName(className);
      while(elements.length > 0){
         elements[0].parentNode.removeChild(elements[0]);
      }
   }

   let queryObject = {}

   const getQueryObject = () => {
      queryObject = queryString.substring(1).split("&").reduce((prev, curr) => {

         if(curr === "")
            return prev
         const key = curr.split("=")[0]
         const value = curr.split("=")[1]

         const infoElement = document.createElement('p', )
         return {...prev, [key]: value}
      },{})


      inputSearch.value = decodeURI(queryObject?.key|| '')

      const optionValue = document.querySelector(".current")
      const optionArr = ["Default", "Low To High", "High To Low"]
      optionValue.innerHTML = optionArr[queryObject?.sort|| 0]

   }

   getQueryObject()

   function triggerFilter(e, key, value) {
      e.preventDefault()

      if(key !== "page")       delete queryObject["page"]


      queryObject = {...queryObject, [key]: value}


      const queryString = Object.keys(queryObject).reduce((prev, curr) => {

         return prev + '&' + curr + "=" + queryObject[curr]
      },'')

      console.log(queryString)
      window.location.search = queryString
   }


   inputSearch.addEventListener("keydown", (e) => {
      if(e.keyCode === 13) {
         triggerFilter(e, 'key', e.target.value)
      }
   })
</script>
</body>

</html>
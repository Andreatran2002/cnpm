<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>Đăng nhập/Đăng ký</title>
   <link rel="stylesheet" href="./assets/css/authentication.css">
</head>
<body>
<div class="wrapper">
   <div class="cont">
      <form class="form sign-in" method="post" action="login">
         <h2>WELCOME TO MALE FASHION</h2>
         <div class="input-control">
            <input name="username" id="name" type="text" placeholder="Username" value="${username}"/>
         </div>
         <div class="input-control">
            <input name="password" id="pass" type="password" placeholder="Password" value="${password}"/>
         </div>
         <div class="form-group text-center">
            <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
            <label for="remember"> Remember Me</label>
         </div>
         <p class="message-text">${message}</p>
         <p class="forgot-pass">Quên mật khẩu?</p>
         <button type="submit" class="submit">Đăng nhập</button>
      </form>
      <div class="sub-cont">
         <div class="img">
            <div class="img__text m--up">
               <h2>Bạn chưa có tài khoản?</h2>
               <p>Đăng ký ngay nào!</p>
            </div>
            <div class="img__text m--in">
               <h2>One of us?</h2>
               <p>Nếu bạn đã có tài khoản,vui lòng đăng nhập tại đây!</p>
            </div>
            <div class="img__btn">
               <span class="m--up">Đăng ký</span>
               <span class="m--in">Đăng nhập</span>
            </div>
         </div>
         <form class="form sign-up" method="post" action="register">
            <h2>ĐĂNG KÝ TÀI KHOẢN</h2>
            <div class="input-control">
               <input name="fullName" id="nameRe" type="text" placeholder="Họ và tên"/>
            </div>
            <div class="input-control">
               <input name="usernameNew" id="usernameR" type="text" placeholder="Tên đăng nhập"/>
            </div>
            <div class="input-control">
               <input name="phone" id="phone" type="text" placeholder="Số điện thoại"/>
            </div>
            <div class="input-control">
               <input name="passwordNew" id="passwd" type="password"  placeholder="Mật khẩu"/>
            </div>
            <div class="input-control">
               <input name="passwordNewRetype" id="passwdRetype" type="password"  placeholder="Nhập lại Mật khẩu"/>
            </div>
            <div class="input-control radio">
                  <span>
                     <input type="radio" name="gender" id="gender-male" value="Name">
                     <label for="gender-male">Nam</label>
                  </span>
               <span>
                     <input type="radio" name="gender" id="gender-female" value="Nữ">
                     <label for="gender-female">Nữ</label>
                  </span>
               <span>
                     <input type="radio" name="gender" id="gender-others" value="Khác">
                     <label for="gender-others">Khác</label>
                  </span>
            </div>
            <c:choose>
               <c:when test="${messageRegisterFail!=null}"><p class="message-text-fail">${messageRegisterFail}</p></c:when>
               <c:otherwise><p class="message-text-success">${messageRegisterSuccess}</p></c:otherwise>
            </c:choose>
            <button type="submit" class="submit">Đăng ký</button>
         </form>
      </div>
   </div>
</div>
<script>
   window.addEventListener('load', () => {
      document.querySelector('.img__btn').addEventListener('click', function() {
         document.querySelector('.cont').classList.toggle('s--signup');
      });
   })
</script>
</body>
</html>

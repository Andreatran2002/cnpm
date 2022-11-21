<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
   <title>Đăng nhập</title>
   <link rel="stylesheet" href="./assets/css/authentication.css">
</head>
<body>
<div class="wrapper">
   <div class="cont">
      <div class="form sign-in">
         <h2>WELCOME BACK</h2>
         <div class="input-control">
            <input type="email" placeholder="Username"/>
         </div>
         <div class="input-control">
            <input type="password" placeholder="Password"/>
         </div>
         <p class="forgot-pass">Forgot password?</p>
         <button type="button" class="submit">Sign In</button>
      </div>
      <div class="sub-cont">
         <div class="img">
            <div class="img__text m--up">
               <h2>New here?</h2>
               <p>Sign up and discover great amount of new opportunities!</p>
            </div>
            <div class="img__text m--in">
               <h2>One of us?</h2>
               <p>If you already has an account, just sign in. We've missed you!</p>
            </div>
            <div class="img__btn">
               <span class="m--up">Sign Up</span>
               <span class="m--in">Sign In</span>
            </div>
         </div>
         <div class="form sign-up">
            <h2>TIME TO FEEL LIKE HOME</h2>
            <div class="input-control">
               <input type="text" placeholder="Name"/>
            </div>
            <div class="input-control">
               <input type="text" placeholder="Phone"/>
            </div>
            <div class="input-control">
               <input type="password" placeholder="Password"/>
            </div>
            <div class="input-control radio">
                  <span>
                     <input type="radio" name="gender" id="gender-male">
                     <label for="gender-male">Male</label>
                  </span>
               <span>
                     <input type="radio" name="gender" id="gender-female">
                     <label for="gender-female">Female</label>
                  </span>
               <span>
                     <input type="radio" name="gender" id="gender-others">
                     <label for="gender-others">Others</label>
                  </span>
            </div>
            <button type="button" class="submit">Sign Up</button>
         </div>
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

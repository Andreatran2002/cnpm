<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
    <div class="app-brand demo">
        <a href="${pageContext.request.contextPath}/seller/index.jsp" class="app-brand-link">
         <h4 class="app-brand-logo demo">
             Sale Management
         </h4>
        </a>
        <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
            <i class="bx bx-chevron-left bx-sm align-middle"></i>
        </a>
    </div>
    <div class="menu-inner-shadow"></div>
    <ul class="menu-inner py-1">
        <!-- Menu Items -->
<%--        <li class="menu-item" id="menu-overview">--%>
<%--            <a href="${pageContext.request.contextPath}/seller/index.jsp" class="menu-link">--%>
<%--                <i class="menu-icon tf-icons bx bx-home-circle"></i>--%>
<%--                <div data-i18n="Analytics">Overview</div>--%>
<%--            </a>--%>
<%--        </li>--%>
        <li class="menu-item" id="menu-managements">
            <a href="javascript:void(0);" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-layout"></i>
                <div data-i18n="Managements">Managements</div>
            </a>
            <ul class="menu-sub">
                <li class="menu-item" id="menu-managements-products">
                    <a href="${pageContext.request.contextPath}/seller/product" class="menu-link">
                        <div data-i18n="Without navbar">Products</div>
                    </a>
                </li>
                <li class="menu-item" id="menu-managements-orders">
                    <a href="${pageContext.request.contextPath}/seller/orders" class="menu-link">
                        <div data-i18n="Without navbar">Orders</div>
                    </a>
                </li>

            </ul>
        </li>
        <li class="menu-item" id="menu-account">
            <a href="" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-dock-top"></i>
                <div data-i18n="Account Settings">Account</div>
            </a>
            <ul class="menu-sub">
                <li class="menu-item" id="menu-account-profile">
                    <a href="${pageContext.request.contextPath}/seller/account-profile.jsp" class="menu-link">
                        <div data-i18n="Account">Profile</div>
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</aside>
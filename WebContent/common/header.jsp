<%@page import="java.util.List"%>
<%@page import="com.slpl.web.service.community.*"%>
<%@page import="com.slpl.web.entity.community.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- reset을 제일 위에 -->
<link rel="stylesheet" href="../../assets/css/reset.css" type="text/css">
<link rel="stylesheet" href="../../assets/css/common/style.css" type="text/css">

<script type="text/javascript">
window.addEventListener("load", function() {
	var loginDiv = document.querySelector(".login");
	var logoutDiv = document.querySelector(".logout");

	if(loginDiv != null) {
		loginDiv.addEventListener("click", function(e){
			location.href="/common/login";
	    });
	}
	
	if(logoutDiv != null) {
		logoutDiv.addEventListener("click", function(e){
			location.href="/common/logout";
	    });
	}
});

</script>	
</head>

<body>
	<header id="header">
		<div class="top_header">
			<div class="top-header-container">
				<span>mypage</span>
	       		<c:choose>
	       			<c:when test="${login == null}">
	       				<span class="login">login</span>
	       			</c:when>
	       			<c:otherwise>
	       				<%-- <div class="profile-img" style="background: center / cover no-repeat url('/admin/common/profile?id=${login.id}');"></div> --%>   <!-- 물리주소 안됨 프로젝트로부터 갖고와야됨 -->
	       				<span class="logout">logout</span>
	       				<div class="loginName-box">
		       				<span class="loginName">${login.name}</span>
		       				<span>님 반가워요!</span>
	       				</div>
	       			</c:otherwise>
	       		</c:choose>
			</div>
		</div>
		<div id="main_header" class="main_header">
			<ul class="main_nav">
				<a href="#"><img src="../../assets/images/common/logo.png"alt="심리플리 로고"></a>
				<li><a href="/test/make/formSelect.jsp">MAKE</a></li>
				<li><a href="/test/list/test_list">TEST</a></li>
				<li><a href="/community/all/list?c=전체게시판">COMMUNITY</a></li>
			</ul>
		</div>
	</header>






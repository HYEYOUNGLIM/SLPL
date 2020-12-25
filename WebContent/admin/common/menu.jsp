<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.addEventListener("load", function() {
	var acc = document.getElementsByClassName("first-menu");
	var i;

	for (i = 0; i < acc.length; i++) {
	  acc[i].addEventListener("click", function() {
	    this.classList.toggle("active");
	    var panel = this.nextElementSibling;
	    if (panel.style.maxHeight) {
	      panel.style.maxHeight = null;
	    } else {
	      panel.style.maxHeight = panel.scrollHeight + "px";
	    } 
	  });
	}
});
</script>
</head>
<body>
<nav>
	    <h1 class="d-none">메인 메뉴</h1>
	    <ul class="main-menu">
	        <li>
	            <div class="first-menu">
	                <img class="menu-img" src="<%=request.getContextPath()%>/assets/images/admin/admin_menu_member.png" alt="회원 관리"/> 
	                <a href="#">MEMBER</a>    
	            </div>           
	            <ul class="second-menu">
	                <li><a href="/admin/member/list">회원 목록</a></li>
	                <li><a href="/admin/member/add">회원 추가</a></li>
	                <li><a href="/admin/member/message/list">메세지 목록</a></li>
	            </ul>
	        </li>
	        <li>
	            <div class="first-menu">
	            	<img class="menu-img" src="<%=request.getContextPath()%>/assets/images/admin/admin_menu_community.png" alt="게시판 관리"/> 
	                <!-- <img class="menu-img" src="../../assets/images/admin/admin_menu_community.png" alt="게시판 관리"/>  -->
	                <a href="#">COMMUNITY</a>    
	            </div>                    
	             <ul class="second-menu">
	             	<li><a href="/admin/community/category/list">카테고리 관리</a></li>
	             	<li><a href="/admin/community/report/list">신고 관리</a></li>
	             	<li><a href="/admin/community/notice/list">공지사항</a></li>
	             </ul>
	        </li>
	        <li>
	            <div class="first-menu">
	                <img class="menu-img" src="<%=request.getContextPath()%>/assets/images/admin/admin_menu_test.png" alt="테스트 관리"/> 
	                <a href="#">TEST</a>
	            </div>                    
	            <ul class="second-menu">
	                <li><a href="/admin/test/list/test-list">전체</a></li>
	                <li><a href="/admin/test/best/test">인기 테스트 </a></li>
	                <li><a href="/admin/test/report/list">신고 테스트</a></li>
	            </ul>
	        </li>
	    </ul>
	</nav> 
</body>
</html>
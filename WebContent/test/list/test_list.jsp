<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.slpl.web.service.test.TestListViewService" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Document</title>
   <!-- reset을 제일 위에 -->
   <link rel="stylesheet" href="../../assets/css/reset.css" type="text/css">
   <link rel="stylesheet" href="../../assets/css/test/test_list.css" type="text/css">
   <script src="../../assets/js/test/list/test_list.js"></script>

</head>

<body>
   <!------------------------------------------- 헤더 -------------------------------------------------->
    <jsp:include page="../../common/header.jsp"></jsp:include> 
   

   <section id="body" class="body">
      <div class="container">
         <main id="main" class="main">
            <!------------------------------------------- 메뉴  -------------------------------------------------->
                <jsp:include page="../../common/nav.jsp"></jsp:include>

            <section class="content">
               <div class="content-box">
                  <div class="recommend-test">
                     <h3>심리플리 추천 테스트</h3>
                     <ul>
                     	<c:forEach var="n" items="${bestList }" begin="7" end="9">
                     	<a href="../play/action?id=${n.id }">
	                     	<li>
	                           <img src="/test/listImg?id=${n.id}" alt="표지">
	                           <div>${n.name }</div>
	                        </li>
                        </a>
                        </c:forEach>
                        <c:forEach var="n" items="${bestList }">
                     	<a href="../play/action?id=${n.id }">
	                     	<li>
	                           <img src="/test/listImg?id=${n.id}" alt="표지">
	                           <div>${n.name }</div>
	                        </li>
                        </a>
                        </c:forEach>
                        <c:forEach var="n" items="${bestList }" begin="0" end="2">
                     	<a href="../play/action?id=${n.id }">
	                     	<li>
	                           <img src="/test/listImg?id=${n.id}" alt="표지">
	                           <div>${n.name }</div>
	                        </li>
                        </a>
                        </c:forEach>
                     </ul>
                  </div>
                  <form class="filter">
                     <span class="order">
                        <select name="a">
                            <option value="REGDATE">최신순</option>
							<option value="HIT_CNT">조회순</option>
                            <option value="RECOMMEND_CNT">추천순</option>
                        </select>
                     </span>
                     <span class="category">
                        <select name="c">
                           <option value="NAME">테스트 명</option>
                           <option value="NICKNAME">제작자</option>
                           <option value="ID">테스트 번호</option>
                        </select>
                        <input type="text" name="k" class="search-keyword" placeholder="검색어 입력">
                        <input type="submit" class="puple-button-m" name="search" value="검색">
                     </span>
                  </form>
                  <div class="test-list">
                     <ul>
                        <c:forEach var="n" items="${list }" begin="0" end="3">
                        <a href="../play/action?id=${n.id }">
	                     	<li>
	                           <img src="/test/listImg?id=${n.id}" alt="표지">
	                           <div>${n.name }</div>
	                        </li>
                        </a>
                        </c:forEach>
                     </ul>
                     <ul>
                        <c:forEach var="n" items="${list }" begin="4" end="7">
                     	<a href="../play/action?id=${n.id }">
	                     	<li>
	                           <img src="/test/listImg?id=${n.id}" alt="표지">
	                           <div>${n.name }</div>
	                        </li>
                        </a>
                        </c:forEach>
                     </ul>
                  </div>


                  <c:set var="page" value="${(param.p == null)?1:param.p }"/>
                        <c:set var="startNum" value="${page-(page-1)%5}"  />
                        <div class="page">
                            <div class="link">
                            	<c:if test="${startNum>1 }">
                                <a class="prev" href="test_list?p=${startNum-1}}&c=${param.c}&k=${param.k}&a=${param.a}">이전</a>
                                </c:if>
                                <c:if test="${startNum<=1 }">
                               	<a class="prev">이전</a>
                                </c:if>
                                <c:forEach var="i" begin="0" end="4">
                                <a class="page-num" href="test_list?p=${startNum+i}&c=${param.c}&k=${param.k}&a=${param.a}">${startNum+i}</a>
                                </c:forEach>
                                <a class="next" href="test_list?p=${startNum+5}&c=${param.c}&k=${param.k}&a=${param.a}">다음</a>
                            </div>  
                        </div>


               </div>
            </section>
         </main>
      </div>
   </section>
   <!------------------------------------------- 풋터  -------------------------------------------------->
    <jsp:include page="../../common/footer.jsp"></jsp:include> 

</body>
</html>
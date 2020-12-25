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
    <title>관리자 - 회원관리</title>
    <link href="../../../assets/css/reset.css" type="text/css" rel="stylesheet">
    <link href="../../../assets/css/admin/style.css" type="text/css" rel="stylesheet">
    <link href="../../../assets/css/admin/test-list.css" type="text/css" rel="stylesheet">
</head>
<body>    
    <jsp:include page="../../common/header.jsp"></jsp:include>

    <div id="body" class="body" >  
        <jsp:include page="../../common/menu.jsp"></jsp:include>
       

        <main class="main">
            <section class="container">
                <h1 class="d-none"> 회원관리 </h1> 

                <!-- container-item클래스가 꽉 채워진 박스 기준이라 min-width만 설정해주기 위해 member-container를 같이 넣어줌-->            
                <section class="container-item member-container">             
                    <h1> <a href="test-list">테스트 목록</a> </h1> 

                    <div class="tools">
                        
                        <section class="filter">
                            <h1 class="d-none">필터</h1>
                            <form action="test-list">
                                <select class="category" name="a">
                                    <option value="REGDATE">정렬 기준</option>
                                    <option value="ID">번호</option>
                            		<option value="NAME">테스트 명</option>
                                    <option value="HIT_CNT">조회수</option>
                            		<option value="RECOMMEND_CNT">추천수</option>
                                    <option value="SHARE_CNT">공유수</option>
                            		<option value="REGDATE">등록일</option>
                                    <option value="PUBLIC_STATE">공개여부</option>
                            		<option value="LEV_NAME">난이도</option>
                                    <option value="CATE_NAME">유형</option>
                            	</select>
                            	<select class="category" name="o">
                                    <option value="DESC">내림차순</option>
                            		<option value="ASC">오름차순</option>
                            	</select>
                            	<select class="category" name="pl">
                            		<option value="10">10개</option>
                                    <option value="20">20개</option>
                                    <option value="50">50개</option>
                                    <option value="100">100개</option>
                            	</select>
                            	<select class="category" name="c">
                                    <option value="NAME">테스트 명</option>
                                    <option value="NICKNAME">제작자</option>
                                    <option value="ID">테스트 번호</option>
                                </select>
                                <input type="hidden" name="o" value="0">
                                <input type="text" name="k" class="search-keyword" placeholder="검색어 입력">
                                <input type="submit" class="gray-button-m" value="검색"> 
                            </form>
                        </section>   
            
                    </div>
                    
                        
                    <section class="test-list">
                        <h1 class="d-none">테스트 목록</h1>
                        <table>
                            <thead>
                                <tr>
                                    <th>번호</th>
                                    <th>테스트명</th>
                                    <th>제작자</th>
                                    <th>조회수</th>
                                    <th>추천수</th>
                                    <th>공유수</th>
                                    <th>등록일</th>
                                    <th>공개 여부</th>
                                    <th>난이도</th>
                                    <th>유형</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="n" items= "${list}">
                            	<c:set var="state" value="${(n.publicState == 1)?'공개':'비공개' }"/>
                                <tr>
                                    <td>${n.id }</td>
                                    <td>${n.name }</td>
                                    <td>${n.nickname }</td>
                                    <td>${n.hitCnt }</td>
                                    <td>${n.recommendCnt }</td>
                                    <td>${n.shareCnt }</td>
                                    <td>${n.regdate }</td>
                                    <td>${state }</td>
                                    <td>${n.levName }</td>
                                    <td>${n.cateName }</td>
                                </tr>
                                </c:forEach>      
                            </tbody>
                           
                            <div class="detail-position"></div>
                        </table>
						
						<c:set var="page" value="${(param.p == null)?1:param.p }"/>
                        <c:set var="startNum" value="${page-(page-1)%5}"  />
                        <div class="page">
                            <div class="link">
                            	<c:if test="${startNum>1 }">
                                <a class="prev" href="test-list?p=${startNum-1}&pl=${param.pl}&c=${param.c}&k=${param.k}&a=${param.a}&o=${param.o}">이전</a>
                                </c:if>
                                <c:if test="${startNum<=1 }">
                               	<a class="prev">이전</a>
                                </c:if>
                                <c:forEach var="i" begin="0" end="4">
                                <a class="page-num" href="test-list?p=${startNum+i}&pl=${param.pl}&c=${param.c}&k=${param.k}&a=${param.a}&o=${param.o}">${startNum+i}</a>
                                </c:forEach>
                                <a class="next" href="test-list?p=${startNum+5}&pl=${param.pl}&c=${param.c}&k=${param.k}&a=${param.a}&o=${param.o}">다음</a>
                            </div>  
                        </div>
                    </section>
                </section >
            </section>
            
            
            <!------------------------------------------- 풋터  -------------------------------------------------->
			<jsp:include page="../../common/footer.jsp"></jsp:include>

        </main>

    </div>

</body>
</html>
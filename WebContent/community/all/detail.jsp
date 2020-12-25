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
<link rel="stylesheet" href="../../assets/css/common/style.css"
	type="text/css">
<link rel="stylesheet" href="../../assets/css/community/community.css"
	type="text/css">



<script type="text/javascript">
	window.onload = function() {
		console.log("시작");
		var recommendBtn = document.getElementsByClassName("recommend-button")[0];
		var heart = document.getElementsByClassName("heart")[0];
		var regBtn = document.getElementsByClassName("reg-button")[0];
		var commentBox = document.getElementsByClassName("input-reply")[0];
		//recommendBtn.comment-form //visibility:hidden; heart-click-check
		var commentForm = document.getElementsByClassName("comment-form")[0];
		var heartClick = document.getElementsByClassName("heart-click-check")[0];
		regBtn.onclick = function(e) {
			e.preventDefault();
			if (commentBox.value == "") {
				alert("댓글을 입력해주세요");
				return false;
			} else {
				commentForm.submit();
			}
		}
		//visibility: hidden;
		recommendBtn.onclick = function(e) {
			console.log("하트 보여줘");
			e.preventDefault();
			if (heartClick.value == "click2") {
				heart.style.visibility = "hidden";
				heartClick.value = "click";
				console.log(heartClick.value);
			} else if (heartClick.value == "click") {
				heart.style.visibility = "visible";
				heartClick.value = "click2";
				console.log(heartClick.value);
			}
		}
		var reportAdd = document.getElementById("report-add");
		reportAdd.onclick = function() {
			var win = open("report?id=${n.id}", "_blank",
					"width=350px, height=370px");
		};
	}
</script>

</head>

<body>
	<jsp:include page="../../common/header.jsp" />
	<section id="body" class="body">


		<div class="container">
			<main id="main" class="main">
				<jsp:include page="../../common/nav.jsp" />
				<section class="content detail">
					<div class="content-box">
						<div class="content-box-top">
							<div class="detail-list">
								<a class="a-input" href="list?c=${param.c}">목록</a>
								<!-- <a
									class="a-input" href="">이전글</a> <a class="a-input" href="">다음글</a> -->
							</div>
							<div class="detail-update">
								<a class="a-input-black" href="update?c=${param.c}&id=${n.id}">수정</a>
								<a class="a-input-black" href="delete?c=${param.c}&id=${n.id}">삭제</a>
								<a id="report-add" class="a-input-black">신고</a>
							</div>
						</div>


						<div class="breadcrumbs">
							<ul>
								<li>community&nbsp></li>
								<li>all</li>
							</ul>
						</div>

						<h3 class="hidden">내용</h3>
						<span class="detail-title">${n.title}</span>
						<div class="cover-info">${n.memberNick}&nbsp${n.regDate}&nbsp
							${n.hitCnt}</div>
						<div class="board-post">${n.content}</div>
						<div class="hidden">${n.categoryId }</div>


						<div class="recommend-box">
							<div class="recommend-button">
								<input class="hidden heart-click-check" value="click" /> <img
									class="heart" src="/assets/images/community/heart.png">
								<!-- <div class="box"></div> -->

								<img class="hand" src="/assets/images/community/hand.png">
								<span class="recommen-cnt">${n.recommendCnt}</span>
							</div>
						</div>


						<h3 class="hidden">댓글</h3>

						<div class="reply-post">
							<form method="post" class="comment-form">
								<input class="input-reply" type="text" min="1" max="200"
									placeholder="댓글작성(최소 1자 이상  최대 200자 이하로 적어주세요 )" name="comment">
								<input class="button reg-button" type="submit" value="등록">
							</form>
						</div>

						<div class="reply-view">
							<div class="reply-count">댓글 ${n.commentCnt}</div>
							<c:forEach var="c" items="${comment}">

								<div class="comment-info">
									<img class="comment-info-img"
										src="/assets/images/community/dog.jpg" />

									<div>
										<span class="comment-info-nick">${c.nickName}</span> <span
											class="comment-info-reg">${c.regdate}</span>
										<div class="comment-info-content">${c.content}</div>
									</div>
								</div>
							</c:forEach>


						</div>

					</div>

				</section>
			</main>
		</div>
	</section>
	<!--//content -->
	<jsp:include page="../../common/footer.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">

<head>

<meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"> -->

<style>

body {

padding-top: px;

padding-bottom: 30px;

}

</style>
<script>
	$(document).on('click', '#btnWriteForm', function(e){
		e.preventDefault();  //기본기능을 삭제  submit , reset , a
		location.href = "boardWrite";  //a태그와 유사하게 동작 (이건 팝업을 띄우고 그걸 실행하도록 해줌.)
	});

</script>
	<!-- reset -->
	<link rel="stylesheet" href="resources/normalize.css" >
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>board</title>
</head>

<body>


<article>
<div class="container">
<h2>board list</h2>
<div class="table-responsive">

<!-- 검색 -->
<div class = "pull-right">
<form action="boardList" method="get"> 
<select name="searchItem" style="height:25px;">
   <option value="title" ${searchItem=='title'?'selected' :''}>제목</option>
   <option value="userid" ${searchItem=='userid'?'selected' :''}>글쓴이</option>
   <option value="content" ${searchItem=='content'?'selected' :''}>내용</option>
</select>
<input type="text" name="searchWord" value=${searchWord}>
<input type="submit"value="검색">
</form>

</div>



<table class="table table-striped table-sm table-hover">

		<colgroup>

			<col style="width:5%;" />

			<col style="width:auto;" />

			<col style="width:15%;" />

			<col style="width:10%;" />

			<col style="width:10%;" />

		</colgroup>

		<thead>

			<tr>

				<th>NO</th>

				<th>글제목</th>

				<th>작성자</th>

				<th>조회수</th>

				<th>작성일</th>

			</tr>

		</thead>

		<tbody>
            <!-- 게시글이 없는 경우 -->
            <c:if test="${empty list}">
            <tr>
               <td colspan="5" align="center">데이터가 없습니다.</td>
            </tr>
            </c:if>
            
            <!-- 게시글이 있는 경우 -->
           
            <c:if test="${not empty list}">
            
               <c:forEach items="${list}" var="board" varStatus="stat">
          
                <tr>
                  <td>${stat.count+(currentPage-1)*countPerPage}</td>
                  <td><a href="boardDetail?boardseq=${board.boardseq}">${board.title}</a></td>
                  <td>${board.userid}</td>
                  <td>${board.viewcount}</td>
                  <td>${board.regdate}</td>
                </tr>
                </c:forEach>
              
            </c:if>
             

		</tbody>

	</table>
</div>
		<div >
            <c:if test="${loginId!=null}">
			<button type="button" class="btn btn-sm btn-primary" id="btnWriteForm">글쓰기</button>
            </c:if>
		</div>



</div>


<!-- 페이징 시작 -->
<div class="text-center">
		<ul class="pagination">
		<c:forEach var="page" begin="1" end="${totalPages}">
		<li><a href="boardList?currentPage=${page}&searchItem=${searchItem}&searchWord=${searchWord}">${page}</a></li>
		</c:forEach>
			
		</ul>
	</div>


</article>
</body>

</html>

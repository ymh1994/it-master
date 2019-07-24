<%@page import="javax.servlet.jsp.tagext.TagLibraryInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="ko">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Stylish Portfolio - Start Bootstrap Template</title>

  <!-- Bootstrap Core CSS -->
  <link href="resources/vendor2/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="resources/vendor2/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
  <link href="resources/vendor2/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

  <!-- Custom CSS -->
  <link href="resources/css/stylish-portfolio.min.css" rel="stylesheet">

<style>
@font-face{
font-family:"Seven Swordsmen BB";
src:url("resources/font/SEVESBRG.TTF") format("truetype");
font-style:normal;
}

@font-face{
font-family:"Dragon Harbour";
src:url("resources/font/Dragon Harbour.ttf") format("truetype");
font-style:normal;
}


@font-face{
font-family:"가비아 솔미체";
src:url("resources/font/gabia_solmee.ttf") format("truetype");
font-style:normal;
}
</style>

</head>


<body>


  <!-- Header -->
  <header class="masthead d-flex" style="width:100%;height:2000px;">
  
   
    <div class="container"  style="margin-top:-120px;" >
    
    
    
      <a class="navbar-brand js-scroll-trigger" href="${pagecontext.request.contextpath}/ymh" style="font-family:가비아 솔미체;font-size:30px; width:50px;height:35px">Back</a>
    
      <h2 style="padding-left:450px;font-family:가비아 솔미체;font-size:3em; font-weight: bold;">${sessionScope.userName} 님의 TO DO LIST</h2>
      <br>
      <h3 style="font-family:가비아 솔미체;">${serverTime} 오늘 할 일</h3>
      
      
      
      <div class="Wrapper">
      
         <form action="insert" method="post">
                  <h4 style="font-family:가비아 솔미체;font-size:3em;"> 할일</h4><textarea rows="1" cols="80" name="todoData" style="background-color:rgb(66, 230, 245); border-color:rgb(255,204,255); border-style:dashed;font-family:가비아 솔미체;"></textarea><h4 style="font-family:가비아 솔미체;font-size:3em;">중요도</h4>
              
       <div class="Wrapper" style="display:inline-flex;font-family:가비아 솔미체; ">          
                <input type = "radio" name ="importance" value="good" style="width:2em;height:2em;" >
 <h4>상</h4><input type = "radio" name ="importance" value="average" style="width:2em;height:2em;"checked>
             <h4>중</h4><input type = "radio" name ="importance" value="poor"style="width:2em;height:2em;"><h4>하</h4>
              </div>
                  <br> <textarea rows="1" cols="80" placeholder="comments" name="comments"style="background-color:rgb(66, 230, 245); border-color:rgb(255,204,255); border-style:dashed;font-family:가비아 솔미체;"></textarea><br>
                    <h3 style="font-family:가비아 솔미체;font-size:3em;">기간</h3><input type="date" name="deadLine" placeholder="날짜" style="font-family:가비아 솔미체;"> 
       <input type="submit" value="등록" style="font-family:가비아 솔미체;">
       
       </form>
       
       </div> 
          <hr style="border:solid 3px skyblue;">
    
<div class = "pull-right">
<form action="allday" method="get"> 
<select name="searchItem" style="height:25px;font-family:가비아 솔미체;">
   <option value="todoData" ${searchItem=='todoData'?'selected' :''} style="font-family:가비아 솔미체;">할일</option>
   <option value="comments" ${searchItem=='comments'?'selected' :''} style="font-family:가비아 솔미체;">코멘트</option>
</select>
<input type="text" name="searchWord" value="${searchWord}" style="font-family:가비아 솔미체;" >

<input type="submit"value="검색"style="font-family:가비아 솔미체;">
</form>

</div>

    
    <table class="table table-striped table-sm table-hover">

		<colgroup>

			<col style="width:auto;" />

			<col style="width:25%;" />

			<col style="width:20%;" />

			<col style="width:15%;" />

			<col style="width:10%;" />

		</colgroup>
      
          <tr>

				<th style="font-family:가비아 솔미체;">${serverTime}</th>

				<th style="font-family:가비아 솔미체;">중요도</th>

				<th style="font-family:가비아 솔미체;">기간</th>

				<th style="font-family:가비아 솔미체;">결과</th>

		        <th></th>
			</tr>
			
			
			  <!-- 게시글이 없는 경우 -->
            <c:if test="${empty list}">
            <tr>
               <td colspan="5" align="center">데이터가 없습니다.</td>
            </tr>
            </c:if>
			 
			
   
             <c:if test="${not empty list}">
             <c:forEach items="${tList}" var="t" varStatus="stat">
           <tr>

				<th style="font-family:가비아 솔미체;">${stat.count + navi.startRecord}.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${t.todoData}</th>

				<th style="font-family:가비아 솔미체;">${t.importance}</th>

				<th style="font-family:가비아 솔미체;">${t.deadLine}</th>

                <th style="font-family:가비아 솔미체;"><input type="radio" name="result" value="complete">완료
       <input type="radio" name="result" value="failure">실패
       <input type="radio" name="result" value="pend">보류</th>
			<th>
			<a href="delete?todoSeq=${t.todoSeq}"><button type="button" style="font-family:가비아 솔미체; background-color:skyblue">삭제</button></a>
			
			<a href="update?todoSeq=${t.todoSeq}"><button type="button" style="font-family:가비아 솔미체;background-color:skyblue">수정</button></a>
			</th>
			
			
			</tr>
			<th>${t.comments}</th>
    </c:forEach>      
         </c:if>
          
            </table> 
        
          
          
            
                <!-- 페이징 시작 -->
       <div class="text-center"style="padding-left:500px;">
		<ul class="pagination">
	    <li><a href="boardList?currentPage=${navi.currentPage-navi.pagePerGroup}&searchItem=${searchItem}&searchWord=${searchWord}">◀</a></li>          <!-- 앞그룹요청 -->
	    <li><a href="boardList?currentPage=${navi.currentPage-1}&searchItem=${searchItem}&searchWord=${searchWord}">◁</a></li>                    <!-- 앞 페이지요청 -->
		<c:forEach var="page" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
		<li><a href="boardList?currentPage=${page}&searchItem=${searchItem}&searchWord=${searchWord}">${page}</a></li>
		</c:forEach>
		<li><a href="boardList?currentPage=${navi.currentPage+1}&searchItem=${searchItem}&searchWord=${searchWord}">▷</a></li>
	    <li><a href="boardList?currentPage=${navi.currentPage+navi.pagePerGroup}&searchItem=${searchItem}&searchWord=${searchWord}">▶</a></li>
		</ul>
	   </div>
	   <div class="btn_full"></div>
     </div>
      
    
      
      
       <a href="allday"><button type="button" style=" background-color:yellow;font-size:20px;float:left;font-family:가비아 솔미체;font-size:1em;">전체날짜 조회</button></a>&nbsp;&nbsp;
       <a href="searchday"><button type="button" style=" background-color:yellow;font-size:20px;float:left;font-family:가비아 솔미체;font-size:1em;">기간 조회</button></a>&nbsp;&nbsp;
       <a href="backup"><button type="button" style=" background-color:yellow;font-size:20px;float:left;font-family:가비아 솔미체;font-size:1em;">백업</button></a>&nbsp;&nbsp;
        
      
    
     
    <div class="overlay"></div>
  </header>

</body>

</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 자세히 보기</title>
<style>
.wrapper{
width:900px;
margin:0 auto;                /*block level 요소를 가운데로 하는 것*/
}

h2{
text-align:center;
}
table{
 width:880px;
}
table,tr,td,th{
   border :1px solid gray;
}
th{
width:400px;
padding:5px;
}

input{
 display : inLine-block;
 width : 400px;

}

pre{
width: 800px;
height:100px;
}

</style>

<script>
function updateReply(seq,content){
	
	var formdata="";

	formdata+="<form action='updateReply' method='post'>";
	formdata+="<input type='hidden' name='boardseq' value='${board.boardseq}'/>";
	formdata+="<input type='hidden' name='replyseq' value='"+seq+"'/>";
	formdata+="<input type='text' name='content' value='"+content+"'/>";
	formdata+="<button>수정</button>";
	formdata+="</form>";
	
	document.getElementById(seq).innerHTML=formdata;
}

</script>

</head>
<body>

<div class="wrapper">


<h2>게시판 글 자세히 보기</h2>

<table>

<tr>
   <th>아이디</th>
   <td>
   ${board.userid}
   </td>

</tr>
<tr>
   <th>날짜</th>
   
   <td>
 ${board.regdate}
    
   </td>
</tr>





<tr>
   <th>제목</th>
   <td>${board.title}</td>
</tr>


<tr>
   <th>첨부파일</th>
   <td>
   <c:if test="${board.originalfile!=null}">
   <a href = "download?boardseq=${board.boardseq}">
      ${board.originalfile}
   </a>
   </c:if>
   
   <c:if test="${board.originalfile==null}">
       첨부된 파일이 없습니다.
   </c:if>
   
   </td>
</tr>



<tr>
   <th>글내용</th>
   <td>
   <pre>${board.content}</pre>
   </td>
</tr>


<tr>
  <th colspan="2">
     <form action="insertReply" method="post">
      <input type="hidden" name="boardseq" value="${board.boardseq}">
      <input type="hidden" name="viewcount" value="${board.viewcount}">
      <input type="text" name="content">
      <button>댓글등록</button>
     </form>
   </th>
</tr>

<tr>

					<th colspan="2">
						<c:forEach items="${replyList}" var="reply">
						<div id="${reply.replyseq}"> 
							${reply.userid} : ${reply.content}&nbsp;&nbsp; ${reply.regdate} 
						</div>
						<br>
						
						<c:if test="${reply.userid==sessionScope.loginId}">
							<form action="deleteReply" method="post">
								<input type="hidden" value="${board.boardseq}" name="boardseq">
								<input type="hidden" value="${reply.replyseq}" name="replyseq">
								<button type="submit">x</button>
							</form>
							<button onclick='updateReply("${reply.replyseq}","${reply.content}")'>수정</button>
						</c:if>
						
						</c:forEach>
					</th>
				</tr>
				<tr>
					<th colspan="2" >
					
						<a href="${pageContext.request.contextPath}/boardList "  >목록으로</a>
						<c:if test="${sessionScope.loginId == board.userid}">
						<a href="boardDelete?boardseq=${board.boardseq}">글 삭제</a>
						<a href="boardUpdate?boardseq=${board.boardseq}">글 수정</a>
						</c:if>
					</th>
				</tr>


</table>


</div>



</body>
</html>
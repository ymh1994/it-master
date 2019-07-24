<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정</title>
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


</style>
</head>
<body>

<div class="wrapper">


<h2>게시판 글수정</h2>
<form action="boardUpdate" method="post" enctype="multipart/form-data">
<input type="hidden" name="boardseq" value="${board.boardseq}">
<table>

<tr>
   <th>아이디</th>
   <td>
    <input type="text" value="${sessionScope.loginId}" disabled="disabled">
   </td>

</tr>
<tr>
   <th>날짜</th>
   
   <td>
    <input type="text" value="${board.regdate}" disabled="disabled"/>
    
   </td>
</tr>
<tr>
   <th>제목</th>
   <td><input type="text" name="title" value="${board.title}" placeholder="제목입력"></td>
</tr>
<tr>
   <th>글내용</th>
   <td><textarea name="content"rows="15" cols="100">${board.content}</textarea></td>
</tr>

<tr>
   <th>원본파일</th>
   <td>
   <a href="download?boardseq=${board.boardseq}">
   ${board.originalfile}
   </a>
   </td>
</tr>


<tr>
   <th>수정파일 업로드</th>
   <td><input type="file" name="upload"></td>
</tr>



<tr>
   <th colspan="2">
   <button type="submit">글수정</button>
   <a href="return"><button type="button">취소</button></a>
   </th>
</tr>


</table>
</form>
</div>



</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title>회원 정보들 </title>
</head>
<body>
   <h2>회원 정보</h2>
   <table class="table table-striped table-bordered table-hover">
        <tr>
            <th>No</th>
            <th>이름</th>
            <th>나이</th>
            <th>성별</th>
        </tr>
        <c:forEach var="person" items="${perL}">
        <tr>
            <td>${person.id}</td>
            <td>${person.name}</td>
            <td>${person.age}</td>
            <td>${person.sex}</td>
        </tr>
        </c:forEach>
    </table>
    
</body>
</html>

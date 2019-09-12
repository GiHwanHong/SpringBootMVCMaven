<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title>MODIFY</title>
</head>
<body>
	<h1>삭제결과</h1>
	<table class="table table-striped table-bordered table-hover">
        <tr align="center"> 
            <td>status</td>
            <td>error</td>
            <td>exception</td>
            <td>message</td>
            <td>stacktrace</td>
            <td>list</td>
            <td>map</td>
            <td>data</td>
            <td>success</td>
        </tr>
        <tr>
            <td>${mailR.status}</td>
            <td>${mailR.error}</td>
            <td>${mailR.exception}</td>
            <td>${mailR.message}</td>
            <td>${mailR.stacktrace}</td>
            <td>${mailR.list}</td>
            <td>${mailR.map}</td>
            <td>${mailR.data}</td>
            <td>${mailR.success}</td>
        </tr>
	</table>
	<a href="../mailList">돌아가기</a>
</body>
</html>
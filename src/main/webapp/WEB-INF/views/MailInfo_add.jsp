<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ADD</title>
</head>
<body>
	<h1>회원정보 추가하기</h1>
	<div>
		<form action="./insert"  method="post">
			<table border="0" cellpadding="10">
	                <tr> 
	                  <td>name : </td>
	                  <td><input type="text" name="name"></td>
	                </tr>
	          	    <tr>
	          	      <td>userid : </td>
	          	      <td><input type="text" name="userid"></td>
	          	    </tr>
	          	    <tr>
	          	      <td>phonenumber : </td>
	          	      <td><input type="text" name="phonenumber"></td>
	          	    </tr>
	          	    <tr>
	          	      <td>address</td>
	          	      <td><input type="text" name="address"></td>
	          	    </tr>
	          	    <tr>
	          	      <td colspan="2"><button type="submit">저장</button> </td>
	          	    </tr>
			</table>
		</form>
	</div>
</body>
</html>
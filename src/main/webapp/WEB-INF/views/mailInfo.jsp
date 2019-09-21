<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://unpkg.com/slickgrid@2.3.3/slick.grid.css" />
    	<!-- <link rel="stylesheet" href="https://unpkg.com/slickgrid-kunovsky@2.2.0/css/smoothness/jquery-ui-1.8.24.custom.css" /> -->
    	<link rel="stylesheet" href="https://unpkg.com/slickgrid-kunovsky@2.2.0/examples/examples.css" />
    	<script src="//code.jquery.com/jquery.js"></script>
        <script type="text/javascript" src="/resources/js/script.js"></script> 
		<title>회원 정보들 </title>
	</head>
	<body>
		<h2 align="center">회원 정보</h2>
		
		<button id="btnView" type="button">조회</button>
		
		
		<script src="http://code.jquery.com/jquery-1.7.min.js"></script>
		<script src="http://mleibman.github.com/SlickGrid/lib/jquery.event.drag-2.2.js"></script>
		<script src="http://mleibman.github.com/SlickGrid/slick.core.js"></script>
		<script src="http://mleibman.github.com/SlickGrid/slick.grid.js"></script>	
		<jsp:include page="/WEB-INF/views/mailInfo_add.jsp"/>
		<table width="100%">
			  <tr>
			    <td valign="top" width="50%">
			      <div id="myGrid" style="width:450px;height:500px;"></div>
			    </td>
			  </tr>
		</table>
	</body>
	</body>
</html>
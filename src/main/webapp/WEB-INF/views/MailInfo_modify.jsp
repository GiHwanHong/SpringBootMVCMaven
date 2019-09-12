<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MODIFY</title>
<script>
$(function() {
  $('#add-save-btn').on('click', function() {
      var userId = $('form input[name=userid]').val();
      var userName = $('form input[name=username]').val();
      var phoneNumber = $('form input[name=phonenumber]').val();
      var address = $('form input[name=address]').val();
      addMailInfo(userId, userName, phoneNumber,address);
  });
});

function addMailInfo(userId, userName, phoneNumber ,address) {
    $.post(userId, 
            {
                "userid" : title,
                "username" : author,
                "phonenumber" : comment,
                "address" : address
            }, 
            function(jsonResult){
                alert(jsonResult);
            }, 'json')
            .done(function(jsonResult) {
                console.log(jsonResult);
            })
            .fail(function(jsonResult) {
                console.log(jsonResult);
            });
            
}
</script>
</head>
<body>
	<h1 align="center">회원정보 수정하기</h1>
	<form method="post" action="./update/${mailInfo[0].userid}">
		<label>userid : </label> <input type="text" name="userid" value="${mailInfo[0].userid}"><br>
		<label>username : </label> <input type="text" name="username" value="${mailInfo[0].username}"><br>
		<label>phonenumber : </label> <input type="text" name="phonenumber" value="${mailInfo[0].phonenumber}"><br>
		<label>address : </label> <input type="text" name="address" value="${mailInfo[0].address}"><br>
		<input id="save-btn" type="submit" value="저장">
	</form>
	<a href="../mailList">돌아가기</a>
</body>
</html>




function ajaxTest(){
	$.ajax({
	    url : "./mailList",
		type:"post",
		dataType:"text",
		success : function(data){
		     console.log(data);
		 }
	 });
}

$(document).ready(function(){
	$("#btnAdd").on("click", function() {
		console.log($('input[name="userid"]').val());
		console.log($('input[name="name"]').val());
		console.log($('input[name="address"]').val());
		console.log($('input[name="phonenumber"]').val());
		
		var userId=$('input[name="userid"]').val();
		var userName=$('input[name="name"]').val();
		var addRess=$('input[name="address"]').val();
		var phoneNumber=$('input[name="phonenumber"]').val();
		
		$.ajax({
			url : "./insert",
			type:"post",
			contentType : "application/json",
			dataType:"json",
			data : { userid:userId, username: userName, address: addRess, phonenumber: phoneNumber},
			success : function(data){
			     console.log(data);
			 },
			 error : function(request,status,error){
				 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			 }
		});
	});
	
	$("#btnView").on("click", function() {
		$.ajax({
			url : "../ajaxMessage/mailListData",
			type:"post",
			dataType:"json",
			success : function(data){
			     console.log(data);
			     console.log(data.length);
			     console.log(data[0]);
			     console.log(data[0]["idx"]);
			     console.log(data[0]["address"]);
			     SlickGridLoad(data);
			 },
			 error : function(equest,status,error){
				 
			 }
		});
		
	});
	
});

function SlickGridLoad(data){
	var grid;
	var columns = [
	   {id: "idx", name: "Idx", field: "idx"},
	   {id: "userid", name: "Userid", field: "userid"},
	   {id: "username", name: "Username", field: "username"},
	   {id: "address", name: "Address", field: "address" },
	   {id: "phonenumber", name: "Phonenumber", field: "phonenumber"}
	];
	
	var options = {
	   enableCellNavigation: true,
	   enableColumnReorder: false
	};
	
	var receiveData = data;
	var showData = [];
	 
	for (var i = 0; i < receiveData.length; i++) {
		showData[i] = {
	  		  idx: data[i]["idx"],
	   		  userid : data[i]["userid"],
	   		  username : data[i]["username"],
	   		  address : data[i]["address"],
	   		  phonenumber : data[i]["phonenumber"]
		   };
	}  
	grid = new Slick.Grid("#myGrid", showData, columns, options);
}
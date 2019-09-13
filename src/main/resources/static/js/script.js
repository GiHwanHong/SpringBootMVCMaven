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
			     SlickGridTest(data);
			 },
			 error : function(equest,status,error){
				 
			 }
		});
		
	});
});

function SlickGridTest(data){
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
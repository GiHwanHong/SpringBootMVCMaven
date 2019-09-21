$(document).ready(function(){
	jQuery.fn.serializeObject = function() {
		var obj = null; 
		try {
			if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
				var arr = this.serializeArray(); 
				console.log("SerializeArrayData : ",arr);
				if(arr){ 
					obj = {}; 
					jQuery.each(arr, function() { 
						obj[this.name] = this.value; 
					}); 
				} 
			} 
		}catch(e) {
			alert(e.message); 
		}finally {} 
		return obj;
	}
	
	$("#btnAdd").on("click", function() {
		var formData = $("#insertform").serializeObject();
		console.log("formData : ", formData);
		
		$.ajax({
			url : "./insert",
			type:"post",
			contentType : "application/json",
			dataType:"json",
			data : JSON.stringify(formData),
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
				if(data==0||data==null){
					console.log("현재 데이터가 존재하지 않습니다.");
				}
				console.log("Success Data : ", data);
				console.log("Data Count : ", data.length);
				SlickGridLoad(data);
			 },
			 error : function(request,status,error){
				alert("Error Message [code = "+ request.status + " message = " + request.responseText + " error = " + error+"]");
				console.log("Error Message [code = "+ request.status + " message = " + request.responseText + " error = " + error+"]");
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

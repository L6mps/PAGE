$(document).ready(function(){
	$('#price').on('change', function(){
		$('#textInput').attr('value', $('#price').val());
	});
});

$('#jqsubmit').click(function(){
	var maakond = $('#D1').val();
	var vald = $('#D2').val();
	alert("Valisid : " + maakond + " valla " + vald);
});
$.ajaxSetup({
	cache:true
});

function submitForm(){
	var request = new Object();
	request.search = $("search").val();
	request.price = $("price").val();
	request.attrib1 = $("demented").val();
	request.attrib2 = $("wheelchair").val();
	request.attrib3 = $("nursing").val();
	request.attrib4 = $("paidservices").val();
	request.loc1 = $("D1").val();
	request.loc2 = $("D2").val();
	$.ajax({
		url: "search",
		type: 'POST',
		data : JSON.stringify(request),
        success:function(){
        	alert("POST Requset success!");
        	document.getElementById("frame").src = "/search";
        	$("frame").fadeIn();
        } ,
        error:function(data, er, errorCode){
    	 alert("POST Request failed! Errorcode : "+errorCode+"; Data:"+data);
        }
	});
	
}
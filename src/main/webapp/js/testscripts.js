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
	var var1 = $("search");
	var var2 = $("price");
	var var3 = $("demented");
	var var4 = $("wheelchair");
	var var5 = $("nursing");
	var var6 = $("paidservices");
	var var7 = $("D1");
	var var8 = $("D2");
	var1 = var1.serialize();
	var2 = var2.serialize();
	var3 = var3.serialize();
	var4 = var4.serialize();
	var5 = var5.serialize();
	var6 = var6.serialize();
	var7 = var7.serialize();
	var8 = var8.serialize();
	$.ajax({
		url: "/search",
		type: 'POST',
		data : { "search": var1, "price": var2, "demented": var3, "wheelchair": var4, 
			"nursing": var5, "paidservices": var6, "D1": var7, "D2": var8} ,
        success:function(){
        	alert("POST Requset success!");
        	document.getElementById("frame").src = "/search";
        	$("frame").fadeIn(2000);
        } ,
        error:function(data, er, errorCode){
    	 alert("POST Request failed! Errorcode : "+errorCode+"; Data:"+data);
        }
	});
	
}
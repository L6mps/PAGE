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
	var var1 = document.getElementById('search').value;
	var var2 = document.getElementById('price').value;
	if(document.getElementById('demented').checked){
		var var3 = document.getElementById('demented').value;
	}
	if(document.getElementById('wheelchair').checked){
	var var4 = document.getElementById('wheelchair').value;
	}
	if(document.getElementById('nursing').checked){
		var var5 = document.getElementById('nursing').value;
	}
	if(document.getElementById('paidservices').checked){
		var var6 = document.getElementById('paidservices').value;
	}
	var var7 = document.getElementById('D1').value;
	var var8 = document.getElementById('D2').value;
	$.ajax({
		url: "/search",
		type: 'GET',
		data : { "search": var1, "price": var2, "demented": var3, "wheelchair": var4, 
			"nursing": var5, "paidservices": var6, "D1": var7, "D2": var8} ,
        success:function(response){
        	$('#frame').contents().find('html').html(response);
        	$("frame").fadeIn(2000);
        }
	});
	
}
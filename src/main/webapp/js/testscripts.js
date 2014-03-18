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
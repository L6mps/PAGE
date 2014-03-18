$(document).ready(function(){
	$('#price').on('change', function(){
		$('#textInput').text($('#price').val());
	});
});
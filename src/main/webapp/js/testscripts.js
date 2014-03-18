$('#price').on('change', function(){
    $('#textInput').val($('#price').val());
});


$('#textInput').on('change', function(){
	$('#price').val($('#textInput').val());
});
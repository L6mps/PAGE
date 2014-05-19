$(document).ready(function(){
	$('#price').on('change', function(){
		$('#textInput').attr('value', $('#price').val());
	});
});

//Server-sent event catcher - restarts every 1 minute to refresh the session - if the session is not refreshed
// the server assumes that connection has been lost and stops sending information.
/*
$(document).ready(function(){
    setInterval(function() {
    	 var eventSource = new EventSource("counter");
         
    	    eventSource.onmessage = function(event) {
    	    	console.log(event.data);
    	        document.getElementById('counting').innerHTML = event.data;
    	     
    	    };
    }, 60000);
});
*/
function counterInit(){
	var eventSource = new EventSource("counter");
    
    eventSource.onmessage = function(event) {
    	console.log(event.data);
        document.getElementById('counting').innerHTML = event.data;
     
    };
}

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
	var var7 = document.getElementById('M1').value;
	var var8 = document.getElementById('M2').value;
	$.ajax({
		url: "/submit",
		type: 'POST',
		data : { "search": var1, "price": var2, "demented": var3, "wheelchair": var4, 
			"nursing": var5, "paidservices": var6, "D1": var7, "D2": var8} ,
        success:function(response){
        	$('#frame').contents().find('html').html(response);
        	$("frame").fadeIn(2000);
        }
	});
	el = document.getElementById("addModal");
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
}
function populateCatalogue(){
	$.ajax({
  	  url: "/catalogue",
  	  type: 'GET',
  	  success:function(data){
  		  var inHtml="";
  		  console.log("request status:" + data.status);
  		  for(var i in data.result[0]){
  			  item=data.result[0][i];
  			  inHtml += '<li>'+item.location + ' (' + item.count + ')</li>';
  		  }
  		  document.getElementById("catalogueList").innerHTML = inHtml;
  	  }
    });
}
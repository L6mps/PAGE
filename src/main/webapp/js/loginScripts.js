function loginAjax(){
	
	
}
function loginModal(){
	el = document.getElementById("loginModal");
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
	el2 = document.getElementById("fbButton");
	el2.style.display = (el2.style.display == "inline") ? "none" : "inline";
	if(el.style.visibility=="hidden"){
		var frameSrc= window.frames[0].document.body.innerHTML;
		window.location.reload(true);
		window.frames[0].document.body.innerHTML=frameSrc;
	}
}
function add(){
	el = document.getElementById("addModal");
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
}
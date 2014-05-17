window.fbAsyncInit = function() {
  FB.init({
    appId      : '619363114805077',
    status     : true, // check login status
    cookie     : true, // enable cookies to allow the server to access the session
    xfbml      : true  // parse XFBML
  });
  // Here we subscribe to the auth.authResponseChange JavaScript event. This event is fired
  // for any authentication related change, such as login, logout or session refresh. This means that
  // whenever someone who was previously logged out tries to log in again, the correct case below 
  // will be handled. 
  FB.Event.subscribe('auth.authResponseChange', function(response) {
    // Here we specify what we do with the response anytime this event occurs. 
    if (response.status === 'connected') {
      // The response object is returned with a status field that lets the app know the current
      // login status of the person. In this case, we're handling the situation where they 
      // have logged in to the app.
    	if(readCookie()){
    		checkWithServer();
    	}
    } else if (response.status === 'not_authorized') {
      // In this case, the person is logged into Facebook, but not into the app, so we call
      // FB.login() to prompt them to do so. 
      // In real-life usage, you wouldn't want to immediately prompt someone to login 
      // like this, for two reasons:
      // (1) JavaScript created popup windows are blocked by most browsers unless they 
      // result from direct interaction from people using the app (such as a mouse click)
      // (2) it is a bad experience to be continually prompted to login upon page load.
      FB.login();
    } else {
      // In this case, the person is not logged into Facebook, so we call the login() 
      // function to prompt them to do so. Note that at this stage there is no indication
      // of whether they are logged into the app. If they aren't then they'll see the Login
      // dialog right after they log in to Facebook. 
      // The same caveats as above apply to the FB.login() call here.
      FB.login();
    }
  });
  };

  // Load the SDK asynchronously
  (function(d){
   var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
   if (d.getElementById(id)) {return;}
   js = d.createElement('script'); js.id = id; js.async = true;
   js.src = "//connect.facebook.net/en_US/all.js";
   ref.parentNode.insertBefore(js, ref);
  }(document));

  // Here we run a very simple test of the Graph API after login is successful. 
  // This testAPI() function is only called in those cases. 
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
    	var name = response.name;
      console.log('Good to see you, ' + name + '.');
    });
  }
  
  
  function checkWithServer() {
	    console.log('Welcome!  Fetching your information...dipshit. ');
	    var id;
	    FB.api('/me', function(response) {
	         id=response.id;
	         console.log(id);
	      
	    	
	      $.ajax({
	    	  url: "/facebook",
	    	  type: 'GET',
	    	  data:	{"userId": id},
	    	  success:function(data){
	    		  console.log("data from server:" + data);
	    		  var cookiedough=$.cookie('session');
	    		  console.log(cookiedough);
	    		  if(cookiedough!=data){
		    		  console.log(data+" 2");
		    		  var d=new Date();
		    		  d.setTime(d.getTime()+1800000);
		    		  var expires=d.toGMTString();
		    		  document.cookie="session="+data+"; expires="+expires;
	    		  }
	    		  else{
	    			  readCookie();
	    		  }
	    	  }
	      });
	    });
	  }
  function readCookie(){
	  console.log("hello");
		  var session = $.cookie('session');
		  console.log(session);
		  if(session){
			  console.log("hello2");
				    	   		$.ajax({
				    			url:"/login",
				    			type:'GET',
				    			data: { "action": "verify", "sessionID": session},
				    		success:function(data){
				    			console.log("data received" + data.data);
				    			if(data.substring(0,4)=="true"){
				    				console.log("tere");
				    				var el1 = document.getElementById("logoutButton");
				    				el1.style.visibility = "visible";
				    				var el2 = document.getElementById("loginButton");
				    				el2.style.visibility = "hidden";
				    				var el3 = document.getElementById("adding");
				    				el3.style.visibility = "visible";
				    			}
				    			else{
				    				var el1 = document.getElementById("logoutButton");
				    				el1.style.visibility = "hidden";
				    				var el2 = document.getElementById("loginButton");
				    				el2.style.visibility = "visible";
				    				var el3 = document.getElementById("adding");
				    				el3.style.visibility = "hidden";
				    			}
				    		},
				    		error:function(response){
				    			$.removeCookie('session');
				    		}
				    		});
				    		return false;
		  }
		  return true;
  }
  function loginModal(){
		el = document.getElementById("loginModal");
		el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
		el2 = document.getElementById("fbButton");
		el2.style.display = (el2.style.display == "inline") ? "none" : "inline";
		if(el.style.visibility=="hidden"){
			readCookie();
		}
	}
	function add(){
		el = document.getElementById("addModal");
		el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
	}
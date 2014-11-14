<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <style type="text/css">
 <!--
#container { 
     width:300px;
     height:300px; 
     background-color:#191970; 
}
#block { 
     width:50px; 
     height:25px; 
     background-color:#ffd700; 
     display:inline-block; 
     position:relative;
     top:0px;
     left:150px;
}
	-->
  </style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#block").hide();
	var interFlag = -1;
	  $("#go").click(function(){
		  var y = 0;
		  $("#block").show();
		  interFlag = setInterval(function() {
			   $("#block").css("top", y);
			   y = y + 50;
			   if(y > 250){
				   $("#block").hide();
				   clearInterval(interFlag);
			   }
			}, 500);
	  });
	
	  $("#stop").click(function(){
		   if(interFlag != -1){
			   $("#block").hide();
			   clearInterval(interFlag);
		   }
	  });	
});  
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>block</title>
</head>
<body>
<h1>block</h1>
<hr>
<div id="container">
    <div id="block">안녕</div>
</div>
<button id="go">GO</button>
<button id="stop">STOP</button>
</body>
</html>